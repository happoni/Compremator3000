package hy.happoni.compremator3000.domain.LZ77;

import hy.happoni.compremator3000.domain.LZW.ByteArray;

/**
 * Luokka, joka toteuttaa LZ77-algoritmin. Saa syötteenään tavulistan (byte
 * array) ja pakkaa sen tupleiksi, jotka sisältävät tiedon ennalta tekstistä
 * löytyneistä peräkkäisistä tavuista. Pakkaa tuplet lopulta tavulistaksi (byte
 * array).
 */
public class LZ77New {

    // Apumuuttujia, joita tarvitaan koodissa.
    LZList compressedData;                                                      // Lista, johon säilötään "koodipalat", tuplet (offset, length, next)
    int dictionaryBegin;                                                        // Varsinainen liukuva etsintäikkuna on välillä dictionaryBegin - bufferEnd.
    int dictionaryLength;                                                       // Sanakirjan maksimipituus.
    int matchLength;                                                            // Osuman pituus  
    int bufferEnd;                                                              // Bufferin loppu.
    int byteCount;                                                              // Kertoo, missä kohtaa syötettä mennään.   
    byte[] searchDictionary;                                                    // Byte array, jossa pidetään sanakirjaa.
    int matchLocation;                                                          // Osuman sijainti sanakirjassa.

    /**
     * Konstruktorissa asetetaan joitain muuttujia ja erityisesti sanakirjan
     * pituus. Pituus vaikuttaa pakkauden tehokkuutteen nopeuden kustannuksella.
     * 16k on maksimi, koska käytetään tilan säästämiseksi shorteja integerien
     * sijaan tupleissa.
     */
    public LZ77New() {
        this.compressedData = new LZList();
        this.dictionaryLength = 16000;
        this.bufferEnd = 32;                                                    // Katsotaan aina 32 seuraavaa tavua (tai vähemmän, jos ollaan aivan lopussa)
        this.byteCount = 0;
    }

    /**
     * Metodilla asetetaan sanakirjan alkupiste. Alkupiste "liukuu"
     * etsintäikkunan mukana.
     *
     * @param byteCount - Sen tavun indeksi, jota ollaan seuraavaksi tutkimassa.
     * @param dictionaryLength - Sanakirjan maksimipituus. Asetettu
     * konstruktorissa.
     * @return 0, jos ei olla vielä tutkittu tavuja yli sanakirjan
     * maksimipituutta, muutoin tavun indeksi - sanakirjan maksimipituus.
     */
    public int setDictionaryBegin(int byteCount, int dictionaryLength) {
        if (byteCount - dictionaryLength >= 0) {
            return byteCount - dictionaryLength;
        }
        return 0;
    }

    /**
     * Metodilla asetetaan bufferin, eli myös etsintäikkunan loppupiste. Tämän
     * yli ei siis tutkita tavuja.
     *
     * @param byteCount - Sen tavun indeksi, jota ollaan seuraavaksi tutkimassa.
     * @param inputLength - Syötteen pituus.
     * @return Jos ei olla lähellä syötteen loppua, eli tavun indeksi + bufferin
     * pituus ovat alle syötteen pituuden, palautetaan tavun indeksi + bufferin
     * pituus. Muutoin syötteen pituus.
     */
    public int setBufferEnd(int byteCount, int inputLength) {
        if (byteCount + bufferEnd < inputLength) {
            return byteCount + bufferEnd;
        }
        return inputLength;
    }

    /**
     * Metodilla asetetaan sanakirja tavulistaksi, eli aina haluttu pala
     * syötteestä listaksi, josta osumia haetaan.
     *
     * @param input - Syöte.
     * @param byteCount - Tämänhetkisen tavun indeksi.
     * @param dictionaryBegin - Sanakirjan alkupiste.
     * @return Null, jos tutkitaan ensimmäistä tavua, muutoin sanakirjan
     * kokoinen byte array.
     */
    public byte[] setSearchArray(byte[] input, int byteCount, int dictionaryBegin) {
        if (byteCount == 0) {
            return null;
        }
        byte[] searchArray = new byte[byteCount - dictionaryBegin];
        for (int i = 0; i < searchArray.length; i++) {
            searchArray[i] = input[dictionaryBegin + i];
        }
        return searchArray;
    }

    /**
     * Metodi, jolla asetetaan bufferi, jonka tavuja haetaan sanakirjasta. Sama
     * idea kuin metodissa setSearchArray.
     *
     * @param input - Syöte.
     * @param byteCount - Tavun indeksi.
     * @param matchLength - Osuman pituus.
     * @return Byte array, jossa bufferista otettu hakukohde.
     */
    public byte[] setSearchTarget(byte[] input, int byteCount, int matchLength) {
        byte[] searchTarget = new byte[matchLength];
        for (int i = 0; i < searchTarget.length; i++) {
            searchTarget[i] = input[byteCount + i];
        }
        return searchTarget;
    }

    /**
     * Metodi kertoo jokseenkin tehokkaasti, onko taulukko toisen osataulukko.
     * Käytetään tunnistamaan hakukohteet sanakirjasta.
     *
     * @param searchDictionary - Sanakirja.
     * @param searchTarget - Hakukohde.
     * @return True, jos hakukohde on sanakirjassa, muuten false.
     */
    public boolean isSubArray(byte[] searchDictionary, byte[] searchTarget) {
        if (searchDictionary == null) {
            return false;
        }
        int i = 0, j = 0;                                                       // Kuljetaan näillä taulukoissa.
        while (i < searchDictionary.length && j < searchTarget.length) {        // Tsekataan molemmat samaan aikaan.
            if (searchDictionary[i] == searchTarget[j]) {                       // Jos tulee osuma, kasvatetaan molempia laskureita.
                i++;
                j++;
                if (j == searchTarget.length) {                                 // Jos hakukohde on käyty läpi, palauta true.
                    return true;
                }
            } else {                                                            // Jos ei, resetoidaan hakukohde ja kasvatetaan vain i:tä.
                i++;
                j = 0;
            }
        }
        return false;
    }

    /**
     * Metodi kertoo osataulukon indeksin taulukossa. Olisi voinut yhdistää
     * isSubArray-metodiin, mutta jäi nyt näin...
     *
     * @param searchDictionary - Sanakirja.
     * @param searchTarget - Hakukohde.
     * @return Hakukohteen indeksi sanakirjassa tai -1, jos sitä ei löydy
     * sieltä.
     */
    public int indexOfByteArray(byte[] searchDictionary, byte[] searchTarget) {
        int i = 0, j = 0;
        while (i < searchDictionary.length && j < searchTarget.length) {
            if (searchDictionary[i] == searchTarget[j]) {
                i++;
                j++;
                if (j == searchTarget.length) {
                    return i - j;
                }
            } else {
                i++;
                j = 0;
            }
        }
        return -1;
    }

    /**
     * Metodilla pakataan annettu syöte tupleja hyväksi käyttäen pienempään
     * tilaan.
     *
     * @param input - Syöte byte arrayna.
     * @return Pakattu tuplelista byte arrayna.
     */
    public byte[] compress(byte[] input) {
        int inputLength = input.length;

        while (byteCount < input.length) {                                      // Tutkitaan niin kauan, kunnes tullaan syötteen loppuun.
            dictionaryBegin = setDictionaryBegin(byteCount, dictionaryLength);  // Asetetaan sanakirjan alkuindeksi.
            bufferEnd = setBufferEnd(byteCount, inputLength);                   // Asetetaan bufferin loppu, korkeintaan syötteen loppu.
            searchDictionary = setSearchArray(input, byteCount, dictionaryBegin); // Asetetaan sanakirja, josta haetaan osumia byte arrayksi.
            matchLength = 1;                                                    // Haetaan ensin yhtä tavua bufferista.
            byte[] searchTarget = setSearchTarget(input, byteCount, matchLength); // Asetetaan haettava byte array.   

            if (isSubArray(searchDictionary, searchTarget)) {                   // Tavu on jo sanakirjassa, eli saimme osuman yhden tavun mittaiseen tavujonoon.
                while (matchLength <= bufferEnd) {                              // Tutkitaan, jatkuuko osuma pidemmälle, muttei ylitetä bufferin pituutta 32.
                    searchTarget = setSearchTarget(input, byteCount, matchLength);
                    matchLocation = indexOfByteArray(searchDictionary, searchTarget);
                    if ((matchLocation != -1) && (byteCount + matchLength < input.length)) {
                        matchLength++;
                    } else {
                        break;
                    }
                }

                matchLength--;
                searchTarget = setSearchTarget(input, byteCount, matchLength);
                matchLocation = indexOfByteArray(searchDictionary, searchTarget);
                byteCount += matchLength;

                int offset = (byteCount < (dictionaryLength + matchLength)) ? byteCount - matchLocation - matchLength : dictionaryLength - matchLocation;
                byte nextByte = input[byteCount];
                compressedData.add(new Tuple(offset, matchLength, nextByte));
            } else {
                byte nextByte = input[byteCount];
                compressedData.add(new Tuple(0, 0, nextByte));
            }
            byteCount++;
        }

        //System.out.println(compressedData.size());
        return compressedData.toByteArray();
    }

    /**
     * Metodi purkaa pakatun tiedoston takaisin alkuperäiseksi tiedostoksi.
     *
     * @param input - pakattu tiedosto
     * @return - purettu tavulista
     */
    public byte[] decompress(byte[] input) {
        ByteArray reconData = new ByteArray();                                  // Kootaan alkuperäinen tiedosto tänne.
        LZList tuples = inputToTupleList(input);                                // Muodostetaan pakatun tiedoston tavuista lista tupleja.
        
        for (int i = 0; i < tuples.size(); i++) {                               
            if (tuples.get(i).getByteLength() == 0) {
                reconData.add(tuples.get(i).getNextByte());
            } else {
                for (int j = 0; j < tuples.get(i).getByteLength(); j++) {
                    byte workingByte = reconData.get(reconData.size() - tuples.get(i).getOffset());
                    reconData.add(workingByte);                    
                }
                reconData.add(tuples.get(i).getNextByte());
            }
        }
        return reconData.getBytes();
    }

    /**
     * Apumetodi, jolla muodostetaan pakatun tiedoston tavuista lista tupleja.
     *
     * @param input - pakattu tiedosto
     * @return tuples - lista tupleja
     */
    public LZList inputToTupleList(byte[] input) {
        LZList tuples = new LZList();

        for (int i = 0; i < input.length; i = i + 5) {
            byte[] offset = new byte[2];
            byte[] length = new byte[2];
            offset[0] = input[i];
            offset[1] = input[i + 1];
            length[0] = input[i + 2];
            length[1] = input[i + 3];
            tuples.add(new Tuple(bytesToShort(offset), bytesToShort(length), input[i + 4]));
        }

        return tuples;
    }

    /**
     * Apumetodi byte arrayn muuntamiseen shortiksi.
     *
     * @param bytes - byte array
     * @return haluttu short
     */
    public short bytesToShort(byte[] bytes) {
        return (short) (((bytes[0] & 0xFF) << 8) | ((bytes[1] & 0xFF)));
    }

}

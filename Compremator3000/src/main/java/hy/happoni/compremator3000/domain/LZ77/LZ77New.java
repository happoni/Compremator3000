package hy.happoni.compremator3000.domain.LZ77;

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
        this.bufferEnd = 32;        // Katsotaan aina 32 seuraavaa tavua (tai vähemmän, jos ollaan aivan lopussa)
        this.byteCount = 0;
    }

    public int setDictionaryBegin(int byteCount, int dictionaryLength) {
        if (byteCount - dictionaryLength >= 0) {    // Jos sanakirja on pidempi kuin, missä kohtaa mennään, sanakirja alkaa nollasta.
            return byteCount - dictionaryLength;
        }
        return 0;
    }

    public int setBufferEnd(int byteCount, int inputLength) {
        if (byteCount + bufferEnd < inputLength) {     // Eli kohta, jossa mennään + bufferin loppu, jos se on alle syötteen pituuden, bufferi saa olla 32 pitkä.
            return byteCount + bufferEnd;
        }
        return inputLength; // Muulloin bufferin pitää loppua siihen mihin syötekin.
    }

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

    public byte[] setSearchTarget(byte[] input, int byteCount, int matchLength) {
        byte[] searchTarget = new byte[matchLength];
        for (int i = 0; i < searchTarget.length; i++) {
            searchTarget[i] = input[byteCount + i];
        }
        return searchTarget;
    }

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

    public byte[] compress(byte[] input) {
        int inputLength = input.length;

        while (byteCount < input.length) {                                      // Tutkitaan niin kauan, kunnes tullaan syötteen loppuun.
            dictionaryBegin = setDictionaryBegin(byteCount, dictionaryLength);  // Asetetaan sanakirjan alkuindeksi.
            bufferEnd = setBufferEnd(byteCount, inputLength);                   // Asetetaan bufferin loppu, korkeintaan syötteen loppu.
            searchDictionary = setSearchArray(input, byteCount, dictionaryBegin); // Asetetaan sanakirja, josta haetaan osumia byte arrayksi.
            matchLength = 1;                                                    // Haetaan ensin yhtä tavua bufferista.
            byte[] searchTarget = setSearchTarget(input, byteCount, matchLength); // Asetetaan haettava byte array.   

            if (isSubArray(searchDictionary, searchTarget)) {                   // Tavu on jo sanakirjassa, eli saimme osuman yhden tavun mittaiseen tavujonoon.
                while (matchLength <= bufferEnd) {                                     // Tutkitaan, jatkuuko osuma pidemmälle, muttei ylitetä bufferin pituutta 32.
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

        System.out.println(compressedData.size());

        return compressedData.toByteArray();
    }

}

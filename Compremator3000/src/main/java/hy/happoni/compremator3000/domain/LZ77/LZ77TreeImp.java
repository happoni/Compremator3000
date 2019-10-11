package hy.happoni.compremator3000.domain.LZ77;

import hy.happoni.compremator3000.domain.LZW.ByteArray;

/**
 *
 * @author hanihani
 */
public class LZ77TreeImp {

    LZList compressedData;      // Lista, johon tuplet säilötään.
    LZ77Word[] dictionary;      // Sanakirjatoteutus, indeksissä 0 on tavua -128 vastaava "sana" ja indeksissä 255 tavua 127.
    ByteArray buffer;           // Haluaisin sanoa "etsintäikkuna", mutta oikeastaan tämä on vain "look ahead buffer"
    int cursor;            // Kursori, joka kertoo, missä kohtaa syötettä mennään, käytetään hyväksi laskettaessa offset-arvoa tupleen
    int bufferStart;
    int bufferEnd;

    public LZ77TreeImp() {
        this.compressedData = new LZList();
        this.dictionary = new LZ77Word[256];
        this.buffer = new ByteArray();
        this.cursor = 0;
        this.bufferStart = 0;
        this.bufferEnd = 32;
    }

    public byte[] encode(byte[] input) {

        while (cursor < input.length) {

            byte firstInBuffer = input[cursor];
            int wordIndex = (int) firstInBuffer + 128;                      // indeksi sanakirjassa 0...255

            if (dictionary[wordIndex] == null) {                            // jos sanakirjassa ei ole tällä tavulla vielä tietoja...
                dictionary[wordIndex] = new LZ77Word(cursor, firstInBuffer);// laitetaan sanakirjaan tälle kohtaa sana
                compressedData.add(new Tuple(0, 0, firstInBuffer));         // laitetaan tuple-listaan tieto
                System.out.println("sanakirja null ja lisätty");
                if (cursor + 1 < input.length) {                            // kunhan ei tarkasteltu viimeistä tavua syötteessä...
                    dictionary[wordIndex].addFollower(input[cursor + 1]);   // laitetaan tavulle seuraaja
                }
                cursor++;                                                   // jatketaan tarkastelua seuraavalla kierroksella seuraavasta tavusta
            } else {                                                        // jos sanakirjassa oli jo tällä tavulla tietoja...
                boolean found = true;                                       // etsitään seuraavaa tavua niin kauan kuin niitä löytyy
                int i = 1;

                while (found == true) {
                    found = dictionary[wordIndex].isFollowed(input[cursor + i]);// true, jos tavua seurasi seuraava tavu, muuten false                                        
                    if (found == false) {                                   // etsintä päättyy
                        compressedData.add(new Tuple((cursor - dictionary[wordIndex].getIndex()), i, input[cursor + i]));
                        System.out.println("found on false ja lisätty");
                        break;
                    } else {
                        wordIndex = (int) input[cursor + i] + 128;
                        i++;
                        if (cursor + i == input.length) {
                            compressedData.add(new Tuple((cursor - dictionary[wordIndex].getIndex()), i, input[cursor + i]));
                            System.out.println("found true ja lisätty");
                        }
                    }
                }               
            }
          
        }

        System.out.println(compressedData.size()); // hmm, nollan kokoinen lista...
        return compressedData.toByteArray();
    }

}

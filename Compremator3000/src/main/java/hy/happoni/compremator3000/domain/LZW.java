package hy.happoni.compremator3000.domain;

// tuodaan kaikki importit nyt
import java.util.*;
import org.apache.commons.lang3.SerializationUtils;

/**
 * Luokka, joka huolehtii Lempel-Ziv-Welch -algoritmin toteutuksesta.
 */
public class LZW {


    
    /**
     * Metodi, joka alustaa sanakirjan LZW-algoritmin pakkausmetodia varten,
     *
     * @return dictionary - algoritmin sanakirja
     */
    public Map<String, Integer> initDictionary() {
        Map<String, Integer> dictionary = new HashMap<>();
        for (int i = 0; i < 256; i++) {
            dictionary.put("" + (char) i, i);
            //System.out.println((char) i);
        }
        
        return dictionary;
    }

    /**
     * Metodi, joka pakkaa tiedoston käyttäen LZW-algoritmia. Tietorakenteet toteutettu pian itse...
     * @param input
     * @return 
     */
    public byte[] encode(byte[] input) {
        // toteuttava itse
        ArrayList<Integer> output = new ArrayList<>();
        
        LZWDictionary dictionary = new LZWDictionary(65536);
        int emptyPrefix = -1;
        int index = emptyPrefix;
        
        for (int i = 0; i < input.length; i++) {            
            LZWElement element = new LZWElement(index, input[i]);
            
            if (dictionary.contains(element)) {
                index = dictionary.getIndexOfElement(element);
            } else {
                dictionary.addElement(element);
                output.add(index);
                index = input[i];
            }            
        }
        output.add(index);
        
        // toteutettava itse
        return SerializationUtils.serialize(output);
    }
    
    
    
    /**
     * Metodi saa merkkijonon, jonka se pakkaa algoritmin avulla koodatuksi
     * listaksi.
     *
     * @param uncompressed - merkkijono, joka pakataan
     * @return - byte array listasta, joka sisältää algoritmin "pakkauskoodin"
     */
    public byte[] compress(String uncompressed) {
        // Rakennetaan sanakirja.
        int dictSize = 256;
        Map<String, Integer> dictionary = initDictionary();

        String w = "";
        ArrayList<Integer> result = new ArrayList<>();
        for (char c : uncompressed.toCharArray()) {
            String wc = w + c;
            if (dictionary.containsKey(wc)) {
                w = wc;
            } else {
                result.add(dictionary.get(w));
                // Lisätään muuttuja wc sanakirjaan.
                dictionary.put(wc, dictSize++);
                w = "" + c;
            }
        }

        // Merkkijonoa w kuvaava koodi.
        if (!w.equals("")) {
            result.add(dictionary.get(w));
        }
        return SerializationUtils.serialize(result);
    }

    /**
     * Metodi saa koodatun listan (byte arrayna), jonka se purkaa merkkijonoksi.
     *
     * @param compressedData - byte arrayna lista kokonaislukuja, jotka kertovat
     * "pakkauskoodin"
     * @return - merkkijonona purettu teksti
     */
    public String decompress(byte[] compressedData) {
        ArrayList<Integer> compressed = SerializationUtils.deserialize(compressedData);

        // Rakennetaan sanakirja.
        int dictSize = 256;
        Map<Integer, String> dictionary = new HashMap<>();
        for (int i = 0; i < 256; i++) {
            dictionary.put(i, "" + (char) i);
        }

        String w = "" + (char) (int) compressed.remove(0);
        StringBuilder result = new StringBuilder(w);
        for (int k : compressed) {
            String entry;
            if (dictionary.containsKey(k)) {
                entry = dictionary.get(k);
            } else if (k == dictSize) {
                entry = w + w.charAt(0);
            } else {
                throw new IllegalArgumentException("Bad compressed k: " + k);
            }

            result.append(entry);

            // Lisätään merkkijono w ja entryn ensimmäinen merkki sanakirjaan.
            dictionary.put(dictSize++, w + entry.charAt(0));

            w = entry;
        }
        return result.toString();
    }
}

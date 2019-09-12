// pakkaus
package hy.happoni.compremator3000.domain;

// tuodaan kaikki importit nyt
import java.util.*;

/**
 * Luokka, joka huolehtii Lempel-Ziv-Welch -algoritmin toteutuksesta. Tällä
 * hetkellä kopioitu Rosettacodesta, jotta saadaan jonkinlainen toiminnallisuus
 * aluille.
 */
public class LZW {
   
    /** 
     * Metodi saa merkkijonon, jonka se pakkaa algoritmin avulla koodatuksi
     * listaksi.
     * @param uncompressed
     * @return 
     */
    public static List<Integer> compress(String uncompressed) {
        // Rakennetaan sanakirja.
        int dictSize = 256;
        Map<String,Integer> dictionary = new HashMap<>();
        for (int i = 0; i < 256; i++)
            dictionary.put("" + (char)i, i);
 
        String w = "";
        List<Integer> result = new ArrayList<>();
        for (char c : uncompressed.toCharArray()) {
            String wc = w + c;
            if (dictionary.containsKey(wc))
                w = wc;
            else {
                result.add(dictionary.get(w));
                // Lisätään muuttuja wc sanakirjaan.
                dictionary.put(wc, dictSize++);
                w = "" + c;
            }
        }
 
        // Merkkijonoa w kuvaava koodi.
        if (!w.equals(""))
            result.add(dictionary.get(w));
        return result;
    }
 
    /** 
     * Metodi saa koodatun listan, jonka se purkaa merkkijonoksi.
     * @param compressed
     * @return 
     */
    public static String decompress(List<Integer> compressed) {
        // Rakennetaan sanakirja.
        int dictSize = 256;
        Map<Integer,String> dictionary = new HashMap<>();
        for (int i = 0; i < 256; i++)
            dictionary.put(i, "" + (char)i);
 
        String w = "" + (char)(int)compressed.remove(0);
        StringBuilder result = new StringBuilder(w);
        for (int k : compressed) {
            String entry;
            if (dictionary.containsKey(k))
                entry = dictionary.get(k);
            else if (k == dictSize)
                entry = w + w.charAt(0);
            else
                throw new IllegalArgumentException("Bad compressed k: " + k);
 
            result.append(entry);
 
            // Lisätään merkkijono w ja entryn ensimmäinen merkki sanakirjaan.
            dictionary.put(dictSize++, w + entry.charAt(0));
 
            w = entry;
        }
        return result.toString();
    }
}

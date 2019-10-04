package hy.happoni.compremator3000.domain;

/**
 * Luokka, joka toimii LZW-algoritmin sanakirjana.
 *
 */
public class LZWDictionary {

    private String[] dictionary;
    int size;

    // Alustetaan sanakirjan 256 ensimmäistä elementtiä. Sanakirjaan mahtuu 2^16 sanaa.
    public LZWDictionary() {
        this.dictionary = new String[65536];

        for (int i = 0; i < 256; i++) {
            this.dictionary[i] = "" + (char) i;
        }
        size = 256;
    }

    public int size() {
        return size;
    }
    
    // onko sana sanakirjassa?
    public boolean contains(String word) {
        for (int i = 0; i < size; i++) {
            if (dictionary[i] == null) {
                return false;
            }
            
            if (dictionary[i].equals(word)) {
                return true;
            }
        }
        return false;
    }

    public void add(String word) {
        dictionary[size + 1] = word;
        size++;
    }

    public int getIndex(String word) {
        for (int i = 0; i < size; i++) {
            if (dictionary[i] == null) {
                return -1;
            }
            if (dictionary[i].equals(word)) {
                return i;
            }
        }
        return -1;
    }

//        if (word.getPrefix() == -1) { // prefix on viite tyhjään, eli sana on vain tavun arvo, eli se varmasti on alustetussa sanakirjassa
//            return word.getValue();
//        } else {
//            int index = getWord(word.getPrefix()).getFirst();
//            while (index != -1) {
//                if (getWord(index).getValue() == word.getValue()) {
//                    return index;
//                }
//                index = getWord(index).getNext();                
//            }
//            return index;
//        }        
//    }
    // lisää sana sanakirjaan
//    public boolean add(LZWWord word) {
//
//        return true;
//    }
//    public LZWWord getWord(int index) {
//        return dictionary[index];
//    }
    // anna sanan indeksi, eli paikka sanakirjassa
//    public int getIndex(LZWWord word) {
//        return 0;
//    }
}
//    public boolean contains(LZWPrefix prefix, byte nextByte) {
//        // tutkitaan onko index+byte sanakirjassa. jos ei löydy, se laitetaan sanakirjaan, vanha indeksi outputtiin
//        // ja indexiksi laitetaan äsken lisätty byte (jotta etsintä jatkuu oikeasta kohdasta)
//        // jos löytyy, laitetaan indeksiksi index+byten indeksi ja jatketaan etsintää sillä ((indeksi index+bytesta) + nextbyte)
//        
//        LZWWord prefixByte = new LZWWord(prefix, nextByte);
//        for (int i = 0; i < dictionary.length; i++) {
//            if (dictionary[i] == prefixByte) {
//                return true;
//            }
//        }
//        
//        return false;
//    }
//    
//    public void add(LZWWord word) {
//        dictionary[size + 1] = word;
//        size++;
//    }
//    
//    public int getPrefixIndex(LZWPrefix prefix) {
//        for (int i = 0; i < dictionary.length; i++) {
//            
//        }
//    }
//    
//}


package hy.happoni.compremator3000.domain;

/**
 * Luokka, joka toimii LZW-algoritmin sanakirjana.
 * 
 */
public class LZWDictionary {
    
    private LZWElement[] array;
    int arrayIndex;
    
    // Alustetaan sanakirjan 256 ensimmäistä elementtiä.
    public LZWDictionary(int bitsize) {
        this.array = new LZWElement[bitsize];                
    
        byte b = Byte.MIN_VALUE;
        for (short i = 0; i < 256; i++) {
            this.array[i] = new LZWElement(-1, (byte) (128 + b));
        }
        arrayIndex = 256;
    }
    
    public boolean contains(LZWElement element) {
        // täältä pitää tutkia, onko index+b sanakirjassa ja palauttaa sen mukaan y/n
        if (element.getPrefixIndex() == -1) {
            return true;
        }
        
        return false;
    }
    
    public int getIndexOfElement(LZWElement element) {
        // täällä pitää etsiä elementin index sanakirjassa ja palauttaa se. voisi yhdistää edelliseen metodiin.
        if (element.getPrefixIndex() == -1) {
            return element.getValue();
        } else {
            int index = element.getFirst();
        }
        
        return 0;
    }
    
    public void addElement(LZWElement element) {
        array[arrayIndex] = element;
        arrayIndex++;
    }
    
    
}

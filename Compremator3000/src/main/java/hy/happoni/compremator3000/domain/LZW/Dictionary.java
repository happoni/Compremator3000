package hy.happoni.compremator3000.domain.LZW;

/**
 * Luokka, joka toimii LZW-algoritmin sanakirjana. Luokassa on käytössä taulukko
 * Word-luokkaa (joka kuvaa sanaa).
 */
public class Dictionary {

    private Word[] dictionary;
    private int size;

    /**
     * Konstruktorissa alustetaan sanakirja. 256 paikkaa (tavun eri arvot).
     * Sanakirjaan käytännössä mahtuu 2^16=65536 sanaa.
     */
    public Dictionary() {
        this.dictionary = new Word[256];
        this.size = 256;

        byte b = -128;
        for (int i = 0; i < size; i++) {
            this.dictionary[i] = new Word(i, b);
            b++;
        }
    }

    /**
     * Simppeli metodi, joka palauttaa sanakirjan koon.
     *
     * @return - kokonaislukuna sanakirjan koko
     */
    public int size() {
        return size;
    }

    /**
     * Metodilla lisätään sana (alkuosa + seuraava tavu) sanakirjaan, eli
     * käytännössä "edeltävän" sanan lapseksi.
     *
     * @param bytes - alkuosa
     * @param nextByte - seuraava tavu
     * @return - tosi
     */
    public boolean add(Prefix bytes, byte nextByte) {
        Word w = searchWord(bytes);
        w.addChild(size++, nextByte);
        return true;
    }

    /**
     * Metodi, joka antaa tietyn sanan alkuosan. Hyödyntää searchWord-metodia.
     *
     * @param bytes - Alkuosa
     * @return - kokonaislukuna alkuosa (eli sen indeksi sanakirjassa)
     */
    public int getPrefix(Prefix bytes) {
        if (bytes.size() == 1) {
            int i = 128 + bytes.get(0);
            return dictionary[i].getPrefix();
        }
        Word w = searchWord(bytes);        
        return w.getPrefix();
    }

    /**
     * Metodi, jolla tarkastetaan, onko alkuosa + seuraava tavu = sana
     * sanakirjassa.
     *
     * @param bytes - Alkuosa
     * @param nextByte - Seuraava tavu syötteessä
     * @return - tosi, jos alkuosa on tyhjä tai sana löytyy. False, jos koko
     * alkuosaa ei löydy tai sanan lapsilla ei ole haluttua seuraavaa tavua.
     */
    public boolean contains(Prefix bytes, byte nextByte) {
        if (bytes.size() == 0) {
            return true;
        }
        Word w = searchWord(bytes);
        if (w == null) {
            return false;
        }
        return w.getChild(nextByte) != null;
    }

    /**
     * Metodi, jolla etsitään sanaa sanakirjasta alkuosan perusteella.
     * Käytännössä tutkii linkitettyä listaa. Ensimmäiseksi katsotaan alkuosan
     * ensimmäisen tavun perusteella saadun sanan lapsia alkuosan seuraavien
     * tavujen perusteella.
     *
     * @param bytes - alkuosa Prefix-apuluokan avulla esitettynä
     * @return - Palautetaan null, jos sanaa ei löydy, jos löytyy palautetaan se
     * sana.
     */
    private Word searchWord(Prefix bytes) {
        Word w = dictionary[bytes.get(0) + 128];
        for (int i = 1; i < bytes.size(); i++) {
            w = w.getChild(bytes.get(i));
            if (w == null) {
                return null;
            }
        }
        return w;
    }
}

package hy.happoni.compremator3000.domain.LZW;

/**
 * Luokka, joka huolehtii Lempel-Ziv-Welch -algoritmin toteutuksesta.
 */
public class LZW {

    /**
     * Metodi pakkaa byte arrayn LZW-algoritmilla kompaktimmaksi byte arrayksi.
     * Tarvitsee luokkia Dictionary, Prefix ja ByteArray.
     *
     * @param input - byte array, luettu tekstitiedostosta, joka pakataan
     * @return Apuluokka ByteArray huolehtii tavujen säilönnästä, metodilla
     * getBytes annetaan koko ByteArrayn sisältö tavulistana.
     */
    public byte[] compress(byte[] input) {
        Dictionary dictionary = new Dictionary();           // Sanakirja alustetaan aina, kun se luodaan.
        Prefix prefix = new Prefix();                       // Alkuosa, uusi alkuosa on aina tyhjä.
        ByteArray output = new ByteArray();                 // ByteArray-apuluokka huolehtii ulosannista.

        for (int i = 0; i < input.length; i++) { //         
            if (dictionary.contains(prefix, input[i])) {    // Alkuosa + seuraava tavu = sana, katsotaan, löytyykö sanakirjasta jo.
                prefix.add(input[i]);                       // Jos löytyy, alkuosa = alkuosa + tavu. Muulloin laitetaan sanakirjaan.
            } else {                                        // Sanan indeksi laitetaan outputiin.
                addBytes(output, dictionary.getPrefix(prefix));
                if (!dictionary.add(prefix, input[i])) {    // Jos sanakirja täyttyy, resetoidaan se.
                    dictionary = new Dictionary();
                }
                prefix.clear();
                prefix.add(input[i]);
            }
            if (dictionary.size() == 65536) {               // Jos sanakirja täyttyy, resetoidaan se.
                dictionary = new Dictionary();
            }
        }
        addBytes(output, dictionary.getPrefix(prefix));

        return output.getBytes();
    }

    /**
     * Metodi, joka purkaa pakatun byte arrayn niin ikään byte arrayksi.
     * Käytetään purkamiseen luokkaa PrefixDictionary.
     *
     * @param input - byte array, joka sisältää pakatun "koodin"
     * @return - ByteArray-apuluokan antama tavulista, siis purettu
     * tekstitiedosto
     */
    public byte[] decompress(byte[] input) {
        PrefixDictionary dictionary = new PrefixDictionary();                   
        ByteArray output = new ByteArray();                                     // tähän kerätään purettu tiedosto

        int index = Byte.toUnsignedInt(input[0]) << 8 | Byte.toUnsignedInt(input[1]); // muutetaan tavuista integeriksi
        int old = index;                                                        // pidetään kirjaa vanhasta indeksistä
        addBytes(output, dictionary.get(index));                                
        byte nextByte;

        for (int i = 2; i < input.length; i = i + 2) {                          // kaksi tavua kuvaavat aina yhtä indeksiä
            index = Byte.toUnsignedInt(input[i]) << 8 | Byte.toUnsignedInt(input[i + 1]);
            if (dictionary.size() > index) {
                addBytes(output, dictionary.get(index));

                nextByte = dictionary.get(index).getBytes()[0];
                Prefix bytes = dictionary.get(old).merge(nextByte);
                if (bytes != null) {
                    dictionary.add(bytes);
                } else {
                    dictionary.reset();
                }
            } else {
                nextByte = dictionary.get(old).getBytes()[0];
                Prefix bytes = dictionary.get(old).merge(nextByte);
                dictionary.add(bytes);
                addBytes(output, bytes);
            }
            old = index;
            if (dictionary.size() == 65536) {
                dictionary.reset();
            }
        }
        return output.getBytes();
    }

    /**
     * Metodi laittaa ByteArray-apuluokkaan talteen puretun sanan.
     *
     * @param array - ByteArray, johon talletetaan.
     * @param bytes - Alkuosa, joka talletetaan.
     */
    public void addBytes(ByteArray array, Prefix bytes) {
        for (int i = 0; i < bytes.size(); i++) {
            array.add(bytes.getBytes()[i]);
        }
    }

    /**
     * Metodi toimii muuten kuin addBytes, joka saa bytearrayn ja prefixin,
     * mutta tavujen sijaan alkuosa on kokonaisulukuna. Käytetään siis pakatun
     * "koodin" tallettamiseen.
     *
     * @param array
     * @param prefix
     */
    private void addBytes(ByteArray array, int prefix) {
        int end = prefix >> 8;
        array.add((byte) end);
        array.add((byte) prefix);
    }
}

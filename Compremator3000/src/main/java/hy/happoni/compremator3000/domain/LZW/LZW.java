package hy.happoni.compremator3000.domain.LZW;

// tuodaan kaikki importit nyt

/**
 * Luokka, joka huolehtii Lempel-Ziv-Welch -algoritmin toteutuksesta.
 */
public class LZW {

    public byte[] compress(byte[] input) {
        Dictionary dictionary = new Dictionary();
        Prefix prefix = new Prefix();
        ByteArray output = new ByteArray();

        for (int i = 0; i < input.length; i++) {
            if (dictionary.contains(prefix, input[i])) {
                prefix.add(input[i]);
            } else {
                addBytes(output, dictionary.getPrefix(prefix));
                if (!dictionary.add(prefix, input[i])) {
                    dictionary = new Dictionary();
                }
                prefix.clear();
                prefix.add(input[i]);
            }
            if (dictionary.size() == 65536) {
                dictionary = new Dictionary();
            }
        }
        addBytes(output, dictionary.getPrefix(prefix));

        return output.getBytes();
    }

    public byte[] decompress(byte[] input) {
        PrefixDictionary dictionary = new PrefixDictionary();
        ByteArray output = new ByteArray();

        int index = Byte.toUnsignedInt(input[0]) << 8 | Byte.toUnsignedInt(input[1]);
        int old = index;
        addBytes(output, dictionary.get(index));
        byte nextByte;

        for (int i = 2; i < input.length; i += 2) {
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

    public void addBytes(ByteArray array, Prefix bytes) {
        for (int i = 0; i < bytes.size(); i++) {
            array.add(bytes.getBytes()[i]);
        }
    }

    private void addBytes(ByteArray array, int prefix) {
        int end = prefix >> 8;
        array.add((byte) end);
        array.add((byte) prefix);
    }

}

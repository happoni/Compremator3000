# Määrittelydokumentti

Projektin tarkoitus on toteuttaa algoritmeja, joiden avulla tiedostoja voidaan pakata pienempään tilaan ja purkaa takaisin alkuperäiseen muotoon, sekä verrata näiden eri
algoritmien tehokkuutta keskenään.

## Käytettävät algoritmit

Projektissa käytetään Lempel-Zivin algoritmin eri muunnelmia. Algoritmit, jotka on tarkoitus toteuttaa, ovat seuraavat:
- Lempel-Ziv 1977 (LZ77), ns. alkuperäinen Abraham Lempelin ja Jacob Zivin vuonna 1977 kehittämä pakkausalgoritmi.
- Lempel-Ziv-Welch (LZW), Lempelin ja Zivin yhdessä Terry Welchin kanssa vuonna 1984 julkaisema, LZ77:n paranneltu implementaatio.
- Lempel-Ziv-Storer-Szymanski (LZSS), James Storerin ja Thomas Szymanskin vuonna 1982 luoma pakkausalgoritmi, joka perustuu LZ77:aan.
- Lempel-Ziv-Markov chain algorithm (LZMA), Igor Pavlovin 1990-luvun lopulta asti kehittämä LZ77:aan perustuva algoritmi.

*Projektissa käytettäviä tietorakenteja tullaan päivittämään...*

## Ratkaistavat ongelmat

Projektin on tarkoitus ratkaista miten tiedostoja voidaan pakata pienempään tilaan niin, että ne ovat palautettavissa takaisin alkuperäiseen muotoonsa. Erityisesti
on tarkoitus selvittää, minkälaisia eroja LZ-algoritmien pakkaamisen tehokkuudessa (miten pieneen tilaan tiedosto saadaan pakattua ja miten nopeaa pakkaaminen on) on.
LZ-algoritmit on valittu, koska ne ovat laajalti käytössä tiedostojen pakkaamisessa, eivätkä täysin triviaaleja algoritmeja.

## Syötteet ja niiden käyttö

Ohjelmalle on tarkoitus pystyä syöttämään minkälainen tiedosto tahansa tietokoneen muistista (jonkinlainen kokorajoitus on mahdollinen). Liikkeelle lähdetään
todennäköisesti tekstitiedostoista. Ohjelma pakkaa syötetyn tiedoston pienempään tilaan kaikilla algoritmeilla ja tarjoaa tietoa algoritmien pakkaustehokkuudesta ja 
-nopeudesta.

## Aika- ja tilavaativuustavoitteet

Ilmeisesti LZ77-algoritmilla tiedoston pakkaamisen aikavaativuus on O(n). Palataan aika- ja tilavaativuuksiin tarkemmin, mutta asetetaan alustavaksi tavoitteeksi, että 
algoritmit pystyvät pakkaamaan yhden megatavun kokoisen tiedoston yhdessä sekunnissa.

## Lähteet

[LZ77 and LZ78.](https://en.wikipedia.org/wiki/LZ77_and_LZ78) (n.d.) Wikipedia. Haettu 6.9.2019.

[Lempel-Ziv-Welch.](https://en.wikipedia.org/wiki/Lempel%E2%80%93Ziv%E2%80%93Welch) (n.d.) Wikipedia. Haettu 6.9.2019.

[Lempel-Ziv-Storer-Szymanski](https://en.wikipedia.org/wiki/Lempel%E2%80%93Ziv%E2%80%93Storer%E2%80%93Szymanski) (n.d.) Wikipedia. Haettu 6.9.2019.

[Lempel-Ziv-Markov chain algorithm](https://en.wikipedia.org/wiki/Lempel%E2%80%93Ziv%E2%80%93Markov_chain_algorithm) (n.d.) Wikipedia. Haettu 6.9.2019.

[LZW Compression technique](https://www.geeksforgeeks.org/lzw-lempel-ziv-welch-compression-technique/) Saikia A. R. Geeks for Geeks. Haettu 10.9.2019.

[LZW Compression](http://rosettacode.org/wiki/LZW_compression) (n.d.) Rosettacode. Haettu 11.9.2019.

[Understanding the Lempel-Ziv Data Compression Algorithm in Java](https://www.developer.com/java/data/article.php/3586396/Understanding-the-Lempel-Ziv-Data-Compression-Algorithm-in-Java.htm) Baldwin R. G. Developer.com. Haettu 13.9.2019.

[LZSS (LZ77) Discussion and Implementation](http://michael.dipperstein.com/lzss/). Dipperstein M. Haettu 15.9.2019.

# Testausdokumentti

Ohjelmaa on testattu sekä automaattisilla testeillä että manuaalisesti. Manuaalisilla testeillä on huolehdittu lähinnä käyttöliittymän testaamisesta. Automaattiset testit
on toteutettu JUnitilla.

## Yksikkö- ja integraatiotestaus

### Sovelluslogiikan testaus

Sovelluslogiikkaa testataan automaattisella testillä testiluokassa [AppLogicTest](https://github.com/happoni/Compremator3000/blob/master/Compremator3000/src/test/java/hy/happoni/compremator3000/ui/AppLogicTest.java).

### I/O -käsittelyn testaus

Tiedon lukemisesta ja tallentamisesta huolehtivaa luokkaa on testattu testiluokassa [FileIOTest](https://github.com/happoni/Compremator3000/blob/master/Compremator3000/src/test/java/hy/happoni/compremator3000/io/FileIOTest.java).
Testeissä on luotu JUnitin *TemporaryFolder*:n avulla tilapäisiä testitiedostoja. Poikkeustilanteita (Exception) ei ole testattu.

### Algoritmien testaus

Algoritmit toteuttavia luokkia on testattu testiluokissa [LZTest](https://github.com/happoni/Compremator3000/blob/master/Compremator3000/src/test/java/hy/happoni/compremator3000/domain/LZTest.java) ja
[LZWTest](https://github.com/happoni/Compremator3000/blob/master/Compremator3000/src/test/java/hy/happoni/compremator3000/domain/LZWTest.java).

### Testikattavuus

Testikattavuus käyttöliittymä mukaan luettuna on 84 %.

![Testikattavuus](https://github.com/happoni/Compremator3000/blob/master/Compremator3000/Dokumentaatio/testikattavuus.png)

## Järjestelmätestaus

Sovelluksen järjestelmätestaaminen on suoritettu manuaalisesti. Pakattujen ja purettujen tiedostojen yhteneväisyys alkuperäiseen tiedostoon on testattu manuaalisesti.

## Algoritmien tehokkuuden vertailu

Algoritmeja vertailtiin viidellä erikokoisella tekstitiedostolla. Tiedostot pakattiin ja purettiin erikseen molemmilla algoritmeilla. Tutkittiin sekä pakkaustehokkuutta
että pakkaamisen ja purkamisen nopeutta.

### Testisyötteet

Kaikki syötteet ovat tekstitiedostoja (.txt). Syötteiden koot:
1. 14 197 bytes
2. 161 244 bytes
3. 647 912 bytes
4. 1 253 865 bytes
5. 3 268 374 bytes

### Tulokset

Tulokset taulukkona:

Tiedostokoko | LZ77 | LZ77 % | LZ77-pakkausaika | LZ77-purkuaika | LZW | LZW % | LZW-pakkausaika | LZW-purkuaika
--- | --- | --- | --- | --- | --- | --- | --- | ---
14 197 | 12 855 | 91 % | 88 ms | 8 ms | 9696 | 68 % | 14 ms | 24 ms
161 244 | 124 500 | 77 % | 1187 ms | 23 ms | 74 930 | 46 % | 68 ms | 82 ms
647 912 | 509 945 | 79 % | 4118 ms | 59 ms | 274 494 | 42 % | 192 ms | 371 ms
1 253 865 | 1 030 635 | 82 % | 5811 ms | 20 ms | 587 608 | 47 % | 336 ms | 477 ms
3 268 374 | 2 735 265 | 84 % | 19324 ms | 60 ms | 1 468 138 | 45 % | 781 ms | 796 ms

Tulokset graafisesti esitettyinä:

![Pakkaustehokkuus](https://github.com/happoni/Compremator3000/blob/master/Compremator3000/Dokumentaatio/pakkaustehokkuus.PNG)

![Pakkaamisen ja purkamisen nopeudet](https://github.com/happoni/Compremator3000/blob/master/Compremator3000/Dokumentaatio/nopeudet.PNG)

### Johtopäätöksiä

LZ77-algoritmin aikavaativuus kasvaa samassa suhteessa syötteen kanssa. Koska algoritmia ei alkujaankaan ole kovin nopea, se johtaa äkkiä hitaaseen pakkaamiseen.
Toisaalta LZ77:n purkaminen on hyvin yksinkertaista ja nopeaa. LZW-algoritmia on optimoitu paljon enemmän ja se kestää erittäin paljon paremmin syötteen koon kasvun.
Vaikka purkaminen on hitaampaa kuin LZ77:lla, se on kuitenkin suhteellisen nopeaa.

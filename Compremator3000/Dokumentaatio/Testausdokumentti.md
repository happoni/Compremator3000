# Testausdokumentti

Ohjelmaa on testattu sekä automaattisilla testeillä että manuaalisesti. Manuaalisilla testeillä on huolehdittu lähinnä käyttöliittymän testaamisesta. Automaattiset testit
on toteutettu JUnitilla ja tällä hetkellä on suoritettu vain yksikkötestausta.

## Yksikkö- ja integraatiotestaus

### Sovelluslogiikan testaus

...

### I/O -käsittelyn testaus

Tiedon lukemisesta ja tallentamisesta huolehtivaa luokkaa on testattu testiluokassa [FileIOTest](https://github.com/happoni/Compremator3000/blob/master/Compremator3000/src/test/java/hy/happoni/compremator3000/io/FileIOTest.java).
Testeissä on luotu JUnitin *TemporaryFolder*:n avulla tilapäisiä testitiedostoja. Poikkeustilanteiden (Exception) testaus ei ole vielä täysin kattavaa.

### Algoritmien testaus

Algoritmit toteuttavia luokkia on testattu testiluokissa [LZTest](https://github.com/happoni/Compremator3000/blob/master/Compremator3000/src/test/java/hy/happoni/compremator3000/domain/LZTest.java),
[LZWTest](https://github.com/happoni/Compremator3000/blob/master/Compremator3000/src/test/java/hy/happoni/compremator3000/domain/LZWTest.java) ja 
[LZSSTest](https://github.com/happoni/Compremator3000/blob/master/Compremator3000/src/test/java/hy/happoni/compremator3000/domain/LZSSTest.java).

### Testikattavuus

Tämän hetkinen testikattavuus (käyttöliittymä mukaan luettuna) on 62 %. *Tähän myöhemmin graafinenkin esitys.*

## Algoritmien tehokkuuden vertailu

*Tähän palataan myöhemmin.*

### Testisyötteet

...

### Tulokset

...

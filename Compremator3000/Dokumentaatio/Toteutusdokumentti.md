# Toteutusdokumentti

Ohjelma koostuu käyttöliittymästä ja sovelluslogiikasta huolehtivista luokista pakkauksessa UI, tiedostojen lukemisesta ja kirjoittamisesta huolehtivasta luokasta
pakkauksessa IO ja varsinaisista pakkausalgoritmeista huolehtivista luokista pakkauksessa Domain. Ohjelman käyttöliittymä on tekstipohjainen. Tiedostojen lukeminen ja 
kirjoittaminen toteutetaan Java NIO:n avulla suoraan tietokoneen muistissa. Tiedostot luetaan ja kirjoitetaan *tavuina* (byte array).

## Algoritmien toteutus

### Lempel-Ziv 1977

Lempel-Ziv -algoritmi perustuu merkkijonossa samanlaisena toistuviin osamerkkijonoihin. LZ77 käyttää ns. liukuvaa etsintäikkunaa, joka etsii tietystä pätkästä merkkijonoa
mahdollisimman pitkän merkkijonon, joka on jo kertaalleen esiintynyt tiedostossa. Pakattavaan tiedostoon talletetaan osuman suhteellinen sijainti etsintäikkunassa, osuman
pituus ja osumaa seuraava merkki. Pakatun tiedoston purkamisessa alkuperäinen tiedosto kootaan näiden tietojen mukaan.

Ohjelmassa näitä tietoja kuvaa luokka Tuple, joka on pitää sisällään sijainnin ja pituuden short-muuttujina ja seuraavan merkin byte-muuttujana. Tuplella on vain getteri-
metodeja. Tuplet säilötään listarakenteeseen LZList. LZList on perinteinen taulukkolistarakenne, joskaan kaikkia listan toiminnallisuuksia ei ole toteutettu. LZList:llä
on lisäksi LZW-algoritmin ByteArray-luokkaa hyödyntävä metodi, jolla listan Tuplet muutetaan byte arrayksi.

### Lempel-Ziv-Welch

Lempel-Ziv-Welch algoritmi perustuu alkusanakirjaan, jossa on jokainen tavun eri arvo. Sanakirjaan lisätään eri "sanoja", jotka koostuvat alkuosasta ja viimeisestä merkistä.
Lisättävän sanan alkuosa on jo sanakirjassa (jokin muu alkuosa + viimeinen tavu = nykyinen alkuosa), joten sanakirjaan voidaan tallentaa alkuosan indeksi ja merkki.
Tämä mahdollistaa puurakenteen sanakirjalle.

## Saavutetut aikavaativuudet

LZ77-algoritmin aikavaativuus on noin O(n^2). Algoritmi käy syötteen läpi ja läpikäynnin aikana joutuu käymään osaa syötteestä läpi etsiessään osumia ja liikuttaessaan
etsintäikkunaa. LZList käy listan alkiot läpi, kun listaa kasvatetaan ja muutettaessa alkiot byte arrayksi, mutta lista kasvaa eksponentiaalisesti ja byte arrayksi
muunto tehdään vain kerran, joten niillä ei ole suurta merkitystä aikavaativuuteen. LZ77:n purkaminen on hieman nopeampaa, koska suoraan syötteen sijaan katsotaan syötteen
tupleja ja niiden mukaan rakennetaan alkuperäinen tiedosto jo saaduista paloista.

LZW-algoritmin aikavaativuus on noin O(n log n). Algoritmi käy syötteen läpi ja läpikäynnin aikana muodostaa ja etsii "sanoja" puurakenteesta.

## Algoritmien suorituskyvyn vertailu

LZW-algoritmi on selvästi LZ77-toteutusta nopeampi. LZ77 jäi aika yksinkertaiseksi toteutukseksi, sitä olisi varmasti mahdollista nopeuttaa. Toisaalta LZ77 ei saa tiedostoja
pakattua yhtä tiiviisti kuin LZW, mikä johtuu algoritmin luonteesta. LZW:ssä laitetaan talletettavaan tiedostoon vain sanan indeksejä, eli kokonaislukuja. LZ77:n Tuple
vaatii kaksi kokonaislukua ja yhden tavun siihen päälle. Yritin helpottaa tilannetta tallentamalla sijainnin ja pituuden shorteina integerin sijaan, mutta toisaalta se
rajoittaa etsintäikkunan kokoa.

[Testausdokumentista](https://github.com/happoni/Compremator3000/blob/master/Compremator3000/Dokumentaatio/Testausdokumentti.md) löytyy tarkempaa tietoa algoritmien
suorituksista.

## Puutteet ja parannusehdotukset

Selkein puute ohjelman toteutuksessa on se, että määrittelydokumentissa suunnitelluista algoritmeista kaikkia ei saatu toteutettua. Alkuperäisestä neljästä algoritmista
toteutettiin vain kaksi. Näistä lisäksi LZ77-algoritmi ei toimi aivan toivotulla tehokkuudella. Pieniä parannusehdotuksia ovat algoritmien automaattinen vertailu ja
mahdollisesti graafinen käyttöliittymä.

## Lähteet

*Kts.[määrittelydokumentti](https://github.com/happoni/Compremator3000/blob/master/Compremator3000/Dokumentaatio/M%C3%A4%C3%A4rittelydokumentti.md).*

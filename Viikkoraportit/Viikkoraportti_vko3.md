# Viikkoraportti - viikko 3

Kolmannen viikon viikkoraportti. Viikolla olen tähän mennessä käyttänyt aikaa tiralabraan noin kymmenen (10) tuntia.

### Viikolla tehtyä

Sain vihdoinkin LZSS-algoritmin alustavan toteutuksen valmiiksi. Päätin jättää LZMA-algoritmin pois toteutuksesta tässä vaiheessa. Loin alustavan tekstipohjaisen
käyttöliittymän ja kommentoin ja dokumentoin sen. Lisäksi dokumentoin ja kommentoin alustavan sovelluslogiikka-luokan. Käyttöliittymää ei ole tarkoitus testata, mutta
sovelluslogiikka on. Lisäksi loin toiminnallisuuden tekstitiedoston lukemiseen ja sen sisällön pakkaamiseen.

### Ohjelman edistyminen

LZSS-algoritmi muodostui muokkaamalla LZ77-algoritmia jonkinmoisten hankaluuksien jälkeen. Sille luotiin myös alustavat testit. Käyttöliittymä ja sovelluslogiikka ovat
edistyneet. LZMA-algoritmi jää pois toteutuksesta. Tiedoston lukemisesta (ja tulevaisuudessa kirjoittamisesta) huolehtiva luokka FileIO on luotu. Lisäksi LZ77-algoritmista
korjattiin eräs bugi.

### Opitut asiat

LZ77- ja LZSS-algoritmien toimintaperiaatteiden erot ovat kirkastuneet. Reilusti on tullut vietettyä aikaa kynän ja paperin kanssa näitä pohtiessa, mutta se on ollutkin
hyödyllistä. LZ77-algoritmissa oli pieni bugi, jota metsästäessä ymmärrys siitä, miten algoritmi käy merkkijonoja läpi, kirkastui. Hyvää kertausta tuli myös
tiedostojen lukemisesta.

### Epäselvät ja vaikeuksia tuottaneet asiat

Tiedostojen lukemisesta ja kirjoittamisesta huolehtivassa luokassa saa varmastikin käyttää Javan omia tietorakenteita ja importata tarvittavia palikoita?

### Seuraavat askeleet

Algoritmeille luodaan toteutukset omilla tietorakenteilla. Sovelluslogiikkaa kehitetään, jotta saadaan vertailtua algoritmeja. Tiedostojen lukemisen lisäksi kirjoittaminen
toimintaan.

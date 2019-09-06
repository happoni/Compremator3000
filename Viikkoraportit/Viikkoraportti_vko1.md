# Viikkoraportti - viikko 1

Ensimmäisen viikon viikkoraportti. Viikolla olen käyttänyt aikaa tiralabraan noin kuusi (6) tuntia.

### Viikolla tehtyä

Ensimmäinen haaste oli valita sopiva aihe. Mitään suoraa aihetta ei ollut mielessä, ensin ajattelin jotain salaukseen liittyvää, mutta sieltä ei irronut mitään kirkasta
ajatusta. Kaverin ja ohjaajan kanssa juteltuani päädyin tutkimaan Lempel-Ziv -algoritmin eri muunnelmien eroavaisuuksia tiedostojen pakkaamisessa. Aiheen valinnan kanssa
painimiseen meni aika monta viikon tunneista, mutta kun sen sai valittua, jäi tunne, että valinta on hyvä. Kesän aikana tuli töiden puolesta hieman ihmeteltyä isojen
tiedostojen pakkaamista WinRarilla ja 7-zipillä, joten on kiinnostava tutkia pakkaamista lisää.

Loin repositorion työlle ja kirjoitin vaaditut dokumentaatiot, määrittelydokumentin, hieman readmeta ja viikkoraportin. Loin myös Maven-projektin NetBeansissa ja tarvittavia
plugineja, checkstylea yms. Varsinaista koodausta en vielä tehnyt. Pienen ajan käytin algoritmien toimintaan tutustumiseen, mutta sitä täytyy vielä jatkaa viikonlopun aikana.

### Ohjelman edistyminen

Ohjelmaa varten on luotu repo ja Maven-projekti. Dokumentaatiota on kirjoitettu. Itse koodia ei ole vielä käytännössä riviäkään.

### Opitut asiat

Opin jo hieman Lempel-Ziv -algoritmin toiminnasta, mutta siihen täytyy tutustua tarkemmin.

### Epäselvät ja vaikeuksia tuottaneet asiat

Muutama asia mietityttää:
1. Onko valitsemani neljä eri varianttia LZ-algoritmista sopiva määrä? En tarkkaan vielä tunne algoritmin toimintaa, mutta ne lienevät melko pienellä vaivalla muunnettavissa
, kun toteuttaa yhden alkuun?
2. Algoritmin tila- ja aikavaativuuksista oli vaikea sanoa tässä vaiheessa mitään. Miten tarkasti tila- ja aikavaativuuksia täytyy kuvata? Pajassa ohjaaja ehdotti,
että hyvä tavoite voisi olla, että algoritmi pakkaa yhden megatavun kokoisen tiedoston yhdessä sekunnissa. Onko tämä realistinen tavoite?
3. En ole vielä kovin varma, miten pakkaisin jotain muita kuin tekstitiedostoja, mutta siihen varmaan löytyy ohjeita. Miten tärkeää on, että ohjelma pakkaisi muutakin kuin
tekstitiedostoja?

### Seuraavat askeleet

Ensi viikolla aloitetaan koodaaminen. Tutustun ensin LZ77-algoritmin toimintaan ja toteutan sen ytimen Javan standardikirjastojen valmista kalustoa käyttäen. Sen jälkeen
toteutan samoin muutkin algoritmit. Dokumentaatiota pidetään yllä.

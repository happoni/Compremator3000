# Viikkoraportti - viikko 2

Toisen viikon viikkoraportti. Viikolla olen tähän mennessä käyttänyt aikaa tiralabraan noin kymmenen (10) tuntia.

### Viikolla tehtyä

Ohjelmointi on aloitettu. Loin alkuun pääluokan ja muutamia perusluokkia (käyttöliittymä yms.), joiden sisältö tosin on vielä hieman vajaa. Hain Rosettacodesta mallikoodin
LZW-algoritmista lähinnä saadakseni esimerkin pakkausten ja luokkien muodostamiseen. Rakensin itse ensimmäisen version LZ77-algoritmista *R. Baldwinin* artikkelin antaman
inspiraation avulla. Muokkasin tätä versiota vielä jonkin verran, pilkoin metodeja ja viilasin muuttujia. Loin myös alustavat testit LZ77-luokalle. Samoin hieman
muokkasin LZW-algoritmia ja loin sillekin alustavat testit.

Olen käyttänyt aika paljon aikaa algoritmeihin tutustumiseen, erityisesti LZ77-algoritmia olen tutkinut perusteellisesti. Lisäksi olen selvittänyt kevyesti muiden työni
algoritmien toimintamalleja. Kävin pajassa tekemässä työtä ja hieman juttelemassa.

### Ohjelman edistyminen

Ohjelma on saanut ensimmäiset koodinsa. LZ77-algoritmi on edistynyt tilanteeseen nähden oikein hyvin, algoritmi toimii ja on suhteellisen siisti. Siihen olen luonut testejä
, mutta niissä on vielä hieman hiomista. Samoin LZW-algoritmi toimii ja on myös näppärässä tilassa, mutta se vaatii minulta vielä muokkausta. LZW:n testejä on aloiteltu,
ne ovat vielä melko köykäisiä.

Kahta muuta algoritmia en ole vielä ehtinyt aloittaa koodaamaan, tarkoitus olisi tehdä ne mahdollisimman pian.

### Opitut asiat

Seikkaperäistä oppia on tullut LZ77-algoritmin toimintaperiaatteesta. Kävin asiaa paperillakin läpi ja erityisesti algoritmin metodien pilkkominen auttoi ymmärtämään sen
toimintaa. LZW on myös tullut tutummaksi. Ohjelmoinnin aloittaminen kesän jäljiltä on palautellut mieleen mukavasti Javan asioita.

### Epäselvät ja vaikeuksia tuottaneet asiat

Ei tässä kohtaa varmaan sen kummempaa, LZ77:n kanssa hetken painittua se selkeni aika hyvin, joten uskoisin, että nuo muutkin algoritmit aukeavat.

### Seuraavat askeleet

Käyttöliittymän voisi hioa jonkinlaiseen alustavaan kuosiin, jotta sen avulla voisi pyöritellä ohjelmaa. LZSS- ja LZMA-algoritmit on tarkoitus saada aluille mahdollisimman
pian. Dokumentaatiota on huomioitu jo nyt aloitellussa koodissa, mutta sitäkin on tarkoitus laittaa eteenpäin.

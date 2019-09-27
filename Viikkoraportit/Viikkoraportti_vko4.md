# Viikkoraportti - viikko 4

Neljännen viikon viikkoraportti. Viikolla olen tähän mennessä käyttänyt aikaa tiralabraan noin yksitoista (11) tuntia.

### Viikolla tehtyä

Tällä viikolla painiskeltiin käyttöliittymän ja tiedostojen lukemisen ja kirjoittamisen kanssa. Toteutin parikin kertaa uudestaan FileIO-luokan ja sen myötä korjasin
tarvittavilta osilta algoritmeja. Käyttöliittymää muokkasin toimivampaan suuntaan. Koodin dokumentoinnista ja kommentoinnista olen huolehtinut säntillisesti ja kirjoitin
myös jonkin verran varsinaista dokumentaatiota. Myös testausta on päivitetty vastaamaan muutoksia.

### Ohjelman edistyminen

Ohjelman algoritmien ulkopuoliset osat edistyivät hyvin. Ohjelma lukee ja kirjoittaa tiedostot nyt tavuina (byte array). Algoritmit osaavat nyt käsitellä byte array:ta, mutta
niiden toiminta perustuu yhä merkkijonoesitykseen. Uskoisin, että tiedostojen käsittelyyn ei tarvitse enää tehdä muutoksia ja käyttöliittymäänkin lähinnä lisätä vertailutoiminnallisuus.

### Opitut asiat

Kokeilin useita eri vaihtoehtoja tiedostojen lukemiseen ja kirjoittamiseen, ja näppärämmäksi osoittautui Apache IOUtils:n *SerializationUtils*. Täytyy vielä toki omiin
algoritmeihin katsoa byte array:n kanssa toimiminen mahdollisesti ilman tuota ominaisuutta. Byte array:n sisäistäminen oli hieman hankalaa alkuun, mutta uskoisin ymmärtäneen
sen riittävän hyvin.

### Epäselvät ja vaikeuksia tuottaneet asiat

Hankalin asia oli ymmärtää miten merkkijonoesitys ja tavuesitys eroavat tiedostojen lukemisessa ja kirjoittamisessa sekä erityisesti algoritmieni toteutuksessa. Lopulta
eroavaisuutta ei taida käytännön tasolla olla juurikaan, joten eiköhän tuo jää pieneksi ongelmaksi.

### Seuraavat askeleet

Seuraavan viikon tärkein asia on algoritmien toteuttaminen omilla tietorakenteilla ja muokkaaminen käyttämään tavuesitystä. Sen jälkeen ohjelman testausta tulee lisätä ja
päivittää sen myötä dokumentaatiota.

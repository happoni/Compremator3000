# Viikkoraportti - viikko 5

Viidennen viikon viikkoraportti. Palautus on myöhässä melkein kaksi vuorokautta, mutta sain sentään jotain Githubiin. Meinasi mennä motivaatio kokonaan ja jäädä ohjelma puolitiehen.
Viikolla olen tähän mennessä käyttänyt aikaa tiralabraan noin kaksikymmentäneljä (24) tuntia ja vertaisarviointiin reilun tunnin.

### Viikolla tehtyä

Neljännen viikon vertaisarvioinnin hoidin alta pois ensimmäisenä. Sitten. LZW-algoritmin oma toteutus sai aivot täysin solmuun. Aloitin uudestaan noin seitsemän kertaa eri
lähestymistavoilla epäonnistuen surkeasti joka kerta. Sain lopulta tarpeeksi mallialgoritmeja tutkittuani toteutettua algoritmin alustavasti. Sörkin myös kahta muuta algoritmia
sillä seurauksella, että niille annetaan bytearray, mutta ne eivät niitä vielä saa luettua. Harkitsin, että toteutan vain tuon LZW-algoritmin, kun onnistuin tällä viikolla
laittamaan yli tuplaten aikaa edistyen minimaalisesti.

### Ohjelman edistyminen

LZW-algoritmi toimii ihan ok. Siltä puuttuu testit käytännössä kokonaan. Kaksi muuta algoritmia eivät nyt oikein toimi, jää nähtäväksi, miten paljon pystyn niitä kehittämään.

### Opitut asiat

LZW:tä on nyt jumpattu aika huolella ja mielestäni ymmärrän kyllä sen toiminnan, mutta jotenkin sen optimointi ja järkeävä rakentaminen on osoittautunut erittäin hankalaksi.
Aika paljon olen kyllä saanut oppia tavujen kanssa säätämisestä.

### Epäselvät ja vaikeuksia tuottaneet asiat

Kaikki. No joo, hanke meinasi kaatua siihen, etten saanut tavuja käyttäytymään merkkien tavoin, ja siihen, että vaikka minulla oli palikoita hakea tehokkaasti taulukosta (
tai käytännössä rakentaa taulukko järkevämmällä tavalla), en silti asiassa meinannut onnistua.

### Seuraavat askeleet

Dokumentoidaan ja testataan tuo, mikä nyt jokseenkin toimii. Katsotaan mahdollisuuksien mukaan LZ77:n ja LZSS:n toteutuksia.

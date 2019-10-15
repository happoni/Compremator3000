# Viikkoraportti - viikko 6

Kuudennen viikon viikkoraportti. Palautus on pahasti myöhässä, pisteet saattavat olla jo mennyttä. Vertaisarviointiin en ehtinyt mukaan tällä viikolla. Hankaa oli, mutta
ehkä tämä tästä vielä valmistuu. Viikolla meni aikaa tiralabraan noin 12 tuntia.

### Viikolla tehtyä

LZW-algoritmia hioin, se on ihan hyvällä mallilla. LZ77-algoritmin kanssa otin hyviä askeleita lopulta tässä alkuviikosta. LZSS-algoritmi on nyt tiputettu pois ohjelmasta.

### Ohjelman edistyminen

LZW-algoritmi sai testejä ja sen testikattavuus on jokseenkin ok. Testit toki ovat suhteellisen yksinkertaisia. LZ77-algoritmi lukee nyt kunnolla bytearrayta ja on saanut
lisää vauhtia viime viikosta kahden taulukon vertailun muuttuessa fiksummaksi. Siitä tosin puuttuu vielä tiedoston purkaminen ja testejä aika reilustikin.

### Opitut asiat

Osataulukon löytäminen taulukosta kirkastui internetin avustuksella. Myös oman listarakenteen toteuttaminen on otettu paremmin haltuun.

### Epäselvät ja vaikeuksia tuottaneet asiat

Heh. Tosiaan jotenkin näiden pakkausjuttujen kanssa on meinannut pää hajota. Varmaan liikaa kiirettä ja stressiä muualta, ja sitten pieni jumi muuttunut isoksi jumiksi.
Mietin jo kurssin keskeyttämistä, mutta sain tähän nyt pari onnistumista, niin kyllä tämän varmaan valmiiksi tappelee.

### Seuraavat askeleet

LZ77:lle purkumetodi, testit sille, kaikki dokumentaatiot javadocia lukuunottamatta kuntoon. UI:hin pari lisäystä demoa varten. Ehkä se sillä.

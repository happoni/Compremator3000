# Ennen ohjelman käyttöä

## Komentorivitoiminnot

### Testaus

Testit suoritetaan komennolla:

`mvn test`

Testikattavuusraportti testaamisen yhteydessä luodaan komennolla:

`mvn test jacoco:report`

Testikattavuusraporttia voi tarkastella selaimessa avaamalla tiedoston */target/site/jacoco.index.html*.

### Suoritettavan jar:n generointi

Suoritettava jar-tiedosto luodaan komennolla:

`mvn package`

Kansioon *target* generoidaan tällöin suoritettava jar-tiedosto *Compremator3000-1.0-SNAPSHOT.jar*.

### JavaDoc

JavaDocin voi generoida komennolla:

`mvn javadoc:javadoc`

JavaDocia voi tarkastella selaimessa avaamalla tiedoston */target/site/apidocs/index.html*. **JavaDoc löytyy myös GitHubista.**

### Checkstyle

[Checkstyle-tiedostossa](https://github.com/happoni/Compremator3000/blob/master/Compremator3000/checkstyle.xml) määritellyt tarkistukset suoritetaan komennolla:

`mvn jxr:jxr checkstyle:checkstyle`

Checkstylen tarkistusraportin saa selaimessa auki avaamalla tiedoston */target/site/checkstyle.html*.

## Suoritettava ohjelma

Ohjelman voi ladata [täältä]() tai voit generoida jar-tiedoston yllä olevien ohjeiden mukaan.

# Ohjelman käyttö

Käynnistä ohjelma komennolla:

`java -jar Compremator3000.jar`

## Pakkaaminen

- Ohjelma kysyy päävalikossa, mitä haluat tehdä. Valitse pakkaaminen kirjoittamalla `1`.
- Ohjelma kysyy algoritmia, jota käytetään pakkaamiseen. Valitse haluamasi kirjoittamalla `1` tai `2`.
- Ohjelma pyytää pakattavan tiedoston nimeä. Anna tarkka polku.
- Ohjelma pakkaa tiedoston samaan kansioon, missä alkuperäinen tiedosto on ja tulostaa pakkaamisesta informaatiota.

## Purkaminen

- Valitse päävalikossa purkaminen kirjoittamalla `2`.
- Ohjelma pyytää purettavan tiedoston nimeä. Anna tarkka polku ja muista pakatun tiedoston loppupääte.
- Ohjelma purkaa tiedoston samaan kansioon, missä alkuperäinen tiedosto on ja tulosta purkamisesta informaatiota.

## Ohjelman lopettaminen

- Kirjoita päävalikossa `x`.

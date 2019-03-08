# badabin 🧙🏻‍♂️ [![Build Status](https://travis-ci.org/rflpazini/badabin.svg?branch=master)](https://travis-ci.org/rflpazini/badabin) [![codecov](https://codecov.io/gh/rflpazini/badabin/branch/master/graph/badge.svg)](https://codecov.io/gh/rflpazini/badabin)
Want to have some answers to your midness questions? Badabin will help you with...

Just an API running as a microservice in a docker container that create great responses . It was used in a demonstration on my University, talking about "Agile development".

## Installation

To install this project on your machine, just follow two steps:

 1 - Clone it:
```
$ git clone git@github.com:rflpazini/badabin.git
```
 2 - Build `.jar` file of our application:
```
$ ./gradlew clean bootJar
```
 3 - Run `docker-compose` command:
```
$ docker-compose up --build
```

And... TchaDa!! Badabin is running at your `localhost:8080` 🤓.

## License

[MIT License](http://rflpazini.mit-license.org) :copyright: Rafael Pazini

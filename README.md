# Pokemon Manager CLI

[![Build Status](https://travis-ci.org/MollieS/PokemonManagerCLI.svg?branch=master)](https://travis-ci.org/MollieS/PokemonManagerCLI) [![Coverage Status](https://coveralls.io/repos/github/MollieS/PokemonManagerCLI/badge.svg?branch=master)](https://coveralls.io/github/MollieS/PokemonManagerCLI?branch=master)

This project allows users to search for pokemon and receive a few details about the pokemon in return.  It retrieves data from the [PokeApi](http://pokeapi.co/).

### Requirements

This is a java project built with gradle.  To run and test the application, you need both of these available to you.

### How to run

clone this repository with `git clone git@github.com:MollieS/PokemonManagerCLI.git`

navigate into the repository with `cd PokemonManagerCLI`

build the project with `gradle build`

you will need to set up the database for the application, which you can do with `gradle sql`

run the app with `gradle -q run`

### How to test

PokemonManager is test driven.  You can run the tests with `gradle test`
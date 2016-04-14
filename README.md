# sdlh

[![Build Status](https://travis-ci.org/hogehiga/sdlh.svg?branch=master)](https://travis-ci.org/hogehiga/sdlh)

## What is this?
This is a tool resolving "When I went in there?".

## Example of use
```
$ LC_ALL=C java -jar sdlh-*-jar-with-dependencies.jar 40.0000000 126.0000000 30.0000000 130.0000000 location-history.json
Sunday, February 22, 2015
Monday, February 23, 2015
```

## Detail of Input/Output
### Input
Upper left latitude, Upper left longitude, Lower right latitude, Lower right longitude, data of Google Location History.

- Data of Google Location History is the JSON file which is exported from Google Takeout.

### Output
Dates when coodinates in location history are recorded within the specified range.

![how-it-works](./how-it-works.png)

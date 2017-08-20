# get your feet wet with *mongoDB* (with Java)    

Demonstrates how to get started with mongodb in about 2 minutes.



### prerequisites

* git 1.7.10 +
* Java 1.7 +
* docker 17+

### dependencies

are being taken care of automatically (no need to download / install anything manually!) by a `build.gradle` script file as

`compile 'org.mongodb:casbah_2.10:2.6.2'`


### run the demo

* run mongoDB: `docker-compose up -d`
* check it's running: `docker ps`
* clone this repo
* `cd <repo-directory>/mongodb`
* run the gradle `run` task from a console

    `./gradlew clean run`

___

### where to go from here

* describe a document schema
* list the available collections
* try out the [mongo shell](http://docs.mongodb.org/v2.2/mongo/)

### resources

* [mongodb home](http://www.mongodb.org/)
* [getting started with the Java driver](http://docs.mongodb.org/ecosystem/tutorial/getting-started-with-java-driver/)
* [another 'getting started' tutorial](http://www.drdobbs.com/database/getting-started-with-mongodb/240151028)
* [Robomongo](http://robomongo.org/) a cross-platform MongoDB management tool
* more [Admin UI](http://docs.mongodb.org/ecosystem/tools/administration-interfaces/)s
* all the [wetfeet tutorials](http://wetfeet.mike-wendler.de/tutorials.html)


**Have Fun!**

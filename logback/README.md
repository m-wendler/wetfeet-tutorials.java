# get your feet wet with *logback*  

Demonstrates how to get started with logback in about 2 minutes.  

### prerequisites

* git 1.7.10 +
* Java 1.5 +

### dependencies

are being taken care of automatically (no need to download / install anything manually!) by a `build.gradle` script file as

`compile 'ch.qos.logback:logback-classic:1.0.13'`


### run the demo

* clone this repo
* `cd <repo-directory>/logback`
* run the gradle `run` task from a console

    `./gradlew clean run`

___

### where to go from here

* use it in your own code
* read the [manual](http://logback.qos.ch/manual/index.html)
* read about [MDC](http://www.slf4j.org/manual.html#mdc) in detail
* move the configuration into an external file  
* experiment with logging to all kinds of destinations (file, db, remote) and with different patterns

### resources

* [logback home](http://logback.qos.ch/)
* [slf4j home](http://www.slf4j.org/)
* a [DZone article](http://java.dzone.com/articles/logging-java-switching-logback)
* [javacodegeeks article](http://www.javacodegeeks.com/2012/04/using-slf4j-with-logback-tutorial.html)



    **Have Fun!**

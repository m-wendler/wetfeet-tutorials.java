# get your feet wet with Google *guice*   

Demonstrates how to get started with guice in about 2 minutes.  


### prerequisites

* git 1.7.10 +
* Java 1.7 +

### dependencies

are being taken care of automatically (no need to download / install anything manually!) by a `build.gradle` script file as

```gradle
	compile 'com.google.inject:guice:3.0'
	testCompile 'junit:junit:4.11'
```

### run the demo

* clone this repo
* `cd <repo-directory>/guice`
* run the gradle `run` task from a console

    `./gradlew clean run`


___

### where to go from here

* read the [user guide](http://code.google.com/p/google-guice/wiki/Motivation?tm=6)  
* look at the code to see how things get wired
* have a look at the [test module](src/test/java/wetfeet/guice/GuiceTestModule.java) to see how to inject a different implementation for testing purposes


### resources

* [guice home](http://code.google.com/p/google-guice/)
* Martin Fowler's [classic article on Dependency Injection](http://martinfowler.com/articles/injection.html)
* a book recommendation on [Dependency Injection](http://www.amazon.com/Dependency-Injection-Dhanji-R-Prasanna/dp/193398855X/ref=sr_1_1?s=books&ie=UTF8&qid=1390467030&sr=1-1&keywords=guice) covering Spring and Guice
* an article on [Spring vs. Guice](http://www.dzone.com/links/r/comparing_spring_vs_google_guice_by_example.html)
* all the [wetfeet tutorials](http://wetfeet.mike-wendler.de/tutorials.html)

    **Have Fun!**

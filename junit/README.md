# get your feet wet with *JUnit* (4)  

Demonstrates how to get started with Junit in about 2 minutes.  



### prerequisites

* git 1.7.10 +
* Java 1.5 +

### dependencies

are being taken care of automatically (no need to download / install anything manually!) by a `build.gradle` script file as

`testCompile 'junit:junit:4.+'`

(this dependency is only necessary to compile the TEST sources)


### run the tests

* clone this repo
* `cd <repo-directory>/junit`
* run the gradle `test` task from a console

    `gradle clean test`

___

### where to go from here

* uncomment the `@Ignore` on `OrderItemTest.testNIY ()`
* improve the _class under test_ `OrderItem` and test everything you can think of
* write a test for one of your own classes
* use it regularly ...
* find out about [hamcrest](http://code.google.com/p/hamcrest/) and what it could help you with
* read about [TDD](http://www.agiledata.org/essays/tdd.html)
* learn about [CI](http://martinfowler.com/articles/continuousIntegration.html)


### resources

* [junit home](http://junit.org/)
* [eclipse centered tutorial](http://www.vogella.com/articles/JUnit/article.html)
* [book recommendation](http://www.amazon.com/Effective-Unit-Testing-guide-Developers/dp/1935182579/ref=sr_1_5?ie=UTF8&qid=1384366167&sr=8-5&keywords=Junit)


    **Have Fun!**

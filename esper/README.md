# get your feet wet with *Esper* (with Java)   

Demonstrates how to get started with Esper and _complex event processing_ in about 2 minutes.  
Imagine having an elevator designed to carry 12 people at the max. The demo simulates a sensor counting the number of users concurrently in there and ring an alarm if there are too many.  


### prerequisites

* git 1.7.10 +
* Java 1.7 +

### dependencies

are being taken care of automatically (no need to download / install anything manually!) by a `build.gradle` script file as

`compile 'com.espertech:esper:4.10.0'`


### run the demo

* clone this repo
* `cd <repo-directory>/esper`
* run the gradle `run` task from a console

    `./gradlew clean run`

___

### where to go from here

* look at the [documentation](http://esper.codehaus.org/esper-4.10.0/doc/reference/en-US/html/index.html) to get inspired
* think about other usecases in your domain you could throw at Esper

### resources

* [Esper home](http://esper.codehaus.org/)
* [Blog on CEP](http://www.thecepblog.com/2007/05/14/what-is-complex-event-processing-part-1/)
* [another 'getting started' tutorial](http://coffeeonesugar.wordpress.com/2009/07/21/getting-started-with-esper-in-5-minutes/)

    **Have Fun!**

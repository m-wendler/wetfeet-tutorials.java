# get your feet wet with *Jetty* (with Java)   

Demonstrates how to get started with Jetty in about 2 minutes.  
The demo starts up Jetty programmatically to listen on port `8111` (you can change this) and serve you an `index.html` file once you request it.  


### prerequisites

* git 1.7.10 +
* Java 1.7 +

### dependencies

are being taken care of automatically (no need to download / install anything manually!) by a `build.gradle` script file as

`compile 'org.eclipse.jetty:jetty-server:9.1.0.RC2'`


### run the demo

* clone this repo
* (**optional**) set the system properties:
	* `portNr`: the port to listen on (e.g. `8111`)  
in the `src\main\resources\jetty.properties` file
* run the gradle `run` task from a console

    `./gradlew clean run`

* throw this URL

        http://localhost:8111/index.html

at your browser (of course using _your_ hostname and port number)

* terminate the process after use

___

### where to go from here

* let Jetty serve something else, e.g. a servlet (check the  [embedded examples](http://www.eclipse.org/jetty/documentation/current/embedded-examples.html#embedded-one-webapp))
* use jetty for testing

### resources

* [Jetty home](http://www.eclipse.org/jetty/)
* the [Jetty 9.1 documentation](http://www.eclipse.org/jetty/documentation/current/)

    **Have Fun!**

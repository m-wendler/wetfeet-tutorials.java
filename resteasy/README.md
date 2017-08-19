# get your feet wet with *RESTEasy* (with Java)  

Demonstrates how to get started accessing RESTful webservices in about 5 minutes.  
Since it's based on a standard the webservices code looks the same as for _wink_ or _jersey_. The main difference is in the setup, so have a close look at the `web.xml`!

### prerequisites

* git 1.7.10 +
* Java 1.7 +
* a JVM based web server


### dependencies

are being taken care of automatically (no need to download / install anything manually!) by a `build.gradle` script file as

```gradle
	compile 'javax.ws.rs:javax.ws.rs-api:2.0'
	compile 'org.jboss.resteasy:resteasy-jaxrs-all:3.0.4.Final'
	compile 'org.jboss.resteasy:resteasy-servlet-initializer:3.0.4.Final'
```

### run the demo

* clone this repo
* (**optional**) change the system properties
    * `hostName`: the name of the host you want to deploy the _war_ to (default `localhost`)
	* `portNr`: the port number your web server listens on (default `8080`)
	* `displayName`: the name of the web application (default `wetfeet.resteasy`)  
in the `src\main\resources\resteasy.properties` file


1. run the server

   * run the gradle `war` task from a console

    `./gradlew clean war`

   * deploy the resulting _war_ file (look in `build\libs`) to your web server and restart it

2. run the client
    * run the gradle `run` task from a console

    `gradle clean run`

___

### where to go from here

* use an alternative client to access the resources, e.g. `cURL`:

    `curl --request GET http://localhost:8080/wetfeet.resteasy/v1/names`

* implement your own REST resouce(s)
* return the URI of the newly created resource in `add`
* use an alternative representation format to return values, e.g. XML


### resources

* [RESTEasy home](http://www.jboss.org/resteasy)
* [comparison of REST client frameworks](http://sleeplessinslc.blogspot.de/2010/02/rest-client-frameworks-your-options.html)
* a [slightly longer, step-by-step tutorial](http://www.mkyong.com/webservices/jax-rs/resteasy-hello-world-example/)
* have a look at my [other Rest implementation demo with Apache Wink](https://github.com/wetfeet-tutorials/wetfeet.wink)

    **Have Fun!**

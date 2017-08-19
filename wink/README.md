# get your feet wet with Apache *Wink* (with Java)   

Demonstrates how to get started accessing RESTful webservices in about 5 minutes.

### prerequisites

* git 1.7.10 +
* Java 1.7 +

### dependencies

are being taken care of automatically (no need to download / install anything manually!) by a `build.gradle` script file as

```gradle
	compile 'javax.ws.rs:javax.ws.rs-api:2.0'
	compile 'org.apache.wink:wink-server:1.3.0'
	compile 'org.apache.wink:wink-client:1.3.0'
	compile 'org.apache.wink:wink-common:1.3.0'
	compile 'org.apache.httpcomponents:httpclient:4.2.3'```

### run the demo

* clone this repo
* (**optional**) change the system properties
    * `hostName`: the name of the host you want to deploy the _war_ to (default `localhost`)
	* `portNr`: the port number your web server listens on (default `8080`)
	* `displayName`: the name of the web application (default `wetfeet.wink`)
in the `src\main\resources\wink.properties` file


1. start the server

   * run the gradle `war` task from a console

    `./gradlew clean war`

   * deploy the resulting _war_ file (look in `build\libs`) to your web server and restart it

2. start the client
    * run the gradle `run` task from a console

    `./gradlew clean run`

___

### where to go from here

* use an alternative client to access the resources, e.g. `cURL`:

    `curl --request GET http://localhost:8080/wetfeet.wink/rest/v1/names`

* implement your own REST resouce(s)
* return the URI of the newly created resource in `add`
* use an alternative representation format to return values, e.g. XML


### resources

* Apache [Wink home](http://wink.apache.org/)
* [comparison of REST client frameworks](http://sleeplessinslc.blogspot.de/2010/02/rest-client-frameworks-your-options.html)
* a more elaborate [wink tutorial](http://www.ibm.com/developerworks/library/wa-apachewink1/)

    **Have Fun!**

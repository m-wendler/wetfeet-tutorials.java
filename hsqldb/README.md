# get your feet wet with *hsqldb* (with Java)   

Demonstrates how to get started with accessing hsqldb programmatically with JDBC in about 2 minutes.   
As we use the in-process/in-memory setup there is no need to install or setup the database.

### prerequisites

* git 1.7.10 +
* Java 1.5 +
* some familiarity with JDBC concepts

### dependencies

are being taken care of automatically (no need to download / install anything manually!) by a `build.gradle` script file as

`compile 'org.hsqldb:hsqldb:2.3.0'`


### run the demo

* clone this repo
* `cd <repo-directory>/hsqldb`
* run the gradle `run` task from a console

    `./gradlew clean run`

___

### where to go from here

* add some more columns to the 'persons' table and try out the other available data types
* add another table and _join_ them in a query
* use a _prepared statement_ for the inserts
* use a datasource instead of the connection string
* [install the db software](https://wiki.palantir.com/pgdz/db-authentication-hsqldb-setup.html) and try another setup (change the settings in the `src\main\resources\hsqldb.properties` file!)
* have a look at [the utitilies doc](http://hsqldb.org/doc/2.0/util-guide/index.html)


### resources

* [hsqldb home](http://hsqldb.org/)
* the [user guide](http://hsqldb.org/doc/2.0/guide/index.html)


    **Have Fun!**

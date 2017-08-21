# get your feet wet with *postgreSQL* (with Java)   

Demonstrates how to get started with accessing postgreSQL programmatically with JDBC in about 2 minutes.



### prerequisites

* git 1.7.10 +
* Java 1.7 +
* docker 17+
* some familiarity with JDBC concepts

### dependencies

are being taken care of automatically (no need to download / install anything manually!) by a `build.gradle` script file as

```gradle
compile 'postgresql:postgresql:9.1-901.jdbc4'
compile 'postgresql:postgresql:9.1-901.jdbc3'
```


### run the demo

* start postgresql: `docker-compose up -d`
* check it's running: `docker ps`
* clone this repo
* `cd <repo-directory>/postgres`
* run the gradle `run` task from a console

    `./gradlew clean run`

* stop postgres again: `docker-compose down`

___

### where to go from here


* add some more columns to the 'persons' table and try out the other available data types
* add another table and join them in a query
* use a _prepared statement_ for the inserts
* use a datasource instead of the connection string


### resources

* [postgreSQL home](http://www.postgresql.org/)
* the [official doc](http://www.postgresql.org/docs/9.1/interactive/index.html)
* a [lot longer tutorial](http://zetcode.com/db/postgresqljavatutorial/)
* yet [another tutorial](http://www.tutorialspoint.com/postgresql/postgresql_java.htm)
* a [JDBC Helper utility](http://commons.apache.org/proper/commons-dbutils/)  


**Have Fun!**

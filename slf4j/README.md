# get your feet wet with *slf4j*  

Demonstrates how to get started with the SLF4J api in about 2 minutes.  
For the sake of simplicity I use the simple binding which sends all enabled log messages to `System.err`. Only messages of level `INFO` and higher are printed.

### prerequisites

* git 1.7.10 +
* Java 1.5 +

### dependencies

are being taken care of automatically (no need to download / install anything manually!) by a `build.gradle` script file as

```gradle
compile 'org.slf4j:slf4j-api:1.7.5'
runtime 'org.slf4j:slf4j-simple:1.7.5'`
```

This means we need the _API_ to compile our sources and a _binding_ (the '...simple...' in this case) to run the compiled code.
Alternatively we could declare just the dependency to '...simple...' (as _compile_) because this
references the '...api...'.

### run the demo

* clone this repo
* `cd <repo-directory/slf4j`
* run the gradle `run` task from a console

    `./gradlew clean run`

___


### where to go from here

* use it in your own code
* check out the provided [articles, blogs & presentations](http://www.slf4j.org/docs.html), especially [this one for motivation](http://blog.frankel.ch/thoughts-on-java-logging-and-slf4j)
* read the [FAQ](http://www.slf4j.org/faq.html)
* learn about [bindings](http://www.slf4j.org/manual.html#swapping) so that you can use a another logging implentation instead of _simple_
* learn what [MDC](http://www.slf4j.org/manual.html#mdc) can do for you (and that you'd   need _log4j_ or _logback_ to make use of it)

### resources

* [slf4j home](http://www.slf4j.org/)
* [logback](http://logback.qos.ch/)
* [log4j](http://logging.apache.org/log4j/2.x/)
* [MDC](http://logback.qos.ch/manual/mdc.html)



    **Have Fun!**

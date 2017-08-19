# get your feet wet with *gson*    [![Build Status](https://travis-ci.org/wetfeet-tutorials/wetfeet.gson.png?branch=master)](https://travis-ci.org/wetfeet-tutorials/wetfeet.gson)  

Demonstrates how to get started with gson in about 2 minutes.  
 

### prerequisites

* git 1.7.10 +
* gradle 1.7 +
* Java 1.5 +
* (**optional**, for use with Eclipse): the [gradle plug-in](https://marketplace.eclipse.org/content/gradle-integration-eclipse), as Eclipse uses the gradle build script under the hood to resolve the dependencies
 
### dependencies

are being taken care of automatically (no need to download / install anything manually!) by a `build.gradle` script file as 
 
 * compile 'com.google.code.gson:gson:2.2.4'


### preparation

* clone this repo
* (**optional**, for use with Eclipse): 
    * import the project
        *  File / Import / Gradle / Gradle Project 
        *  select the root folder you cloned the repo into
        *  press the `Build Model` button (causes gradle to resolve the dependencies)
        *  tick the project `wetfeet.gson`
        *  select `Finish`
    * (if not done automatically) build the project  

### run it

* run the gradle `run` task from a console 

        gradle clean run 

* (**Alternatively** for Eclipse users): run the provided Eclipse launch script `GsonDemo.launch`
 
___

### where to go from here

* check out the [user guilde](https://sites.google.com/site/gson/gson-user-guide)  
* try the [array](https://sites.google.com/site/gson/gson-user-guide#TOC-Array-Examples) and [collection examples](https://sites.google.com/site/gson/gson-user-guide#TOC-Collections-Examples)
* convert your own data


### resources

* [gson home](http://code.google.com/p/google-gson/)
* a [slightly longer tutorial](http://www.mkyong.com/java/how-do-convert-java-object-to-from-json-format-gson-api/)
* something [more real-worldly](http://www.codeproject.com/Articles/604719/Java-JSON-mapping-with-GSON)
* if you don't need object mapping look at [minimal-json](https://github.com/ralfstx/minimal-json)
* for anything more advanced you'll need [Jackson](https://github.com/FasterXML/jackson)

    **Have Fun!**

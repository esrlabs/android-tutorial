#Session1: Gradle

##Goal

Get to know the basic of gradle:

1. How to create a task
1. The configuration and execution stages of a task
1. Task inputs and outputs (incremental builds)
1. Task dependencies 
1. How to work with plugins

##Task

In this session you will create a java class which only contains constants, and arhive it into a jar. The values for the constants will be read from a properties file. 

###Steps

1. Manually create a properties file containing values for
    * pi
    * sqrt(2)
1. Inside the build.gradle, create a task which will
    * read values from the properties file
    * use these values to create java constants
    * create a java class containing these constants.
1. Using gradle, generate a jar containing the newly created java class.
1. Change the gradle build process in order to make it incremental.

###Things to watch out for
- The default project structure of the java plugin

## Tips & Tricks
- How to read from a properties file and print it

    ```groovy
    def properties = new Properties()
    properties.load(new FileInputStream("input.properties"))
    properties.each { key, value -> 
        println "${key} = ${value}" 
    };
    ```
- Hints on how to add incremental build support to a task [here](http://www.gradle.org/docs/current/userguide/more_about_tasks.html#sec:up_to_date_checks) and [here](http://mrhaki.blogspot.de/2010/10/gradle-goodness-add-incremental-build.html) 
- More about tasks [here](http://www.gradle.org/docs/current/userguide/more_about_tasks.html)

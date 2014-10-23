#Session1: Gradle

##Goal

Get to know the basic of Gradle:

1. How to create a task
1. The configuration and execution stages of a task
1. Task inputs and outputs (incremental builds)
1. Task dependencies 
1. How to work with plugins

##Task

In session we will create a simple DSL to describe string constants. The input for our DSL should look like:

```
GREETING="Hallo"
GOODBYE="Tschüss"
```

Your task is to write a compiler task in Gradle, which translates the input file into the following java file:

```java
class Constants {

  public static String GREETING = "Hallo";
  public static String GOODBYE  = "Tschüss";

}
```

###Steps

1. Create a directory for our java source files according to the Gradle conventions: `src/main/java`.
1. Manually create a properties file: `input.properties` with some example string values.
1. Inside build.gradle, create a task `compileProperties` which parses the values from the properties file and generates the java constants class into the `src/main/java` folder.
1. Compile your project into a jar.
1. Make sure that the task `compileProperties` is always performed before our java classes are compiled.
1. Make the task `compileProperties` only compile if the input or output file has changed.

###Things to watch out for

- The default project structure of the java plugin.

## Tips & Tricks

- How to read from a properties file: 

    ```groovy
    def properties = new Properties()
    properties.load(new FileInputStream("input.properties"))
    properties.each { key, value -> 
        println "${key} = ${value}" 
    };
    ```
- Hints on how to add incremental build support to a task [here](http://www.gradle.org/docs/current/userguide/more_about_tasks.html#sec:up_to_date_checks) and [here](http://mrhaki.blogspot.de/2010/10/gradle-goodness-add-incremental-build.html) 
- More about tasks [here](http://www.gradle.org/docs/current/userguide/more_about_tasks.html)
- Task dependencies

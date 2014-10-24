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
1. Inside `build.gradle`, create a task `compileProperties` which parses the values from the properties file and generates the java constants class into the `src/main/java` folder.
1. Compile your project into a jar.
1. Make sure that the task `compileProperties` is always performed before our java classes are compiled.
1. Make the task `compileProperties` only compile if the input or output file has changed.

###Things to watch out for

- The default project structure of the java plugin.

## Tips & Tricks
- Defining tasks and task dependencies
    * Defining a simple task

        ```groovy
        task hello << {
          println 'Hello world!'
        }
        ```

    * Task dependencies
        
        ```groovy
        task hello << {
          println 'Hello world!'
        }
        task intro(dependsOn: hello) << {
          println "I'm Gradle"
        }
        ```
        
        or 
        
        ```groovy
        task hello << {
          println 'Hello world!'
        }
        task intro << {
          println "I'm Gradle"
        }
        intro.dependsOn hello
        ```

- How to parse the values from the properties file and generate a string

    ```groovy
    // loading the file
    def properties = new Properties()
    properties.load(new FileInputStream("input.properties"))
    
    // joining properties into a single string
    def constants = properties.collect{ key, value -> 
      "$key = $value"
    }.join("\n") 
    ```
    
- Gradle has a very powerful incremental build feature. This means Gradle will not execute a task unless until necessary. We can help Gradle and configure our task so it is ready for an incremental build. Every Gradle task has an inputs and outputs property. We can assign a file(s), directories or properties as inputs to be checked for changes. For outputs we can also assign a file, directory or custom code in a closure to determine the output of the task. Gradle uses these values to determine if a task needs to be executed.

    In the following example task `generate` will generate a file containing some text. 
      
      ```groovy
      task generate {
        outputs.file file("input.txt")
        doLast {
          println "generating"
          file("input.txt").text = "hello"
        }
      }
      task compile(dependsOn: generate) << {
        println "compiling"
      }
      ```
      
    Let's run the `compile` task for the first time. The console output will be:
  
      ```
      $ gradle compile
      :generate
      generating
      :compile
      compiling
      
      BUILD SUCCESSFUL
      ```
    Now we run it again and notice how Gradle tells us the task is UP-TO-DATE:
      
      ```
      $ gradle compile
      :generate UP-TO-DATE
      :compile
      compiling
  
      BUILD SUCCESSFUL
      ```    

    Notice that the `generate` task doesn't need to be executed as nothing has changed, hence it is marked `UP-TO-DATE` .

- More about tasks [here](http://www.gradle.org/docs/current/userguide/more_about_tasks.html)
- More details on how to add incremental build support to a task [here](http://www.gradle.org/docs/current/userguide/more_about_tasks.html#sec:up_to_date_checks)
- More details on the java plugin [here](http://www.gradle.org/docs/current/userguide/java_plugin.html)

[Go to Session 2](https://github.com/esrlabs/android-tutorial/tree/master/session2)


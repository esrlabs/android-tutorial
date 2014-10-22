#Session1: Gradle

##Goal

Get to know the basic of gradle.

##Task

Create a gradle build script which will read from a properties file values for java constants (such as the value for Pi). A java class containing these constants will be generated and archived into a jar. The build process should be at first non-incremental and then incremental.

###Steps

1. Create a properties file 
    1. Save it as config.groovy  
    2. Values should be stored as 
    
    ```groovy
    pi=3.1416
    ```

1. Create a task which will read the values from the config.groovy file and print them.
    1. To parse the file using groovy please refer to this [link](http://groovy.codehaus.org/ConfigSlurper).
1. Create a task which will use the previously read values and generate a java class containing java constants. 
    1. To write to a file use: 
    ```groovy
    def hello = "Hallo"
    def name = "Android"
    new File("test.txt").text ='''
        ${hello} ${name}
    '''
    ```

1. Use the java plugin to create the jar file

###Things to watch out for
- The default project structure of the java plugin

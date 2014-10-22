#Session1: Gradle

##Goal

Get to know the basic of gradle.

##Task

Create a gradle build script which will read from a properties file values for java constants (such as the value for Pi). A java class containing these constants will be generated and archived into a jar. The build process should be at first non-incremental and then incremental.

###Steps

1. Create a task which will read the values from the properties file and print them.
    ```groovy
    def properties = new Properties()
    properties.load(new FileInputStream("input.properties"))
    properties.each { key, value -> 
    println "${key} = ${value}" 
    };
    ```
    
1. Create a task which will use the previously read values and generate a java class containing java constants. 
    1. To write to a file use: 
    ```groovy
    def hello = "Hallo"
    def name = "Android"
    new File("test.txt").text ='''
        ${hello} ${name}
    '''
    ```

1. Use the java plugin to create the jar file.
1. Make the build incremental.

###Things to watch out for
- The default project structure of the java plugin

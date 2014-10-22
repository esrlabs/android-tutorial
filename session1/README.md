#Session1: Gradle

##Goal

Get to know the basic of gradle.

##Task

Create a build script which will read from a Groovy properties file the values for Pi, SQRT(2) and will create class containing these values as constants. At the end it will build a jar.

###Steps
1. Create a .groovy file which will contain the values for the two constants in the following format pi=3.1416.
1. Create a task which will print the read values
1. To parse the file using groovy please refer to this [link](http://groovy.codehaus.org/ConfigSlurper).
1. To write to a file use : 

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

#Session 2: Android & Robolectric

##Goal

Learn how to build and test and Android app. 

##Task

After we implemented our game engine in the previous session, it is now time to implement the Android app. The app should support the following features:

1. The app starts with the visual playback of a color sequence.
1. The player can enter the color sequence.
1. If the entered sequence does **not** match the expected sequence, the game is over.
1. If the entered sequence does match the expected sequence, a new game starts with an increased pattern length.

###Steps

1. Start with the project from the previous session. 
1. Configure the gradle plugin

        buildscript {
            repositories {
                jcenter()
            }
            dependencies {
                classpath 'com.android.tools.build:gradle:0.13.0'
                classpath 'org.robolectric:robolectric-gradle-plugin:0.13.2'
            }
        }

        apply plugin: 'com.android.application'
        apply plugin: 'robolectric'

1. Configure JUnit 4 !

        dependencies {
            compile project(":lib")
            androidTestCompile 'org.robolectric:robolectric:2.3'
            androidTestCompile 'junit:junit-dep:4.11'
        }

###Things to watch our for

- Do not block the [UI thread](https://developer.android.com/training/multiple-threads/communicate-ui.html).

###Tips & Tricks


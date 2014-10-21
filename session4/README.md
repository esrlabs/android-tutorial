#Session 2: Android & Robolectric

##Goal

Learn how to build and test and Android app. 

##Task

After we implemented our game engine in the previous session, it is now time to implement the actual Android app. The app should support the following features:

1. The app starts with the visual playback of a color sequence of length 1.
1. The player enters one color after the other.
1. If the entered sequence: 
     * matches the expected sequence, a new game starts with an incremented pattern length.
     * does **not** match the expected sequence, the game is over.

###Steps

1. Start with the project from the previous session. If you weren't finished, you can checkout a working setup via `git checkout -b origin/sesssion1`.
1. We will use [Robolectric](http://robolectric.org/) to execute our tests without an actual Android device. This has the following advantages:
    * faster test execution
    * easy to run on jenkins
    * JUnit 4 instead of JUnit 3
     
     To get started with Robolectric add robolectric's plugin and runtime dependencies to `app/build.gradle`. It is also necessary to add a compile time dependency to the `lib` module containing the game engine: 

               buildscript {
                 repositories {
                     jcenter()
                 }
                 dependencies {
                    classpath 'com.android.tools.build:gradle:0.13.0'
            >>      classpath 'org.robolectric:robolectric-gradle-plugin:0.13.2'
                 }
               }
            
               apply plugin: 'com.android.application'
            >> apply plugin: 'robolectric'
            
               ...
            
               dependencies {
            >>    compile project(":lib")
            >>    androidTestCompile 'org.robolectric:robolectric:2.3'
            >>    androidTestCompile 'junit:junit-dep:4.11'
               }

###Things to watch our for

- Do not block the [UI thread](https://developer.android.com/training/multiple-threads/communicate-ui.html).

###Tips & Tricks

- Android gradle tasks

    ```
    $./gradlew assembleDebug  # creates a debug version of your app
    $./gradlew installDebug   # installs a debug version to any connected device or emulator
    $./gradlew test 
    ```

- Delayed execution on the UI thread (or any other thread with a [Looper](http://developer.android.com/reference/android/os/Looper.html)):
 
    ```java
    Handler handler = new Handler();
    handler.postDelayed(new Runnable() {
      @Override
      public void run() {
        //Do something after 100ms
      }
    }, 100);
    ``` 

- [Android Studio designer tutorial](http://www.techotopia.com/index.php/Designing_a_User_Interface_using_the_Android_Studio_Designer_Tool)


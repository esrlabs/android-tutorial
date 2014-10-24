#Session 2: IntelliJ & Android

##Goal 

Over the next three sessions you will implement the game [Simon Says](https://www.youtube.com/watch?v=_UCnn4BI9S4) for an android phone. The goal of this session is to get to know the basics of IntelliJ and Android. You will learn how to create an Android project in IntelliJ and deploy your app for the first time. 

###Steps

1. Create new project: `Android -> Gradle: Android Module`
     * App name: SimonSays
     * Package name: com.esrlabs.simonsays
     * Minimum SDK: API 17 (Android 4.2)
     * Target SDK: API 19 (Android 4.4)
     * Compile With SDK: API 19 (Android 4.4)
     * Activity : blank activity
1. Run newly created application on your phone 

## Tips & Tricks

- use gradlew to run gradle commands
    ```
    $./gradlew assembleDebug  # build apk
    $./gradlew installDebug   # installs apk
    $./gradlew test           # run tests
    ```
    
- IntelliJ cheat sheets for  [Windows](https://www.jetbrains.com/idea/docs/IntelliJIDEA_ReferenceCard.pdf) and [Mac](https://www.jetbrains.com/idea/docs/IntelliJIDEA_ReferenceCard_Mac.pdf)

- Gradle task to launch the Android app directly onto the device
    ```groovy
    import org.apache.tools.ant.taskdefs.condition.Os
    task launchDebug(type: Exec, dependsOn: 'installDebug') {
        if (Os.isFamily(Os.FAMILY_WINDOWS)) {
            commandLine 'cmd', '/c', 'adb', 'shell', 'am', 'start', '-n', 'com.esrlabs.simonsays/.NewGameActivity'
        } else {    
            commandLine 'adb', 'shell', 'am', 'start', '-n', 'com.esrlabs.simonsays/.NewGameActivity'
        }
    }
    ```
- The activity life cycle 

      ![The activity life cycle](img/activity_lifecycle.png?raw=true "The activity life cycle")

[Go to Session 3](https://github.com/esrlabs/android-tutorial/tree/master/session3)

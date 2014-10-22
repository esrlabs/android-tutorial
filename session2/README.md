#Session 2: IntelliJ & Android

##Goal 

Learn how to setup an Android project in IntelliJ

###Steps
1. Create project: Gradle Android Module
    1. App name: SimonSays
    1. Package name: com.esrlabs.simonsays
    1. Minimum SDK: API 17 (Android 4.2)
    1. Target SDK: API 19 (Android 4.4)
    1. Compile With SDK: API 19 (Android 4.4)
    1. Activity : blank activity


## Tips & Tricks

- use the gradlew to run gradle commands
- Android gradle tasks

    ```
    $./gradlew assembleDebug  # build apk
    $./gradlew installDebug   # installs apk
    $./gradlew test 
    ```
- IntelliJ cheat sheets for  [windows](https://www.jetbrains.com/idea/docs/IntelliJIDEA_ReferenceCard.pdf) and [Mac](https://www.jetbrains.com/idea/docs/IntelliJIDEA_ReferenceCard_Mac.pdf)

- gradle task to launch the Android app directly onto the device
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
#Session 4: Android & Robolectric

###Goal

The goal of this session is to learn how to build and test an Android app. After we implemented our game engine in the previous session, it is now time to implement the actual Android app. The app should support the following features:

1. The app starts with the visual playback of a color sequence of length 1.
1. The player enters one color after the other.
1. If the entered sequence: 
     * matches the expected sequence, a new game starts with an incremented pattern length.
     * does **not** match the expected sequence, the game is over.

The challenge is to cover as much functionality with your unit tests as possible.

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


###Testing with Robolectric 

Robolectric is a framework that allows you to write unit tests and run them on a desktop JVM. It mocks part of the Android framework contained in the android.jar file. This is what a JUnit test with Robolectric looks like (note the annotations in front of the class header):

```java
@Config(manifest = "src/main/AndroidManifest.xml", resourceDir = "res", emulateSdk = 18)
@RunWith(RobolectricTestRunner.class)
public class ColorInputActivityTest {

  @Before
  public void setUp() throws Exception {
    // initialization goes here
  }

  @Test
  public void myAppShouldDoSomething() throws Exception {
    assertThat(myApp, is("awesome"));
  }

}
```

With Robolectric you write unit tests for your Android activities or Services. Here is a simple activity test with Robolectric:

```java
@Test
public void clickOnMyButtonStartsNewActitiy(){
  // create activity and run lifecycle
  MyActivity myActivity = Robolectric.buildActivity(MyActivity.class)
                                            .create()
                                            .start()
                                            .visible().get();

  // get button from our activity
  View myButton = myActivity.findViewById(R.id.myButton);
  myButton.callOnClick();

  // define the expected intent
  Intent expectedIntent = new Intent(myActivity, NextActivity.class);
  assertThat(shadowOf(myActivity).getNextStartedActivity(), is(expectedIntent));
}
```

It is easy to test if an activity handles intents correctly. 

```java
@Test
public void sendingIntentsToActivities(){
  Intent aSimpleIntent = new Intent();
  aSimpleIntent.putExtra("greeting", "Hello World"));

  MyActivity myActivity = Robolectric.buildActivity(MyActivity.class)
                                            .create().withIntent(aSimpleIntent).get();

  Intent actualIntent = myActivity.getIntent();
  assertThat(actualIntent, is(aSimpleItent));
  assertThat(actualIntent.getExtra("greeting"), is("Hello World"));
}
```

Mocking is a good approach to test the interaction between the Android UI and our game engine.

```java
@Test
public void clickOnStartButtonStartsGame(){
  // create a mock collaborator (here we use Mockito)
  Game aGame = mock(Game.class);

  // manually create the activity to inject our mock
  MyActivity myActivity = new MyActivity(game);
  ActivityController.of(myActivity).create().start().visible();

  // trigger start button 
  View startButton = myActivity.findViewById(R.id.startButton);
  startButton.callOnClick();

  // verify that the method Game#start() has been called
  verify(aGame).start();
}
```


###Things to watch our for

- Avoid blocking the [Android UI thread](https://developer.android.com/training/multiple-threads/communicate-ui.html).
- Currently it is not possible to execute Robolectric based unit tests from IntelliJ. However, IntelliJ will show your unit tests, when you execute the gradle `test` task from within IntelliJ.

      ![Executing gradle tests](img/gradle_task_view.png?raw=true "Executing Gradle tasks from within IntelliJ")

###Tips & Tricks

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


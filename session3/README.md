#Session 3: Creating a domain model

##Goal 

Learn how to build and test a plain Java project.

##Task

Over the next three sessions you will implement the game [Simon Says](https://www.youtube.com/watch?v=_UCnn4BI9S4) for an android phone. In this session you will implement the core game engine: 

- Given a number n, generate a random sequence of colors (red, blue, green) of length *n*.
- The player has to enter the same colors in the correct order. 

As we don't need Android for implementing our game engine, we will implement it as a normal java library. This has the advantage that our game engine is not tied to Android and could be reused in desktop or server applications. Furthermore, tests run a lot faster as we have less dependencies to load.

###Steps

1. Add a new **Gradle module** (not Android!) named `lib` inside the project. 
1. Create new folders `src/main/java` and `src/test/java`
1. Create a class `com.esrlabs.simonsays.Game` in `src/main/java`
1. Create a test `com.esrlabs.simonsays.GameTest` in `src/test/java` and make it fail
1. Run the test via from the command line via `$gradle -> gradle test`
1. Run the test from the IDE
1. Implement the game engine

###Things to watch our for

- Don't start too complicated. What is simplest API to solve our problem? We don't know yet what we need...
- How do we test generating a random sequence?


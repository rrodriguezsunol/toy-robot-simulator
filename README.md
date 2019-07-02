# toy-robot-simulator

The toy robot simulator is an application simulates a robot moving on a grid table. The interface to move the robot 
around is of command-line type: after starting this application you send requests to the robot via the terminal.

## Development Environment Setup

In order to be able to build, run and execute the tests you need to follow this guide. First, clone this project and 
then install the following software.

### Java 8

Install JDK 8. 

### Maven

We use Maven as the project's build tool. Install version 3.5.0 or later.

## Build and Run the Application

In order to run this application, first:

* Run `mvn clean package`.
* Run the command `java -jar target/toy-robot-simulator-1.0-SNAPSHOT.jar`.

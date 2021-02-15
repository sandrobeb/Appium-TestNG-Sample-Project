##### Setup and Installation
###### Tools needed:
* Java sdk - this project support JDK 12. Install java sdk and add $JAVA_HOME to your path
* Install Android studio and launch it
* Create an emulator
* Install [node.js](https://nodejs.org/en/download/), [appium](https://www.npmjs.com/package/appium) [appium-doctor](https://www.npmjs.com/package/appium-doctor) by running ```npm install -g appium appium-doctor```
* Install [maven](https://maven.apache.org/install.html)
* Install [Android Studio](https://developer.android.com/studio/) and create an emulator
* Run ```appium-doctor``` and fix whatever issues were found
    * java, appium, maven and adb have to be in your $PATH!
    
##### How to run
1. There are multiple ways to run the project. You can use you IDE to lauch the test adding ```testng.xml``` to the runner.
Or you can use maven, example ```mvn clean test``` to launch the entire suite, ```mvn clean test -D<classname>``` to run all tests in the class or
```mvn clean test -D<classname>#<test method name> test``` to run one particular test.
2. Screenshots appear separately in ```screenshots/<date>/<test name>``` 
3. After tests have run a report can be generated using the command ```mvn allure:serve``` 

#### Overview
The main runner is ```BaseRunner.class``` and the class contains 3 methods:

* ```initiateServices()``` which is run ```@BeforeSuite```
    * Prepares logging through ```tinylogger``` for the framework and appium
    
* ```Runner()``` which runs before every method
    * Sets the capabilities and starts the ```Android driver```
    
* ```tearDown()```
    * quits the driver after every test
  
    
 
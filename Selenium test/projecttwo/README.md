#Test automation framework using Selenium with Java, TestNG and Maven-
This is a project to test users page for MES QA Interview used page object model framework and selenium to automate.
TestNG is used as test framework.

Dependency
Java
Maven

###libraries used
Selenium
TestNG
log4j
Extent Reports

THINGS TO NOTE:
---------------
This project is build for windows as default with chrome as the browser version 79.0.3945.88 with corresponding chromedriver donwloaded. Please download a different chromedriver if you are using a different version of chrome.
To run as Mac user, please:
1. go to /projecttwo/src/main/java/com/qainterview/base/TestBase.java and change line 42 from "chromedriver.exe" to just "chromedriver".
2. go to /projecttwo/src/test/java/com/qainterview/testcases/QaInterviewTest.java and change line 24 from "chromedriver.exe" to just "chromedriver"

To run as firefox, make sure you are running the most up to date version of firefox and please do the same for TestBase.java as you did for chromedriver: Delete the .exe from the end of the file path reference in line 47.

To run TestNG, create a new configuration for your project and select "suite", with the path: "target/classes/testng.xml"

---------------
### Steps to clone execute the tests
```
https://github.com/jimmy33321/Projects/tree/master/projecttwo
mvn clean install test
```

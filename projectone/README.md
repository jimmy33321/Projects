This qa interview project is created using maven and cucumber by Jimmy Yu. It is part 1 of a 2 part qa interview assignment. It has the basic GET,POST,PUT Rest API methods in it.

Project description :-

class : projectone/src/test/java/com/qainterview/common/apispecificMethod.java
This class is serving as a base class for framework. Every test case is inheriting this class and accessing functions
to execute GET,PUT,POST method.

projectone/src/test/java/com/qainterview/api/stepDefinitions/getDetails.java and 
projectone/src/test/java/com/qainterview/api/stepDefinitions/postDetails.java, commentDetails, and userDetails are step definition files of tests in cucumber.
These classes contains the test case in form of user story.

Example :- 
Given url is this,
And the content type is text or json,
Then add new post, comment or user
Then update the very same element.

projectone/src/test/java/com/qainterview/testRunner/getdetailRunnerTest.java and
projectone/src/test/java/com/qainterview/testRunner/postdetailRunnerTest.java, commentRunnerTest,userRunnerTest are the test runner class for 
step definition class.
We can run test case individually using runner class and Junit

projectone/resources/features/getapi.feature and projectone/resources/features/*.feature are the main
feature classes from where steps are defined to test case.
projectone/resources/data/*.json are all the example json data used in this project.

Execute test case in batch : Go to project root directory where pom.xml is located and run command :   mvn test
All test cases will be executed by maven and normal html report will be generated under project/target directory.
To view the test report: 
Go to the project root directory and run command : allure serve target/surefire-reports/




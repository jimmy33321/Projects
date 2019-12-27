package com.qainterview.testRunner;

import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
      features="resources/features/getapi.feature",
      glue="com.qainterview.api.stepDefinitions",
      plugin = {"pretty", "html:target/cucumber-html-report","json:target/cucumber.json"},
      monochrome = true
)
public class getdetailRunnerTest {
}

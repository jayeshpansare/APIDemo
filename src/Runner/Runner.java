package Runner;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(
        features = {"src/features"},
        glue = {"stepDefinition"},
        tags = {"@test"},
        plugin = {"pretty",
                "html:target/cucumber-reports/cucumber",
                "json:target/cucumber-reports/cucumber.json"
        })
public class Runner extends AbstractTestNGCucumberTests {
}
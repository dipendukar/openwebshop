package org.example;

import io.cucumber.java.Scenario;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(
        features = "src/test/resources/login_demoweshop.feature",
        glue = "org.example",
        monochrome = false,
        plugin = {"pretty","html:target/cucumber-report.html"},
        dryRun = false,
        tags = "@datatable"
)

public class RunnerClassTest extends AbstractTestNGCucumberTests {
    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }

}

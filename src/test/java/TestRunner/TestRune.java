package TestRunner;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		
		features = {"C:\\Users\\Lenovo\\finemake\\Cucumber\\Feature\\creatnewcustomer.feature","C:\\Users\\Lenovo\\finemake\\Cucumber\\Feature\\login.feature" },
		glue="stepDefinition",
		dryRun = false,
		monochrome = false,
		
		plugin = {"pretty","com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"}
		)

public class TestRune {

}

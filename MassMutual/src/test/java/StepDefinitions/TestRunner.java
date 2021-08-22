package StepDefinitions;



import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;





@CucumberOptions(
		features= {"src\\test\\resources\\Features"},
		glue= {"StepDefinitions"},
		monochrome=true,
		plugin= {"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"},
		dryRun=false,
		//tags= "@Regression",
		publish=true
		)

public class TestRunner extends AbstractTestNGCucumberTests{

}

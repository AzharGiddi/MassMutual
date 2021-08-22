package com.massmutual.stepdefinitions;



import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;




@CucumberOptions(
		features= {"src\\test\\resources\\Features"},
		glue= {"com.massmutual.stepdefinitions"},
		monochrome=true,
		plugin= {"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"},
		dryRun=false,
		tags= "@Regression",
		publish=true
		)

public class TestRunner extends AbstractTestNGCucumberTests{

}

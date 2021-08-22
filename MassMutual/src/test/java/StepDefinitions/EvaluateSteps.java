package StepDefinitions;

import java.util.Properties;

import org.openqa.selenium.WebDriver;

import PageClasses.HomePage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class EvaluateSteps {

	public WebDriver driver = BaseClass.driver;

	public Properties prop = BaseClass.prop;

	@Given("Browser is launched with {string}")
	public void browser_is_launched_with(String string) {

		HomePage.launchUrl(string);
	}

	@When("^Test page with title \"(.*)\" has loaded successfully$")
	public void test_page_has_loaded_successfully(String title) {

		HomePage.VerifyPageLoaded(title);
	}

	@Then("Verify {int} values are displayed.")
	public void verify_values_are_displayed(Integer int1) {

		HomePage.verifyValueCount1(int1);
	}

	@Then("Verify currency values are greater than {int}")
	public void verify_currency_values_are_greater_than(Integer int1) {

		HomePage.verifyCurrencyGreaterThan1(int1);
	}

	@Then("Verify sum of all the values is refelecting correctly in Total Balance")
	public void verify_sum_of_all_the_values_is_refelecting_correctly_in_total_balance() {

		HomePage.verifyTotalBalance1();
	}

	@Then("Verify values are formatted as currency.")
	public void verify_values_are_formatted_as_currency() {

		HomePage.verifyCurrencyFormat1();
	}

}
package PageClasses;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import StepDefinitions.BaseClass;
import Utils.CurrencyUtil;

/***
 * 
 * @author Azhar Ali Baig
 *
 */
public class HomePage {

	private static WebDriver driver = BaseClass.driver;

	private static SoftAssert verify = BaseClass.verify;

	private static Properties prop = BaseClass.prop;

	private static String listValues_lbl = "//*[starts-with(@id,'lbl_val')]";

	private static String listValues_txt = "//*[starts-with(@id,'txt_val')]";

	private static String lblTotalBalance = "lbl_ttl_val";

	private static String txtTotalBalance = "lbl_ttl_txt";

	public static void launchUrl(String url) {

		driver.get(url);
	}
	
	/***
	 * Verifies target page has loaded or not.
	 * 
	 * @param expTitle
	 * Expected title
	 */
	public static void VerifyPageLoaded(String expTitle) {

		String actTitle = driver.getTitle();

		Assert.assertEquals(actTitle, expTitle,
				"Expected title: " + actTitle + " doesn't match the actual title: " + expTitle);
	}

	/***
	 * Verifies total number of values displayed on the screen.
	 * 
	 * @param count
	 * number to compare with
	 */
	public static void verifyValueCount(int count) {

		int actCount = driver.findElements(By.xpath(listValues_lbl)).size();

		Assert.assertTrue(count == actCount, "Expected count: " + count + " doesn't match actual count:" + actCount);
	}
	
	/***
	 * Verifies total number of values displayed on the screen. Test method
	 * 
	 * @param count
	 * number to compare with
	 */
	public static void verifyValueCount1(int count) {

		int actCount = getList().size();

		Assert.assertTrue(count == actCount, "Expected count: " + count + " doesn't match actual count:" + actCount);
	}

	/***
	 * Use below method to compare two numbers
	 *  
	 * @param number
	 * Number to compare with
	 * @return void
	 */
	public static void verifyCurrencyGreaterThan(int number) {

		List<WebElement> currencyList = driver.findElements(By.xpath(listValues_txt));

		int i = 0;

		String localeCode = prop.getProperty("currency.code");

		for (WebElement ele : currencyList) {

			i++;

			String currencyString = ele.getText();

			double currencyDouble = CurrencyUtil.convertCurrencyToDouble(currencyString, localeCode);

			verify.assertTrue(currencyDouble > (double) number,
					"Currency value in Value " + i + " is not greater than " + String.valueOf(number));
		}

	}

	/***
	 * 
	 * @param number 
	 * Number to compare with
	 * 
	 */
	public static void verifyCurrencyGreaterThan1(int number) {

		Map<String, String> currencyList = getMap();

		int i = 0;

		String localeCode = prop.getProperty("currency.code");

		for (String key : currencyList.keySet()) {

			String currencyString = currencyList.get(key);
			
			Assert.assertNotNull(CurrencyUtil.getCurrencyFormat(currencyString, localeCode),
					"Currency value of " + key + ": " + currencyString + ", is not formatted as " + localeCode + " currency");

			double currencyDouble = CurrencyUtil.convertCurrencyToDouble(currencyString, localeCode);

			System.out.println("Currency value in Value " + key + " is " + String.valueOf(currencyDouble));

			verify.assertTrue(currencyDouble > (double) number,
					"Currency value in Value " + key + " is not greater than " + String.valueOf(number));

		}
		verify.assertAll();
	}

	
	/***
	 * Sums all the values and compAre the resultant sum with total balance
	 * 
	 * @return void
	 */
	public static void verifyTotalBalance() {

		List<String> listValues = getList(listValues_lbl);

		List<String> listCurrency = getList(listValues_txt);

		Assert.assertTrue(listValues.size() == listCurrency.size(),
				"Value and Currency lists size is not same, lists size should be same to proceed.");

		Map<String, String> currencyList = getMapFromLists(listValues, listCurrency);

		double sum = 0.00;

		String localeCode = prop.getProperty("currency.code");

		double total = CurrencyUtil
				.convertCurrencyToDouble(driver.findElement(By.xpath(txtTotalBalance)).getText().trim(), localeCode);

		for (String s : currencyList.keySet()) {

			double currencyDouble = CurrencyUtil.convertCurrencyToDouble(currencyList.get(s), localeCode);

			sum += currencyDouble;
		}

		verify.assertTrue(sum == total, "Expected total  " + total + " is not equal to actual total " + sum);

		verify.assertAll();
	}

	/***
	 * Use below method to verify total balance. Test Method
	 * 
	 */
	public static void verifyTotalBalance1() {

		Map<String, String> currencyList = getMap();

		double sum = 0.00;

		String localeCode = prop.getProperty("currency.code");

		double total = CurrencyUtil.convertCurrencyToDouble("$1,000,000.00", localeCode);

		for (String s : currencyList.keySet()) {

			double currencyDouble = CurrencyUtil.convertCurrencyToDouble(currencyList.get(s), localeCode);

			sum += currencyDouble;
		}

		verify.assertTrue(sum == total, "Expected total  " + total + " is not equal to actual total " + sum);

		verify.assertAll();
	}
	
	/***
	 * Use this method to verify currency format.
	 * 
	 * @return void
	 * 
	 */
	public static void verifyCurrencyFormat() {

		List<String> listValues = getList(listValues_lbl);

		List<String> listCurrency = getList(listValues_txt);

		Assert.assertTrue(listValues.size() == listCurrency.size(),
				"Value and Currency lists size is not same, lists size should be same to proceed.");

		Map<String, String> currencyList = getMapFromLists(listValues, listCurrency);

		String localeCode = prop.getProperty("currency.code");

		for (String s : currencyList.keySet()) {

			String currency = currencyList.get(s);
			
			verify.assertNotNull(CurrencyUtil.getCurrencyFormat(currency, localeCode),
					"Currency value of " + s + ": " + currency + ", is not formatted as " + localeCode + " currency");

		}

		verify.assertAll();
	}
	/***
	 * Use this method to verify currency format. Test method
	 * 
	 * @return void
	 */
	public static void verifyCurrencyFormat1() {

		Map<String, String> currencyList = getMap();

		String localeCode = prop.getProperty("currency.code");

		for (String key : currencyList.keySet()) {

			String currency = currencyList.get(key);

			verify.assertNotNull(CurrencyUtil.getCurrencyFormat(currency, localeCode),
					"Currency value of " + key + ": " + currency + ", is not formatted as " + localeCode + " currency");

		}

		verify.assertAll();
	}

	/***
	 * below method return list object for testing
	 * 
	 * @return list of String
	 */
	public static List<String> getList() {

		List<String> list = new ArrayList<String>();
		list.add("$122,365.24");
		list.add("$599.00");
		list.add("$850,139.00");
		list.add("$23,329.50");
		list.add("$566.27");

		return list;

	}
	
	/***
	 * To convert list of WebElelemnt into list of text of WebElement
	 * 
	 * @param locator
	 * 
	 * @return List<String>
	 * list of text from list of WebElement
	 */
	public static List<String> getList(String locator) {

		List<WebElement> list = driver.findElements(By.xpath(locator));

		return list.stream().map(ele -> ele.getText()).collect(Collectors.toList());

	}


	/***
	 * Converts two lists into key value pair Map.
	 * 
	 * @return Map<K,V>
	 * map object by combining two lists for testing purpose
	 *  
	 */
	public static Map<String, String> getMapFromLists(List<String> listKeys, List<String> listValues) {

		return IntStream.range(0, listKeys.size()).boxed()
				.collect(Collectors.toMap(i -> listKeys.get(i), i -> listValues.get(i)));
	}

/***
 * Provides a map object for testing purpose
 *  
 */
	public static Map<String, String> getMap() {

		Map<String, String> list = new HashMap<String, String>();
		list.put("Value 1", "$122,365.24");
		list.put("Value 2", "$599.00");
		list.put("Value 3", "$850,139.99");
		list.put("Value 4", "$23,329.50");
		list.put("Value 5", "$3,566.27");

		return list;

	}

}
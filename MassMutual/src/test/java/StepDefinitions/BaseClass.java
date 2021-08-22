package StepDefinitions;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.asserts.SoftAssert;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;


/***
 * 
 * @author Azhar Ali Baig
 *
 */

public class BaseClass {

	public static WebDriver driver;
	
	public static Properties prop ;
	
	public static SoftAssert verify;
	
	private static String userDir = System.getProperty("user.dir");
	/***
	 * Set up driver and application.prop files
	 */
	@Before
	public void setup() {
		
		//loading property file, to parameterize global properties
				try {
					prop = new Properties();
					
					prop.load(new FileInputStream(userDir+"\\src\\test\\resources\\application.properties"));
				
				} catch (FileNotFoundException e) {
					
					System.out.println("Propert file not found");
					
					e.printStackTrace();
				
				} catch (IOException e) {
					
					e.printStackTrace();
				}
		
		//setting chrome driver path
		System.setProperty("webdriver.chrome.driver", userDir+prop.getProperty("chrome.driver"));
		
		//initiating chrome driver
		driver = new ChromeDriver();
		driver.manage().window().maximize();		
		verify = new SoftAssert();
		
	}
	
	/***
	 * Takes scrrenshots whenever steps failed
	 * 
	 * @param scenario
	 * 
	 * @throws IOException
	 */
	@AfterStep
	public void addScreenshot(Scenario scenario) throws IOException {
		 
		if(scenario.isFailed()) {
		final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
		 
		scenario.attach(screenshot, "image/png", "image");
		}
	}
	
	/***
	 * Close WebDriver Session
	 */
	@After
	public void teardown() {
		
		driver.quit();
				
	}
	
	
	
	
}
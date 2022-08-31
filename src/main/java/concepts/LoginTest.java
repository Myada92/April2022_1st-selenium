package concepts;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class LoginTest {
	WebDriver driver;
	String browser ="chrome";
	
	@BeforeMethod
	public void readConfig() {
		
		//inputStraem //BufferStream //FileReader //scanner
		
		try {
			
			InputStream input = new FileInputStream("src\\main\\java\\config\\config.properties");
			Properties prop = new Properties();
			prop.load(input);
			 browser=  prop.getProperty(browser);
			
		}catch(IOException e) {
			e.printStackTrace();
	}}
	@BeforeTest

	public void init() throws InterruptedException {
		
		if(browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", "Driver\\chromedriver.exe");

		driver = new ChromeDriver();
		}else {

		System.setProperty("webdriver.gecko.driver", "Driver\\geckodriver.exe");
		driver = new FirefoxDriver();
		
}	
		
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.get("https://www.techfios.com/billing/?ng=admin/");
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		Thread.sleep(3000);
	}
	
	@Test
	public void loginTest() {

		driver.findElement(By.id("username")).sendKeys("demo@techfios.com");
		driver.findElement(By.id("password")).sendKeys("abc123");
		driver.findElement(By.name("login")).click();
		
		
}}

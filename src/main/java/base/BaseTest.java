package base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.v109.browser.Browser;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	
	public WebDriver driver;
	public static Properties prop;
	
	public static ThreadLocal<WebDriver> tdriver = new ThreadLocal<>();
	
//	@BeforeClass(alwaysRun = true)
//	@Parameters({"browser"})
//	public WebDriver initialize_driver(@Optional("chrome") String browser) {
	public WebDriver initialize_driver(String browser) {
		if(browser.equals(null)) {
			throw new IllegalArgumentException("Browser value can't be null");
		}
		
		switch(browser.toLowerCase()) {
		case "chrome":
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			System.out.println("Chrome browser started");
			break;
		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			System.out.println("Firefox browser started");
			break;
		case "edge":
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			System.out.println("Edge browser started");
			break;
		case "ie":
			WebDriverManager.iedriver().setup();
			driver = new EdgeDriver();
			System.out.println("IE browser started");
			break;
		default:
			throw new IllegalArgumentException("Browser name is invalid");
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
//		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		tdriver.set(driver);
		return getDriver();
	}

	public static synchronized WebDriver getDriver() {
		return tdriver.get();
	}
	
//	@AfterClass
	public void tearDown() {
		getDriver().quit();
		System.out.println("value of driver after quit "+ BaseTest.getDriver());
	}
//	
//	public Properties initialize_Properties() {
//		prop = new Properties();
//		try {
//			FileInputStream fi = new FileInputStream(System.getProperty("user.dir")+"/src/main/java/config/config.properties");
//			prop.load(fi);
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		return prop;
//	}
}

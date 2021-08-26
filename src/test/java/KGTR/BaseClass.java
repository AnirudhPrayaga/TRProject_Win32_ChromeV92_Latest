package KGTR;

import org.testng.ITest;
import org.testng.annotations.AfterMethod;

import org.testng.annotations.AfterSuite;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentTest;

import Utils.ExtentManager;



public class BaseClass {
	
	protected  WebDriver driver;
	protected WebDriverWait explctWait;
	protected Properties prop = new Properties();
	protected ExtentTest test;
    

	
	public WebDriver initializeDriver() throws IOException {
	try {
		String dir = System.getProperty("user.dir");
		System.out.println(dir);
		FileInputStream fis = new FileInputStream(dir+"/src/test/java/KGTR/data.properties");
		prop.load(fis);
		String browserName = prop.getProperty("browser");
		
		if(browserName.equalsIgnoreCase("chrome")) {
			
			System.setProperty("webdriver.chrome.driver", dir+"/Browserdrivers/chromedriver.exe");
			driver = new ChromeDriver();
		}
		
		else if(browserName.equalsIgnoreCase("firefox")) {
		
			System.setProperty("webdriver.gecko.driver", dir+"/Browserdrivers/geckodriver");
			driver = new FirefoxDriver();
		}
		
	} catch(Exception e) {
		System.out.println(e.toString());
	}
	
	return driver;
	}
	
	@BeforeTest
	public void initializeBrowser() throws Exception{
		driver = initializeDriver();
		driver.manage().window().maximize();
	    explctWait = new WebDriverWait(driver, 20);
	    test = ExtentManager.getInstance().createTest(getClass().getName());
	}
	
	
	@AfterTest
	public void tearDown() throws Exception {
		ExtentManager.getInstance().flush();
		driver.quit();
	}

	
	
	
	
}

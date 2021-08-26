package KGTR;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.Status;

import pageObjects.CanadianOppsPage;
import pageObjects.CareersPage;
import pageObjects.TradeRevHomePage;
/**
 * 
 * @author anirudhprayaga
 *
 * Class to verify Scenario 1: To check whether Canada TradeRev career page is displayed properly 
 */


public class Problem1 extends BaseClass {

 /*
  * This method below is used to validate if the TradeRev's Canadian Careers Page is displayed as expected or not
  */
	
	
	@Test
	public void CanadaCareersPageDisplay_Vrfy() throws IOException {
		
		try {
		
		// Instantiating required page objects by passing driver (WebDriver object initialized in BaseClass)
			
		TradeRevHomePage thp = new TradeRevHomePage(driver);
		CareersPage cp = new CareersPage(driver);
		CanadianOppsPage cop = new CanadianOppsPage(driver);
		
		//Getting necessary test data from the property file specified in the Baseclass
		
		String TradeRevHome_URL = prop.getProperty("TradeRevHome_URL");
		String CareersPageTitle = prop.getProperty("CareersPageTitle");
		String CareersPageURL = prop.getProperty("CareersPageURL");
		String JS_script_clickElement = prop.getProperty("JS_script_clickElement");
		String CanadianOppsPageTitle = prop.getProperty("CanadianOppsPageTitle");
		String CanadianOppsPageURL = prop.getProperty("CanadianOppsPageURL");
		
		//Verifying if we have successfully navigated to TradeRev's Career's Page after visiting it's Home Page.
		
		driver.get(TradeRevHome_URL); 
		test.info("Visiting TradeRev's Home Page"); //'test' Holds instance of ExtentManager used for reporting 
		explctWait.until(ExpectedConditions.presenceOfElementLocated(thp.CareerPageLocator())); // WebDriverWait's instance initialized in BaseClass for handling any latency
	    thp.CareersBtn().click();
		Set<String> windows = driver.getWindowHandles();
		test.info("Getting the window handles after clicking Carrers Button on Home Page "+driver.getWindowHandles().toString());
		ArrayList<String> ordWind = new ArrayList<String>(windows);
		driver.switchTo().window(ordWind.get(1));
		test.info("Trasnferring the driver control to the Careers Page Tab");
		Assert.assertTrue(driver.getTitle().equals(CareersPageTitle), "The title of the Careers page is not displayed as expected");
		Assert.assertTrue(driver.getCurrentUrl().equals("https://work.traderev.com/"), "The URL of the Careers page is not displayed as expected");
		test.info("VERIFIED! The title of the Careers Page is " +driver.getTitle());
		test.info("VERIFIED! URL of the Careers Page is " +driver.getCurrentUrl()); 
		System.out.println("VERIFIED! The title of the Careers Page is " +driver.getTitle());
		System.out.println("VERIFIED! URL of the Careers Page is " +driver.getCurrentUrl()); 
		
		
		// Verifying if we are able to successfully navigate to Canadian Careers Page after clicking on "Canadian Opps" button in Career's Page
		
		explctWait.until(ExpectedConditions.elementToBeClickable(cp.CanadianOpps_WebElement()));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript(JS_script_clickElement, cp.CanadianOpps_WebElement());
		System.out.println(driver.getWindowHandles());
		Set<String> windows2 = driver.getWindowHandles();
		ArrayList<String> ordWind3 = new ArrayList<String>(windows2);
		test.info(" Getting the window handles after after clicking Canadian Opportunities Button "+ordWind3.toString());
		
		// Verifying if we are able to successfully navigate to Canadian Opportunities/Jobs Page.
		
		driver.switchTo().window(ordWind3.get(2));
		test.info("Transferring the driver control to the Canadian Opps page, where all the jobs are listed");
		explctWait.until(ExpectedConditions.titleIs(CanadianOppsPageTitle));
		explctWait.until(ExpectedConditions.urlToBe(CanadianOppsPageURL));
		explctWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(cop.ApplyJobBtn_Locator()));
		Assert.assertTrue(driver.getTitle().equals(CanadianOppsPageTitle), "The title of the Canadian Opps page is not displayed as expected");
		Assert.assertTrue(driver.getCurrentUrl().equals(CanadianOppsPageURL), "The URL of the Canadian Opps is not displayed as expected");
		test.info("VERIFIED! The title of the Canadian Opps Page is " +driver.getTitle());
		test.info("VERIFIED! URL of the Canadian Opps Page is " +driver.getCurrentUrl()); 
		System.out.println("VERIFIED! The title of the Canadian Opps Page is " +driver.getTitle());
		System.out.println("VERIFIED! URL of the Canadian Opps Page is " +driver.getCurrentUrl()); 
		}
		
		catch(Exception e) {
			test.log(Status.FAIL, e.toString());
			System.out.println(e.toString());
		}
		
		
	}
	
}

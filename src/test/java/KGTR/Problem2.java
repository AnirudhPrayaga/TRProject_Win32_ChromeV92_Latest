package KGTR;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
 * Class to verify Scenario 2: To check whether job filter (city) is working properly on Canadian Opps/Jobs Page
 */

public class Problem2 extends BaseClass {

	 /*
	  * This method below is used to validate whether the Job Location Filter is functioning as expected or not,
	  * By using Toronto Location as the filter in the Canadian Opportunities/Jobs Page.
	  */
	
	@Test
	public void CanadianOpps_JobLocFilter_Vrfy() throws IOException {
		try {
		
	    //Instantiating required page object by passing driver (WebDriver object initialized in BaseClass)
			
		CanadianOppsPage cop = new CanadianOppsPage(driver);
		
		//Getting necessary test data from the property file specified in Baseclass 
		
		String CanadianOppsPageURL = prop.getProperty("CanadianOppsPageURL");
		String JobLocation_AustinTX = prop.getProperty("JobLocation_AustinTX");
		String JobLocation_CarmelIN = prop.getProperty("JobLocation_CarmelIN");
		String JobLocation_ChicagoIL = prop.getProperty("JobLocation_ChicagoIL");
		String JobLocation_GrtrChicagoArea = prop.getProperty("JobLocation_GrtrChicagoArea");
		String JobLocation_IndianapolisIndArea = prop.getProperty("JobLocation_IndianapolisIndArea");
		String JobLocation_MesaAZ = prop.getProperty("JobLocation_MesaAZ");
		String JobLocation_PhoenixAZ = prop.getProperty("JobLocation_PhoenixAZ");
		String JobLocation_Remote = prop.getProperty("JobLocation_Remote");
		String JobLocation_TorontoCAN = prop.getProperty("JobLocation_TorontoCAN");
		String JobLocation_VancouverCAN = prop.getProperty("JobLocation_VancouverCAN");
		
		// Filter the Jobs by Toronto Location
		
		driver.get(CanadianOppsPageURL); 
		test.info("Visiting Canadian Opps/Jobs Page"); //'test' Holds instance of ExtentManager used for reporting 
		explctWait.until(ExpectedConditions.presenceOfElementLocated(cop.JobLocationFilter_Locator())); // WebDriverWait's instance initialized in BaseClass for handling any latency
		cop.JobLocationFilter_WebElement().click();
		explctWait.until(ExpectedConditions.elementToBeClickable(cop.TorontoJobLoc_Locator()));
        cop.TorontoJobLoc_WebElement().click();
        test.info("Filtering all the Jobs in the Candian Jobs page by Toronto Location filter");
        explctWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(cop.JobLocPostingTag_Locator()));
        explctWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(cop.JobPostingName_Locator()));
        
        // After filtering the jobs, get all the job postings and their Location, Job Title web elements
        
        List<WebElement> TorontoElems = driver.findElements(cop.JobLocPostingTag_Locator());
		List<WebElement> JobTitles = driver.findElements(cop.JobPostingName_Locator());
		test.info("Accessing the results after filtering by Toronto Location so that we may verify if those displayed results are as expected or not");
		 
		// Validating that the job results displayed are only belonging to Toronto and not other cities
		
		 for(int i = 0,j = 0; i<TorontoElems.size() && j<JobTitles.size(); i++, j++) 
		 {
	     
		  int s = j+1;
	      System.out.println("Job title of the job number " +s+ " is " + JobTitles.get(j).getText() + " and the location of the job posting is " + TorontoElems.get(i).getText());
		    
	  	  //Making sure other cities are not in the location field when toronto is applied as a filter
		
	  	  if(!(Arrays.asList(JobLocation_AustinTX,JobLocation_CarmelIN,JobLocation_ChicagoIL,JobLocation_GrtrChicagoArea,JobLocation_IndianapolisIndArea,JobLocation_MesaAZ,JobLocation_PhoenixAZ,JobLocation_Remote,JobLocation_VancouverCAN).contains(TorontoElems.get(i).getText()))) 
		    {
	  		Assert.assertTrue(true, "In the jobs page, Filter by location functionality is not working as expected, other city/cities are also showing up when we filter by location for Toronto city");
		    
		    }
		
	  	  //Making sure toronto is in the location field
		
	  	    Assert.assertTrue(TorontoElems.get(i).getText().equals(JobLocation_TorontoCAN));	  	  
	  	 }
		test.info("VERIFIED! Other cities are not displayed when toronto is applied as a filter");
		test.info("VERIFIED! Toronto indeed is the location for the displayed job posting and concluding that filer functionality is working as expected.");
		System.out.println("VERIFIED! Other cities are not displayed when toronto is applied as a filter");
		System.out.println("VERIFIED! Toronto indeed is the location for the displayed job posting and concluding that filer functionality is working as expected.");   
		System.out.println("No. of job openings are : " +JobTitles.size());
		
	    }
	
	    catch(Exception e)
		{
		test.log(Status.FAIL, e.toString());
		System.out.println(e.toString());
	    }
		  
	}
	
}

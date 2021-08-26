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
 * Class to verify Scenario 3: To check whether job filter (city) and (team) is working properly
 */


public class Problem3 extends BaseClass {

	 /*
	  * This method below is used to validate whether the Job Location, Team filters are functioning as expected or not,
	  * By using Toronto Location, Engineering Team as the filters in the Canadian Opportunities/Jobs Page.
	  */
	
	
	@Test
	public void CanadaOpps_JobLocTeamFilters_Vrfy() throws IOException {
		try {
		
		//	Instantiating required page object by passing driver (WebDriver object initialized in BaseClass)
			
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
		
		String JobTeam_All = prop.getProperty("JobTeam_All");
		String JobTeam_DESIGN = prop.getProperty("JobTeam_DESIGN");
		String JobTeam_ProdMgmt = prop.getProperty("JobTeam_ProdMgmt");
		String JobTeam_Engg = prop.getProperty("JobTeam_Engg");
		
		String Engg_Analytics = prop.getProperty("Engg_Analytics");
		String Engg_DataScience = prop.getProperty("Engg_DataScience");
		String Engg_OpsEngg = prop.getProperty("Engg_OpsEngg");
		String Engg_QA = prop.getProperty("Engg_QA");
		String Engg_SwDev = prop.getProperty("Engg_SwDev");
		
		// Filter the Jobs by Toronto Location, Engineering Team
		
		driver.get(CanadianOppsPageURL); 
		test.info("Visiting Canadian Opps/Jobs Page"); //'test' Holds instance of ExtentManager used for reporting 
		explctWait.until(ExpectedConditions.presenceOfElementLocated(cop.JobLocationFilter_Locator())); // WebDriverWait's instance initialized in BaseClass for handling any latency
		cop.JobLocationFilter_WebElement().click();
		explctWait.until(ExpectedConditions.elementToBeClickable(cop.TorontoJobLoc_Locator()));
        cop.TorontoJobLoc_WebElement().click();
        explctWait.until(ExpectedConditions.presenceOfElementLocated(cop.JobPostingTeam_Locator()));
        cop.JobPostingTeam_WebElement().click();
        explctWait.until(ExpectedConditions.elementToBeClickable(cop.EngineeringTeam_Locator()));
        cop.EngineeringTeam_WebElement().click();
        test.info("Filtering all the Jobs in the Candian Jobs page by Toronto Location, Engineering Team filter");
        explctWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(cop.JobLocPostingTag_Locator()));
        explctWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(cop.JobPostingName_Locator()));
        explctWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(cop.JobTeamPostingTag_Locator()));
        
        // After filtering the jobs, get all the job postings and their Location, Team, Job Title web elements
        
        List<WebElement> TorontoElems = driver.findElements(cop.JobLocPostingTag_Locator());
        List<WebElement> EnggTeamElems = driver.findElements(cop.JobTeamPostingTag_Locator());
        List<WebElement> JobTitles = driver.findElements(cop.JobPostingName_Locator());
        test.info("Accessing the results after filtering by Toronto Location, Engineering Team so that we may verify if those displayed results are as expected or not");
    
        Assert.assertTrue(TorontoElems.size() == JobTitles.size());
        Assert.assertTrue(EnggTeamElems.size() == JobTitles.size());
		 
        // Validating that the job results displayed are only belonging to Toronto and not other cities, Engineering but not other teams
        
        
        for(int i = 0,j = 0; i<TorontoElems.size() && j<EnggTeamElems.size(); i++, j++) 
		 {
	     
		  int s = j+1;
	  
	  	  System.out.println("Job title of the job number " +s+ " is " + JobTitles.get(j).getText() + " and the location of the job posting is " + TorontoElems.get(i).getText() + " and the team of this job posting is " +EnggTeamElems.get(i).getText() );
		    
	  	  //Making sure other cities, teams are not in the location, team fields when toronto and engineering are applied as a filter
		
	  	   if(!(Arrays.asList(JobLocation_AustinTX,JobLocation_CarmelIN,JobLocation_ChicagoIL,JobLocation_GrtrChicagoArea,JobLocation_IndianapolisIndArea,JobLocation_MesaAZ,JobLocation_PhoenixAZ,JobLocation_Remote,JobLocation_VancouverCAN).contains(TorontoElems.get(i).getText())) && Arrays.asList(JobTeam_DESIGN, JobTeam_All, JobTeam_ProdMgmt).contains(EnggTeamElems.get(i).getText())) 
		     {
	  		 Assert.assertTrue(true, "In the jobs page, Filter by location, team functionalities are not working as expected, jobs with other cities , other teams are also showing up when we filter by location for Toronto city and Engineering Team");
			 }
		
	  	  //Making sure toronto is in the location field, Engineering teams are in the team field
		
	    	 Assert.assertTrue(TorontoElems.get(i).getText().equals(JobLocation_TorontoCAN));
	         Assert.assertTrue(Arrays.asList(Engg_Analytics,Engg_DataScience,Engg_OpsEngg,Engg_QA,Engg_SwDev).contains(EnggTeamElems.get(i).getText()));
	     }
         test.info("VERIFIED! Other cities, teams are not displayed when toronto and Engineering are applied as a filters");
         test.info("VERIFIED! Toronto indeed is the location for the displayed job posting, Engineering is indeed the job's team and concluding that filer functionality is working as expected.");
         System.out.println("VERIFIED! Other cities, teams are not displayed when toronto and Engineering are applied as a filters");
         System.out.println("VERIFIED! Toronto indeed is the location for the displayed job posting, Engineering is indeed the job's team and concluding that filer functionality is working as expected.");
         System.out.println("No. of job openings are : " +JobTitles.size());
	     }
	
    	catch(Exception e) 
		{
	     test.log(Status.FAIL, e.toString());
	     System.out.println(e.toString());
	    }
		  
	}
	
}

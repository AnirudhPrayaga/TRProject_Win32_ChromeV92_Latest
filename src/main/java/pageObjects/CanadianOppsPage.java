package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CanadianOppsPage {
	
	public WebDriver driver;
	
	public CanadianOppsPage(WebDriver driver)
	{
		
		this.driver = driver;

	}
	
	private By ApplyJobBtn_Locator = By.xpath("//a[@class='posting-btn-submit template-btn-submit hex-color']"); 
	private By JobLocationFilter_Locator = By.xpath("//div[@aria-label='Filter by Location: All']");
    private By TorontoJobLoc_Locator = By.linkText("Toronto, Ontario, Canada");
    private By JobLocPostingTag_Locator = By.xpath("//span[@class='sort-by-location posting-category small-category-label']");
    private By JobPostingName_Locator = By.xpath("//h5[@data-qa='posting-name']");
    private By JobPostingTeam_Locator = By.xpath("//div[@aria-label='Filter by Team: All']");
    private By EngineeringTeam_Locator = By.linkText("Engineering");
    private By JobTeamPostingTag_Locator = By.xpath("//span[@class='sort-by-team posting-category small-category-label']");
	
	
	public By ApplyJobBtn_Locator() 
	{
	return ApplyJobBtn_Locator;
	}

	public WebElement ApplyJobBtn_WebElement() 
	{
	return driver.findElement(ApplyJobBtn_Locator);
	}
	
	public By JobLocationFilter_Locator() 
	{
	return JobLocationFilter_Locator;
	}

	public WebElement JobLocationFilter_WebElement() 
	{
	return driver.findElement(JobLocationFilter_Locator);
	}

	public By TorontoJobLoc_Locator() 
	{
	return TorontoJobLoc_Locator;
	}

	public WebElement TorontoJobLoc_WebElement() 
	{
	return driver.findElement(TorontoJobLoc_Locator);
	}

	public By JobLocPostingTag_Locator() 
	{
	return JobLocPostingTag_Locator;
	}

	public WebElement JobLocPostingTag_WebElement() 
	{
	return driver.findElement(JobLocPostingTag_Locator);
	}
	
	public By JobPostingName_Locator() 
	{
	return JobPostingName_Locator;
	}

	public WebElement JobPostingName_WebElement() 
	{
	return driver.findElement(JobPostingName_Locator);
	}

	public By JobPostingTeam_Locator() 
	{
	return JobPostingTeam_Locator;
	}

	public WebElement JobPostingTeam_WebElement() 
	{
	return driver.findElement(JobPostingTeam_Locator);
	}
	
	public By EngineeringTeam_Locator() 
	{
	return EngineeringTeam_Locator;
	}

	public WebElement EngineeringTeam_WebElement() 
	{
	return driver.findElement(EngineeringTeam_Locator);
	}
	
	public By JobTeamPostingTag_Locator() 
	{
	return JobTeamPostingTag_Locator;
	}

	public WebElement JobTeamPostingTag_WebElement() 
	{
	return driver.findElement(JobTeamPostingTag_Locator);
	}
}

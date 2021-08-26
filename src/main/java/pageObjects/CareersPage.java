package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CareersPage {
	
	public WebDriver driver;
	
	public CareersPage(WebDriver driver)
	{
		
		this.driver = driver;

	}
	
	private By CanadianOppsLocator = By.xpath("//a[@href='https://jobs.lever.co/traderev']"); 
	
	
	


	public By CanadianOpps_Locator() 
	{
	return CanadianOppsLocator;
	}

	public WebElement CanadianOpps_WebElement() 
	{
	return driver.findElement(CanadianOppsLocator);
	}
	

}

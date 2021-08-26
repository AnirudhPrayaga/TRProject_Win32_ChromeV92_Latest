package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class TradeRevHomePage {
	
	public WebDriver driver;
	
	
	
	private By CareersPageBtn = By.xpath("//a[@href='https://work.traderev.com/']"); 
	
	
	
public TradeRevHomePage(WebDriver driver) {
	
	this.driver = driver;

}

public By CareerPageLocator() {
	return CareersPageBtn;
}

public WebElement CareersBtn() {
	
	return driver.findElement(CareersPageBtn);
}
	

}

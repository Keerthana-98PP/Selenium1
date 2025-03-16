package keerthanaAutomation.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import keerthanaAutomation.AbstractComponents.AbstractComponents;

public class CheckOutPage extends AbstractComponents {
	WebDriver driver;
	public CheckOutPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(css="input[placeholder='Select Country']")
	WebElement country;
	@FindBy(css=".ta-item:nth-of-type(2)")
	WebElement selectCountry;
	@FindBy(css=".btnn.action__submit")
	WebElement placeOrder;
	
	
	
	public void placeOrder(String countryName) {
		
        country.sendKeys(countryName);
		
		selectCountry.click();		
		
		placeOrder.click();
		
		 
	}
	

}

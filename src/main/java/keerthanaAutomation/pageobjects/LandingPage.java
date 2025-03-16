package keerthanaAutomation.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import keerthanaAutomation.AbstractComponents.AbstractComponents;

public class LandingPage extends AbstractComponents {
	
	WebDriver driver;
	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="userEmail")
	WebElement emailElement;
	
	@FindBy(id="userPassword")
	WebElement passwordElement;
	
	@FindBy(id="login")
	WebElement loginElement;
	
	@FindBy(css="[class*='flyInOut']")
	WebElement errorMsg;
	
	public void loginApplication(String email,String password) {
		emailElement.sendKeys(email);
		passwordElement.sendKeys(password);
		 loginElement.click();
		
	}
	
	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client");
		
	
	}
	
	public String getError() {
		waitForElementToAppear(errorMsg);
		return errorMsg.getText();
	}
	public void clearTextBoxes() {
		emailElement.clear();
		passwordElement.clear();
	}

}

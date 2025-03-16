package keerthanaAutomation.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import keerthanaAutomation.AbstractComponents.AbstractComponents;

public class OrderConformationPage extends AbstractComponents {
	WebDriver driver;
	public OrderConformationPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".hero-primary")
	WebElement msg;
	
	By successPopUp=By.cssSelector(".toast-container");
	
	public void orderPlaced() {
		 waitForElementsToAppear(successPopUp);
	}
	
	public String verifyOrderConformationMsg() {
		String actual=msg.getText();
		return actual;
	}

}

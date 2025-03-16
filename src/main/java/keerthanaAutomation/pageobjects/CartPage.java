package keerthanaAutomation.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPage {
	
	WebDriver driver;
	public CartPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".cartSection h3")
	List<WebElement> cartItems;
	
	@FindBy(css=".subtotal button")
	WebElement checkOutButton;
	
	
	public boolean verifyProductDisplayed(String productName) {
		
		Boolean match=cartItems.stream().anyMatch(cartItem -> cartItem.getText().equalsIgnoreCase(productName));
		return match;
	}
	
	public void checkOut() {
		checkOutButton.click();
	}

}

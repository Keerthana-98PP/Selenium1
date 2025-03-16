package keerthanaAutomation.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrdersPage {

	WebDriver driver;

	public OrdersPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//button[@routerlink='/dashboard/myorders']")
	WebElement ordersElement;

	@FindBy(css = "tr td:nth-child(3)")
	List<WebElement> productNames;

	public boolean checkOrderDisplayed() {
		ordersElement.click();

		Boolean match = productNames.stream().anyMatch(name -> name.getText().equalsIgnoreCase("Zara Coat 3"));
		return match;

	}
}

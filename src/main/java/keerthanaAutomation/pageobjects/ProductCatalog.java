package keerthanaAutomation.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import keerthanaAutomation.AbstractComponents.AbstractComponents;

public class ProductCatalog extends AbstractComponents{
	
	WebDriver driver;
	public ProductCatalog(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".mb-3")
	List <WebElement> productsElement;
	
	By productsBy=By.cssSelector(".mb-3");
	By addToCart=By.cssSelector(".w-10");
	By toastContainer=By.id("toast-container");
	@FindBy(css=".ng-animating")
	WebElement spinner;
	
	 
	public List<WebElement> getProductList() {
		waitForElementsToAppear(productsBy);
		return productsElement;
	}
	
	public WebElement getProductByName(String productName) {
		
		WebElement product = productsElement.stream()
				.filter(item -> item.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst()
				.orElse(null);
		return product;
	}
	
	public void addProductToCart(String productName) {
		WebElement product=getProductByName(productName);
		product.findElement(addToCart).click();
		waitForElementsToAppear(toastContainer);
		waitForElementToDisappear(spinner);
		
	}
	
}

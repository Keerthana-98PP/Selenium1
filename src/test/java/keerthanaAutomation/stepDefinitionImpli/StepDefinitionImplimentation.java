package keerthanaAutomation.stepDefinitionImpli;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.AssertJUnit;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import keerthanaAutomation.TestComponents.BaseTest;
import keerthanaAutomation.pageobjects.CartPage;
import keerthanaAutomation.pageobjects.CheckOutPage;
import keerthanaAutomation.pageobjects.LandingPage;
import keerthanaAutomation.pageobjects.OrderConformationPage;
import keerthanaAutomation.pageobjects.ProductCatalog;

public class StepDefinitionImplimentation extends BaseTest {
	
	public LandingPage landingPage;
	public  ProductCatalog productCatalog;
	@Given ("landed on Ecommerce page")
		public void I_landed_on_eCommercePage() throws IOException {
		
		landingPage=LaunchApplication();
		
	}
	
	  @Given ("^login with username(.+) and password(.+)$")
	  public void login_with_userName_password(String userName,String password) {
		 landingPage.loginApplication(userName,password);
	  }
    @When ("^Add product(.+) to cart$")
    public void add_product_to_cart(String productName) {
    	ProductCatalog productCatalog = new ProductCatalog(driver);
		List<WebElement> productList = productCatalog.getProductList();
		productCatalog.addProductToCart(productName);
		productCatalog.goToCartPage();
    	
    }
    @When ("^checkout(.+) and submit order$")
    public void checkOut_and_submit_order(String productName) {
    	
    	CartPage cartpage = new CartPage(driver);
		Boolean match = cartpage.verifyProductDisplayed(productName);
		Assert.assertTrue(match);
		cartpage.checkOut();
		CheckOutPage checkOutPage = new CheckOutPage(driver);
		checkOutPage.placeOrder("India");
    }
	 @Then ("{string} message is displayed on conformation page")
	 public void validate_conformation_message_displayed(String string) {
		 OrderConformationPage orderConform = new OrderConformationPage(driver);
			orderConform.orderPlaced();
			String expected = "THANKYOU FOR THE ORDER.";
			String actual = orderConform.verifyOrderConformationMsg();
			AssertJUnit.assertEquals(actual, expected);
			
			driver.close();

	 }
	 
	 @Then("{string} message is displayed on Sign In page")
	 public void sign_in_errorMsg_validation(String string) {
		 String errorMsg= landingPage.getError();
		 landingPage.clearTextBoxes();
		 AssertJUnit.assertEquals(errorMsg,"Incorrect email or password.");

	 }

}

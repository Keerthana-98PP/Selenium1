package keerthanaAutomation;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import keerthanaAutomation.TestComponents.BaseTest;
import keerthanaAutomation.TestComponents.Retry;
import keerthanaAutomation.data.DataReader;
import keerthanaAutomation.pageobjects.CartPage;
import keerthanaAutomation.pageobjects.CheckOutPage;
import keerthanaAutomation.pageobjects.LandingPage;
import keerthanaAutomation.pageobjects.OrderConformationPage;
import keerthanaAutomation.pageobjects.OrdersPage;
import keerthanaAutomation.pageobjects.ProductCatalog;

public class StandAloneTest extends BaseTest {

	private static final WebDriver TakesScreenshot = null;



	@Test(dataProvider = "getData", groups = { "placeOrder" })
	public void placeOrder(HashMap<String, String> input) throws IOException {

		landingPage.loginApplication(input.get("email"), input.get("password"));

		ProductCatalog productCatalog = new ProductCatalog(driver);
		List<WebElement> productList = productCatalog.getProductList();
		productCatalog.addProductToCart(input.get("product"));
		productCatalog.goToCartPage();

		CartPage cartpage = new CartPage(driver);
		Boolean match = cartpage.verifyProductDisplayed(input.get("product"));
		Assert.assertTrue(match);
		cartpage.checkOut();

		CheckOutPage checkOutPage = new CheckOutPage(driver);
		checkOutPage.placeOrder("India");

		OrderConformationPage orderConform = new OrderConformationPage(driver);
		orderConform.orderPlaced();
		String expected = "THANKYOU FOR THE ORDER.";
		String actual = orderConform.verifyOrderConformationMsg();
		AssertJUnit.assertEquals(actual, expected);

	}

	@Test(dependsOnMethods = { "placeOrder" },dataProvider = "getData")
	public void orderDisplayed(HashMap<String, String> input) {
		landingPage.loginApplication(input.get("email"), input.get("password"));
		OrdersPage orders = new OrdersPage(driver);

		AssertJUnit.assertTrue(orders.checkOrderDisplayed());
	}

	

	
	
	
	
//	@DataProvider
//	public Object[][] getData() {
//		return new Object[][] {{"keerthanapp@gmail.com", "Pass@123","ZARA COAT 3"},{"rahul9316@gmail.com","Pass1234","ADIDAS ORIGINAL"}};
//	}
//
//	HashMap<String,String>map=new HashMap<String,String>();
//	map.put("email","keerthanapp@gmail.com");
//	map.put("password", "Pass@123");
//	map.put("product","ZARA COAT 3" );
//	
//	HashMap<String,String>map1=new HashMap<String,String>();
//	map1.put("email","rahul9316@gmail.com");
//	map1.put("password", "Pass1234");
//	map1.put("product","ADIDAS ORIGINAL" );
//	return new Object[][] {{map},{map1}};
}

package keerthanaAutomation;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import keerthanaAutomation.TestComponents.BaseTest;
import keerthanaAutomation.TestComponents.Retry;
import keerthanaAutomation.pageobjects.CartPage;
import keerthanaAutomation.pageobjects.LandingPage;
import keerthanaAutomation.pageobjects.ProductCatalog;

public class ErrorValidationTest extends BaseTest {
@Test(groups= {"ErrorHandling"})
public void errorValidation() {
landingPage.loginApplication("keerthp@gmail.com", "Ps@123");
String errorMsg= landingPage.getError();
landingPage.clearTextBoxes();
AssertJUnit.assertEquals(errorMsg,"Incorrect email or password.");

}

@Test(dataProvider = "getData")
public void productErrorValidation(HashMap<String, String> input) {
landingPage.loginApplication(input.get("email"), input.get("password"));
ProductCatalog productCatalog = new ProductCatalog(driver);
List<WebElement> productList = productCatalog.getProductList();
productCatalog.addProductToCart(input.get("product"));
productCatalog.goToCartPage();

CartPage cartpage = new CartPage(driver);
Boolean match = cartpage.verifyProductDisplayed(input.get("product"));
Assert.assertTrue(match);
}
}

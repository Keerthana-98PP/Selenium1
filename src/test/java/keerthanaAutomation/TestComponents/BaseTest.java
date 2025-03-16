package keerthanaAutomation.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;

import keerthanaAutomation.data.DataReader;
import keerthanaAutomation.pageobjects.LandingPage;

public class BaseTest {
	public WebDriver driver;
	public LandingPage landingPage;
	public WebDriver initializeDriver() throws IOException {
		
		Properties properties=new Properties();
		FileInputStream fileStream=new FileInputStream("C:\\Users\\hp\\eclipse-workspace\\SeleniumFrameWorkDesign\\src\\main\\java\\keerthanaAutomation\\resources\\GlobalResources.properties");
		properties.load(fileStream);
		String browserName=System.getProperty("browser")!=null?System.getProperty("browser"):properties.getProperty("browser");		
		
		
		if(browserName.contains("Chrome")) {
			ChromeOptions options=new ChromeOptions();
			if(browserName.contains("headless")) {
				options.addArguments("headless");
			}
		 driver = new ChromeDriver(options);
		 driver.manage().window().setSize(new Dimension(1440,900));
		}
	
		if(browserName.equalsIgnoreCase("Edge")) {
			 driver = new EdgeDriver();
			}
		if(browserName.equalsIgnoreCase("Firefox")) {
			 driver = new FirefoxDriver();
			}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		return driver;
	}
	@BeforeMethod(alwaysRun=true)
	public LandingPage LaunchApplication() throws IOException {
		driver=initializeDriver();
        landingPage = new LandingPage(driver);
		landingPage.goTo();
		return landingPage;
	}
@AfterMethod(alwaysRun=true)
public void tearDown() {
	driver.close();
}

public String getScreenShots(String testCaseName,WebDriver driver) throws IOException {
	
	TakesScreenshot ts=(TakesScreenshot)driver;
	File source=ts.getScreenshotAs(OutputType.FILE);
	File file=new File(System.getProperty("user.dir")+"//reports"+testCaseName+".png");
	FileUtils.copyFile(source, file);
	return System.getProperty("user.dir")+"//reports"+testCaseName+".png";
	
	}
@DataProvider
public Object[][] getData() throws IOException {

	
	DataReader dataReader = new DataReader();

	List<HashMap<String, String>> data = dataReader.getDataToMap(System.getProperty("user.dir")
			+ "\\src\\test\\java\\keerthanaAutomation\\data\\purchaseOrderFile.json");
	return new Object[][] { { data.get(0) }, { data.get(1) } };

}
}

package com.sqa.na.salesforce.test;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.sqa.na.salesforce.pageobject.HomePage;
import com.sqa.na.salesforce.pageobject.LoginPage;
import com.sqa.na.salesforce.pageobject.TryForFreePage;

public class GoogleButtonTest {
	private String browser = "firefox";

	private WebDriver driver;

	@BeforeMethod
	public void setup() {

		if (this.browser.isEmpty()) {
			System.out.println("Invalid Browser");
			return;
		}
		switch (this.browser.toLowerCase()) {
		case "firefox":
			this.driver = new FirefoxDriver();
			break;
		case "chrome":
			System.setProperty("webdriver.chrome.driver", "C:\\Program Files (x86)\\chromedriver\\chromedriver.exe");
			this.driver = new ChromeDriver();
			break;
		case "edge":
			String edgeDriverPath = "C:\\Program Files (x86)\\Microsoft Web Driver2\\MicrosoftWebDriver.exe";
			System.setProperty("webdriver.edge.driver", edgeDriverPath);
			EdgeOptions options = new EdgeOptions();
			options.setPageLoadStrategy("eager");
			// DesiredCapabilities capabilities = DesiredCapabilities.edge();
			this.driver = new EdgeDriver();
			this.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			break;
		default:
			break;
		}
	}

	@AfterMethod
	public void tearDown() {
		this.driver.quit();
	}

	@Test
	public void testGoogleBtnVisibilty() {
		// can use the HomePage(driver) or pageobject, they do the same thing
		HomePage homepage = PageFactory.initElements(this.driver, HomePage.class);
		homepage.navigateToHomePage();
		LoginPage loginpage = homepage.clickOnLoginBtn();
		TryForFreePage tryforfreepage = loginpage.clickTryForFreeLink();
		Assert.assertTrue("Google button is not visible", tryforfreepage.isGoogleBtnDisplayed());

	}
}

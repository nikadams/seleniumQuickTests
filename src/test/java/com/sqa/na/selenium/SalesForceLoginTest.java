package com.sqa.na.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class SalesForceLoginTest {
	private WebDriver driver;

	@DataProvider(name = "login")
	public Object[][] dp() {
		return new Object[][] { { "user", "password" }, { "user@aol.com", "bad3" }, { "@aol.com", "asdf" } };

	}

	@Test(dataProvider = "login")
	public void f(String username, String password) {
		final By loginBtnLocator = By.id("button-login");
		this.waitUntil(15, loginBtnLocator);
		this.driver.findElement(loginBtnLocator).click();

		final By usernameTxtFieldLocator = By.name("username");
		final By passwordTxtFieldLocator = By.name("pw");
		this.driver.findElement(usernameTxtFieldLocator).sendKeys(username);
		this.driver.findElement(passwordTxtFieldLocator).sendKeys(password);

		this.driver.findElement(By.id("Login")).click();

		WebDriverWait wait = new WebDriverWait(this.driver, 10);
		wait.until(ExpectedConditions.textToBePresentInElementLocated(By.id("error"),
				"Please check your username and password. If you still can't log in, contact your Salesforce administrator."));
		// Assert.assertEquals(this.driver.findElement(By.className("loginError")).getText(),
		// "Please check your username and password. If you still can't log in,
		// contact your Salesforce administrator.");

	}

	@BeforeMethod
	public void setup() {

		// String edgeDriverPath = "C:\\Program Files (x86)\\Microsoft Web
		// Driver2\\MicrosoftWebDriver.exe";
		// System.setProperty("webdriver.edge.driver", edgeDriverPath);
		// EdgeOptions options = new EdgeOptions();
		// options.setPageLoadStrategy("eager");
		// DesiredCapabilities capabilities = DesiredCapabilities.edge();
		// this.driver = new EdgeDriver(capabilities);
		this.driver = new FirefoxDriver();

		this.driver.get("http://app.work.com");
	}

	@AfterMethod
	public void tearDown() {
		this.driver.quit();
	}

	private void waitUntil(long time, By locator) {
		WebDriverWait wait = new WebDriverWait(this.driver, time);
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
}

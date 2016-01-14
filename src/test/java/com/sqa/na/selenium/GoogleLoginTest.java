/**
 *   File Name: GoogleLoginTest.java<br>
 *
 *   Adams, Nik<br>
 *   Java Boot Camp Exercise<br>
 *   Instructor: Jean-francois Nepton<br>
 *   Created: Jan 11, 2016
 *
 */

package com.sqa.na.selenium;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * GoogleLoginTest //ADDD (description of class)
 * <p>
 * //ADDD (description of core fields)
 * <p>
 * //ADDD (description of core methods)
 *
 * @author Adams, Nik
 * @version 1.0.0
 * @since 1.0
 *
 */
public class GoogleLoginTest {

	private WebDriver driver;

	@BeforeMethod
	public void setup() {

		String edgeDriverPath = "C:\\Program Files (x86)\\Microsoft Web Driver2\\MicrosoftWebDriver.exe";
		System.setProperty("webdriver.edge.driver", edgeDriverPath);
		EdgeOptions options = new EdgeOptions();
		options.setPageLoadStrategy("eager");
		DesiredCapabilities capabilities = DesiredCapabilities.edge();
		this.driver = new EdgeDriver(capabilities);

		this.driver.get("app.work.com");
	}

	@AfterMethod
	public void tearDown() {
		this.driver.quit();
	}

	@Test
	public void testGoogleLogo() {
		final By loginBtnLocator = By.id("button-login");
		this.waitUntil(15, loginBtnLocator);
		this.driver.findElement(loginBtnLocator).click();

		final By tryForFreeBtnLocator = By.id("signup_link");
		this.waitUntil(15, tryForFreeBtnLocator);
		this.driver.findElement(tryForFreeBtnLocator).click();

		final By googleBtnLocator = By.className("google");
		this.waitUntil(15, googleBtnLocator);
		this.driver.findElement(googleBtnLocator).click();

		ArrayList<String> windowHandles = new ArrayList<String>(this.driver.getWindowHandles());
		this.driver.close();
		this.driver.switchTo().window(windowHandles.get(1));

		this.waitUntil(15, By.className("logo-w"));
	}

	private void waitUntil(long time, By locator) {
		WebDriverWait wait = new WebDriverWait(this.driver, time);
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

}

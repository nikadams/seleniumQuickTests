/**
 *   File Name: GoogleSearchTest.java<br>
 *
 *   Adams, Nik<br>
 *   Java Boot Camp Exercise<br>
 *   Instructor: Jean-francois Nepton<br>
 *   Created: Jan 9, 2016
 *
 */

package com.sqa.na.selenium;

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
 * GoogleSearchTest //ADDD (description of class)
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
public class GoogleSearchTest {

	private WebDriver driver;

	@Test
	public void googleSearchTest() {

		final By link = By.partialLinkText("Yelp");
		// WebDriverWait wait = new WebDriverWait(this.driver, 5);

		this.driver.findElement(By.name("q")).sendKeys("Seafood city");
		this.driver.findElement(By.name("btnG")).click();
		// wait.until(ExpectedConditions.visibilityOfElementLocated(link));
		this.waitUntil(5, link);
		// this.driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		this.driver.findElement(By.partialLinkText("Yelp")).click();
		this.waitUntil(5, link);
		// wait.until(ExpectedConditions.visibilityOfElementLocated(link));
		// assert not needed
		// Assert.assertTrue(this.driver.findElement(By.linkText("Yelp")).isDisplayed(),
		// "Yelp logo is not displayed");
	}

	@BeforeMethod
	public void setup() {
		// this.driver = new FirefoxDriver();
		// System.setProperty("webdriver.chrome.driver", "C:\\Program Files
		// (x86)\\chromedriver\\chromedriver.exe");
		// this.driver = new ChromeDriver();
		String edgeDriverPath = "C:\\Program Files (x86)\\Microsoft Web Driver2\\MicrosoftWebDriver.exe";
		System.setProperty("webdriver.edge.driver", edgeDriverPath);
		// EdgeOptions options = new EdgeOptions();
		// EdgeDriverManager.getInstance().setup();
		// options.setPageLoadStrategy("eager");
		// this.driver = new EdgeDriver();

		EdgeOptions options = new EdgeOptions();
		options.setPageLoadStrategy("eager");
		DesiredCapabilities capabilities = DesiredCapabilities.edge();
		this.driver = new EdgeDriver(capabilities);

		this.driver.get("https://google.com");
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

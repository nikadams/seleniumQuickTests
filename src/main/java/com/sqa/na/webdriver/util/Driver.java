/**
 *   File Name: Driver.java<br>
 *
 *   Adams, Nik<br>
 *   Created: Jan 21, 2016
 *
 */

package com.sqa.na.webdriver.util;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Driver //ADDD (description of class)
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
public class Driver {

	private static WebDriver driver;

	public static WebDriver driverInit(String browser) {

		switch (browser.toLowerCase()) {
		case "firefox":
			driver = new FirefoxDriver();
			break;
		case "chrome":
			System.setProperty("webdriver.chrome.driver", "C:\\Program Files (x86)\\chromedriver\\chromedriver.exe");
			driver = new ChromeDriver();
			break;
		case "edge":
			String edgeDriverPath = "C:\\Program Files (x86)\\Microsoft Web Driver2\\MicrosoftWebDriver.exe";
			System.setProperty("webdriver.edge.driver", edgeDriverPath);
			EdgeOptions options = new EdgeOptions();
			options.setPageLoadStrategy("eager");
			// DesiredCapabilities capabilities = DesiredCapabilities.edge();
			driver = new EdgeDriver();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			break;
		default:
			break;
		}

		return driver;

	}

	public static void waitUntil(long time, By locator) {
		WebDriverWait wait = new WebDriverWait(driver, time);
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
}

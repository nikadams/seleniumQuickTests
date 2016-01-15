package com.sqa.na.selenium;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.Augmenter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ScreenShotTest {
	private String browser = "firefox";
	private WebDriver driver;

	@Test
	public void f() throws IOException {

		this.driver.get("http://google.com");
		this.takeScreenshot();
	}

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
			this.driver = new FirefoxDriver();
			break;

		}

	}

	@AfterMethod
	public void tearDown() {
		this.driver.quit();
	}

	/**
	 *
	 *
	 * @return timestamp in the format MMddyyyyhhmm
	 */
	private String getTimestamp() {
		String pattern = "MMddyyyyhhmmss";
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		Date date = new Date();
		return format.format(date);
	}

	private void takeScreenshot() throws IOException {
		WebDriver augmentedDriver = new Augmenter().augment(this.driver);
		augmentedDriver = new Augmenter().augment(this.driver);
		File screenshot = ((TakesScreenshot) augmentedDriver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screenshot, new File("C:\\Users\\User\\Dropbox\\"
				+ this.getClass().getSimpleName().toString() + "_" + this.getTimestamp() + ".png"));
	}
}

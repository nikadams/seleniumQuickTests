/**
 *   File Name: XlsTest.java<br>
 *
 *   Adams, Nik<br>
 *   Java Boot Camp Exercise<br>
 *   Instructor: Jean-francois Nepton<br>
 *   Created: Jan 11, 2016
 *
 */

package com.sqa.na.selenium;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

/**
 * XlsTest //ADDD (description of class)
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
public class XlsTest {

	private String browser = "edge";
	private WebDriver driver;
	final String LOGIN_ERROR_TXT = "Please check your username and password. If you still can't log in, contact your Salesforce administrator.";

	@DataProvider(name = "login")
	public Object[][] dp() throws BiffException, IOException {
		final String file = "C:\\Users\\User\\Dropbox\\data2.xls";
		return this.getDataFromExcel(file, "Login", "StartPoint", "EndPoint");

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
		wait.until(ExpectedConditions.textToBePresentInElementLocated(By.id("error"), this.LOGIN_ERROR_TXT));
		// Assert.assertEquals(this.driver.findElement(By.className("loginError")).getText(),
		// "Please check your username and password. If you still can't log in,
		// contact your Salesforce administrator.");

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
			break;
		}

		this.driver.get("http://app.work.com");
	}

	@AfterMethod
	public void tearDown() {
		this.driver.quit();
	}

	private String[][] getDataFromExcel(String xlsFilePath, String sheetName, String startPoint, String endPoint)
			throws BiffException, IOException {
		String[][] tabArray = null;
		// open an workbook
		Workbook workbook = Workbook.getWorkbook(new File(xlsFilePath));

		// open a sheet "Login"
		Sheet sheet = workbook.getSheet(sheetName);
		int startRow, startCol, endRow, endCol, ci, cj;
		// find a cell labeled with "StartPoint"
		Cell tableStart = sheet.findCell(startPoint);
		// get a row of that cell
		startRow = tableStart.getRow() + 1;
		// get a column of that cell
		startCol = tableStart.getColumn() + 1;
		// find a cell#2 labeled with "EndPoint"
		Cell tableEnd = sheet.findCell(endPoint);

		// get a row of that cell
		endRow = tableEnd.getRow();
		// get a row of that cell
		endCol = tableEnd.getColumn();

		tabArray = new String[(endRow - startRow)][(endCol - startCol)];
		for (int i = 0; i < endRow - 2; i++) {
			for (int j = 0; j < endCol - 1; j++) {
				tabArray[i][j] = sheet.getCell(startCol + j, startRow + i).getContents();
			}
		}
		return tabArray;
	}

	private void waitUntil(long time, By locator) {
		WebDriverWait wait = new WebDriverWait(this.driver, time);
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
}

package com.sqa.na.salesforce.test;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.sqa.na.salesforce.pageobject.HomePage;
import com.sqa.na.salesforce.pageobject.LoginPage;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class LoginButtonTest {
	private String browser = "firefox";

	private WebDriver driver;

	@DataProvider(name = "login")
	public Object[][] dp() throws BiffException, IOException {
		final String file = "C:\\Users\\User\\Dropbox\\data2.xls";
		return this.getDataFromExcel(file, "Login", "StartPoint", "EndPoint");

	}

	@Test(dataProvider = "login")
	public void f(String username, String password) {
		HomePage homepage = PageFactory.initElements(this.driver, HomePage.class);
		homepage.navigateToHomePage();
		LoginPage loginpage = homepage.clickOnLoginBtn();
		loginpage.typeIntoUserNameField(username);
		loginpage.typeIntoPasswordField(password);
		loginpage.clickOnLoginBtnStayOnSamePage();
		Assert.assertEquals(loginpage.getLoginErrorActual(), loginpage.getLoginErrorExpected(),
				"The login error does not match");
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

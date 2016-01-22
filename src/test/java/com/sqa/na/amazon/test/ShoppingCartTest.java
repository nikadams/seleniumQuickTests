package com.sqa.na.amazon.test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.sqa.na.amazon.pageobject.HomePage;
import com.sqa.na.amazon.pageobject.LoginPage;
import com.sqa.na.webdriver.util.Driver;

public class ShoppingCartTest {
	private WebDriver driver;
	// private HomePage homepage = PageFactory.initElements(this.driver,
	// HomePage.class);

	@BeforeMethod
	public void setup() {
		this.driver = Driver.driverInit("firefox");
	}

	@AfterMethod
	public void tearDown() {

		this.driver.quit();
	}

	@Test
	public void testAddItems() {
		HomePage homepage = PageFactory.initElements(this.driver, HomePage.class);

		homepage.navigateToHomePage();
		LoginPage loginPage = homepage.clickOnLoginBtn();
		loginPage.typeIntoEmailField("nikardoadams@yahoo.com");
		loginPage.typeIntoPasswordField("temp4now");
		loginPage.clickSignInBtn();
		homepage.typeSearchForItem("head first java");
		homepage.clickSearchBtn();
		homepage.clickItemOnPageLink("Head First Java, 2nd Edition");
		homepage.clickAddToCartBtn();
		homepage.typeSearchForItem("Selenium Testing");
		homepage.clickSearchBtn();
		homepage.clickItemOnPageLink("Selenium Testing Tools Cookbook");
		homepage.clickAddToCartBtn();
		homepage.typeSearchForItem("testng");
		homepage.clickSearchBtn();
		homepage.clickItemOnPageLink("The Testing");
		homepage.clickAddToCartBtn();
		homepage.clickCartBtn();

		homepage.clickLogOut();
	}

}

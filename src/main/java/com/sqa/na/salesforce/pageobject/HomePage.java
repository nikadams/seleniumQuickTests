/**
 *   File Name: HomePage.java<br>
 *
 *   Adams, Nik<br>
 *   Java Boot Camp Exercise<br>
 *   Instructor: Jean-francois Nepton<br>
 *   Created: Jan 14, 2016
 *
 */

package com.sqa.na.salesforce.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * HomePage //ADDD (description of class)
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
public class HomePage {
	private WebDriver driver;
	@FindBy(id = "button-login")
	// @FindBy(how = How.ID, using = "button-login")
	private WebElement loginBtn;

	public HomePage(WebDriver driver) {
		this.driver = driver;
	}

	// return the LoginPage object since it changes to a different page
	public LoginPage clickOnLoginBtn() {
		this.loginBtn.click();

		// need to return the new page object if staying on same page then use
		// 'return this';
		return PageFactory.initElements(this.driver, LoginPage.class);
	}

	public HomePage navigateToHomePage() {
		this.driver.get("http://www.salesforce.com/sales-cloud/sales-performance-management.jsp");
		return this;
	}
}

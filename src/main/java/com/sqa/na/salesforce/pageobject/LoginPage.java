/**
 *   File Name: LoginPage.java<br>
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
 * LoginPage //ADDD (description of class)
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
public class LoginPage {
	private WebDriver driver;

	@FindBy(id = "Login")
	private WebElement loginBtn;

	@FindBy(id = "error")
	private WebElement loginErrorTxt;

	@FindBy(id = "password")
	private WebElement passwordInputField;

	@FindBy(id = "signup_link")
	private WebElement signUpLink;

	@FindBy(id = "username")
	private WebElement userNameInputField;

	final String LOGIN_ERROR_MSG = "Please check your username and password. If you still can't log in, contact your Salesforce administrator.";

	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}

	public AccountPage clickOnLoginBtnAndNavigateInsideApp() {
		this.loginBtn.click();
		return PageFactory.initElements(this.driver, AccountPage.class);
	}

	public LoginPage clickOnLoginBtnStayOnSamePage() {
		this.loginBtn.click();
		return this;
	}

	public TryForFreePage clickTryForFreeLink() {
		this.signUpLink.click();
		return PageFactory.initElements(this.driver, TryForFreePage.class);
	}

	public String getLoginErrorActual() {
		return this.loginErrorTxt.getText();
	}

	public String getLoginErrorExpected() {
		return this.LOGIN_ERROR_MSG;
	}

	public LoginPage typeIntoPasswordField(String password) {
		this.passwordInputField.sendKeys(password);
		return this;
	}

	public LoginPage typeIntoUserNameField(String username) {
		this.userNameInputField.sendKeys(username);
		return this;
	}

}

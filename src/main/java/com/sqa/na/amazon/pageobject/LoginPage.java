/**
 *   File Name: LoginPage.java<br>
 *
 *   Adams, Nik<br>
 *   Java Boot Camp Exercise<br>
 *   Instructor: Jean-francois Nepton<br>
 *   Created: Jan 21, 2016
 *
 */

package com.sqa.na.amazon.pageobject;

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

	@FindBy(id = "ap_email")
	private WebElement emailField;

	@FindBy(id = "ap_password")
	private WebElement passwordField;

	@FindBy(id = "signInSubmit")
	private WebElement signInBtn;

	public HomePage clickSignInBtn() {
		this.signInBtn.click();
		return PageFactory.initElements(this.driver, HomePage.class);

	}

	public LoginPage typeIntoEmailField(String email) {
		this.emailField.sendKeys(email);
		return this;
	}

	public LoginPage typeIntoPasswordField(String password) {
		this.passwordField.sendKeys(password);
		return this;
	}

}

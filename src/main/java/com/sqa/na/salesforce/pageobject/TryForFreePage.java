/**
 *   File Name: TryForFreePage.java<br>
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

/**
 * TryForFreePage //ADDD (description of class)
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
public class TryForFreePage {

	private WebDriver driver;
	@FindBy(css = "a.facebook")
	private WebElement facebookBtn;
	@FindBy(css = "a.google")
	private WebElement googleBtn;

	@FindBy(css = "a.linkedin")
	private WebElement linkedInBtn;

	public TryForFreePage(WebDriver driver) {
		this.driver = driver;
	}

	public boolean isGoogleBtnDisplayed() {
		return this.googleBtn.isDisplayed();
	}
}

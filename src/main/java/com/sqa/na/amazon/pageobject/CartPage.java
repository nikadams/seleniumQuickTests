/**
 *   File Name: CartPage.java<br>
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
import org.openqa.selenium.support.ui.Select;

/**
 * CartPage //ADDD (description of class)
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
public class CartPage {

	private WebDriver driver;

	public String getElementDropDownText(WebElement element) {
		Select select = new Select(element);
		return select.getFirstSelectedOption().getText();
	}
}

/**
 *   File Name: HomePage.java<br>
 *
 *   Adams, Nik<br>
 *   Java Boot Camp Exercise<br>
 *   Instructor: Jean-francois Nepton<br>
 *   Created: Jan 21, 2016
 *
 */

package com.sqa.na.amazon.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
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

	@FindBy(id = "add-to-cart-button")
	private WebElement addToCartBtn;

	@FindBy(id = "hlb-view-cart-announce")
	private WebElement cartBtn;

	private WebDriver driver;

	@FindBy(css = "a.a-link-normal.s-access-detail-page.a-text-normal[title=Head First Java, 2nd Edition]")
	private WebElement headFirstBookLink;

	@FindBy(id = "nav-link-yourAccount")
	private WebElement loginBtn;

	@FindBy(id = "nav-item-signout")
	private WebElement logoutLink;

	@FindBy(id = "hlb-ptc-btn-native")
	private WebElement ptcBtn;

	@FindBy(css = "input.nav-input")
	private WebElement searchBtn;

	@FindBy(id = "twotabsearchtextbox")
	private WebElement searchField;

	public HomePage(WebDriver driver) {
		this.driver = driver;
	}

	public HomePage clickAddToCartBtn() {
		this.addToCartBtn.click();
		return this;
	}

	public CartPage clickCartBtn() {
		this.cartBtn.click();
		return PageFactory.initElements(this.driver, CartPage.class);
	}

	public HomePage clickItemOnPageLink(String item) {
		By itemLinkLocator = By.linkText(item);
		this.driver.findElement(itemLinkLocator).click();
		return this;
	}

	public HomePage clickLogOut() {
		Actions action = new Actions(this.driver);
		action.moveToElement(this.loginBtn).perform();
		this.logoutLink.click();
		return this;
	}

	public LoginPage clickOnLoginBtn() {

		this.loginBtn.click();

		return PageFactory.initElements(this.driver, LoginPage.class);

	}

	public CheckOutPage clickProceedToCheckOutBtn() {
		this.ptcBtn.click();
		return PageFactory.initElements(this.driver, CheckOutPage.class);

	}

	public HomePage clickSearchBtn() {
		this.searchBtn.click();
		return this;
	}

	public HomePage navigateToHomePage() {
		this.driver.get("http://amazon.com");
		return this;
	}

	public HomePage typeSearchForItem(String item) {
		this.searchField.sendKeys(item);
		return this;
	}

}

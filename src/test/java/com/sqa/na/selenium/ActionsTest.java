package com.sqa.na.selenium;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ActionsTest {

	private String browser = "firefox";
	private WebDriver driver;

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

	}

	@AfterMethod
	public void tearDown() {
		this.driver.quit();
	}

	// @Test
	public void testDraggable() {
		this.driver.get("http://jqueryui.com/draggable/");
		this.driver.switchTo().frame(this.driver.findElement(By.className("demo-frame")));
		new Actions(this.driver).dragAndDropBy(this.driver.findElement(By.id("draggable")), 120, 50).perform();

	}

	// @Test
	public void testDropable() {
		this.driver.get("http://jqueryui.com/droppable/");
		this.driver.switchTo().frame(this.driver.findElement(By.className("demo-frame")));
		Actions actions = new Actions(this.driver);
		actions.dragAndDrop(this.driver.findElement(By.id("draggable")), this.driver.findElement(By.id("droppable")));

		actions.perform();
	}

	// @Test
	public void testResizable() {
		this.driver.get("http://jqueryui.com/resizable/");
		this.driver.switchTo().frame(this.driver.findElement(By.className("demo-frame")));
		Actions actions = new Actions(this.driver);
		actions.dragAndDropBy(this.driver.findElement(By.className("ui-resizable-e")), 120, 0).perform();
		actions.dragAndDropBy(this.driver.findElement(By.className("ui-resizable-s")), 0, 100).perform();
		actions.dragAndDropBy(this.driver.findElement(By.className("ui-resizable-se")), 50, 50).perform();
		actions.perform();

	}

	// @Test
	public void testSelectable() {
		this.driver.get("http://jqueryui.com/selectable/");
		this.driver.switchTo().frame(this.driver.findElement(By.className("demo-frame")));
		Actions actions = new Actions(this.driver);
		List<WebElement> elements = this.driver.findElements(By.cssSelector("ul#selectable *"));

		actions.keyDown(Keys.SHIFT);
		actions.click(elements.get(0));
		actions.dragAndDrop(elements.get(0), elements.get(5));
		// actions.keyDown(elements.get(0), Keys.DOWN);
		actions.perform();

	}

	// @Test
	public void testSlider() {
		this.driver.get("http://jqueryui.com/slider/");
		this.driver.switchTo().frame(this.driver.findElement(By.className("demo-frame")));
		new Actions(this.driver).dragAndDropBy(this.driver.findElement(By.id("slider")), 120, 0).perform();

	}

	@Test
	public void testSortable() {
		this.driver.get("http://jqueryui.com/sortable/");
		this.driver.switchTo().frame(this.driver.findElement(By.className("demo-frame")));
		Actions actions = new Actions(this.driver);
		List<WebElement> elements = this.driver.findElements(By.cssSelector("ul#sortable >li"));
		// actions.click(elements.get(0));
		actions.clickAndHold(elements.get(0));
		actions.moveToElement(elements.get(5));
		// actions.dragAndDrop(elements.get(0), elements.get(4));

		actions.perform();

	}

}

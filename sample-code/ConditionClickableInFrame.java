package service.apm.ui_auto.dataentry.helpers;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;

/*
 * Expected Condition to wait for an element inside a frame to become clickable, 
 * when the frame might be reloaded during the wait
 * 
 * Works around an issue in geckodriver but should work for any driver
 *
 * (C) 2017 IBM, Mikhail Ramendik. Sample code. MIT License.
 */

public class ConditionClickableInFrame implements ExpectedCondition<WebElement> {
	private final By locatorFrame;
	private final By locator;
	

	@Override
	public WebElement apply(WebDriver driver) {
		try {
			driver.switchTo().defaultContent();
			driver.switchTo().frame(driver.findElement(locatorFrame));

			List<WebElement> elems = driver.findElements(locator);
			for (WebElement elem : elems) {
				if (elem.isDisplayed() && elem.isEnabled()) return elem;
			}
			return null;

		} catch (WebDriverException e) {
			return null;
		}
	}

	@Override
	public String toString() {
		return "element located by: " + locator + " in frame located by: " + locatorFrame;
	}

	public ConditionClickableInFrame(By locator, By locatorFrame) {
		this.locator = locator;
		this.locatorFrame = locatorFrame;
	}

}

package service.apm.ui_auto.dataentry.helpers;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;

/*
 * Expected Condition to wait for all elements defined by a locator to become invisible, or none to exist
 *
 * (C) 2017 IBM, Mikhail Ramendik. Sample code. MIT License.
 */

public class ConditionAllInvisible implements ExpectedCondition<Boolean> {
	private final By locator;

	public ConditionAllInvisible(By locator) {
		this.locator = locator;
	}

	@Override
	public Boolean apply(WebDriver driver) {
		List<WebElement> elements = driver.findElements(locator);
		for (WebElement element : elements) {
			try {
				if (element.isDisplayed()) return false;
			} catch ( StaleElementReferenceException sere) {
				// a redraw is in progress
				// while this particular element is no longer visible, it is safest to wait for now
				return false;
			}
		}
		return true;
	}

	@Override
	public String toString() {
		return "all elements located by: " + locator + " to be invisible";
	}

}

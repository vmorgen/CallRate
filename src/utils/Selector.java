package utils;

import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

/**
 * 简介： Selector
 * 
 * @author panyanan
 *
 */
public class Selector extends UiAutomatorTestCase {
	public static UiSelector textSelector(String string) {
		return new UiSelector().text(string);
	}

	public static UiSelector resourceIdSelector(String string) {
		return new UiSelector().resourceId(string);
	}

	public static UiSelector classNameSelector(String string) {
		return new UiSelector().className(string);
	}

	public static UiSelector textMatchs(String string) {
		return new UiSelector().textMatches(string);
	}

	public static UiSelector textContains(String string) {
		return new UiSelector().textContains(string);
	}

	public static UiSelector resourceIdMatchs(String string) {
		return new UiSelector().resourceIdMatches(string);
	}

	public static UiSelector classNameMatches(String string) {
		return new UiSelector().classNameMatches(string);
	}

	public static UiSelector packageNameSelector(String string) {
		return new UiSelector().packageName(string);
	}
}
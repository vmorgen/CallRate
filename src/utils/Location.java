package utils;

import com.android.uiautomator.core.UiCollection;
import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiScrollable;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

/**
 * 简介： UiObject UiScrollable
 * 
 * @author panyanan
 *
 */
public class Location extends UiSelector {

	// UiObject
	public static UiObject textObject(String string) {
		return new UiObject(Selector.textSelector(string));
	}

	public static UiObject textMatchsObject(String string) {
		return new UiObject(Selector.textMatchs(string));
	}

	public static UiObject textContainsObject(String string) {
		return new UiObject(Selector.textContains(string));
	}

	public static UiObject resourceIdObject(String string) {
		return new UiObject(Selector.resourceIdSelector(string));
	}

	public static UiObject resourceIdMatchsObject(String string) {
		return new UiObject(Selector.resourceIdMatchs(string));
	}

	public static UiObject classNameObject(String string) {
		return new UiObject(Selector.classNameSelector(string));
	}

	public static UiObject classNameMatchesObject(String string) {
		return new UiObject(Selector.classNameMatches(string));
	}

	public static UiObject packageNameObject(String string) {
		return new UiObject(Selector.packageNameSelector(string));
	}

	// UiScrollable
	public static UiScrollable textScrollable(String string) {
		return new UiScrollable(Selector.textSelector(string));
	}

	public static UiObject textMatchsScrollable(String string) {
		return new UiScrollable(Selector.textMatchs(string));
	}

	public static UiScrollable resourceIdScrollable(String string) {
		return new UiScrollable(Selector.resourceIdSelector(string));
	}

	public static UiObject resourceIdMatchsScrollable(String string) {
		return new UiScrollable(Selector.resourceIdMatchs(string));
	}

	public static UiScrollable classNameScrollable(String string) {
		return new UiScrollable(Selector.classNameSelector(string));
	}

	public static UiObject classNameMatchsScrollable(String string) {
		return new UiScrollable(Selector.classNameMatches(string));
	}
}

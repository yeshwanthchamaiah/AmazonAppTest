package com.addchart.demo;

import static io.appium.java_client.touch.offset.ElementOption.element;

import org.openqa.selenium.WebElement;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class Utilities {
	
	AndroidDriver<AndroidElement> driver;
	
	public Utilities(AndroidDriver<AndroidElement> driver) {
		this.driver=driver;
	}
	
	public void scrollTo(String classlocator,String country) {
		driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView("+classlocator+"(\""+country+"\"));");
	
	
	}
	
	public void dragNdrop(WebElement source,WebElement destination) {
		TouchAction t = new TouchAction(driver);
	    //longpress(source)/move(destination)//release
//	    t.longPress(longPressOptions().withElement(element(source))).moveTo(element(destination)).release().perform();
	    t.longPress(element(source)).moveTo(element(destination)).release().perform();

	
	
	}
	
	
}

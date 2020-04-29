package com.addchart.demo;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

import pageObjects.LoginPage;
import pageObjects.MenuPage;


/** Perform Change country setting and validate changes

Action
1. Change country setting in App
2. Validate change country settings

Expected
1. Country setting changed
2. Validate changed Country

 **/

public class AmazChgCountryTest extends Base{

	AndroidDriver<AndroidElement> driver;

	String inputCtry="Australia";

	String expCtry="";
	String actCtry="";

	@BeforeTest
	public void setUp() {
		driver = this.getDriver("ApkFile");
	}

	@Test
	public void changeCountrySetting(){

		//performing Change country setting in App

		driver.findElementById("com.amazon.mShop.android.shopping:id/skip_sign_in_button").click();
		driver.findElementById("com.amazon.mShop.android.shopping:id/chrome_action_bar_burger_icon").click();
		driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Settings\"));").click();
		driver.findElementById("com.amazon.mShop.android.shopping:id/drawer_item_title").click();
		driver.findElementByXPath("//android.widget.Button[@text='Country/Region: United States']").click();
		driver.findElementByXPath("//android.widget.RadioButton[@text='Australia Amazon.com.au ']").click();
		driver.findElementByXPath("//android.widget.Button[@text='Done']").click();

		//Validate the country changed settings
		if(inputCtry.contains("India")) {
			actCtry="Country/Region: India";
			expCtry=checkCountryChange(actCtry);
			Assert.assertEquals(actCtry,expCtry);			
		}
		else if(inputCtry.contains("Australia")) {
			actCtry="Country/Region: Australia";
			expCtry=checkCountryChange(actCtry);
			Assert.assertEquals(actCtry,expCtry);
		}		
		else Assert.assertTrue(false, "Invlaid country input");

	}


	public String checkCountryChange(String inputCtry)
	{
		driver.findElementById("com.amazon.mShop.android.shopping:id/chrome_action_bar_burger_icon").click();
		driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Settings\"));").click();
		driver.findElementById("com.amazon.mShop.android.shopping:id/anp_drawer_item").click();
		expCtry=driver.findElementByXPath("//android.widget.Button[@text='"+actCtry+"']").getText();

		return expCtry;

	}

	@AfterTest 
	public void tearDown(){
		if(driver!=null) {
			driver.quit();
		}
	}

}

package com.addchart.demo;

/** Perform Search,add item to cart and validate item

Prerequisite: Login Successful

Action
1. Perform search.
2. Add item to cart
3. Validate the added item

Expected
1. Search Result set returned with some records
2. Item is added successfully to cart
3. checkout item must same as Item added to the cart earlier

 **/

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Keyboard;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MenuPage;

@Test
public class AmazAddToCartTest extends Base{

	private AndroidDriver<AndroidElement> driver;

	@BeforeTest
	public void setUp() {
		driver = this.getDriver("ApkFile");
	}

	@Test(testName="Add To Cart", dataProvider = "LoginUser",dataProviderClass = TestData.class)
	public void doLogin(String userID,String pwd) throws IOException {
		
		try {
			if(this.loginCheck(driver,userID,pwd))
			{
				//search items
				driver.findElementByClassName("android.widget.ImageView").click();
				driver.findElementByXPath("//android.widget.TextView[@text='Home']").click();
				driver.findElementById("com.amazon.mShop.android.shopping:id/rs_search_src_text").sendKeys("Almond 1kg");		
				driver.pressKey(new KeyEvent(AndroidKey.ENTER));		

				//Add item to cart
				driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(textContains(\"Miltop\"));").click();		
				driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(textContains(\"One-time\"));").click();		
				driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Add to Cart\"));").click();

				//Validate the added item
				driver.findElementById("com.amazon.mShop.android.shopping:id/chrome_action_bar_cart_image").click();		
				String expSeller="by Miltop";
				String actSellet=driver.findElementByXPath("//android.view.View[@text='by Miltop']").getText();		
				Assert.assertEquals(expSeller, actSellet); 
			}

			else
				Assert.assertTrue(false, "Login Failed");

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	@AfterTest 
	public void tearDown(){
		if(driver!=null) {
			driver.quit();
		}
	}
}
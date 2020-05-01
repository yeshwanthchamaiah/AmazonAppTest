package com.addchart.demo;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

public class AmazonAppTestAll extends Base{
	
	private AndroidDriver<AndroidElement> driver;

	@BeforeTest
	public void setUp() {
		driver = this.getDriver("ApkFile");
	}
	
	@Test(dataProvider = "LoginUser",dataProviderClass = TestData.class, priority=1)
	public void doLogin(String userID,String pwd) {
		try {
			if(this.loginCheck(driver,userID,pwd))
				Assert.assertTrue(true, "Login Success");
			else
				Assert.assertTrue(false, "Login Failed");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
	
	@Test(priority=2)
	public void changeCountrySetting(){
		
		String inputCtry="India";

		String expCtry="";
		String actCtry="";

		//performing Change country setting in App

		driver.findElementById("com.amazon.mShop.android.shopping:id/chrome_action_bar_burger_icon").click();
		driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Settings\"));").click();
		driver.findElementById("com.amazon.mShop.android.shopping:id/drawer_item_title").click();
		driver.findElementByXPath("//android.widget.Button[@text='Country/Region: United States']").click();
		driver.findElementByXPath("//android.widget.RadioButton[@text='India Amazon.in ']").click();
		driver.findElementByXPath("//android.widget.Button[@text='Done']").click();

		//Validate the country changed settings
	/*	if(inputCtry.contains("India")) {
			actCtry="Country/Region: India";
			expCtry=checkCountryChange(actCtry);
			Assert.assertEquals(actCtry,expCtry);			
		}	
		else Assert.assertTrue(false, "Invlaid country input");*/
		

	}

	@Test(priority=3)
	public void addToCart(String userID,String pwd) throws IOException {
		//if(this.loginCheck(driver,userID,pwd))
		//{
			//search items
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


	public String checkCountryChange(String inputCtry)
	{
		driver.findElementById("com.amazon.mShop.android.shopping:id/chrome_action_bar_burger_icon").click();
		driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Settings\"));").click();
		driver.findElementById("com.amazon.mShop.android.shopping:id/anp_drawer_item").click();
		String ctrystr=driver.findElementByXPath("//android.widget.Button[@text='"+inputCtry+"']").getText();
		driver.findElementByXPath("//android.widget.Button[@text='Done']").click();
		
		return ctrystr;
				

	}
	
	@Test(priority=4)
	public void screenRotationCheck(String userID,String pwd ) throws IOException, InterruptedException {
		
		System.out.println("Current screen orientation Is : " +driver.getOrientation());
		System.out.println("Changing screen Orientation to LANDSCAPE.");
		
		driver.findElementByClassName("android.widget.ImageView").click();
		driver.findElementByXPath("//android.widget.TextView[@text='Home']").click();
		driver.rotate(org.openqa.selenium.ScreenOrientation.LANDSCAPE);
		Thread.sleep(2000);
		driver.findElementById("com.amazon.mShop.android.shopping:id/rs_search_src_text").sendKeys("Almond 1kg");		
		driver.pressKey(new KeyEvent(AndroidKey.ENTER));		
		
		driver.rotate(org.openqa.selenium.ScreenOrientation.PORTRAIT);
		
	}


	@AfterTest 
	public void tearDown(){
		if(driver!=null) {
			System.out.println("Quitting Driver");
			driver.quit();
		}
	}


}

package com.addchart.demo;

/** Perform screen rotation, Search,add item to cart and validate item

Action
1. Perform screen rotation

Expected
1. Screen rotation successful

 **/

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MenuPage;

@Test
public class ScnRotationTest extends Base{

	AndroidDriver<AndroidElement> driver;

	@BeforeTest
	public void setUp() {
		driver = this.getDriver("ApkFile");
	}
	
	@Test(dataProvider = "LoginUser",dataProviderClass = TestData.class)
	public void screenRotationCheck(String userID,String pwd ) throws IOException {
		
		System.out.println("Current screen orientation Is : " +driver.getOrientation());
		System.out.println("Changing screen Orientation to LANDSCAPE.");
		driver.rotate(org.openqa.selenium.ScreenOrientation.LANDSCAPE);
		
		try {
			if(this.loginCheck(driver, userID, pwd))
				Assert.assertTrue(true, "Login Success");
			else
				Assert.assertTrue(false, "Login Failed");
							
			System.out.println("Changing screen Orientation to PORTRAIT.");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.rotate(org.openqa.selenium.ScreenOrientation.PORTRAIT);
		
	}
	
	@AfterTest 
	public void tearDown(){
		if(driver!=null) {
			driver.quit();
		}
	}
}




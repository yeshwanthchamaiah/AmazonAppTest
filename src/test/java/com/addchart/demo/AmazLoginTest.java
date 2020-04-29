package com.addchart.demo;

import org.testng.Assert;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MenuPage;

/** Perform Change country setting and validate changes

Action
1. Validate User Login

Expected
1. User Login Successful

 **/

public class AmazLoginTest extends Base{

	private AndroidDriver<AndroidElement> driver;

	@BeforeTest
	public void setUp() {
		driver = this.getDriver("ApkFile");
	}

	@Test(testName="Login Test", dataProvider = "LoginUser",dataProviderClass = TestData.class)
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

	@AfterTest 
	public void tearDown(){
		if(driver!=null) {
			driver.quit();
		}
	}

}

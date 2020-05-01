package com.addchart.demo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;

public abstract class Base {
	
	public AndroidDriver<AndroidElement> getDriver(String appName) {
		AndroidDriver<AndroidElement> driver = null;
		FileInputStream fs = null;
		Properties pro=new Properties();
		try {
			fs = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\config.properties");
			pro.load(fs);

			String apkName=pro.getProperty(appName);
			String dName=pro.getProperty("emulatorName");

			File appPath=new File(System.getProperty("user.dir")+"\\src\\main\\java");
			File app=new File(appPath,apkName);
			DesiredCapabilities cap = new DesiredCapabilities();

			cap.setCapability(MobileCapabilityType.DEVICE_NAME, dName);
			//cap.setCapability(MobileCapabilityType.DEVICE_NAME, "Android Device"); //Real Device
			
			cap.setCapability(MobileCapabilityType.AUTOMATION_NAME,"uiautomator2");
			cap.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT,40);
			cap.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
			driver=new AndroidDriver<AndroidElement>(new URL("http://127.0.0.1:4723/wd/hub"), cap);
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		} catch (FileNotFoundException e) {
			System.out.println("Error while reading Config File");
			e.printStackTrace();
		}catch (MalformedURLException e) {
			System.out.println("Error while creating Android Driver");
			e.printStackTrace();
		}catch(IOException e) {
			System.out.println("Error while loading properties from config file");
			e.printStackTrace();
		}
		return driver;
	}


	/*
	 * public void getScreenshot(String s) throws IOException { File scrfile=
	 * ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	 * FileUtils.copyFile(scrfile,new
	 * File(System.getProperty("user.dir")+"\\sceenshots"+"\\"+s+".png"));
	 * 
	 * }
	 */	
	
	public boolean loginCheck(AndroidDriver<AndroidElement> driver, String userID,String pwd) throws InterruptedException {
		
		driver.findElementByXPath("//android.widget.Button[@text='Already a customer? Sign in']").click();
		//driver.findElementById("com.amazon.mShop.android.shopping:id/sign_in_button").click();
		Thread.sleep(2000);
		driver.findElementByClassName("android.widget.EditText").sendKeys(userID);
		driver.findElementByXPath("//android.widget.Button[@text='Continue']").click();
		Thread.sleep(2000);
		driver.findElementByClassName("android.widget.EditText").sendKeys(pwd);
		driver.findElementByXPath("//android.widget.Button[@text='Sign-In']").click();
		Thread.sleep(2000);
		driver.findElementById("com.amazon.mShop.android.shopping:id/chrome_action_bar_burger_icon").click();
		String loginUser = driver.findElementByClassName("android.widget.TextView").getText();
		driver.findElementByXPath("//android.widget.TextView[@text='Home']").click();
		return loginUser.contains("Dillip");
	}
	
	

	/*@BeforeTest
	public void killAllNodes() throws IOException, InterruptedException
	{
		//taskkill /F /IM node.exe
		Runtime.getRuntime().exec("taskkill /F /IM node.exe");
		Thread.sleep(3000);

	}*/

}

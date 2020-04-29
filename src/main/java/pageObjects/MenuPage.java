package pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class MenuPage {
	
	
	public MenuPage(AndroidDriver<AndroidElement> driver) {
			PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@AndroidFindBy(id = "com.amazon.mShop.android.shopping:id/chrome_action_bar_burger_icon")
	public WebElement  labelmenuSel;
		
	@AndroidFindBy(id = "com.amazon.mShop.android.shopping:id/drawer_item_title")
	public WebElement  labelcntryTitle;
	
	@AndroidFindBy(id = "com.amazon.mShop.android.shopping:id/anp_drawer_item")
	public WebElement  labelcntryitm;
	
	@AndroidFindBy(xpath = "//android.widget.Button[@text='Country/Region: United States']")
	public WebElement  btnRegion;
	
	@AndroidFindBy(xpath = "//android.widget.Button[@text='Done']")
	public WebElement  btnDone;
	
	@AndroidFindBy(xpath = "//android.widget.RadioButton[@text='India Amazon.in ']")
	public WebElement  rbtnRegionIn;
	
}

package PageObject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class AmazonCheckOutPage {

	AndroidDriver<AndroidElement> driver;
	WebDriverWait waitDriver;

	public AmazonCheckOutPage(AndroidDriver<AndroidElement> driver){
		this.driver=driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@FindBy(xpath="//*[@resource-id='sc-item-Cd7324cf5-d597-48c5-a1d4-96dbecac79db']//android.widget.ListView/preceding-sibling::android.view.View[1]")
	private WebElement productNameText;

	@FindBy(xpath="//*[@resource-id='sc-item-Cd7324cf5-d597-48c5-a1d4-96dbecac79db']//android.widget.ListView/android.view.View")
	private WebElement productPriceText;

	public WebElement getProductPriceText() {
		return productPriceText;	
	}

	public WebElement getProductNameText() {
		return productNameText;	
	}

	public void waitForPageToLoad() {
		waitDriver = new WebDriverWait(driver, 50);
		waitDriver.until(ExpectedConditions.visibilityOf(getProductNameText()));
	}
}

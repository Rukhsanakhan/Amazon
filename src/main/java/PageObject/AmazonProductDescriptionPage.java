package PageObject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class AmazonProductDescriptionPage {

	AndroidDriver<AndroidElement> driver;
	WebDriverWait waitDriver;

	public AmazonProductDescriptionPage(AndroidDriver<AndroidElement> driver){
		this.driver=driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@FindBy(xpath="//*[@resource-id='title_feature_div']/android.view.View")
	private WebElement productNameText;

	@FindBy(xpath="//*[@resource-id='atfRedesign_priceblock_priceToPay']/android.widget.EditText")
	private WebElement productPriceText;

	@FindBy(xpath="//*[@resource-id='add-to-cart-button']")
	private WebElement addToCartButton;

	@FindBy(xpath="//*[@text='Proceed to checkout']")
	private WebElement proceedToCheckoutButton;

	@FindBy(xpath="//*[@resource-id='com.amazon.mShop.android.shopping:id/action_bar_cart_image']")
	private WebElement addToCartIcon;

	public WebElement getaddToCartIcon() {
		return addToCartIcon;	
	}

	public WebElement getProductNameText() {
		return productNameText;	
	}

	public WebElement getAddToCartButton() {
		return addToCartButton;	
	}
	public WebElement getProceedToCheckoutButton() {
		return proceedToCheckoutButton;	
	}

	public WebElement getProductPriceText() {
		return productPriceText;	
	}

	public void waitForPageToLoad() {
		waitDriver = new WebDriverWait(driver, 50);
		waitDriver.until(ExpectedConditions.visibilityOf(getProductNameText()));
	}
}

package PageObject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class AmazonLandingPage {

	AndroidDriver<AndroidElement> driver;
	WebDriverWait waitDriver;

	public AmazonLandingPage(AndroidDriver<AndroidElement> driver) {
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@FindBy(xpath = "//*[@text='Already a customer? Sign in']")
	private WebElement alreadyCustomerSignInButton;

	@FindBy(xpath = "//*[@text='Sign in to your account']")
	private WebElement signInToYouraccountHeader;

	// if using find elements use List<webelement> as a return type
	public WebElement getAlreadyCustomerSignInButton() {
		return alreadyCustomerSignInButton;
	}

	public WebElement getSignInToYouraccountHeader() {
		return signInToYouraccountHeader;
	}

	public void waitForPageToLoad() {
		waitDriver = new WebDriverWait(driver, 50);
		waitDriver.until(ExpectedConditions.visibilityOf(getSignInToYouraccountHeader()));
	}

}

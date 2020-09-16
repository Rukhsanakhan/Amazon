package PageObject;

import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.amazon.BasePage;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class AmazonLoginPage {

	AndroidDriver<AndroidElement> driver;
	WebDriverWait waitDriver;

	public AmazonLoginPage(AndroidDriver<AndroidElement> driver) {
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@FindBy(xpath = "//*[@text='Welcome']")
	private WebElement welcomeHeader;

	@FindBy(xpath = "//*[@resource-id='ap_email_login']")
	private WebElement emailTextBox;

	@FindBy(xpath = "//*[@resource-id='ap_password']")
	private WebElement passwordTextBox;

	@FindBy(xpath = "//*[@resource-id='signInSubmit']")
	private WebElement loginButton;

	@FindBy(xpath = "//*[@text='Continue']")
	private WebElement continueButton;

	public WebElement getWelcomeHeader() {
		return welcomeHeader;
	}

	public WebElement getEmailTextBox() {
		return emailTextBox;
	}

	public WebElement getPasswordTextBox() {
		return passwordTextBox;
	}

	public WebElement getLoginButton() {
		return loginButton;
	}

	public WebElement getContinueButton() {
		return continueButton;
	}

	public void waitForPageToLoad() {
		waitDriver = new WebDriverWait(driver, 50);
		waitDriver.until(ExpectedConditions.visibilityOf(getEmailTextBox()));
	}
	
	public void invoke() throws IOException {
	  AmazonLandingPage landingPage = new AmazonLandingPage(driver);
	  landingPage.waitForPageToLoad();
	  landingPage.getAlreadyCustomerSignInButton().click();
	  waitForPageToLoad();	
	}
}

package PageObject;

import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class AmazonHomePage {

	AndroidDriver<AndroidElement> driver;
	WebDriverWait waitDriver;

	public AmazonHomePage(AndroidDriver<AndroidElement> driver){
		this.driver=driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@FindBy(xpath="//*[@resource-id='com.amazon.mShop.android.shopping:id/chrome_action_bar_home_logo']")
	private WebElement amazonHeader;

	@FindBy(xpath="//*[@text='Search']")
	private WebElement searchTextBox;

	@FindBy(xpath="//*[@resource-id='com.amazon.mShop.android.shopping:id/iss_search_dropdown_item_text_layout']")
	private WebElement firstElement;
	
	public WebElement getWelcomeHeader() {
		return amazonHeader;	
	}

	public WebElement getSearchTextBox() {
		return searchTextBox;	
	}

	public WebElement getFirstElement() {
		return firstElement;	
	}

	public void waitForPageToLoad() {
		waitDriver = new WebDriverWait(driver, 50);
		waitDriver.until(ExpectedConditions.visibilityOf(getSearchTextBox()));
	}

	public void invoke(String uname,String pwd) throws IOException, InterruptedException {
		AmazonLoginPage loginPage = new AmazonLoginPage(driver);
		loginPage.invoke();
		loginPage.getEmailTextBox().sendKeys(uname);
		loginPage.getContinueButton().click();
		WebDriverWait waitDriver = new WebDriverWait(driver, 30);
		waitDriver.until(ExpectedConditions.visibilityOf(loginPage.getPasswordTextBox()));

		loginPage.getPasswordTextBox().sendKeys(pwd); Thread.sleep(3000);
		loginPage.getLoginButton().click();
		waitForPageToLoad();
	}
}

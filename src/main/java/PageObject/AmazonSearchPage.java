package PageObject;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class AmazonSearchPage {

	AndroidDriver<AndroidElement> driver;
	WebDriverWait waitDriver;

	public AmazonSearchPage(AndroidDriver<AndroidElement> driver){
		this.driver=driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@FindBy(xpath="//*[ends-with(text, 'Results')]")
	private WebElement resultsText;

	@FindBy(xpath="//*[@resource-id='com.amazon.mShop.android.shopping:id/item_title']") 
	List<WebElement> itemList;

	@FindBy(xpath="//*[@resource-id='com.amazon.mShop.android.shopping:id/item_title']/following::android.widget.TextView[2]")
	List<WebElement> itemPriceList;

	public WebElement getResultsText(){ 
		return resultsText; 
	}
	
	public List<WebElement> getItemList(){ 
		return itemList; 
	}
	
	public List<WebElement> getItemPriceList(){ 
		return itemPriceList; 
	}

	public void waitForPageToLoad() {
		waitDriver = new WebDriverWait(driver, 30);
		waitDriver.until(ExpectedConditions.visibilityOf(getItemList().get(1)));
	}

	public WebElement getItemFromSearchList(String itemName) {
		int size = getItemList().size();
		int temp=0;
		for(int i=0;i<size;i++)
		{
			if(getItemList().get(i).getText().equalsIgnoreCase(itemName))
			{
				temp=i;
				break;
			}
		}
		return getItemList().get(temp);
	}
	
	public String getPriceForItemFromSearchList(String itemName) {
		int size = getItemList().size();
		int temp=0;
		for(int i=0;i<size;i++)
		{
			if(getItemList().get(i).getText().equalsIgnoreCase(itemName))
			{
				temp=i;
				break;
			}
		}
		String price = getItemPriceList().get(temp).getText(); 
		String[] priceValues = price.split("[\\s@&.?$+-]+");
		return priceValues[0].substring(2);	 
	}
}

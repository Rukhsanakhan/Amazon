package com.amazon;


import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.amazon.BasePage;
import PageObject.AmazonCheckOutPage;
import PageObject.AmazonHomePage;
import PageObject.AmazonProductDescriptionPage;
import PageObject.AmazonSearchPage;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import resources.TestData;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class AppTest extends BasePage
{
	
	public static Logger log = LogManager.getLogger(BasePage.class.getName());

	@BeforeTest
	public void StartNodes() throws IOException, InterruptedException{
		//service=startServer();
	}
	
	@Test(dataProvider="data-provider",dataProviderClass=TestData.class) 
	public void amzonProductPerchase(String uname,String pass,String textToBeSearched,String productToBeSelected) throws IOException, InterruptedException
	{
		
		  AndroidDriver<AndroidElement> driver = BasePage.capabilities(); String
		  searchPagePrice,descriptionPagePrice,checkoutPagePrice,searchPageProductName,
		  descriptionPageProductName,checkoutPageProductName;
		  
		  AmazonHomePage amazonHomePage =new AmazonHomePage(driver);
		  amazonHomePage.invoke(uname, pass);
		  log.info("Amazon Home page is displayed");
		  amazonHomePage.getSearchTextBox().click();
		  amazonHomePage.getSearchTextBox().sendKeys(textToBeSearched);
		  amazonHomePage.getFirstElement().click();
		  log.info("Searched item is selected");
		  
		  AmazonSearchPage amazonSearchPage =new AmazonSearchPage(driver);
		  amazonSearchPage.waitForPageToLoad(); 
		  log.info("Searched Page is displayed");
		  Utilities utilities = new
		  Utilities(driver); Utilities.scrollToText(productToBeSelected);
		  searchPagePrice
		  =amazonSearchPage.getPriceForItemFromSearchList(productToBeSelected);
		  searchPageProductName=amazonSearchPage.getItemFromSearchList(
		  productToBeSelected). getText();
		  amazonSearchPage.getItemFromSearchList(productToBeSelected).click();
		  log.info(productToBeSelected + "is selected");
		  AmazonProductDescriptionPage amazonProductDescriptionPage = new
		  AmazonProductDescriptionPage(driver);
		  amazonProductDescriptionPage.waitForPageToLoad(); 
		  log.info("Amazon product description page is displayed");
		  descriptionPageProductName
		  = amazonProductDescriptionPage.getProductNameText().getText();
		  Assert.assertTrue(descriptionPageProductName.equalsIgnoreCase(
		  searchPageProductName),
		  "Search page product name is same as desscription product  name");
		  log.info("Search page product name is same as desscription product  name");
		  Utilities.scrollToText(amazonProductDescriptionPage.getProductPriceText().
		  getText());
		  descriptionPagePrice=amazonProductDescriptionPage.getProductPriceText().
		  getText().substring(7);
		  Assert.assertTrue(descriptionPagePrice.equals(searchPagePrice)
		  ,"Search page product price is same as desscription product  price");
		  log.info("Search page product price is same as desscription product  price");
		  Utilities.scrollToText("Qty:");
		  amazonProductDescriptionPage.getAddToCartButton().click();
		  amazonProductDescriptionPage.getaddToCartIcon().click();
		  log.info("Items added to the cart");
		  AmazonCheckOutPage AmazonCheckOutPage = new AmazonCheckOutPage(driver);
		  AmazonCheckOutPage.waitForPageToLoad(); 
		  log.info("Amazon checkout page is displayed");
		  checkoutPageProductName =
		  AmazonCheckOutPage.getProductNameText().getText(); checkoutPagePrice =
		  AmazonCheckOutPage.getProductPriceText().getText().replaceAll("\u00A0", "");
		  int size = checkoutPagePrice.length(); String checkoutPageProductName1=
		  checkoutPageProductName.substring(0, checkoutPageProductName.length()-3);
		  Assert.assertTrue(searchPageProductName.contains(checkoutPageProductName1),
		  "Checkout page product name is same as desscription product  name");
		  log.info("Checkout page product name is same as desscription product  name");
		  Assert.assertTrue(searchPagePrice.equals(checkoutPagePrice.substring(0,
		  size-3)),"Search page product price is same as desscription product  price");
		  log.info("Search page product price is same as desscription product  price");

		
		
	}
	
	@AfterTest
	public void killAllNodes() throws IOException, InterruptedException{
		// kill all nodes for appium server instance
		Runtime.getRuntime().exec("taskkill /F /IM node.exe");
		Thread.sleep(3000);	
	}
	
}

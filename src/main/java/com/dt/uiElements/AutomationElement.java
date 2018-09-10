package com.dt.uiElements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.dt.core.DriverFactory;

;
public class AutomationElement extends DriverFactory {
	String locatorString=new String();
	String locatoR=new String();
	static String actualLocatorStringXpath;
	static ThreadLocal<WebDriver> intance = new ThreadLocal<WebDriver>();
	public AutomationElement(String locator)
	{
		locatorString=locator;
	}
	public AutomationElement()
	{
		
	}
	public String getLocatorValue()
	{
		if(locatorString.contains("xpath"))
		{
			 locatoR=locatorString.split("path=")[1];
		}
		else
		{
			locatoR="";
		}
		actualLocatorStringXpath=locatoR;
		return locatoR;
	}
	private WebDriver getDriverIntance()
	{
		if(DriverFactory.class.isAssignableFrom(this.getClass()))
		{
			return getWebDriver();
		}
		else
		{
		return null;
		}
	} 
	private By getElementXpath(String locator)
	{
		
			return By.xpath(locator);
		
	}
	public AutomationElement waitUntilElementPresent()
	{
		try{
			WebDriverWait waitobj=new WebDriverWait(getDriverIntance(),40);
			waitobj.until(ExpectedConditions.visibilityOfElementLocated(getElementXpath(getLocatorValue()))); 
			log(true,"Wait until Element Present located by :"+getLocatorValue().toString());
		}
		catch(Exception e)
		{
			e.printStackTrace();
			log(false,"Wait Failed for elemet located by :"+getLocatorValue().toString());
		}
		intance.set(getDriverIntance());
		return new AutomationElement();
	}
	public AutomationElement verifyElementPresent()
	{
		try {
			if(intance.get().findElement(getElementXpath(actualLocatorStringXpath)).isDisplayed())
			log(true, "Verify Element :"+getLocatorValue().toString());
			}
			catch(Exception e){
				log(false, "Verification Failed for Element :"+getLocatorValue().toString());}	
		return new AutomationElement(actualLocatorStringXpath);
	}
	public AutomationElement click()
	{
		try{intance.get().findElement(getElementXpath(actualLocatorStringXpath)).click();
		log(true,"Click Element: "+actualLocatorStringXpath);}
		catch(Exception e )
		{
			e.printStackTrace();
			log(null, "Click Failed for Element :"+actualLocatorStringXpath);
		}
		return new AutomationElement(actualLocatorStringXpath);
	}
	public AutomationElement clearAndSendKeys(String text)
	{
		try{
			getDriverIntance().findElement(getElementXpath(getLocatorValue())).clear();
			getDriverIntance().findElement(getElementXpath(getLocatorValue())).sendKeys(text);
			log(true,"Clear Element: "+getLocatorValue()+" Send Keys :"+text);}
			catch(Exception e )
			{
				e.printStackTrace();
				log(null, "ClearAndSendKeys Failed for Element :"+getLocatorValue().toString());
			}
		String locator=getLocatorValue();
		return new AutomationElement(locator);
	}
	public AutomationElement verifyTextExistsOnElement(String text)
	{
		try{
			if(intance.get().findElement(getElementXpath(actualLocatorStringXpath)).getText().contains(text))
			log(true,"Verify Text Exists on: "+getLocatorValue()+" Text :"+text);
			else
				log(false,"Verify Text Exists on: "+actualLocatorStringXpath+" Text :"+text);	
			}
			catch(Exception e )
			{
				e.printStackTrace();
				log(null, "verifyTextExistsOnElement Failed for Element :"+actualLocatorStringXpath);
			}
		String locator=getLocatorValue();
		return new AutomationElement(locator);
	}
}

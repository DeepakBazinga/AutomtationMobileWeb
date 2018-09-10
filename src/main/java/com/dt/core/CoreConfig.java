package com.dt.core;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.SystemClock;

import io.appium.java_client.remote.MobileCapabilityType;

public class CoreConfig {
	private String configFilePath=System.getProperty("user.dir")+"/resources/Config/config.properties";
	private String browserName;
	private String platformName;
	private String platformVersion;
	private String deviceName;
	private String UDID;
	ThreadLocal<RemoteWebDriver> threadLocalDriver = new ThreadLocal<RemoteWebDriver>();
	private String url;
	private RemoteWebDriver driver;
    public  RemoteWebDriver setConfig()
    {
    	int fileFlag=0;
    	try{
    		File f = new File(configFilePath);
    	
    	if(f.exists() && !f.isDirectory()) { 
    	    fileFlag=1;
    	}
    	}
    	catch(Exception e )
    	{
    		e.printStackTrace();
    	}
    	if(fileFlag==1)
    	{
    		try {
    			@SuppressWarnings("resource")
				BufferedReader br = new BufferedReader(new FileReader(configFilePath));
    		    String line;
    		    while ((line=br.readLine()) != null) 
    		    {
    		    	if(line.contains("platformName"))
    		    		platformName=line.split("\\=")[1];
    		    	else if(line.contains("platformVersion"))
    		    		platformVersion=line.split("\\=")[1];
    		    	else if(line.contains("deviceName"))
    		    		deviceName=line.split("\\=")[1];
    		    	else if(line.contains("deviceName"))
    		    		deviceName=line.split("\\=")[1];
    		    	else if(line.contains("URL"))
    		    		url=line.split("\\=")[1];
    		    	else if(line.contains("browserName"))
    		    		browserName=line.split("\\=")[1];
    		    }
    		}
    		catch (Exception e)
		    {
    			e.printStackTrace();
		    }
    		try {
    			System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"/resources/chromedriver");
				RemoteWebDriver remoteDriver = new RemoteWebDriver(new URL("http://127.0.0.1:4723/wd/hub"), createCapabilities());
				driver=remoteDriver;
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	else
    	{
    		
    	}
    	threadLocalDriver.set(driver);
    	return threadLocalDriver.get();
    }
    public String getURL()
    {
    	return "http://"+url;
    }
    public DesiredCapabilities createCapabilities()
    {
    	DesiredCapabilities desiredCaps= new DesiredCapabilities();
    	desiredCaps.setCapability(MobileCapabilityType.PLATFORM_NAME, platformName);
    	desiredCaps.setCapability(MobileCapabilityType.PLATFORM_VERSION, platformVersion);
    	desiredCaps.setCapability(MobileCapabilityType.BROWSER_NAME, browserName);
    	desiredCaps.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
    	return desiredCaps;
    }
}

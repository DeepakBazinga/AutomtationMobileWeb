package TestCases;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.dt.core.FunctionLibrary;

import PageObjects.AuthorizationPage;
import PageObjects.HomePage;
import PageObjects.SKUDetailPage;

/**
 * Unit test for simple App.
 */
public class AppTest extends FunctionLibrary 
{
	@BeforeTest
	public void setup()
	{
		FunctionLibrary lib=new FunctionLibrary();
		lib.startBrowserNormal();
	}
	@Test
	public void runTest()
	{	
		AuthorizationPage auth=new AuthorizationPage();
		HomePage home=new HomePage();
		SKUDetailPage SKU=new SKUDetailPage();
		auth.wwwLabel.waitUntilElementPresent();
		auth.wwwLink.waitUntilElementPresent().click();
		auth.oDLogo.waitUntilElementPresent().verifyElementPresent();	
		home.fieldSearchMain.waitUntilElementPresent().verifyElementPresent();
		home.fieldSearchMain.clearAndSendKeys(getData("SKU.SKU_Normal_SKU1"));
		home.buttonSearch.waitUntilElementPresent().click();
		SKU.labelSKUID.waitUntilElementPresent().verifyTextExistsOnElement(getData("SKU.SKU_Normal_SKU1"));
	}
	@AfterTest
	public void tearDown()
	{
		quitWebSession();
	}
}

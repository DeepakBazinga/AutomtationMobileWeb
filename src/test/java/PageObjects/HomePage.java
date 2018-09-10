package PageObjects;

import com.dt.uiElements.AutomationElement;

public class HomePage {
	public AutomationElement fieldSearchMain = new AutomationElement("xpath=//input[@id='mobileSearchField']");
	public AutomationElement buttonSearch = new AutomationElement("xpath=//input[@id='searchSubmit']");
}

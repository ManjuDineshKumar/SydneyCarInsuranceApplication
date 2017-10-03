/**
 * 
 */
package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Util.Constants;


/**
 * @author Manju Dinesh Kumar
 *
 */
public class Landing  {
public WebDriver driver;
@FindBy(xpath=Constants.Get_Quote_Button)
public WebElement getquote_button;

@FindBy(xpath=Constants.SydneyTesterCarIsurance_Label)
public WebElement Sydneytestercarisurance_Label;
public Landing(WebDriver drive)
{
	driver=drive;
}
public GetQuote GetQuote_Pageloaded()
{
	if (Sydneytestercarisurance_Label.isDisplayed())
	{
		GetQuote getquotepage=PageFactory.initElements(driver, GetQuote.class);		
		return getquotepage;
	}
	else
		return null;
}
}

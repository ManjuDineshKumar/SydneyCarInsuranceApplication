/**
 * 
 */
package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import Util.Constants;

/**
 * @author Manju Dinesh Kumar
 *
 */
public class ReceivedPremium {
	public WebDriver driver;
	
	@FindBy(xpath=Constants.Monthly_Premium_Amount)
	WebElement monthly_premium_amount;
	
	public ReceivedPremium(WebDriver drive)
	{
		driver=drive;
	}
	
	public String GetMonthlyPremiumAmount()
	{
		try {
			Thread.sleep(1000);
		}catch(InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String Premium_Amout_Value=monthly_premium_amount.getText();
		System.out.println("Premium Amount is " +Premium_Amout_Value);
		
		try {
			Thread.sleep(1000);
		}catch(InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return Premium_Amout_Value;
	}
	

}

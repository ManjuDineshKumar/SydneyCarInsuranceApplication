/**
 * 
 */
package Pages;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Calendar;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import Util.Constants;

/**
 * @author Manju Dinesh Kumar
 *
 */
public class GetQuote {
	public WebDriver driver;
	public static Properties datas = null;
	
	@FindBy (xpath= Constants.Make) public WebElement make;
	@FindBy (xpath=Constants.Year) public WebElement year;
	@FindBy(xpath=Constants.Driver_Age) public WebElement driverage;
	@FindBy(xpath=Constants.Driver_Gender_Male)public WebElement drivergendermale;
	@FindBy(xpath=Constants.Driver_Gender_Female)public WebElement drivergenderfemale;
	@FindBy(xpath=Constants.State) public WebElement state;
	@FindBy(xpath=Constants.Email) public WebElement email;
	@FindBy(xpath=Constants.Get_Quote_Button) public WebElement getquotebutton;
	@FindBy(xpath=Constants.Year_NotEmptyValidation) public WebElement year_notEmptyvalidation;
	@FindBy(xpath=Constants.Year_NotZeroValidation) public WebElement year_notZeroValidation;
	@FindBy(xpath=Constants.Age_NotEmptyValidation) public WebElement age_notEmptyValidation;
	@FindBy(xpath=Constants.Age_NotZeroValidation) public WebElement age_notZeroValidation;
	@FindBy(xpath=Constants.Email_NotEmptyValidation) public WebElement email_notEmptyValidation;
	@FindBy(xpath=Constants.Email_FormatValidation) public WebElement email_formatValidation;
	@FindBy(xpath=Constants.Buy_Insurance_Button) public WebElement buyInsurance_Button;

	// Constructor
	public GetQuote(WebDriver drive)
	{
		driver = drive;
		datas= new Properties();
		FileInputStream fis;
		try{
			fis= new FileInputStream(System.getProperty("user.dir")+"\\src\\Data\\DataFile.properties");
			datas.load(fis);
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}catch(IOException e )
		{
			e.printStackTrace();
		}
	}
	
	//Selection of car make from the drop down
	public void SelectCarMake(String Fielddata)
	{
		if (!Fielddata.isEmpty())
		{
			Select Selection = new Select(make);			
			Selection.selectByIndex(Integer.valueOf(Fielddata));
		}
		else
			System.out.println("Default Car Make Selected");
	}
	
	//Enter Year of the Car 
	public void EnterYear(String Fielddata)
	{
		if (!Fielddata.isEmpty())
		{
			year.clear();
			year.sendKeys(Fielddata);						
		}		
	}
    public boolean CheckYearLimit(String Fielddata)
    {    	
    	try {
    		    		
			if ( (Integer.valueOf(datas.getProperty("CarYear"))<=Integer.valueOf(Fielddata))&&
			    (Integer.valueOf(Fielddata)<=Calendar.getInstance().get(Calendar.YEAR)))
			{			         
			          return true;
			}
			else
			{				
				return false;
			}
		} catch (NumberFormatException e) {
			// handle number Exception 	   
	   return false;
		}
    	    
    }
	// Enter Driver age
	public void EnterAge(String Fielddata)
	{
		if (!Fielddata.isEmpty())
		{
			driverage.clear();
			driverage.sendKeys(Fielddata);
		}
		
	}
	//check age range
	
	public boolean CheckAgeRange(String Fielddata)
	{
		
		try {
			if (Integer.valueOf(Fielddata) >=Integer.valueOf(datas.getProperty("Agestart")) && 
					(Integer.valueOf(Fielddata)<= Integer.valueOf(datas.getProperty("Ageend"))))
			    return true;
			else 
				return false;
		} catch (NumberFormatException ex) {
			// TODO Auto-generated catch block
			return false;
		}
	}
	//Enter Driver's Gender
	public void EnterDriverGender(String Fielddata)
	{		
		if (Fielddata.equalsIgnoreCase("male"))
		{			
			drivergendermale.click();
		}
		else
			drivergenderfemale.click();	
	}

	// Enter State
	public void Selectstate(String Fielddata)
	{
		if (!Fielddata.isEmpty())
		{
			Select Selection = new Select(state);
			Selection.selectByIndex(Integer.valueOf(Fielddata));
		}
		else
			System.out.println("Enter valid car Make");
	}
	// Select state from the drop down
	
	public void EnterEMail(String Fielddata)
	{
		if (!Fielddata.isEmpty())
		{
			email.clear();
			email.sendKeys(Fielddata);
		}
		
	}
	
	public boolean CheckEmailFormat(String Fielddata)
	{
		Pattern regexPattern;
		Matcher regMatcher;
		regexPattern = Pattern.compile("^[(a-zA-Z-0-9-\\_\\+\\.)]+@[(a-z-A-z)]+\\.[(a-zA-z)]{2,3}$");
		regMatcher=regexPattern.matcher(Fielddata);
		if (regMatcher.matches())
			return true;
		else
			return false;
	}
	//Year Error Message
	
		public String YearErrormessage()
		{
			String Year_not_Empty_ErrorMessage = year_notEmptyvalidation.getText();
	        String Year_notZero_Validation_ErrorMessage = year_notZeroValidation.getText();
	        String Year_IssueErrormessage ="Error in the Year";
	        //email_notEmptyValidation,email_formatValidation
			
			if (!Year_not_Empty_ErrorMessage.equals(null))
					
				      return Year_not_Empty_ErrorMessage;
			else if(!Year_notZero_Validation_ErrorMessage.equals(null))
					
				return Year_notZero_Validation_ErrorMessage;
			return Year_IssueErrormessage;
			
		}
	
	//Age Error Message
	
	public String AgeErrormessage()
	{
		String Age_not_Empty_ErrorMessage = age_notEmptyValidation.getText();
        String Age_not_Zero_ErrorMessage = age_notZeroValidation.getText();
        String Age_IssueMessage ="Error in the Age";
		
		if (!Age_not_Empty_ErrorMessage.equals(null))
				
			      return Age_not_Empty_ErrorMessage;
		else if(!Age_not_Zero_ErrorMessage.equals(null))
				
			return Age_not_Zero_ErrorMessage;
		return Age_IssueMessage;
		
	}
	
	//Email Error Message
	
	public String EmailErrormessage()
	{
		String Email_not_Empty_ErrorMessage = email_notEmptyValidation.getText();
        String Email_FormatValidation_ErrorMessage = email_formatValidation.getText();
        String Email_IssueErrormessage ="Error in the Email";
        //email_notEmptyValidation,email_formatValidation
		
		if (!Email_not_Empty_ErrorMessage.equals(null))
				
			      return Email_not_Empty_ErrorMessage;
		else if(!Email_FormatValidation_ErrorMessage.equals(null))
				
			return Email_FormatValidation_ErrorMessage;
		return Email_IssueErrormessage;
		
	}
	
	// PageFactory Usage for the connecting pages
	public ReceivedPremium  ClickGetquote_Button() throws InterruptedException
	{
		  JavascriptExecutor jse = (JavascriptExecutor)driver;
		  jse.executeScript("window.scrollBy(0,250)", "");
		
		  Thread.sleep(1000);		  		
		  if (getquotebutton.isEnabled())
		  {			 
			  getquotebutton.click();
			  Thread.sleep(2000);			  
					
		      if (buyInsurance_Button.isDisplayed())
	           	{		    	  
		          	ReceivedPremium GotPremium=PageFactory.initElements(driver, ReceivedPremium.class);		          	
		          	Thread.sleep(1000);
		          	return GotPremium;		   
	           	}
		  }
		  else
		  {			  
			  return null;			  
		  }
		  
		return null;
	}	

}

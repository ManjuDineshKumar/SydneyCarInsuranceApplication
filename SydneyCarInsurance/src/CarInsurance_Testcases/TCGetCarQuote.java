package CarInsurance_Testcases;

import java.util.Hashtable;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.annotations.AfterTest;

import Pages.GetQuote;
import Pages.Landing;
import Pages.ReceivedPremium;
import Util.BrowserFactory;
import Util.ExcelProcess;

public class TCGetCarQuote extends BrowserFactory{

	

@Test(dataProvider="getTestData")
 
  public void GetInusranceQuote(Hashtable<String,String> data) throws InterruptedException {
	
	  Landing landingpage =PageFactory.initElements(driver,Landing.class);
	  GetQuote getquotepage =landingpage.GetQuote_Pageloaded();
	  //ReceivedPremium rcvpremium=getquotepage.ClickGetquote_Button();
	  
	  JavascriptExecutor jse = (JavascriptExecutor)driver;
	  jse.executeScript("window.scrollBy(0,250)", "");
	  
	  getquotepage.SelectCarMake(data.get("Make"));	  
	  getquotepage.EnterYear(data.get("Year"));
	  getquotepage.EnterAge(data.get("Driver Age"));
	  getquotepage.EnterDriverGender(data.get("Driver Gender"));	  
	  getquotepage.Selectstate(data.get("State"));
	  getquotepage.EnterEMail(data.get("Email ID"));
	  boolean AgeRangeFlag=getquotepage.CheckAgeRange(data.get("Driver Age"));
	  boolean EmailFlag=getquotepage.CheckEmailFormat(data.get("Email ID"));
	  boolean YearFlag=getquotepage.CheckYearLimit(data.get("Year"));
	  
	 ReceivedPremium rcvpremium=getquotepage.ClickGetquote_Button();
	  if (rcvpremium==null)
	  {
		  if (!AgeRangeFlag &&(data.get("Driver Age")==null))
				  {
			  if(getquotepage.AgeErrormessage()==null)
				  Assert.fail("Age field is empty,please Enter Age:");
			  else
			  {
				  Assert.assertEquals(getquotepage.AgeErrormessage(), "The age is required. Numeric, "
				  		+ "bigger than zero."," Please Enter Age :" );	
				  Reporter.log("The Error message is displayed.Test is passed");
			  }
			       }
		  else if(!AgeRangeFlag &&(data.get("Driver Age")!=null))
		  {
			  if(getquotepage.AgeErrormessage()==null)
				  Assert.fail("Age field is Invalid,Age Entered is :"+data.get("Driver Age"));  
			  else
			  {
				  Assert.assertEquals(getquotepage.AgeErrormessage(), "The age is required. Numeric, "
					  		+ "bigger than zero."," Entered Age is  :" +data.get("Driver Age"));	
					  Reporter.log("The Error message is displayed.Test is passed");
			  }
		  }
	  
	  // year validation
	  if (!YearFlag && data.get("Year")=="")
	  {
		  if(getquotepage.YearErrormessage()==null)
			  Assert.fail("Year field is empty,please Enter Year:");
		  else
		  {
			  Assert.assertEquals(getquotepage.YearErrormessage(),"Year Required.","Year Field Empty");
			  Reporter.log("The Error message is displayed.Test is passed");
		  }
	  }
	  else if (!YearFlag && data.get("Year")!=null)
	  {
		  if(getquotepage.YearErrormessage()==null)
			  Assert.fail("Valid Year Required,Year entered is :"+data.get("Year"));
		  else
		  {
			  Assert.assertEquals(getquotepage.YearErrormessage(),"Enter Valid Year.Year entered "
			  		+ "is :"+data.get("Year"));
			  Reporter.log("The Error message is displayed.Test is passed");
			  
		  }
	  }
	  
	  //Email Validation
	  if (!EmailFlag && data.get("Email ID")=="")
	  {
		  if(getquotepage.EmailErrormessage()==null)
			  Assert.fail("Email field is empty,please Enter Email:");
		  else
		  {
			  Assert.assertEquals(getquotepage.EmailErrormessage(),"Email Required.","Email Field Empty");
			  Reporter.log("The Error message is displayed.Test is passed");
		  }
	  }
	  else if (!EmailFlag && data.get("Email ID")!=null)
	  {
		  if(getquotepage.EmailErrormessage()==null)
			  Assert.fail("Valid Email Required,Email entered is :"+data.get("Email ID"));
		  else
		  {
			  Assert.assertEquals(getquotepage.EmailErrormessage(),"Enter Valid Email.Email "
			  		+ "entered is :"+data.get("Email ID"));
			  Reporter.log("The Error message is displayed.Test is passed");
			  
		  }
	  }
	  }  
   else
  {
	   if(YearFlag && AgeRangeFlag && EmailFlag)
		   Reporter.log("Test is passed. The Premium amount is : "+rcvpremium.GetMonthlyPremiumAmount());
		   
	 driver.navigate().back();
	 System.out.println("Year:"+YearFlag+" Email:"+EmailFlag+"Age Range"+AgeRangeFlag);
	 Assert.assertTrue(YearFlag, "The application proceded for invalid entry in "
	        + "the YEAR field. "+"The entered year is "+data.get("Year")+"\n");
	 Assert.assertTrue(AgeRangeFlag, "The application proceded for invalid entry in "
		        + "the Age field. "+"The entered age is "+data.get("Driver Age")+"\n");
	 Assert.assertTrue(EmailFlag, "The application proceded inspite of invalid entry in"
	 		+ " the EMAIL field. "+"The entered email is "+data.get("Email ID")+"\n");
  }
	  //driver.navigate().back();
	  
}
@DataProvider
public Object[][] getTestData(){
	 return ExcelProcess.getData("TC_101", xls);
}

  @AfterTest
  public void TearDown() 
  {
	  driver.close();
  }

}

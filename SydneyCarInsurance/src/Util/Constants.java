/**
 * 
 */
package Util;

/**
 * @author Manju Dinesh Kumar
 *
 */
public class Constants {
	public static final String SydneyTesterCarIsurance_Label="//*[@id='quotebox']/div/div[1]/h3";
	public static final String Make = "//*[@id='make']";
	public static final String Year = "//*[@id='year']";
	public static final String Driver_Age = "//*[@id='age']";
	public static final String Driver_Gender_Male = "//*[@id='male']";
	public static final String Driver_Gender_Female = "//*[@id='female']";
	public static final String State= "//*[@id='state']";
	public static final String Email= "//*[@id='email']";
	public static final String Get_Quote_Button= "//*[@id='getquote']";
	//next page
	public static final String Buy_Insurance_Button= "//*[@id='payment']";
	public static final String Premium_frame= "//*[@id='premiumResultform']";
	public static final String Monthly_Premium_Amount="//*[@id='premiumResultform']/div[1]/dl/dd";
	
	
	// Validation of components
	public static final String Year_NotEmptyValidation ="//*[@id='quoteform']/div[2]/div/small[2]";
	public static final String Year_NotZeroValidation="//*[@id=';quoteform']/div[2]/div/small[1]";
	public static final String Age_NotEmptyValidation ="//*[@id='quoteform']/div[3]/div/small[2]";
	public static final String Age_NotZeroValidation="//*[@id='quoteform']/div[3]/div/small[1]";
	public static final String Email_NotEmptyValidation ="//*[@id='quoteform']/div[6]/div/small[1]";
	public static final String Email_FormatValidation ="//*[@id='quoteform']/div[6]/div/small[2]";
	
	

}

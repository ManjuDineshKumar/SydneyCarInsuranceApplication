/**
 * 
 */
package Util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


import Util.Xls_Reader;

/**
 * @author Manju Dinesh Kumar
 *
 */
public class BrowserFactory {
	public static WebDriver driver= null;
	public static Xls_Reader xls=null;
		
	public BrowserFactory()	
	{				
		if (driver==null)
		{		
		
			xls=new Util.Xls_Reader(System.getProperty("user.dir")+"\\TestData\\TestData.xlsx");			
			//System.setProperty("webdriver.chrome.driver", "C:\\Users\\DineshKumar\\workspace\\First Selenium\\"
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")
					+ "\\Chrome\\chromedriver.exe");			
				driver = new ChromeDriver();
				driver.manage().window().maximize();
		    	driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);	
		    	driver.get("http://sydneytesters.herokuapp.com/car");
			}
	}			 
	public void quit()
	{
		driver.close();
	}
}

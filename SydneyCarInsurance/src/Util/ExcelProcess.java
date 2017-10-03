/**
 * 
 */
package Util;

import java.util.Hashtable;

/**
 * @author Manju Dinesh Kumar
 *
 */
public class ExcelProcess {
	public static Boolean IsExecutable(Xls_Reader xlsRead,String nSheetName,String Testcase)
	{
		int count=xlsRead.getRowCount(nSheetName);		
		System.out.println(count);
		for(int x=2;x<=count;x++)
		{
			if(xlsRead.getCellData("TestSuites", "TCID", x).equals(Testcase))
			{
				if(xlsRead.getCellData("TestSuites","Runmode", x).equals("Y"))
				{
					System.out.println("inside Test suite");
				return true;
				}
			}					
		}
		return false;	
	}
	
	public static Object[][] getData(String TCName,Xls_Reader xls)
	{
		int TC_NameCount =0;
		for(int i=1;i<=xls.getRowCount("Data");i++ )
		{
			if((xls.getCellData("Data", 0, i)).equals(TCName))
			{				
				TC_NameCount=i;
				break;		
			}
		}
		int Column_Count =0;
		int Row_Count =0;
		int RowStartNum=TC_NameCount+1;
		int Actual_DataRowStartNum = TC_NameCount+2;
		// Finding column count
		while (!xls.getCellData("Data", Column_Count, RowStartNum).equals(""))
			Column_Count++;
		System.out.println("Total cols="+Column_Count);

		// finding row count
		while(!xls.getCellData("Data",0,Actual_DataRowStartNum+Row_Count).equals(""))
			Row_Count++;
		System.out.println("Total rows="+Row_Count);
// initialising data object with number of rows and one column.The data of one row will be put in one column of Hashtable
		Object[][] data = new Object[Row_Count][1];
		//The column will be 1 only as we put data in hash table
		int tableindex=0;
		
		Hashtable<String,String> table= null;
		for (int row=Actual_DataRowStartNum;row<Row_Count+Actual_DataRowStartNum;row++)
		{
			table= new Hashtable<String,String>();
			for (int Column=0; Column <Column_Count;Column++)
				
			{
				table.put(xls.getCellData("Data",Column,Actual_DataRowStartNum-1),xls.getCellData("Data", Column, row));
				System.out.println(xls.getCellData("Data",Column,row)+ " :::: ");
			}
			data[tableindex][0]= table;
			tableindex++;
		}
				
		return data;
	}

}

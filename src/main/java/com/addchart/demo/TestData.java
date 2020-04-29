package com.addchart.demo;

import org.testng.annotations.DataProvider;
import static com.addchart.util.Constants.*;
public class TestData {
	
	@DataProvider(name="InputData")
	public Object getDataForCountry(){
		
		Object[][] obj=new Object[][] {
			
			{AUSTRALIA}
			
		};
		
		return obj;
	}

        @DataProvider(name="LoginUser")
        public static Object[][] getDataFromDataprovider(){
        	Object[][] data = new Object[1][2];

        	data[0][0] ="yeshwanth.c@gmail.com";
        	data[0][1] = "amazon@123";
        	
        	return data;
        }
}

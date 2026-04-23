package com.ixfi.dataprovider;

import org.testng.annotations.DataProvider;

import com.ixfi.utility.NewExcelLibrary;

public class DataProviders {
	
	NewExcelLibrary excellLibObj=new NewExcelLibrary();
	
	//add data provider method here
	
	@DataProvider(name = "credentials")
	public Object[][] getCredentials() {
		// Totals rows count
		int rows = excellLibObj.getRowCount("Credentials");
		// Total Columns
		int column = excellLibObj.getColumnCount("Credentials");
		int actRows = rows - 1;

		Object[][] data = new Object[actRows][column];

		for (int i = 0; i < actRows; i++) {
			for (int j = 0; j < column; j++) {
				data[i][j] = excellLibObj.getCellData("Credentials", j, i + 2);
			}
		}
		return data;
	}

}

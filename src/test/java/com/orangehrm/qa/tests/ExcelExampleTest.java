package com.orangehrm.qa.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import com.aventstack.extentreports.Status;
import com.codoid.products.exception.FilloException;
import com.orangehrm.qa.configurations.Configuration;
import com.orangehrm.qa.driver.DriverManager;
import com.orangehrm.qa.pageobjects.LoginExamplePage;
import com.orangehrm.qa.reportmanager.Report;
import com.orangehrm.qa.utilities.ExcelManager;

@Listeners(com.orangehrm.qa.utilities.TestListener.class)

public class ExcelExampleTest extends BaseTest {
	ArrayList<String> loginCreds = new ArrayList<String>();

	@Test(dataProvider = "userData")
	public void testExcelLogin(String username, String password, String employeeName, String referenceId, String eventName, String eventStatus, String fromDate, String toDate) throws Exception {
		LoginExamplePage obj = new LoginExamplePage(DriverManager.getDriver());
		obj.login(username, password);
		Assert.assertEquals(obj.getPageCurrentUrl(), "https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index");
		Report.log(Status.PASS, "Login successful");
		obj.searchEmplyeeDetails(employeeName, referenceId, eventName, eventStatus, fromDate, toDate);
	}

	@DataProvider
	public Object[][] userData() throws FilloException {
		ExcelManager fillo = new ExcelManager();
		List<HashMap<String, String>> users = fillo.getAllData(Configuration.TEST_RESOURCE_PATH, "testdata.xlsx", "TestData");
		Object[][] dataObj = new Object[users.size()][2];

		for (int i = 0; i < users.size(); i++) {
			dataObj[i][0] = users.get(i).get("Username");
			dataObj[i][1] = users.get(i).get("Password");
			dataObj[i][2] = users.get(i).get("EmployeeName");
			dataObj[i][3] = users.get(i).get("ReferenceId");
			dataObj[i][4] = users.get(i).get("EventName");
			dataObj[i][5] = users.get(i).get("Status");
			dataObj[i][6] = users.get(i).get("FromDate");
			dataObj[i][7] = users.get(i).get("ToDate");
		}
		return dataObj;
	}
}

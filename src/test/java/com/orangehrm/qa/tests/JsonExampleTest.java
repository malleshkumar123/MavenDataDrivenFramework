package com.orangehrm.qa.tests;

import org.testng.annotations.Test;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import com.aventstack.extentreports.Status;
import com.orangehrm.qa.configurations.Configuration;
import com.orangehrm.qa.driver.DriverManager;
import com.orangehrm.qa.pageobjects.LoginExamplePage;
import com.orangehrm.qa.reportmanager.Report;
import com.orangehrm.qa.utilities.JsonFileReader;

@Listeners(com.orangehrm.qa.utilities.TestListener.class)

public class JsonExampleTest extends BaseTest {

	@Test(dataProvider = "userData")
	public void testJsonLogin(String username, String password) throws Exception {
		LoginExamplePage obj = new LoginExamplePage(DriverManager.getDriver());

		obj.login(username, password);
		Assert.assertEquals(obj.getPageCurrentUrl(), "https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index");
		Report.log(Status.PASS, "Login Successful");
	}

	@DataProvider
	public Object[][] userData() throws Exception {
		JsonFileReader jsonReader = new JsonFileReader();
		JSONArray usersList = jsonReader.readJson(Configuration.TEST_RESOURCE_PATH + "/login.json", "users");
		Object[][] dataObj = new Object[usersList.size()][2];

		for (int i = 0; i < dataObj.length; i++) {
			JSONObject user = (JSONObject) usersList.get(i);
			dataObj[i][0] = user.get("username");
			dataObj[i][1] = user.get("password");
		}
		return dataObj;
	}
}

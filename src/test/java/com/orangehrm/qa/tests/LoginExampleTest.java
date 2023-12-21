package com.orangehrm.qa.tests;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.aventstack.extentreports.Status;
import com.orangehrm.qa.driver.DriverManager;
import com.orangehrm.qa.pageobjects.LoginExamplePage;
import com.orangehrm.qa.reportmanager.Report;

@Listeners(com.orangehrm.qa.utilities.TestListener.class)

public class LoginExampleTest extends BaseTest {

	@Test
	public void testLoginValidInput() throws Exception {
		LoginExamplePage obj1 = new LoginExamplePage(DriverManager.getDriver());
		obj1.login("Admin","admin123");
		Report.log(Status.PASS, "Login successful");
		Assert.assertEquals(obj1.getPageCurrentUrl(), "https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index");
	}

	@Test
	public void testLoginInvalidInput() throws Exception {
		LoginExamplePage obj2 = new LoginExamplePage(DriverManager.getDriver());
		obj2.login("Admin", "admin1234");
		Report.log(Status.FAIL, "Login unsuccessful");
		Assert.assertEquals(obj2.getPageCurrentUrl(), "https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index");
	}

}

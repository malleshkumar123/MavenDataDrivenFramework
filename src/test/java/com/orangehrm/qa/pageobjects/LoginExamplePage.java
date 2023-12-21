package com.orangehrm.qa.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.aventstack.extentreports.Status;
import com.orangehrm.qa.configurations.Configuration;
import com.orangehrm.qa.reportmanager.Report;

public class LoginExamplePage {

	WebDriver driver;

	public By txtUserName = By.name("username");
	public By txtPassword = By.name("password");
	public By btnSubmit = By.partialLinkText("submit");
	public By linkClaim = By.xpath("//span[text()='Claim']");
	public By txtEmployeerName = By.xpath("//label[text()='Employee Name']/parent::div/following-sibling::div/descendant::input");
	public By txtReferenceId = By.xpath("//label[text()='Reference Id']/parent::div/following-sibling::div/descendant::input");
	public By eventNameDropDown = By.xpath("//label[text()='Event Name']/parent::div/following-sibling::div");
	public By eventStatusDropDown = By.xpath("//label[text()='Status']/parent::div/following-sibling::div");
	public By txtFromDate = By.xpath("//label[text()='From Date']/parent::div/following-sibling::div/descendant::input");
	public By txtToDate = By.xpath("//label[text()='To Date']/parent::div/following-sibling::div/descendant::input");
    public By searchButton = By.xpath("//button[contains(text(),'Search')]");
	

	public LoginExamplePage(WebDriver driver) {
		this.driver = driver;
	}

	/**
	 * Login with valid credentials
	 * 
	 * @param userName
	 * @param password
	 * @throws Exception
	 */
	public void login(String userName, String password) throws Exception {
		driver.get(Configuration.BASE_URL);
		Report.log(Status.PASS, "Navigated to the login page");
		driver.findElement(txtUserName).sendKeys(userName);
		driver.findElement(txtPassword).sendKeys(password);
		driver.findElement(btnSubmit).click();
	}
	
	/**
	 * Login with valid credentials
	 * 
	 * @param userName
	 * @param password
	 * @throws Exception
	 */
	public void searchEmplyeeDetails(String employeeName, String referenceId, String eventName, String eventStatus, String fromDate, String toDate) throws Exception {
		driver.get(Configuration.BASE_URL);
		Report.log(Status.PASS, "Verify the search page");
		driver.findElement(linkClaim).click();
		driver.findElement(txtEmployeerName).sendKeys(employeeName);
		driver.findElement(txtReferenceId).sendKeys(referenceId);
		driver.findElement(eventNameDropDown).click();
		driver.findElement(By.xpath("//div[text()='"+eventName+"']")).click();
		driver.findElement(eventStatusDropDown).click();
		driver.findElement(By.xpath("//div[text()='"+eventStatus+"']")).click();
		driver.findElement(txtFromDate).sendKeys(fromDate);
		driver.findElement(txtToDate).sendKeys(toDate);
		driver.findElement(searchButton).click();
	}

	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getPageCurrentUrl() throws Exception {
		return driver.getCurrentUrl();
	}
}

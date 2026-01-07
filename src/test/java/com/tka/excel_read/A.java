package com.tka.excel_read;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class A {
	WebDriver driver;

	@Parameters({ "browser", "url" })
	@BeforeMethod
	public void setUp(String brName, String URL) {
		if (brName.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
		} else if (brName.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		}
		driver.get(URL);
	}

	@AfterMethod
	public void tearDown() {
		// driver.quit();
	}

	@Test(dataProvider = "login", retryAnalyzer = RetryExecution.class)
	public void validLogin(String username, String password, String title) {
		driver.findElement(By.id("email")).sendKeys(username);
		driver.findElement(By.id("password")).sendKeys(password);
		driver.findElement(By.xpath("//button")).click();

		Assert.assertEquals(driver.getTitle(), title);
	}

	@DataProvider(name = "login")
	public String[][] loginData() throws Exception {
		return UtilityClass.readExcel("LoginData", "loginPage");
	}
	
}

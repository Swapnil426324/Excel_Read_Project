package com.tka.excel_read;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataProvider_Demo {
  WebDriver driver;
	
  @Test(dataProvider = "login")
  public void validLogin(String username, String password) {
	    driver = new ChromeDriver();
	    driver.get("https://practicetestautomation.com/practice-test-login/");	
		driver.findElement(By.id("username")).sendKeys(username);
		driver.findElement(By.name("password")).sendKeys(password);
		driver.findElement(By.id("submit")).click();
	       
	}

    @DataProvider(name="login")
	public String[][] loginData() throws Exception {
		
		return ReadExcel_Ex.excelRead();
	}
}

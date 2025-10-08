package com.tka.registerdemo;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class AddUser_Test {

	WebDriver driver;

	@BeforeMethod
	public void setUp() {
		driver = new ChromeDriver();
		driver.get("https://javabykiran.com/liveproject/pages/examples/users.html");
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

	@Test(dataProvider = "userData")
	public void test_addUser(String username, String mobile,String email, String course, String Gender, String State, String password) {
		driver.findElement(By.xpath("//button[text()='Add User']")).click();
		driver.findElement(By.id("username")).sendKeys(username);
		driver.findElement(By.id("mobile")).sendKeys(mobile);
		driver.findElement(By.id("email")).sendKeys(email);
		driver.findElement(By.id("course")).sendKeys(course);
		
		if (Gender.equals("Male") || Gender.equals("Male1") ) {
			driver.findElement(By.xpath("//input[@name='gender']")).click();
		}
		else if(Gender.equals("Female") || Gender.equals("Female1")) {
			driver.findElement(By.xpath("//input[@name='gender']")).click();
		}
		
		Select select = new Select(driver.findElement(By.xpath("//select[@class='form-control']")));
		select.selectByVisibleText(State);
		
		driver.findElement(By.id("password")).sendKeys(password);
		driver.findElement(By.id("submit")).click();
		Alert al = driver.switchTo().alert();
		String text = al.getText();
		al.accept();
		Assert.assertEquals(text, "User Added Successfully. You can not see added user.");

	}

	@DataProvider
	public String[][] userData() throws Exception {
		return ReadExcel.excelRead();
	}
}

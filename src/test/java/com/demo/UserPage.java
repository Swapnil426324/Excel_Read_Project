package com.demo;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UserPage {

	@Test
	public void validateTble() {
		WebDriver driver = new ChromeDriver();
		driver.get("file:///C:/Users/SwapnilAlaskar/Documents/Software/Selenium%20Software/Offline%20Website/pages/examples/users.html");
		List<WebElement> rows = driver.findElements(By.tagName("tr"));
		List<WebElement> actualData;
		List<String> actualText = new ArrayList<String>();
		for (WebElement row : rows) {
			if (rows.indexOf(row) == 0) {
				actualData = row.findElements(By.tagName("th"));
			} else {
				actualData = row.findElements(By.tagName("td"));
			}

			for (WebElement dataText : actualData) {
				actualText.add(dataText.getText());
			}
		}

		List<String> expectedText = new ArrayList<String>();
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 8; j++) {
				expectedText.add(ExcelUtils.excelRead("LoginData", "Sheet2", i, j));
			}
		}
		System.out.println("actualText" + actualText);
		System.out.println("expectedText" + expectedText);
		Assert.assertEquals(actualText, expectedText);
	}
	
	

}

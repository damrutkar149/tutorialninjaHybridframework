package com.tutorialsninja.test;

import org.testng.annotations.Test;

import com.codoid.products.exception.FilloException;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;


public class SearchTest extends BaseTest {
	


	WebDriver driver;
	
	
	@BeforeMethod
	public void setup() throws IOException, FilloException
	{
		loadtest();
		driver=Initiallization (prop.getProperty("browsername"));
		
	}
	@AfterMethod
	public void teardown()
	{
		driver.quit();
	}

	@Test(priority=1)

	public void search_with_valid_product()
	{
		driver.findElement(By.name("search")).sendKeys(dataprop.getProperty("validproduct"));
		driver.findElement(By.xpath("//div[@id='search']/descendant::button")).click();
		Assert.assertTrue(driver.findElement(By.linkText("HP LP3065")).isDisplayed(),"in search result Product not found");
		
	}
	

	@Test(priority=2)
	public void search_with_invalid_product()
	{
		driver.findElement(By.name("search")).sendKeys(dataprop.getProperty("invalidproduct"));
		driver.findElement(By.xpath("//div[@id='search']/descendant::button")).click();
		
		String Actualsearchmessage=driver.findElement(By.xpath("//div[@id='content']/h2/following-sibling::p")).getText();
		Assert.assertEquals(Actualsearchmessage,dataprop.getProperty("Actualsearchmessage"), "No search Result found");
		
	}
	
	@Test(priority=3)
	public void search_withoutproviding_any_product()
	{
		
		driver.findElement(By.xpath("//div[@id='search']/descendant::button")).click();
		String Actualanysearchmessage=driver.findElement(By.xpath("//div[@id='content']/h2/following-sibling::p")).getText();
		Assert.assertEquals(Actualanysearchmessage,dataprop.getProperty("Actualsearchmessage"), "No search Result found");
		
	}
	
}



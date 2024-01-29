package com.tutorialsninja.test;

import org.testng.annotations.Test;

import com.codoid.products.exception.FilloException;

import java.io.IOException;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;


public class RegisterTest extends BaseTest {

WebDriver driver;
	
	
	@BeforeMethod
	public void setup() throws IOException, FilloException
	{
	
		loadtest();
		driver=Initiallization (prop.getProperty("browsername"));
		driver.findElement(By.xpath("//span[text()='My Account']")).click();
		driver.findElement(By.xpath("//a[text()='Register']")).click();
	}
	@AfterMethod
	public void teardown()
	{
		driver.quit();
	}
	
	@Test(priority=1)
	public void verifyregistering_Account_with_mandetoryfields()
	{
		driver.findElement(By.xpath("//input[@name='firstname']")).sendKeys(dataprop.getProperty("firstname"));
		driver.findElement(By.xpath("//input[@name='lastname']")).sendKeys(dataprop.getProperty("lastname"));
		driver.findElement(By.xpath("//input[@name='email']")).sendKeys("damrutkar149"+generatetimestamp()+"@gmail.com");
		driver.findElement(By.xpath("//input[@name='telephone']")).sendKeys(dataprop.getProperty("telno"));
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys("1234523");
		driver.findElement(By.xpath("//input[@name='confirm']")).sendKeys("1234523");
		driver.findElement(By.xpath("//input[@name='agree']")).click();
		driver.findElement(By.xpath("//input[@value='Continue']")).click();
		String Actual_heading_msg = driver.findElement(By.xpath("//div[@id='content']/h1")).getText();
		Assert.assertEquals(Actual_heading_msg, dataprop.getProperty("Actual_heading_msg"));//Your Account Has Been Created!
	}
	
	@Test(priority=2)
	public void verifyregistering_Account_with_Allfields()
	{
		driver.findElement(By.xpath("//input[@name='firstname']")).sendKeys(dataprop.getProperty("firstname"));
		driver.findElement(By.xpath("//input[@name='lastname']")).sendKeys(dataprop.getProperty("lastname"));
		driver.findElement(By.xpath("//input[@name='email']")).sendKeys("damrutkar149"+generatetimestamp()+"@gmail.com");
		driver.findElement(By.xpath("//input[@name='telephone']")).sendKeys(dataprop.getProperty("telno"));
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys("1234523");
		driver.findElement(By.xpath("//input[@name='confirm']")).sendKeys("1234523");
		driver.findElement(By.xpath("//input[@name='newsletter'][@value='1']")).click();
		driver.findElement(By.xpath("//input[@name='agree']")).click();
		driver.findElement(By.xpath("//input[@value='Continue']")).click();
		String Actual_heading_msg = driver.findElement(By.xpath("//div[@id='content']/h1")).getText();
		Assert.assertEquals(Actual_heading_msg, dataprop.getProperty("Actual_heading_msg"));
        
	}
	
	
	@Test(priority=3)
	public void verifyregistering_Account_with_exiting_emailaddress()
	{
		driver.findElement(By.xpath("//input[@name='firstname']")).sendKeys(dataprop.getProperty("firstname"));
		driver.findElement(By.xpath("//input[@name='lastname']")).sendKeys(dataprop.getProperty("lastname"));
		driver.findElement(By.xpath("//input[@name='email']")).sendKeys(prop.getProperty("validemail"));
		driver.findElement(By.xpath("//input[@name='telephone']")).sendKeys(dataprop.getProperty("telno"));
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys("1234523");
		driver.findElement(By.xpath("//input[@name='confirm']")).sendKeys("1234523");
		driver.findElement(By.xpath("//input[@name='newsletter'][@value='1']")).click();
		driver.findElement(By.xpath("//input[@name='agree']")).click();
		driver.findElement(By.xpath("//input[@value='Continue']")).click();
        String Warning= driver.findElement(By.xpath("//div[@class='alert alert-danger alert-dismissible']")).getText();
        String Warningmsg= dataprop.getProperty("Warningmsg");
		Assert.assertTrue(Warning.contains(Warningmsg),"Warning measage Dupicate email not dispalyed");
	}
	
	
	@Test(priority=4)
	public void verifyregistering_Account_without_filling_anydetails()
	{
		driver.findElement(By.xpath("//input[@value='Continue']")).click();
        String ActualPPWarning= driver.findElement(By.xpath("//div[@class='alert alert-danger alert-dismissible']")).getText();
        String PPWarningmsg= "Warning: You must agree to the Privacy Policy!";
		Assert.assertTrue(ActualPPWarning.contains(PPWarningmsg),"Warning measage Dupicate email not dispalyed");
		//for firstname
        String firstnamewarning=driver.findElement(By.xpath("//input[@id='input-firstname']/following-sibling::div")).getText();
        Assert.assertEquals(firstnamewarning,"First Name must be between 1 and 32 characters!","Warning measage first name not dispalyed");
      //for lastname
        String lastnamewarning=driver.findElement(By.xpath("//input[@id='input-lastname']/following-sibling::div")).getText();
        Assert.assertTrue(lastnamewarning.contains("Last Name must be between 1 and 32 characters!"),"Warning measage Last name not dispalyed");
      
	}
	public String generatetimestamp()
	{
		Date date=new Date();
		return date.toString().replace(" ","_").replace(":","_");
	}
	

}

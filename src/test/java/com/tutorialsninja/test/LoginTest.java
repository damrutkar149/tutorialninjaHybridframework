package com.tutorialsninja.test;

import org.testng.annotations.Test;

import com.codoid.products.exception.FilloException;
import com.tutorialsninja.pages.accountpage;
import com.tutorialsninja.pages.homepage;
import com.tutorialsninja.pages.loginpage;

import java.io.IOException;
import java.util.Date;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;



public class LoginTest extends BaseTest {
	
	
	WebDriver driver;
	
	
	@BeforeMethod
	public void setup() throws IOException, FilloException
	{
		
		loadtest();
		driver=Initiallization (prop.getProperty("browsername"));
		homepage hp=new homepage(driver);
		hp.clickonMyAccount();
		hp.LoginOption();
		
	}
	@AfterMethod
	public void teardown()
	{
		driver.quit();
	}

	@Test(priority=1)
	public void verifyLoginwithvalidcredentials(String email,String password)
	{
		loginpage lp=new loginpage(driver);
		lp.enterEmailaddress(email);
		lp.enterpassword(password);
		lp.clickonLoginbutton();
		accountpage ap=new accountpage(driver);
		Assert.assertTrue(ap.get_accountinfomsgststatus());
		
	}

	
	
	@Test(priority=2)
	public void verifyLoginwithinvalidcredentials()
	{
		driver.findElement(By.xpath("//input[@name='email']")).sendKeys("damrutkar1491"+generatetimestamp()+"@gmail.com");
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys("dhanutesting30");
		driver.findElement(By.xpath("//input[@value='Login']")).click();
		String actualWarningmessage=driver.findElement(By.xpath("//div[@class='alert alert-danger alert-dismissible']")).getText();
		String expectedwarningmessage=dataprop.getProperty("expectedwarningmessage");
		Assert.assertTrue(actualWarningmessage.contains(expectedwarningmessage),"Expected Warning message is not dispalyed");
	}
	
	@Test(priority=3)
	public void verifyLoginwithinvalidemail_and_validpassword()
	{
		driver.findElement(By.xpath("//input[@name='email']")).sendKeys("damrutkar149"+generatetimestamp()+"@gmail.com");
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys(prop.getProperty("validpassword"));
		driver.findElement(By.xpath("//input[@value='Login']")).click();
		String actualWarningmessage=driver.findElement(By.xpath("//div[@class='alert alert-danger alert-dismissible']")).getText();
		String expectedwarningmessage=dataprop.getProperty("expectedwarningmessage");
		Assert.assertTrue(actualWarningmessage.contains(expectedwarningmessage),"Expected Warning message is not dispalyed");	
	}
	
	@Test(priority=4)
	public void verifyLoginwithvalidemail_and_invalidpassword()
	{
		driver.findElement(By.xpath("//input[@name='email']")).sendKeys(prop.getProperty("validemail"));
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys(dataprop.getProperty("invalidpassword"));
		driver.findElement(By.xpath("//input[@value='Login']")).click();
		String actualWarningmessage=driver.findElement(By.xpath("//div[@class='alert alert-danger alert-dismissible']")).getText();
		String expectedwarningmessage=dataprop.getProperty("expectedwarningmessage");
		Assert.assertTrue(actualWarningmessage.contains(expectedwarningmessage),"Expected Warning message is not dispalyed");	
	}
	
	public String generatetimestamp()
	{
		Date date=new Date();
		return date.toString().replace(" ","_").replace(":","_");
	}
}

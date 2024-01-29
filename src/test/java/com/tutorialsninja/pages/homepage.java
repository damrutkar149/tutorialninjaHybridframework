package com.tutorialsninja.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;



public class homepage {


	public WebDriver driver;
	
	public homepage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver,this);
		
	}
	
	
	
	@FindBy(xpath="//span[text()='My Account']")
	WebElement MY_Account;
	
	@FindBy(xpath="//a[text()='Login']")
	WebElement Loginoption;
	
	
	
	
	public void clickonMyAccount()
	{
		MY_Account.click();
	}
	public void LoginOption()
	{
		Loginoption.click();
	}
	
	
	

	
}

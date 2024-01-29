package com.tutorialsninja.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class loginpage {

	
public WebDriver driver;
	
	public loginpage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver,this);
		
	}
	
	
	
	@FindBy(xpath="//input[@name='email']")
	WebElement emailaddress;
	
	@FindBy(xpath="//input[@name='password']")
	WebElement password;
	
	@FindBy(xpath="//input[@value='Login']")
	WebElement Loginbutton;
	
	public void enterEmailaddress(String emailtext)
	{
		emailaddress.sendKeys(emailtext);
	}
	public void enterpassword(String passwordtext)
	{
		password.sendKeys(passwordtext);
	}
	public void clickonLoginbutton()
	{
		Loginbutton.click();
	}
	
}

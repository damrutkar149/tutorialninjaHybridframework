package com.tutorialsninja.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class accountpage {

	public WebDriver driver;
	
	public accountpage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver,this);
		
	}
	
	@FindBy(linkText="Edit your account information")
	WebElement edityouraccountinformationoption;
	
	
	public boolean get_accountinfomsgststatus()
	{
		boolean displaystatus=edityouraccountinformationoption.isDisplayed();
		return displaystatus;
	}
	
	
}


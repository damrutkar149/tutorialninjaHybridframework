package com.tutorialsninja.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;

import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.codoid.products.exception.FilloException;


public class BaseTest {
	
	WebDriver driver;
	public Properties prop;
	public Properties dataprop;
	public  Map<String,Map <String,String>> dt;
	
	public void loadtest() throws IOException, FilloException
	{
	    prop=new  Properties();
		File propfile=new File(System.getProperty("user.dir")+"/src/test/resources/Config/setting.properties");
		FileInputStream fis=new  FileInputStream(propfile);
		prop.load(fis);
		dataprop=new Properties();
		File propfil_testdata=new File(System.getProperty("user.dir")+"/src/test/resources/TestData/testdata.properties");
		FileInputStream fis_testdata=new  FileInputStream(propfil_testdata);
		dataprop.load(fis_testdata);
	}
	public WebDriver Initiallization (String browsername)
	{
		
		if(browsername.equalsIgnoreCase("chrome"))
		{
			driver=new ChromeDriver();	
		}else if(browsername.equalsIgnoreCase("safari"))
		{
			driver=new  SafariDriver();	
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));
		driver.get(prop.getProperty("url"));
		return driver;
	}
	
	
	
}

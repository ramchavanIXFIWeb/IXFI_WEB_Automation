package com.ixfi.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.ixfi.actiondriver.Action;
import com.ixfi.basepage.BaseClass;

public class ForgotPasswordPage extends BaseClass{
	
	//WebElements
	
			//Constructor
			public ForgotPasswordPage(WebDriver driver) {
				BaseClass.driver = driver;

				PageFactory.initElements(driver, this);
			}
			
			//Action Method
			public String getforgotPasswordPageTitle()
			{
				String forgotPasswordPageTitle=Action.getTitle(driver);
				return forgotPasswordPageTitle;
			}


}

package com.ixfi.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.ixfi.actiondriver.Action;
import com.ixfi.basepage.BaseClass;
import com.ixfi.utility.Log;

public class LoginPage extends BaseClass{
	
	
	IndexPage index;
	AuthValidationPage auth;
	KYCPopUpPage kyc;
	//WebElements
	
	@FindBy(xpath="//span[contains(text(), 'https://www.ixfi.com/auth/login')]")
	WebElement loginPageURL;
	
	
	//Email Login
	@FindBy(xpath="//button[@id='pills-email-tab1']")
	WebElement emailButton;
	
	@FindBy(id="email")
	WebElement emailTextField;
	
	@FindBy(id="password")
	WebElement loginPasswordField;//this field is same for both login by email and loginByPhone
	
	@FindBy(id="pills-phone-tab1")
	WebElement phoneButton;

	@FindBy(xpath="//div[@role='combobox']")
	WebElement countryCodeDD;
	
	@FindBy(id="mobile_no")
	WebElement phoneNumberTextField;
	
	@FindBy(css="a[href='/auth/forget-password']") //css means css selector
	WebElement forgotPasswordLink;
	
	@FindBy(xpath="//button[contains(text(), 'Login')]")
	WebElement loginButton;
	
	@FindBy(xpath="//a[@href='/auth/signup']")
	WebElement signUpNowLink;
	
	@FindBy(xpath="//img[@alt='eye']")
	WebElement eyeIconPasswordField;
	
	public LoginPage(WebDriver driver)
	{ 
		 BaseClass.driver = driver;
		PageFactory.initElements(driver, this);
		auth=new AuthValidationPage(driver);
	}
	
	public String getLoginPageTitle()
	{
		String title=Action.getTitle(driver);
		return title;
	}
	
	public String verifyUserIsNavigatedToLoginPage()
	{
		Action.waitForElementToBeVisible(loginPageURL, 5);
		Log.info("User is clicked on Login Page URL");
		String url=loginPageURL.getText();
		Log.info("URL is retrived");
		return url;
	}

	public String loginUsingEmailID(String emailid,String pwd) throws InterruptedException
	{
		
		Action.click(emailButton);
		Action.enterText(emailTextField, emailid);
		Action.enterText(loginPasswordField, pwd);
		Action.click(loginButton);
		//Thread.sleep(5000);
		Action.implicitWait(driver, 5);//this will wait completed script for 5 second until user manually handles the captcha
		System.out.println("Validated captcha slider manually!!!");
		
		//user is navigated to email Authetication page for OTP Validation
		
		kyc=auth.enterOTP();
		
		//now user is navigated to KYC popup now click on I will do it letter button
		index=kyc.clickOnIwillDoItLetterButton();
		Action.implicitWait(driver, 5);
		String ixfititle=index.getIXFITitle();
		return ixfititle;
			//Now user will navigated to slider and then it will navigated to Authentication page and then new homepage
		
	}
	
	public void loginUsingPhoneNumber(String phoneNumber,String pwd) throws InterruptedException
	{
		Action.click(phoneButton);
		Action.enterText(phoneNumberTextField, phoneNumber);
		Action.enterText(loginPasswordField, pwd);
		Action.click(loginButton);
		Thread.sleep(40000);
		System.out.println("Login button clicked successfully");
		//Now user will navigated to slider and then it will navigated to Authentication page and then new homepage
		
	}
	
	
	public void clickOnemailButton()
	{
		Action.click(emailButton);
	}

	public void enterEmailID(String emailId)
	{
		//Action.click(driver,emailTextField);
		Action.enterText(emailTextField,emailId); //returns boolean value
	}
	
	public void enterLoginPassword(String password)
	{
		Action.enterText(loginPasswordField,password);
	}
	
	public void clickOnPhoneButton()
	{
		Action.click(phoneButton);
	}
	
	public void selectCountryCode(String countryName)
	{
		Action.selectByVisibleText(countryCodeDD,countryName); //returns boolean value
	}
	
	public void enterMobileNumber(String phoneNumber)
	{
		//Action.click(driver,emailTextField);
		Action.enterText(phoneNumberTextField,phoneNumber); //returns boolean value
	}
	
	public void clickOnLoginButton()
	{
		Action.click(driver, loginButton);
		
		//After clicking on it, it will navigate to slider popup and then authenticationPage, then new homePage
		
	}
	public SignUpPage clickOnSignUpNowLink()
	{
		//Action.click(driver, signUpNowLink);
		Action.scrollByVisibilityOfElement(signUpNowLink); //scrolling the window so that login link is visible
		Action.waitForElementToBeClickable(signUpNowLink, 5);
		Action.clickUsingJavaScript(driver,signUpNowLink);
		return new SignUpPage(driver);
		
	}
	
	public void clickOnEyeIconPasswordField()
	{
		Action.click(driver, eyeIconPasswordField);
	}
	
}
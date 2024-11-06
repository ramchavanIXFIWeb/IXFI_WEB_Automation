package com.ixfi.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.ixfi.actiondriver.Action;
import com.ixfi.basepage.BaseClass;
import com.ixfi.utility.Log;

public class SignUpPage extends BaseClass {

	IndexPage indexPage;

	// WebElements

	@FindBy(xpath = "//a/span[contains(text(), 'https://www.ixfi.com/auth/signup')]")
	WebElement urlVerificationSignUpPage;

	@FindBy(xpath = "//button[@id='pills-email-tab1']")
	WebElement emailSectionButton;

	@FindBy(xpath = "//ul[@id='pills-tab']/li[2]/button[@id='pills-phone-tab1']")
	WebElement phoneSectionButton;

	@FindBy(xpath = "//div[@class='ng-select-coin']/ng-select/div")
	WebElement countryCodeDD;

	@FindBy(id = "mobile_no")
	WebElement phoneTextField;

	@FindBy(id = "email")
	WebElement emailAddressField;

	@FindBy(id = "password")
	WebElement passwordField;

	@FindBy(id = "confirm_password")
	WebElement confirmPasswordField;

	@FindBy(id = "username")
	WebElement usernameField;

	@FindBy(id = "used_referral_code")
	WebElement referralCodeField;

	@FindBy(xpath = "//input[@formcontrolname='is_send_promo_enabled']")
	WebElement promotionalEmailCheckbox;

	@FindBy(xpath = "//input[@formcontrolname='is_terms_and_condition']")
	WebElement termsAndConditionsCheckbox;

	@FindBy(xpath = "//button[@type='submit' and contains(@class, 'btn-default') and contains(text(), 'Create Account')]")
	WebElement createAccountButton;

	@FindBy(xpath = "//a[@href='/auth/login']")
	WebElement loginLink;

	@FindBy(xpath="//div[@class='email-phone-tabs']//div[2]//div[1]//button[1]//img[1]")
	WebElement passwordEye;
	
	@FindBy(xpath="//div[3]//div[1]//button[1]//img[1]")
	WebElement confirmPasswordEye;
	
	
	// Constructor
	public SignUpPage(WebDriver driver) {
		BaseClass.driver = driver;

		PageFactory.initElements(driver, this);
	}

	public String getSignUpPageTitle() {
		return Action.getTitle(driver);
	}

	// ActionMethods --operates on webelements
	public void createAccountByUsingEmail() throws InterruptedException {
		// Action.waitForLoaderToDisappear(driver);
		indexPage = new IndexPage(driver);
		indexPage.clickOnSignUpButton();
		Log.info("Clicked on signUp button");
		// Action.clickUsingJavaScript(driver,emailSectionButton);
		Action.click(emailSectionButton);
		Log.info("Clicked on Email button on signup page");
		Action.enterText(emailAddressField, prop.getProperty("new_email"));
		Log.info("Entered email in the email field: " + prop.getProperty("new_email"));
		Action.enterText(passwordField, prop.getProperty("newPass"));
		Log.info("Entered the password in the password field");
		Action.enterText(confirmPasswordField, prop.getProperty("confirm_pass"));
		Log.info("Entered the confirm password in the password field");
		Action.enterText(usernameField, prop.getProperty("username"));
		Log.info("Entered the username in the username field" + prop.getProperty("username"));
		Action.scrollByVisibilityOfElement(createAccountButton);
		Log.info("Scrolled the page to Create Account button");
		Action.enterText(referralCodeField, prop.getProperty("referral_code"));
		Log.info("Entered the referral code in referral field");
		Action.clickCheckbox(promotionalEmailCheckbox);
		Log.info("Clicked on pramotional email checkbox");
		Action.clickCheckbox(termsAndConditionsCheckbox);
		Log.info("Clicked on Terms and Conditions checkbox ");
//		Action.click(createAccountButton);
//		Log.info("Clicked on Create Account button");
//		Thread.sleep(5000);
		// this will return AuthValidationPage Object --

		// After click on create account button, the captcha slider will open and user
		// is navigated to otp auth page, then IXFI PIN Setup page
		// after IXFI pin -- Complience page -- user has to accept the complience --
		// then PIP -- then KYC -- then user will navigate to NewHomePage

	}

	public void createAccountByUsingPhoneNumber() throws InterruptedException {
		indexPage = new IndexPage(driver);
		indexPage.clickOnSignUpButton();
		Action.click(phoneSectionButton);
		Log.info("Clicked on Phone section button");
		Action.enterText(phoneTextField, prop.getProperty("phone1"));
		Log.info("Entered the phone in phone number textfield ");
		Action.enterText(passwordField, prop.getProperty("newPass"));
		Log.info("Entered the password in the password field");
		Action.enterText(confirmPasswordField, prop.getProperty("confirm_pass"));
		Log.info("Entered the Confirmpassword in the Confirmpassword field");
		Action.enterText(usernameField, prop.getProperty("username"));
		Log.info("Entered the username in the username field");
		Action.scrollByVisibilityOfElement(createAccountButton);
		Log.info("Clicked on create account button");
		Action.enterText(referralCodeField, prop.getProperty("referral_code"));
		Log.info("Entered the referral code in the referral code field");
		Action.clickCheckbox(promotionalEmailCheckbox);
		Log.info("Clicked on Promotional Email checkbox");
		Action.clickCheckbox(termsAndConditionsCheckbox);
		Log.info("Clicked on Terms and Conditions checkbox");
		
	}

	public void clickOnCreateAccountButton() {
		 Action.click(createAccountButton);
	}
	
	public boolean verifyCreateAccountButtonisClicked() {
		return Action.verifyButtonClicked(createAccountButton);
	}

	public String verifyUserIsNavigatedToSignUpPage() {
		Action.waitForElementToBeVisible(urlVerificationSignUpPage, 6);
		String url = urlVerificationSignUpPage.getText();
		return url;
	}

	public LoginPage clickOnLoginButtonLink() {
		Action.scrollByVisibilityOfElement(loginLink); // scrolling the window so that login link is visible
		Action.waitForElementToBeClickable(loginLink, 5);
		Action.clickUsingJavaScript(driver, loginLink);
		Log.info("Clicked on Login Link present on signup page");
		return new LoginPage(driver);
	}

	public void clickOnPasswordEyeImage()
	{
		Action.click(passwordEye);
	}
	
	public void clickOnConfirmPasswordEyeImage()
	{
		Action.click(confirmPasswordEye);
	}
	
	public void clickOnPramotionalEmailCheckbox()
	{
		Action.click(promotionalEmailCheckbox);
	}
	
	public void clickOnTermsAndConditionCheckbox()
	{
		Action.click(termsAndConditionsCheckbox);
	}
	
	public String getPasswordText()
	{
		Action.waitForElementToBeVisible(passwordField, 10);
		return passwordField.getAttribute("value");
	}
	
	public String getConfirmPasswordText()
	{
		Action.waitForElementToBeVisible(confirmPasswordField, 10);
		return confirmPasswordField.getAttribute("value");
	}
}

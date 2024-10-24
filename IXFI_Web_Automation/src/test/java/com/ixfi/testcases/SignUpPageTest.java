package com.ixfi.testcases;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.ixfi.basepage.BaseClass;
import com.ixfi.pageobjects.IndexPage;
import com.ixfi.pageobjects.LoginPage;
import com.ixfi.pageobjects.SignUpPage;
import com.ixfi.utility.Log;

public class SignUpPageTest extends BaseClass {
	IndexPage index;
	SignUpPage signUpPage;
	LoginPage loginPage;

	@BeforeMethod
	public void setUpTest() {
		index = new IndexPage(driver); // Initialize IndexPage with driver after setup
		// login=new LoginPage(driver);
	}

	@Test
	public void verifyCreateAccountByEmailFunctionalityTest() throws InterruptedException {
		Log.startTestCase("verifyCreateAccountByEmailFunctionalityTest");
		signUpPage = index.clickOnSignUpButton();
		// Log.info("clicked On SignUp Button");
		signUpPage.createAccountByUsingEmail();
		Log.info("Entered all the details in signup form - SignUp by email");
		// Assert.assertFalse(signUpPage.clickOnCreateAccountButton());//after clicking
		// on terms and condition the create account buttons attribute will be changed
		// from disable to empty i.e. enabled
		// therefor it will return us false
		Log.endTestCase("verifyCreateAccountByEmailFunctionalityTest");
	}

	@Test
	public void verifyCreateAccountByPhoneFunctionalityTest() throws InterruptedException {
		Log.startTestCase("verifyCreateAccountByPhoneFunctionalityTest");
		signUpPage = index.clickOnSignUpButton();
		Log.info("Clicked on signup button");
		signUpPage.createAccountByUsingPhoneNumber();
		Log.endTestCase("verifyCreateAccountByPhoneFunctionalityTest");
	}

	@Test
	public void verifyCreateAccountButtonRemainsDisabledUntilTermsAndConditionsAcceptanceTest() {
		Log.startTestCase("verifyCreateAccountButtonRemainsDisabledUntilTermsAndConditionsAcceptanceTest");
		signUpPage = index.clickOnSignUpButton();
		Log.info("Clicked on Signup button");
		Assert.assertTrue(signUpPage.clickOnCreateAccountButton());
		Log.info("Veried that create account button is clicked");
		Log.endTestCase("verifyCreateAccountButtonRemainsDisabledUntilTermsAndConditionsAcceptanceTest");
	}

	@Test
	public void verifyThatUserIsNavigatedToSignUpPageAfterClickingOnRegisterButton() {
		Log.startTestCase("verifyThatUserIsNavigatedToSignUpPageAfterClickingOnRegisterButtonTest");
		signUpPage = index.clickOnSignUpButton();
		Log.info("Clicked on signup button");
		String actualURL = signUpPage.verifyUserIsNavigatedToSignUpPage();
		Log.info("Retrieved the Actual URL from website: " + actualURL);
		String expectedURL = prop.getProperty("signUpPageURL");
		Log.info("Retrieved the expected title from properties file: " + expectedURL);
		Assert.assertEquals(actualURL, expectedURL, "User is not navigated to SignUpPage");
		Log.info("Both the titles are matched ");
		Log.endTestCase("verifyThatUserIsNavigatedToSignUpPageAfterClickingOnRegisterButtonTest");

	}

	// Verify User is navigated to LoginPage from SignUpPage
	@Test
	public void verifyThatUserIsNavigatedToLoginPageAfterClickinOnLoginLinkPresentOnSignUpPageTest()
			throws InterruptedException {

		Log.startTestCase("verifyThatUserIsNavigatedToLoginPageAfterClickinOnLoginLinkPresentOnSignUpPageTest");
		signUpPage = index.clickOnSignUpButton();
		Log.info("Clicked on SignUp Button");
		loginPage = signUpPage.clickOnLoginButtonLink();
		Thread.sleep(5000);
		// String actualURL=loginPage.verifyUserIsNavigatedToLoginPage();
		// System.out.println(actualURL);
		// String expectedURL=prop.getProperty("loginPageURL");
		Assert.assertEquals(loginPage.verifyUserIsNavigatedToLoginPage(), prop.getProperty("loginPageURL"),
				"User is not naviagted to loginPage from signUpPage");
		Log.info("Retreievd both actual and expected url and matched");
		Log.endTestCase("verifyThatUserIsNavigatedToLoginPageAfterClickinOnLoginLinkPresentOnSignUpPageTest");
	}
}

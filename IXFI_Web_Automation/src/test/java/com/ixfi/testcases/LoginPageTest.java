/**
 * 
 */
package com.ixfi.testcases;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.ixfi.actiondriver.Action;
import com.ixfi.basepage.BaseClass;
import com.ixfi.dataprovider.DataProviders;
import com.ixfi.pageobjects.IndexPage;
import com.ixfi.pageobjects.LoginPage;
import com.ixfi.pageobjects.SignUpPage;
import com.ixfi.utility.Log;

/**
 **/
public class LoginPageTest extends BaseClass{
	
	IndexPage index;
	LoginPage login;
	SignUpPage signUp;
	
	

    @BeforeMethod
    public void setUpTest() {
        index = new IndexPage(driver); // Initialize IndexPage with driver after setup
       // login=new LoginPage(driver);
    }
    
    @Test
    public void verifyThatUserIsNavigatedToLoginPage()
    {
    	Log.startTestCase("verifyThatUserIsNavigatedToLoginPage");
    	login=index.clickOnLoginButton();
    	Log.info("User is clicked on login button");
    	String actualURL=login.verifyUserIsNavigatedToLoginPage();
    	Log.info("User retrived the Actual URL");
    	System.out.println(actualURL);
    	String ExpectedURL=prop.getProperty("loginPageURL");
    	Log.info("User Retrived the Expected URL");
    	Log.info("Verifying the actual and expected URL, if both are same or not");
    	Assert.assertEquals(actualURL, ExpectedURL,"User is not navigated to LoginPage");
    	Log.info("Acutal and expected URL is same");
    	Log.endTestCase("verifyThatUserIsNavigatedToLoginPage");
    }
    
    //@Test(dataProvider="Credentials", dataProviderClass=DataProviders.class)
    @Test
    public void verifyLoginByEmailFunctionality() throws InterruptedException
    {
    	Log.startTestCase("verifyLoginByEmailFunctionalityTest");
    	//index.acceptCookies();
    	// Action.waitForLoaderToDisappear(driver);
    	login=index.clickOnLoginButton();
    	Log.info("Clicked on login button");
    	//login.clickOnemailButton();
    	String title=login.loginUsingEmailID(prop.getProperty("email"), prop.getProperty("password"));
    	Log.info("Entered email and password and clicked on Login button");
    	Log.info("Retrieved the login page title: "+title);
    	//String title=login.loginUsingEmailID(uname,pswd);//this will take the data from excell sheet
    	String expected=prop.getProperty("title");
    	Assert.assertEquals(title, expected,"Both the titles are not matched, please check again");
    	Log.info("Verified "+title+" With Expected title "+expected);
    	Log.endTestCase("verifyLoginByEmailFunctionalityTest");
    }
    
    @Test
    public void verifyLoginByPhoneNumberFunctionality() throws InterruptedException
    {	
    	Log.startTestCase("verifyLoginByPhoneNumberFunctionalityTest");
    	//Action.waitForLoaderToDisappear(driver);
    	login=index.clickOnLoginButton();
    	Log.info("Clicked on login button ");
    	//login.clickOnPhoneButton();
    	login.loginUsingPhoneNumber(prop.getProperty("phone"), prop.getProperty("password"));
    	Log.info("Entered phone number: "+prop.getProperty("phone")+" and Password into the textfield and clicked on login button");
    	Log.endTestCase("verifyLoginByPhoneNumberFunctionalityTest");
    }
	
    @Test
    public void verifyThatUserIsNavigatedToSignUpPageByClickingSingUpNowLink()
    {
    	Log.startTestCase("verifyThatUserIsNavigatedToSignUpPageByClickingSingUpNowLinkTest");
    	login=index.clickOnLoginButton();
    	Log.info("Clicked on login button");
    	signUp=login.clickOnSignUpNowLink();
    	Log.info("Clicked on signUpNow link ");
    	Assert.assertEquals(signUp.verifyUserIsNavigatedToSignUpPage(), prop.getProperty("signUpPageURL"));
    	Log.info("Verified that user is navigated to signup page from login page by clicking on singup link");
    	Log.endTestCase("verifyThatUserIsNavigatedToSignUpPageByClickingSingUpNowLinkTest");
    	
    }

}

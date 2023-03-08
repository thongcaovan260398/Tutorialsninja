package com.tutorialsninja.qa.testcases;

import com.beust.ah.A;
import com.tutorialsninja.qa.base.Base;
import com.tutorialsninja.qa.pages.AccountSuccessPage;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.RegisterPage;
import com.tutorialsninja.qa.utils.Utilities;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Register extends Base {
    public Register(){
        super();
    }
    WebDriver driver;

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }

    @BeforeMethod
    public void Setup(){
        driver =initializeBrowserAndOpenApplication(prop.getProperty("browser"));
        HomePage homePage = new HomePage(driver);
        homePage.clickMyAccount();
        homePage.selectRegisterOption();

    }

    @Test(priority = 1)
    public void verifyRegisterAnAccountWithMandatoryFields(){
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.enterFirstName(dataProp.getProperty("firstName"));
        registerPage.enterLastName(dataProp.getProperty("lastName"));
        registerPage.enterEmail(Utilities.generateEmailWithTimeStamp());
        registerPage.enterTelephone(dataProp.getProperty("telephoneNumber"));
        registerPage.enterPassword(prop.getProperty("validPassword"));
        registerPage.enterConfirmPassword(prop.getProperty("validPassword"));
        registerPage.selectPrivacyPolicy();
        registerPage.clickContinueButton();

        AccountSuccessPage accountSuccessPage = new AccountSuccessPage(driver);
        String actualSuccessHeading = accountSuccessPage.retrieveAccountSuccessPageHeading() ;
        Assert.assertEquals(actualSuccessHeading,dataProp.getProperty("accountCreateSuccessfullyHeading"),"Account success page is not displayed");

    }

    @Test(priority = 2)
    public void verifyRegisterAccountByProvidingAllFields(){
        RegisterPage registerPage = new RegisterPage(driver);

        registerPage.enterFirstName(dataProp.getProperty("firstName"));
        registerPage.enterLastName(dataProp.getProperty("lastName"));
        registerPage.enterEmail(Utilities.generateEmailWithTimeStamp());
        registerPage.enterTelephone(dataProp.getProperty("telephoneNumber"));
        registerPage.enterPassword(prop.getProperty("validPassword"));
        registerPage.enterConfirmPassword(prop.getProperty("validPassword"));
        registerPage.selectSubField();
        registerPage.selectPrivacyPolicy();
        registerPage.clickContinueButton();

        AccountSuccessPage accountSuccessPage = new AccountSuccessPage(driver);
        String actualSuccessHeading = accountSuccessPage.retrieveAccountSuccessPageHeading() ;
        Assert.assertEquals(actualSuccessHeading,dataProp.getProperty("accountCreateSuccessfullyHeading"),"Account success page is not displayed");

    }

    @Test(priority = 3)
    public void verifyRegisterAccountWithExistingEmailAddress(){
        RegisterPage registerPage = new RegisterPage(driver);

        registerPage.enterFirstName(dataProp.getProperty("firstName"));
        registerPage.enterLastName(dataProp.getProperty("lastName"));
        registerPage.enterEmail(prop.getProperty("validEmail"));
        registerPage.enterTelephone(dataProp.getProperty("telephoneNumber"));
        registerPage.enterPassword(prop.getProperty("validPassword"));
        registerPage.enterConfirmPassword(prop.getProperty("validPassword"));
        registerPage.selectSubField();
        registerPage.selectPrivacyPolicy();
        registerPage.clickContinueButton();

        String actualWarning = registerPage.retrieveDuplicateEmailAddressWarning();
        Assert.assertTrue(actualWarning.contains(dataProp.getProperty("duplicateEmailWarning")),"Warning message regaring duplicate email address is not displayed");

    }

    @Test(priority = 4)
    public void verifyRegisterAccountWithoutFillingAnyDetails(){

        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.clickContinueButton();

        String actualWarningPrivacyPolicy = registerPage.retrievePrivacyPolicyWarning();
        Assert.assertTrue(actualWarningPrivacyPolicy.contains(dataProp.getProperty("privacyWarningMessage")),"Privacy Policy Warning message is not displayed");

    }


}

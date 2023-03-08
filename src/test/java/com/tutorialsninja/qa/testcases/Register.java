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
    RegisterPage registerPage;
    AccountSuccessPage accountSuccessPage;

    public Register() {
        super();
    }

    WebDriver driver;

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    @BeforeMethod
    public void Setup() {
        driver = initializeBrowserAndOpenApplication(prop.getProperty("browser"));
        HomePage homePage = new HomePage(driver);
        registerPage = homePage.navigateToRegisterPage();

    }

    @Test(priority = 1)
    public void verifyRegisterAnAccountWithMandatoryFields() {
        accountSuccessPage = registerPage.registerWithMandatoryFields(dataProp.getProperty("firstName"), dataProp.getProperty("lastName"), Utilities.generateEmailWithTimeStamp(), dataProp.getProperty("telephoneNumber"), prop.getProperty("validPassword"));
        Assert.assertEquals(accountSuccessPage.retrieveAccountSuccessPageHeading(), dataProp.getProperty("accountCreateSuccessfullyHeading"), "Account success page is not displayed");
    }

    @Test(priority = 2)
    public void verifyRegisterAccountByProvidingAllFields() {
        accountSuccessPage = registerPage.registerWithAllFields(dataProp.getProperty("firstName"), dataProp.getProperty("lastName"), Utilities.generateEmailWithTimeStamp(), dataProp.getProperty("telephoneNumber"), prop.getProperty("validPassword"));
        Assert.assertEquals(accountSuccessPage.retrieveAccountSuccessPageHeading(), dataProp.getProperty("accountCreateSuccessfullyHeading"), "Account success page is not displayed");

    }

    @Test(priority = 3)
    public void verifyRegisterAccountWithExistingEmailAddress() {
        accountSuccessPage = registerPage.registerWithAllFields(dataProp.getProperty("firstName"), dataProp.getProperty("lastName"), prop.getProperty("validEmail"), dataProp.getProperty("telephoneNumber"), prop.getProperty("validPassword"));
        Assert.assertTrue(registerPage.retrieveDuplicateEmailAddressWarning().contains(dataProp.getProperty("duplicateEmailWarning")), "Warning message regaring duplicate email address is not displayed");

    }

    @Test(priority = 4)
    public void verifyRegisterAccountWithoutFillingAnyDetails() {
        accountSuccessPage = registerPage.clickContinueButton();
        Assert.assertTrue(registerPage.displayStatusOfWarningMessages(dataProp.getProperty("privacyWarningMessage")), "Warning message(s) are not displayed");

    }


}

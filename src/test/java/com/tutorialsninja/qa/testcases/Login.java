package com.tutorialsninja.qa.testcases;

import com.tutorialsninja.qa.base.Base;
import com.tutorialsninja.qa.pages.AccountPage;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.LoginPage;
import com.tutorialsninja.qa.utils.Utilities;
import org.apache.commons.logging.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


public class Login extends Base {
    public Login(){
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
        homePage.selectLoginOption();
    }

    @Test(priority = 1,dataProvider = "validCredentialsSupplier")
    public void verifyLoginWithValidCredentials(String email, String password){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterEmailAddress(email);
        loginPage.enterPasswordField(password);
        loginPage.clickLogin();

        AccountPage accountPage=new AccountPage(driver);
        Assert.assertTrue(accountPage.getDisplayStatusOfEditYourAcctountInformationOption(),"Edit your account information is not displayed");
    }

    @DataProvider(name = "validCredentialsSupplier")
    public Object[][] supplyTestData(){
        Object [][] data = Utilities.getTestDataFromExcel("Login");
        return data;
    }

    @Test(priority = 2)
    public void verifyLoginWithInvalidCredentials(){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterEmailAddress(Utilities.generateEmailWithTimeStamp());
        loginPage.enterPasswordField(dataProp.getProperty("invalidPassword"));
        loginPage.clickLogin();

        String actualWarningMessage = loginPage.retrieveEmailPasswordNotMatchingWarningMessageText();
        String expectedWarningMessage =  dataProp.getProperty("emailPasswordNoMatching");
        Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage),"Expected Warning message is not displayed ");
    }

    @Test(priority = 3)
    public void verifyLoginWithValidEmailAndInvalidPassword(){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterEmailAddress(prop.getProperty("validEmail"));
        loginPage.enterPasswordField(dataProp.getProperty("invalidPassword"));
        loginPage.clickLogin();


        String actualWarningMessage = loginPage.retrieveEmailPasswordNotMatchingWarningMessageText();
        String expectedWarningMessage =  dataProp.getProperty("emailPasswordNoMatching");
        Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage),"Expected Warning message is not displayed ");
    }

    @Test(priority = 4)
    public void verifyLoginWithInvalidEmailAndValidPassword(){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterEmailAddress(Utilities.generateEmailWithTimeStamp());
        loginPage.enterPasswordField(prop.getProperty("validPassword"));
        loginPage.clickLogin();


        String actualWarningMessage = loginPage.retrieveEmailPasswordNotMatchingWarningMessageText();
        String expectedWarningMessage =  dataProp.getProperty("emailPasswordNoMatching");
        Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage),"Expected Warning message is not displayed ");
    }

    @Test(priority = 5)
    public void verifyLoginWithoutProvidingCredentials(){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickLogin();

        String actualWarningMessage = loginPage.retrieveEmailPasswordNotMatchingWarningMessageText();
        String expectedWarningMessage =  dataProp.getProperty("emailPasswordNoMatching");
        Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage),"Expected Warning message is not displayed ");
    }


}

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
    LoginPage loginPage;
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
        loginPage = homePage.navigateToLoginPage();
    }

    @Test(priority = 1,dataProvider = "validCredentialsSupplier")
    public void verifyLoginWithValidCredentials(String email, String password){
        AccountPage accountPage = loginPage.login(email,password);
        Assert.assertTrue(accountPage.getDisplayStatusOfEditYourAcctountInformationOption(),"Edit your account information is not displayed");
    }

    @DataProvider(name = "validCredentialsSupplier")
    public Object[][] supplyTestData(){
        Object [][] data = Utilities.getTestDataFromExcel("Login");
        return data;
    }

    @Test(priority = 2)
    public void verifyLoginWithInvalidCredentials(){
        loginPage.login(Utilities.generateEmailWithTimeStamp(),dataProp.getProperty("invalidPassword"));
        Assert.assertTrue( loginPage.retrieveEmailPasswordNotMatchingWarningMessageText().contains(dataProp.getProperty("emailPasswordNoMatching")),"Expected Warning message is not displayed ");
    }

    @Test(priority = 3)
    public void verifyLoginWithValidEmailAndInvalidPassword(){
        loginPage.login(prop.getProperty("validEmail"),dataProp.getProperty("invalidPassword"));
        Assert.assertTrue(loginPage.retrieveEmailPasswordNotMatchingWarningMessageText().contains(dataProp.getProperty("emailPasswordNoMatching")),"Expected Warning message is not displayed ");
    }

    @Test(priority = 4)
    public void verifyLoginWithInvalidEmailAndValidPassword(){
       loginPage.login(Utilities.generateEmailWithTimeStamp(),prop.getProperty("validPassword"));
        Assert.assertTrue(loginPage.retrieveEmailPasswordNotMatchingWarningMessageText().contains(dataProp.getProperty("emailPasswordNoMatching")),"Expected Warning message is not displayed ");
    }

    @Test(priority = 5)
    public void verifyLoginWithoutProvidingCredentials(){
        loginPage.clickLogin();
        Assert.assertTrue(loginPage.retrieveEmailPasswordNotMatchingWarningMessageText().contains(dataProp.getProperty("emailPasswordNoMatching")),"Expected Warning message is not displayed ");
    }


}

package com.tutorialsninja.qa.testcases;

import com.tutorialsninja.qa.base.Base;

import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.SearchPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.naming.directory.SearchResult;


public class Search extends Base {
    public Search(){
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
    }

    @Test(priority = 1)
    public void verifySearchWithValidProduct(){
        HomePage homePage = new HomePage(driver);
        SearchPage searchPage = new SearchPage(driver);
        homePage.enterProductIntoSearch(dataProp.getProperty("validProduct"));
        homePage.clickSearchButton();


        Assert.assertTrue(searchPage.displayStatusOfHPValidProduct(),"Valid product is not displayed");
    }

    @Test(priority = 2)
    public void verifySearchWithInvalidProduct(){
        HomePage homePage = new HomePage(driver);
        SearchPage searchPage = new SearchPage(driver);

        homePage.enterProductIntoSearch(dataProp.getProperty("invalidProduct"));
        homePage.clickSearchButton();


        String actualSearchMessage =searchPage.retrieveNoProductMessageText();
        Assert.assertEquals(actualSearchMessage,dataProp.getProperty("noProductTextInSearchResults"),"No product message in search results is not displayed");
    }

    @Test(priority = 3)
    public void verifySearchWithoutAnyProduct(){
        HomePage homePage = new HomePage(driver);
        SearchPage searchPage = new SearchPage(driver);
        homePage.clickSearchButton();

        String actualSearchMessage = searchPage.retrieveNoProductMessageText();
        Assert.assertEquals(actualSearchMessage,dataProp.getProperty("noProductTextInSearchResults"),"No product message in search results is not displayed");
    }
}

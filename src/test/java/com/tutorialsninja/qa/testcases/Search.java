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
    SearchPage searchPage;
    HomePage homePage;
    public Search(){
        super();
    }
    public WebDriver driver;

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }

    @BeforeMethod
    public void Setup(){
        driver =initializeBrowserAndOpenApplication(prop.getProperty("browser"));
        homePage = new HomePage(driver);
    }

    @Test(priority = 1)
    public void verifySearchWithValidProduct(){
        searchPage = homePage.searchForAProduct(dataProp.getProperty("validProduct"));
        Assert.assertTrue(searchPage.displayStatusOfHPValidProduct(),"Valid product is not displayed");
    }

    @Test(priority = 2)
    public void verifySearchWithInvalidProduct(){
        searchPage = homePage.searchForAProduct(dataProp.getProperty("invalidProduct"));
        Assert.assertEquals(searchPage.retrieveNoProductMessageText(),dataProp.getProperty("noProductTextInSearchResults"),"No product message in search results is not displayed");
    }

    @Test(priority = 3, dependsOnMethods ={"verifySearchWithValidProduct","verifySearchWithInvalidProduct"} )
    public void verifySearchWithoutAnyProduct(){
        searchPage = homePage.clickSearchButton();
        Assert.assertEquals(searchPage.retrieveNoProductMessageText(),dataProp.getProperty("noProductTextInSearchResults"),"No product message in search results is not displayed");
    }
}

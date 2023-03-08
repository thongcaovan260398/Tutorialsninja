package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
    WebDriver driver;
    @FindBy(xpath = "//span[contains(text(),'My Account')]")
    WebElement myAccountDropMenu;

    @FindBy(xpath = "//a[contains(text(),'Login')]")
    WebElement loginOption;

    @FindBy(xpath = "//a[contains(text(),'Register')]")
    private WebElement registerOption;

    @FindBy(name = "search")
    private WebElement searchField;

    @FindBy(xpath = "//div[@id='search']/descendant::button")
    private WebElement searchButton;

    public HomePage(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    public void clickMyAccount(){
        myAccountDropMenu.click();
    }
    public void enterProductIntoSearch(String product){
        searchField.sendKeys(product);
    }
    public LoginPage selectLoginOption(){
        loginOption.click();
        return new LoginPage(driver);
    }
    public RegisterPage selectRegisterOption(){
        registerOption.click();
        return new RegisterPage(driver);
    }
    public RegisterPage navigateToRegisterPage(){
        myAccountDropMenu.click();
        registerOption.click();
        return new RegisterPage(driver);
    }
    public LoginPage navigateToLoginPage(){
        myAccountDropMenu.click();
        loginOption.click();
        return new LoginPage(driver);
    }
    public SearchPage clickSearchButton(){
        searchButton.click();
        return new SearchPage(driver);
    }
    public SearchPage searchForAProduct(String productText){
        searchField.sendKeys(productText);
        searchButton.click();
        return new SearchPage(driver);
    }


}

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

    public void selectLoginOption(){
        loginOption.click();
    }
    public void selectRegisterOption(){
        registerOption.click();
    }
    public void clickSearchButton(){
        searchButton.click();
    }

    //search
    public void enterProductIntoSearch(String product){
        searchField.sendKeys(product);
    }

}

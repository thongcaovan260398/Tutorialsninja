package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    WebDriver driver;
    @FindBy(id = "input-email")
    private WebElement emailAddressField;

    @FindBy(id = "input-password")
    private WebElement passwordField;

    @FindBy(xpath = "//input[@value='Login']")
    private WebElement loginButton;

    @FindBy(xpath = "//body/div[@id='account-login']/div[1]")
    private WebElement emailPasswordNotMatchingWarning;

    public LoginPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public void enterEmailAddress(String emailText){
        emailAddressField.sendKeys(emailText);
    }

    public void enterPasswordField(String passwordText){
        passwordField.sendKeys(passwordText);
    }

    public void clickLogin(){
        loginButton.click();
    }

    public String retrieveEmailPasswordNotMatchingWarningMessageText(){
        String warningText = emailPasswordNotMatchingWarning.getText();
        return warningText;
    }
}

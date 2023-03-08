package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage {
    WebDriver driver;

    @FindBy(id = "input-firstname")
    private WebElement firstNameField;

    @FindBy(id = "input-lastname")
    private WebElement lastNameField;

    @FindBy(id = "input-email")
    private WebElement emailField;

    @FindBy(id = "input-telephone")
    private WebElement telephoneField;

    @FindBy(id = "input-password")
    private WebElement passwordField;

    @FindBy(id = "input-confirm")
    private WebElement confirmPasswordField;
    @FindBy(name = "agree")
    private WebElement privacyPolicyField;
    @FindBy(xpath = "//input[@value='Continue']")
    private WebElement continueButton;
    @FindBy(xpath = "//input[@name='newsletter'][@value='1']")
    private WebElement subField;

    @FindBy(xpath = "//div[contains(@class,'alert-dismissible')]")
    private WebElement duplicateEmailAddressWarning;
    @FindBy(xpath = "//div[contains(@class,'alert-dismissible')]")
    private WebElement privacyPolicyWarning;
    public RegisterPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public void enterFirstName(String firstName){
        firstNameField.sendKeys(firstName);
    }

    public void enterLastName(String lastName){
        lastNameField.sendKeys(lastName);
    }

    public void enterEmail(String email){
        emailField.sendKeys(email);
    }
    public void enterTelephone(String telephone){
        telephoneField.sendKeys(telephone);
    }
    public void enterPassword(String password){
        passwordField.sendKeys(password);
    }
    public void enterConfirmPassword(String passwordConfirm){
        confirmPasswordField.sendKeys(passwordConfirm);
    }

    public void selectPrivacyPolicy(){
        privacyPolicyField.click();
    }

    public void clickContinueButton(){
        continueButton.click();
    }
    public void selectSubField(){
        subField.click();
    }
    public String retrieveDuplicateEmailAddressWarning(){
        return duplicateEmailAddressWarning.getText();

    }
    public String retrievePrivacyPolicyWarning(){
        return privacyPolicyWarning.getText();
    }

}

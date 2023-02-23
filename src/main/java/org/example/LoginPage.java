package org.example;

import org.AbstractComponents.AbstractComponents;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends AbstractComponents {

    WebDriver driver;

    public LoginPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }



    //Fetching WebElement for Email text-field
    @FindBy(id = "userEmail")
    WebElement userEmail;



    //Fetching WebElement for Password text-field
    @FindBy(id = "userPassword")
    WebElement userPassword;



    //Fetching WebElement for Login button
    @FindBy(id = "login")
    WebElement loginSubmit;



    //GoTo website
    public void goTo(){
        driver.get("https://rahulshettyacademy.com/client");
    }



    //Perform login action
    public void loginToApp(String email, String password){

        userEmail.sendKeys(email);
        userPassword.sendKeys(password);
        loginSubmit.click();
    }



}

package org.example.pom;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.codeborne.selenide.Condition.visible;

public class LoginPage {
    //private final WebDriverWait wait;
    @FindBy(css="form#login input[type='text']")
    private SelenideElement userNameField;
    @FindBy(css="form#login input[type='password']")
    private SelenideElement passwordField;
    @FindBy(css="form#login button")
    private SelenideElement loginButton;
    @FindBy(css="div#app > main > div > div > div.error-block.svelte-uwkxn9 > p:nth-child(2)")
    private SelenideElement invalidField;

//    public LoginPage(WebDriverWait wait, WebDriver driver) {
//        this.wait = wait;
//        PageFactory.initElements(driver, this);
//    }

    public void login(String userName, String password){

        setValueInUserNameField(userName);
        setValueInPasswordField(password);
        clickOnLoginButton();
    }

    public void setValueInUserNameField(String userName){
        userNameField.shouldBe(visible).setValue(userName);

    }

    public void setValueInPasswordField(String password){
        passwordField.shouldBe(visible).setValue(password);

    }

    public void clickOnLoginButton(){
        loginButton.shouldBe(visible).click();
    }

    public SelenideElement getInvalidField() {
        return invalidField;
    }
}

package org.example.tests.homework2;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import org.example.pom.LoginPage;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;


public class LoginTest {

    private WebDriver driver;

    @BeforeEach
    public void setUpTest(){
        Selenide.open("https://test-stand.gb.ru/login");
        driver = WebDriverRunner.getWebDriver();
    }
    @Test
    public void loginWithoutValuesTest(){

        LoginPage loginPage = Selenide.page(LoginPage.class);
        loginPage.login("", "");

        String actualResult = loginPage.getInvalidField().getText();

        Assertions.assertEquals("Invalid credentials.", actualResult);

    }

    @AfterEach
    public void close(){
        WebDriverRunner.closeWebDriver();
    }
}

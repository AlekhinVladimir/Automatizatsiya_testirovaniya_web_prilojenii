package org.example.tests.homework1;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import org.apache.commons.io.FileUtils;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalTime;
import java.util.Date;

public class GBTests {
    private final String USERNAME = "GB202302c48fb20";
    private final String PASSWORD = "7abe49a426";
    private String pathToChromeDriver;
    private String testStandUrl;
    private WebDriver webDriver;

    @BeforeEach
    public void setUpTest(){
        Selenide.open("https://test-stand.gb.ru/login");
        webDriver = WebDriverRunner.getWebDriver();
    }

    @Test
    public void testAddGroup() throws InterruptedException {
        pathToChromeDriver = "src/main/resources/chromedriver.exe";
        System.setProperty("webChromeDriver", pathToChromeDriver);

//        webDriver = new ChromeDriver();
//        testStandUrl = "https://test-stand.gb.ru/login";
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        webDriver.get("https://test-stand.gb.ru/login");

        WebElement userNameField = webDriver.findElement(By.cssSelector("form#login input[type='text']"));
        userNameField.sendKeys(USERNAME);

        WebElement userPasswordField = webDriver.findElement(By.cssSelector("form#login input[type='password']"));
        userPasswordField.sendKeys(PASSWORD);

        WebElement loginButton = webDriver.findElement(By.cssSelector("form#login button"));
        loginButton.click();

        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        Thread.sleep(3000);
        WebElement createButton = webDriver.findElement(By.cssSelector("button#create-btn"));
        createButton.click();

        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        String loginAndName = LocalTime.now().toString();
        WebElement loginField = webDriver.findElement(By.cssSelector("form#upsert-item > div:nth-child(5) > label > input"));
        loginField.sendKeys(loginAndName);
        WebElement firstNameField = webDriver.findElement(By.cssSelector("form#upsert-item > div:nth-child(1) > label > input"));
        firstNameField.sendKeys(loginAndName);

        WebElement saveButton = webDriver.findElement(By.cssSelector("form#upsert-item > div.submit > button"));
        saveButton.click();

        Thread.sleep(3000);

        WebElement firstNameLink = webDriver.findElement(By.cssSelector("div#app > main > div > div > div.mdc-data-table > div.mdc-data-table__table-container > table > tbody > tr:nth-child(1) > td:nth-child(2)"));
        String actualFirstName = firstNameLink.getText();

        File screenShot = ((TakesScreenshot)webDriver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(screenShot, new File("src/test/resources/screenShot.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Assertions.assertEquals(loginAndName, actualFirstName);


    }

    @AfterEach
    public void close(){
        WebDriverRunner.closeWebDriver();
    }

}

package org.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Main {
    public static void main(String[] args) {
        String pathToChromeDriver = "src/main/resources/chromedriver.exe";
        System.setProperty("webChromeDriver", pathToChromeDriver);

        WebDriver webDriver = new ChromeDriver();
        webDriver.get("https://www.google.com");

        webDriver.quit();

    }
}
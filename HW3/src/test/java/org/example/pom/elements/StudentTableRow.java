package org.example.pom.elements;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class StudentTableRow {

    private SelenideElement root;

    public StudentTableRow(SelenideElement root) {
        this.root = root;
    }

    public String getName(){
        return root.findElement(By.cssSelector("td:nth-child(2)")).getText();
    }
    public String getStatus(){
        return root.findElement(By.cssSelector("td:nth-child(4)")).getText();
    }
}

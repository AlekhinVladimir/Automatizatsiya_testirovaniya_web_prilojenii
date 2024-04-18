package org.example.pom;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.example.pom.elements.StudentTableRow;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class StudentPage {

    @FindBy(css="button#create-btn")
    private SelenideElement addStudentButton;
    private SelenideElement quantityStudentField = $("div.mdc-data-table__pagination-total");

    @FindBy(css="form#upsert-item > div:nth-child(1) > label > input")
    private SelenideElement firstNameField;
    @FindBy(css="form#upsert-item > div:nth-child(5) > label > input")
    private SelenideElement loginField;
    @FindBy(css="form#upsert-item > div.submit > button")
    private SelenideElement saveButton;
    @FindBy(css="div#app > main > div > div > div.mdc-dialog.mdc-dialog--open > div.mdc-dialog__container > div > div.form-modal-header.mdc-dialog__header > button")
    private SelenideElement closeButton;
    private ElementsCollection rowsInStudentTable = $$("tbody.mdc-data-table__table-content");

    public String getQuantityStudentField() {
        char[] result = {getCharOfQuantityStudentField(8), getCharOfQuantityStudentField(9)};
        return new String(result);
    }

    private char getCharOfQuantityStudentField(int index){
        return quantityStudentField.shouldBe(visible).getText().charAt(index);
    }

    public void addStudent(String loginAndName){
        addStudentButton.shouldBe(visible).click();

        firstNameField.shouldBe(visible).sendKeys(loginAndName);
        loginField.shouldBe(visible).sendKeys(loginAndName);
        saveButton.shouldBe(visible).click();
        closeButton.shouldBe(visible).click();

    }

    public String getStatusInStudentTableRow(){
        return new StudentTableRow(rowsInStudentTable.first()).getStatus();
    }

    private SelenideElement getRowsInStudentTable() {
        return rowsInStudentTable.shouldHave(sizeGreaterThan(0))
                .first();

    }
}

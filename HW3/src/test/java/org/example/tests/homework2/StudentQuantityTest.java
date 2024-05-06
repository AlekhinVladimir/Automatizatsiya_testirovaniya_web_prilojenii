package org.example.tests.homework2;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import org.example.pom.LoginPage;
import org.example.pom.StudentPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import java.time.LocalTime;

public class StudentQuantityTest {
    private final String USERNAME = "GB202302c48fb20";
    private final String PASSWORD = "7abe49a426";
    private StudentPage studentPage;
    private WebDriver driver;

    @BeforeEach
    public void setUp(){

        Selenide.open("https://test-stand.gb.ru/login");
        driver = WebDriverRunner.getWebDriver();
        LoginPage loginPage = Selenide.page(LoginPage.class);
        loginPage.login(USERNAME, PASSWORD);
        studentPage = Selenide.page(StudentPage.class);
        Selenide.sleep(2000);
    }
    @Test
    public void studentQuantityTest(){

        int quantityBefore = Integer.parseInt(studentPage.getQuantityStudentField());
        String loginAndName = LocalTime.now().toString();
        studentPage.addStudent(loginAndName);
        Selenide.sleep(2000);
        int quantityAfter = Integer.parseInt(studentPage.getQuantityStudentField());
        Assertions.assertEquals(quantityAfter, quantityBefore + 1);

    }

    @Test
    public void studentStatusTest(){

        String actualStatus = studentPage.getStatusInStudentTableRow();
        Assertions.assertEquals("active", actualStatus);

    }

    @AfterEach
    public void close(){
        WebDriverRunner.closeWebDriver();
    }
}

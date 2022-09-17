package login;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class Login {

    private WebDriver browser;

    @BeforeAll
    public void init(){
        System.setProperty("webdriver.chrome.driver","chromedriver.exe");
        this.browser = new ChromeDriver();
    }

    @Test
    public void deveEfetuarLoginComDadosValidos(){
        browser.navigate().to("http://localhost:8080/leilao");
        browser.findElement(By.id("username")).sendKeys("fulano");
        browser.findElement(By.id("password")).sendKeys("pass");
        browser.findElement(By.id("login-form")).submit();

        Assertions.assertNotEquals("http://localhost:8080/leilao", browser.getCurrentUrl());
        Assertions.assertEquals("fulano",browser.findElement(By.id("usuario-logado")).getText());
    }
}

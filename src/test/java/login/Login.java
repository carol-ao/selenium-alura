package login;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class Login {

    private WebDriver browser;
    private static final String URL_LOGIN = "http://localhost:8080/login";

    @BeforeAll
    public static void init(){
        System.setProperty("webdriver.chrome.driver","chromedriver.exe");
    }

    @BeforeEach
    public void beforeEach(){
        this.browser = new ChromeDriver();
        browser.navigate().to(URL_LOGIN);
    }

    @AfterEach
    public void afterEach(){
        browser.quit();
    }

    @Test
    public void deveEfetuarLoginComDadosValidos(){
        browser.findElement(By.id("username")).sendKeys("fulano");
        browser.findElement(By.id("password")).sendKeys("pass");
        browser.findElement(By.id("login-form")).submit();

        Assertions.assertFalse("http://localhost:8080/login".equals(browser.getCurrentUrl()));
        Assertions.assertEquals("fulano",browser.findElement(By.id("usuario-logado")).getText());
    }

    @Test
    public void deveNaoEfetuarLoginComDadosInvalidos(){
        browser.findElement(By.id("username")).sendKeys("invalido");
        browser.findElement(By.id("password")).sendKeys("123");
        browser.findElement(By.id("login-form")).submit();

        Assertions.assertTrue("http://localhost:8080/login?error".equals(browser.getCurrentUrl()));
        Assertions.assertTrue(browser.getPageSource().contains("Usuário e senha inválidos."));
        Assertions.assertThrows(NoSuchElementException.class, () -> browser.findElement(By.id("usuario-logado")));
    }

    @Test
    public void naoDeveAcessarPaginaRestritaSemEstarLogado(){
        browser.navigate().to("http://localhost:8080/leilao/2");

        Assertions.assertTrue(browser.getCurrentUrl().equals(URL_LOGIN));
        Assertions.assertFalse(browser.getPageSource().contains("Dados do Leilão"));
    }
}

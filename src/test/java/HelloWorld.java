import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class HelloWorld {
    @Test
    public void deveAbrirAplicacao(){

        System.setProperty("webdriver.chrome.driver","chromedriver.exe");
        WebDriver browser = new ChromeDriver();
        browser.navigate().to("http://localhost:8080/leilao");
    }
}

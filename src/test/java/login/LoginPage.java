package login;

import leiloes.LeiloesPage;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

//Classe criada para seguir o padrão Page Object, em que os métodos do Selenium(ferramenta usada para automação) ficam separados
// dos testes feitos com o JUnit
public class LoginPage {

  private WebDriver browser;
  private static final String URL_LOGIN = "http://localhost:8080/login";
  private static final String URL_LOGIN_ERROR = "http://localhost:8080/login?error";
  private static final String URL_PAGINA_LANCE = "http://localhost:8080/leilao/2";

  public LoginPage() {
    System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
    browser = new ChromeDriver();
    browser.navigate().to(URL_LOGIN);
  }

  public void fechar() {
    browser.quit();
  }

  public void preencheDadosLogin(String usuario, String senha) {
    browser.findElement(By.id("username")).sendKeys(usuario);
    browser.findElement(By.id("password")).sendKeys(senha);
  }

  public LeiloesPage efetuaLogin() {
    browser.findElement(By.id("login-form")).submit();
    return new LeiloesPage(browser);
  }

  public boolean isPaginaDeLogin() {
    return browser.getCurrentUrl() != null && browser.getCurrentUrl().equals(URL_LOGIN);
  }

  public String getUsuarioLogado() {
    try {
      return browser.findElement(By.id("usuario-logado")).getText();
    } catch (NoSuchElementException e) {
      return null;
    }
  }

  public boolean isPaginaErroDeLogin(){
    return browser.getCurrentUrl() != null && browser.getCurrentUrl().equals(URL_LOGIN_ERROR)
            && browser.getPageSource().contains("Usuário e senha inválidos.");
  }

  public void navegarParaPagDeLance(){
    browser.navigate().to(URL_PAGINA_LANCE);
  }

  public boolean isPaginaDeLance() {
    return browser.getPageSource().contains("Dados do Leilão");
  }
}

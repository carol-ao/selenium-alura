package leiloes;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CadastroLeilaoPage {

  private WebDriver browser;
  private static final String URL_CADASTRO_LEILAO = "http://localhost:8080/leiloes/new";

  public CadastroLeilaoPage(WebDriver browser) {
    this.browser = browser;
  }

  public LeiloesPage cadastrarLeilao(String nome, String data, String valor) {
    this.browser.findElement(By.id("nome")).sendKeys(nome);
    this.browser.findElement(By.id("dataAbertura")).sendKeys(data);
    this.browser.findElement(By.id("valorInicial")).sendKeys(valor);
    this.browser.findElement(By.id("button-submit")).submit();

    return new LeiloesPage(this.browser);
  }

  public boolean isPaginaAtual() {
    return this.browser.getCurrentUrl().equals(URL_CADASTRO_LEILAO);
  }

  public boolean isTodasMensagensDeValidacaoVisiveis() {
    String pageSource = this.browser.getPageSource();
    return pageSource.contains("minimo 3 caracteres")
        && pageSource.contains("deve ser um valor maior de 0.1")
        && pageSource.contains("deve ser uma data no formato dd/MM/yyyy");
  }

  public void fechar() {
    this.browser.quit();
  }
}

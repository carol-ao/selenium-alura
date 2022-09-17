package leiloes;

import br.com.alura.leilao.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LeiloesPage extends PageObject {

  private static final String URL_CADASTRO_LEILAO = "http://localhost:8080/leiloes/new";

  public LeiloesPage(WebDriver browser) {
    super(browser);
  }

  public CadastroLeilaoPage carregarFormulario() {
    this.browser.navigate().to(URL_CADASTRO_LEILAO);
    return new CadastroLeilaoPage(this.browser);
  }

  public boolean isLeilaoCadastrado(String nome, String valor,String data){
    WebElement linhaDaTabela = browser.findElement(By.cssSelector("#table-leiloes tbody tr:last-child"));
    WebElement colunaNome = linhaDaTabela.findElement(By.cssSelector("td:nth-child(1)"));
    WebElement colunaValor = linhaDaTabela.findElement(By.cssSelector("td:nth-child(2)"));
    WebElement colunaData = linhaDaTabela.findElement(By.cssSelector("td:nth-child(3)"));

    return colunaNome.getText().equals(nome) &&
            colunaValor.getText().equals(valor) &&
            colunaData.getText().equals(data);
  }

}

package leiloes;

import login.LoginPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Leiloes {

  private LeiloesPage leiloesPage;
  private CadastroLeilaoPage cadastroLeilaoPage;

  @BeforeEach
  public void beforeEach(){
    LoginPage loginPage = new LoginPage();
    loginPage.preencheDadosLogin("fulano", "pass");
    leiloesPage = loginPage.efetuaLogin();
    cadastroLeilaoPage = leiloesPage.carregarFormulario();
  }


  @Test
  public void deveriaCadastrarLeilao() {
    String data = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    String nome = "Leilao do dia " + data;
    String valor = "500.00";
    leiloesPage = cadastroLeilaoPage.cadastrarLeilao(nome, data, valor);
    Assertions.assertTrue(leiloesPage.isLeilaoCadastrado(nome, data, valor));
    leiloesPage.fechar();
  }

  @Test
  public void deveValidarDadosNoCadastroDoLeilao(){
    cadastroLeilaoPage.cadastrarLeilao("", "", "");
    Assertions.assertTrue(cadastroLeilaoPage.isTodasMensagensDeValidacaoVisiveis());
    cadastroLeilaoPage.fechar();
  }
}

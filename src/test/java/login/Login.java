package login;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class Login {

  private LoginPage loginPage;

  @BeforeEach
  public void beforeEach() {
    this.loginPage = new LoginPage();
  }

  @AfterEach
  public void afterEach() {
    loginPage.fechar();
  }

  @Test
  public void deveEfetuarLoginComDadosValidos() {
    loginPage.preencheDadosLogin("fulano", "pass");
    loginPage.efetuaLogin();
    Assertions.assertFalse(loginPage.isPaginaDeLogin());
    Assertions.assertEquals("fulano", loginPage.getUsuarioLogado());
  }

  @Test
  public void deveNaoEfetuarLoginComDadosInvalidos() {
    loginPage.preencheDadosLogin("invalido", "123");
    loginPage.efetuaLogin();

    Assertions.assertTrue(loginPage.isPaginaErroDeLogin());
    Assertions.assertNull(loginPage.getUsuarioLogado());
  }

  @Test
  public void naoDeveAcessarPaginaRestritaSemEstarLogado() {
    loginPage.navegarParaPagDeLance();
    Assertions.assertTrue(loginPage.isPaginaDeLogin());
    Assertions.assertFalse(loginPage.isPaginaDeLance());
  }
}

package br.com.alura.leilao;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class PageObject {

    protected WebDriver browser;

    public PageObject(WebDriver browser) {
        if(browser == null){
            this.browser = new ChromeDriver();
        }
        else{
            this.browser = browser;
        }
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
    }

    public void fechar() {
        browser.quit();
    }
}

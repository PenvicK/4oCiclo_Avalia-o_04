package br.com.api.avalicao4.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class SeleniumTest {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\vinic\\OneDrive\\Documents\\drivers\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("http://localhost:3000/");

        WebElement botao = driver.findElement(By.id("btn-criar"));
        botao.click();

        WebElement name = driver.findElement(By.id("name"));
        name.sendKeys("J");

        WebElement pv = driver.findElement(By.id("precoVenda"));
        pv.sendKeys("3");

        WebElement pc = driver.findElement(By.id("precoCompra"));
        pc.sendKeys("2");

        WebElement qtd = driver.findElement(By.id("qtd"));
        qtd.sendKeys("1");

        WebElement tp = driver.findElement(By.id("tp"));
        tp.sendKeys("1");

        WebElement f = driver.findElement(By.id("f"));
        f.sendKeys("1");

        WebElement enviar = driver.findElement(By.id("bnt-criar-c"));
        enviar.click();
    }
}

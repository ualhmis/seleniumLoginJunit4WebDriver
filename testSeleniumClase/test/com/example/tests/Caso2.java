package com.example.tests;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;

import org.junit.*;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.ui.Select;

public class Caso2 {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
    // driver = new FirefoxDriver();
	driver = new HtmlUnitDriver();
    baseUrl = "https://www.google.es/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testCaso2() throws Exception {
    driver.get(baseUrl + "/?gws_rd=ssl#q=Universidad+de+almeria");
    driver.findElement(By.id("lst-ib")).clear();
    driver.findElement(By.id("lst-ib")).sendKeys("Universidad de almeria");
    driver.findElement(By.name("btnG")).click();
    driver.findElement(By.linkText("Universidad de Almeria")).click();
    driver.findElement(By.linkText("Grados, 1 y 2 Ciclo")).click();
    driver.findElement(By.linkText("Grado en Ingenieria Informatica (Plan 2015)")).click();
    driver.findElement(By.linkText("Plan de Estudios")).click();
    driver.findElement(By.linkText("Asignaturas ordenadas por cursos")).click();
    assertEquals("Integracion de Sistemas Software", driver.findElement(By.xpath("//div[@id='articulo']/table/tbody/tr[9]/td[2]/a/strong")).getText());
  }

  @After
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}

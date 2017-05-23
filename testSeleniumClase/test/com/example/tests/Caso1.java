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

public class Caso1 {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
	  // SELECCIÓN DEL DRIVER: elija entre FirefoxDriver, HtmlUnitDriver, etc
	  // driver = xxxxxDriver(....); 

	  // Firefox 
	  // Descargar geckodriver de https://github.com/mozilla/geckodriver/releases
	  // En mi caso he descargado la version win 32b, y la he copiado en la carpeta drivers
	  System.setProperty("webdriver.gecko.driver", "drivers/geckodriver.exe");
	  driver =new FirefoxDriver();

	  // HtmlUnitDriver (navegador headless)
	  driver = new HtmlUnitDriver(true);

	  // turn off htmlunit warnings
	  java.util.logging.Logger.getLogger("com.gargoylesoftware.htmlunit").setLevel(java.util.logging.Level.OFF);
	  java.util.logging.Logger.getLogger("org.apache.http").setLevel(java.util.logging.Level.OFF);

	  baseUrl = "http://www.ual.es/";
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testCaso1() throws Exception {
    driver.get(baseUrl + "");
    driver.findElement(By.linkText("Órganos de Gobierno")).click();
    driver.findElement(By.linkText("Vicerrectorado de Profesorado y Ordenación Académica")).click();
    assertEquals("Edificio de Gobierno y Paraninfo", driver.findElement(By.cssSelector("strong")).getText());
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

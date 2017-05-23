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

public class RegistroRandom {
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
	  // System.setProperty("webdriver.gecko.driver", "drivers/geckodriver.exe");
	  // driver =new FirefoxDriver();
	  
	  // HtmlUnitDriver (navegador headless)
	  driver = new HtmlUnitDriver(true);
	  
	  
	  driver.manage().window().setPosition(new Point(0,0));
	  driver.manage().window().setSize(new Dimension(1280,720));
	  
	  // turn off htmlunit warnings
	            java.util.logging.Logger.getLogger("com.gargoylesoftware.htmlunit").setLevel(java.util.logging.Level.OFF);
	  java.util.logging.Logger.getLogger("org.apache.http").setLevel(java.util.logging.Level.OFF);
	        baseUrl = "http://loginmay1720170518060532.azurewebsites.net/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testRegistroRandom() throws Exception {
    driver.get(baseUrl + "/");
    driver.findElement(By.id("registerLink")).click();
    // Insertar un valor aleatorio en el email
    // Primero guardamos el valor aleatorio para luego poder usarlo
    // ERROR: Caught exception [ERROR: Unsupported command [getEval |  | ]]
    // Esta linea no la exporta bien, hay que hacerla a mano:
    String emailrandom = "ual" + Math.floor(Math.random()*1500000) + "@ual.es";
    // Mostramos el valor de la variable en el log
    System.out.println(emailrandom);
    // Ahora escribimos el email en el campo de texto
    driver.findElement(By.id("Email")).clear();
    driver.findElement(By.id("Email")).sendKeys(emailrandom);
    driver.findElement(By.id("Password")).clear();
    driver.findElement(By.id("Password")).sendKeys("ABab12!!");
    driver.findElement(By.id("ConfirmPassword")).clear();
    driver.findElement(By.id("ConfirmPassword")).sendKeys("ABab12!!");
    driver.findElement(By.cssSelector("input.btn.btn-default")).click();
    // Aserción para comprobar que ser ha registrado y logeado correctamente
    assertTrue(isElementPresent(By.linkText("Hello " + emailrandom + "!")));
    // Salimos para finalizar el test dejando el navegador en un estado inicial estable
    driver.findElement(By.linkText("Log off")).click();
    // Comprobamos que efectivamente el log off ha funcionado correctamente
    assertTrue(isElementPresent(By.id("loginLink")));
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

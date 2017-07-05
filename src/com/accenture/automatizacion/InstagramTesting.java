package com.accenture.automatizacion;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

public class InstagramTesting {
public static AppiumDriver<WebElement> driver;
    
    DesiredCapabilities capabilities = new DesiredCapabilities();
    
    @BeforeMethod
	public void setUpAppium() throws MalformedURLException, InterruptedException{
    	/*APK INFO:com.google.android.calculator2.Calculator
     	         * com.google.android.calculator2.CalculatorSecurity*/
        String packagename = "com.instagram.android";
        String URL="http://127.0.0.1:4723/wd/hub";
        String activityname = "com.instagram.android.activity.MainTabActivity";
    	DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("deviceName", "device");
		capabilities.setCapability("udid", "7N2UNB159P002533");
		capabilities.setCapability("platformVersion", "6.0");
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability("appPackage", packagename);
		capabilities.setCapability("appActivity", activityname);
		driver = new AndroidDriver<WebElement>(new URL(URL), capabilities);
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    	
	  }

	@AfterTest
	  public void CleanUpAppium() {
		
		driver.quit();}
	
    @Test
    public void mytest() throws InterruptedException {
    	
		try {

			Assert.assertNotNull(driver.getContext());

			/**
			 * Se dirige a la opción ¿Ya tienes una cuenta? Inicia Sesión
			 */
			WebElement inicio = driver.findElement(By.xpath("//android.widget.TextView[@index = '5']"));
			inicio.click();
			System.out.println("Click inicio");

			/**
			 * Ingresa a los campos nombre de usuario
			 */
			WebElement username = driver
					.findElement(By.xpath("//*[@text='Número de teléfono, correo electrónico o nombre de usuario']"));
			username.click();
			username.sendKeys("pavasmarcela");
			System.out.println("Click username");

			/**
			 * Ingresa al campo la contraseña
			 */
			WebElement password = driver.findElement(By.id("com.instagram.android:id/password"));
			password.click();
			/**
			 * Oculta la contraseña
			 */
			// WebElement ocultar =
			// driver.findElement(By.xpath("//*[@text='Ocultar']"));
			// ocultar.click();

			/**
			 * Envia la contraseña
			 */

			// password.sendKeys("123456789");
			password.sendKeys("simpsons");
			System.out.println("Click password");

			/**
			 * Inicia sesión en el botón iniciar sesión
			 */
			WebElement login = driver.findElement(By.xpath("//*[@text='Iniciar sesión']"));

			login.click();
			System.out.println("Click login");
			/**
			 * Se usa para idenficiar si el login es correcto, idenficando si se
			 * muestra un botón cuando el login es correcto, si este botón no se
			 * encuentra lanza la excepción
			 */

			WebElement logincheck = driver.findElement(By.xpath("//*[@content-desc='Cámara']"));

			if (logincheck.isDisplayed()) {
				System.out.println("Click correct login");
			}

			 System.out.println(driver.getCapabilities().getCapability("appActivity"));
			
			 WebElement history =
			 driver.findElement(By.xpath("//*[@content-desc='Cámara']"));
			 history.click();
			
			 WebElement takepicture =
			 driver.findElement(By.id("com.instagram.android:id/prior_shutter_icon"));
			 takepicture.click();
			
			 WebElement takepicture_next =
			 driver.findElement(By.id("com.instagram.android:id/recipients_picker_button"));
			 takepicture_next.click();
			
			 WebElement history_check_box =
			 driver.findElement(By.id("com.instagram.android:id/row_add_to_story_checkbox"));
			 history_check_box.click();
			
			 WebElement send =
			 driver.findElement(By.id("com.instagram.android:id/button_send"));
			 send.click();
			
			 WebElement history_finish =
			 driver.findElement(By.id("com.instagram.android:id/avatar_image_view"));
			 history_finish.click();

				/**
				 * Proceso de cerrar sesión
				 */
			WebElement profile = driver.findElement(By.xpath("//android.widget.FrameLayout[@index='4']"));
			profile.click();

			WebElement home_options = driver.findElement(By.id("com.instagram.android:id/action_bar_overflow_icon"));
			home_options.click();
			// Thread.sleep(20000);
			driver.swipe(330, 1530, 330, 300, 1000);
			System.out.println("1er swipe");
			driver.swipe(330, 1530, 330, 300, 1000);
			System.out.println("2er swipe");
			driver.swipe(330, 1530, 330, 300, 1000);
			System.out.println("3er swipe");
			driver.swipe(330, 1530, 330, 300, 1000);
			System.out.println("4er swipe");
			WebElement logout = driver.findElement(By.xpath("//android.widget.FrameLayout[@index='10']"));
			logout.click();
			WebElement logout_check = driver.findElement(By.id("com.instagram.android:id/button_positive"));
			logout_check.click();
			Thread.sleep(20000);

			//
			//
			//
			// WebElement takepicture =
			// driver.findElement(By.xpath("//android.widget.ImageView.android.widget.LinearLayout[@bounds
			// = '[0,75][144,219]']"));

			// WebElement takepicture =
			// driver.findElement(By.id("com.instagram.android:id/prior_shutter_icon"));
			// takepicture.click();
			// System.out.println("Click camara");
			//
			Thread.sleep(5000);

			// WebElement takepicture = driver.findElement(By);
			// takepicture.click();
			// System.out.println("Click");
			// WebElement plus =
			// driver.findElement(By.id("com.android.calculator2:id/plus"));
			// plus.click();
			// System.out.println("Click");
			//
			// WebElement num9 =
			// driver.findElement(By.id("com.android.calculator2:id/digit9"));
			// num9.click();
			// System.out.println("Click");
			//
			// WebElement num2 =
			// driver.findElement(By.id("com.android.calculator2:id/digit2"));
			// num2.click();
			// System.out.println("Click");
			//
			// WebElement outputBox =
			// driver.findElement(By.className("android.widget.EditText"));
			// String output = outputBox.getText();
			////
			// Assert.assertEquals(output, "13más92");
			//
			// WebElement equals =
			// driver.findElement(By.id("com.android.calculator2:id/equal"));
			// equals.click();
			// outputBox =
			// driver.findElement(By.className("android.widget.EditText"));
			// String result = outputBox.getText();
			// System.out.println(result);
			// int actual = 13+92;
			// Assert.assertEquals(String.valueOf(actual),result);
		} catch (Exception e) {
			System.out.print("\n\t Se presento Excepción " + e);
		}
	}
}

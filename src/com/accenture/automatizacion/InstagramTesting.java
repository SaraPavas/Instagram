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

import com.accenture.automatizacion.datadrive.DataDrivenUsers;
import com.accenture.automatizacion.dto.Credenciales;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDeviceActionShortcuts;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;

public class InstagramTesting {
	public static AppiumDriver<WebElement> driver;

	DesiredCapabilities capabilities = new DesiredCapabilities();
	String XFILEPATH = "C://Users//Administrator//Documents//workspace//Instagram//datadrivenlogin.xlsx";
	boolean FLAG = true;

	@BeforeMethod
	public void setUpAppium() throws MalformedURLException, InterruptedException {
		
		String packagename = "com.instagram.android";
		String URL = "http://127.0.0.1:4723/wd/hub";
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

		driver.quit();
	}

	@Test
	public void mytest() throws InterruptedException {

		try {

			Credenciales credencial = new Credenciales("USUARIO", "CONTRASEÑA");
			Assert.assertNotNull(driver.getContext());
			DataDrivenUsers dataDrivenUsers = new DataDrivenUsers(XFILEPATH);
			int i = 0;
			credencial = dataDrivenUsers.getCellData(i);

			/**
			 * Se dirige a la opción ¿Ya tienes una cuenta? Inicia Sesión
			 */

			do {
				if (FLAG) {
					WebElement inicio = driver.findElement(By.xpath("//android.widget.TextView[@index = '5']"));
					inicio.click();
					System.out.println("Click inicio");
				}

				/**
				 * Ingresa a los campos nombre de usuario
				 */
				WebElement username = driver.findElement(
						By.xpath("//*[@text='Número de teléfono, correo electrónico o nombre de usuario']"));
				username.click();
				// username.sendKeys("pavasmarcela");
				username.sendKeys(credencial.getUsername());
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
				// password.sendKeys("simpsons");
				password.sendKeys(credencial.getPassword());
				System.out.println("Click password");

				/**
				 * Inicia sesión en el botón iniciar sesión
				 */
				WebElement login = driver.findElement(By.xpath("//*[@text='Iniciar sesión']"));

				login.click();
				System.out.println("Click login");

				/**
				 * Se usa para idenficiar si el login es correcto, idenficando
				 * si se muestra un botón cuando el login es correcto, si este
				 * botón no se encuentra lanza la excepción
				 */

				try {
					WebElement logincheck = driver.findElement(By.xpath("//*[@content-desc='Cámara']"));

					if (logincheck.isDisplayed()) {
						System.out.println("Click correct login");
						dataDrivenUsers.setStatus(i, XFILEPATH, "Correct");
						FLAG = false;
					}

					WebElement history = driver.findElement(By.xpath("//*[@content-desc='Cámara']"));
					history.click();

					WebElement takepicture = driver.findElement(By.id("com.instagram.android:id/prior_shutter_icon"));
					takepicture.click();

					WebElement takepicture_next = driver
							.findElement(By.id("com.instagram.android:id/recipients_picker_button"));
					takepicture_next.click();

					WebElement history_check_box = driver
							.findElement(By.id("com.instagram.android:id/row_add_to_story_checkbox"));
					history_check_box.click();

					WebElement send = driver.findElement(By.id("com.instagram.android:id/button_send"));
					send.click();

					WebElement history_finish = driver.findElement(By.id("com.instagram.android:id/avatar_image_view"));
					history_finish.click();

					/**
					 * Proceso de cerrar sesión
					 */
					WebElement profile = driver.findElement(By.xpath("//android.widget.FrameLayout[@index='4']"));
					profile.click();

					WebElement home_options = driver
							.findElement(By.id("com.instagram.android:id/action_bar_overflow_icon"));
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

				} catch (Exception e) {
					FLAG = false;
					System.out.println("Click incorrect login");
					dataDrivenUsers.setStatus(i, XFILEPATH, "Incorrect");
					((AndroidDeviceActionShortcuts) driver).pressKeyCode(AndroidKeyCode.BACK);
					((AndroidDeviceActionShortcuts) driver).pressKeyCode(AndroidKeyCode.BACK);
//					e.get

				}

				WebElement new_login = driver.findElement(By.id("com.instagram.android:id/left_button"));
				new_login.click();
				System.out.println("Cerrar sesion");
				
				System.out.println("While");
				i++;
				credencial = dataDrivenUsers.getCellData(i);

			} while (credencial != null);
		} catch (Exception e) {
			System.out.print("\n\t Se presento Excepción " + e);
		}
	}
}

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
			String name ="";
			String pass ="";
			Credenciales credencial = new Credenciales("USUARIO", "CONTRASE�A");
			Assert.assertNotNull(driver.getContext());
			DataDrivenUsers dataDrivenUsers = new DataDrivenUsers(XFILEPATH);
			int i = 0;
			credencial = dataDrivenUsers.getCellData(i);

			/**
			 * Se dirige a la opci�n �Ya tienes una cuenta? Inicia Sesi�n
			 */

			/**
			 * Ciclo para recorrer el data driven
			 */
			do {

				/**
				 * Controla inicios de sesi�n diferentes
				 */

				// if (FLAG) {

				/**
				 * Se dirige de la actividad principal al login.
				 */
				WebElement inicio = driver.findElement(By.xpath("//android.widget.TextView[@index = '5']"));
				inicio.click();
				System.out.println("Click inicio");
				// }

				/**
				 * Se dirige a la pagina de login, para completar los campos de
				 * usuario y contrase�a, finalmente se accede con el bot�n
				 * iniciar sesi�n Ingresa a los campos nombre de usuario
				 */
				WebElement username = driver.findElement(By.id("com.instagram.android:id/login_username"));
				username.click();
				// ((AndroidDeviceActionShortcuts)
				// driver).pressKeyCode(AndroidKeyCode.BACKSPACE);

				// username.sendKeys("pavasmarcela");
				name = credencial.getUsername();
				username.sendKeys(name);
				System.out.println("Click username: "+name);

				/**
				 * Oculta la contrase�a del usuario con caracteres especiales.
				 */
				// WebElement ocultar =
				// driver.findElement(By.xpath("//*[@text='Ocultar']"));
				// ocultar.click();

				/**
				 * Ingresa al campo la contrase�a
				 */
				WebElement password = driver.findElement(By.id("com.instagram.android:id/password"));
				password.click();
				password.clear();
				// password.sendKeys("123456789");
				// password.sendKeys("simpsons");
				pass=credencial.getPassword();
				password.sendKeys(pass);
				System.out.println("Click password: "+pass);

				/**
				 * Inicia sesi�n en el bot�n iniciar sesi�n
				 */
				WebElement login = driver.findElement(By.xpath("//*[@text='Iniciar sesi�n']"));

				login.click();
				System.out.println("Click login");

				/**
				 * Idenficia si el login es correcto, si se muestra el bot�n de
				 * la c�mara, el login es correcto, si el bot�n no se encuentra
				 * lanza la excepci�n
				 */

				try {

					WebElement logincheck = driver.findElement(By.xpath("//*[@content-desc='C�mara']"));

					/**
					 * Verifica si se muestra el bot�n y escribe el estado del
					 * login al data driven
					 */
					// if (logincheck.isDisplayed()) {
					System.out.println("Click correct login");
					dataDrivenUsers.setStatus(i, XFILEPATH, "Correct");
					FLAG = true;
					// }

					/**
					 * Automatizaci�n de la actividad
					 */

					/**
					 * Se publica una historia en instagram, accede a la c�mara
					 */
					WebElement history = driver.findElement(By.xpath("//*[@content-desc='C�mara']"));
					history.click();

					/**
					 * Captura la foto
					 */
					WebElement takepicture = driver.findElement(By.id("com.instagram.android:id/prior_shutter_icon"));
					takepicture.click();

					/**
					 * Confirma la foto
					 */
					WebElement takepicture_next = driver
							.findElement(By.id("com.instagram.android:id/recipients_picker_button"));
					takepicture_next.click();

					/**
					 * Confirma para publicar la foto en la historia y se env�a
					 */
					WebElement history_check_box = driver
							.findElement(By.id("com.instagram.android:id/row_add_to_story_checkbox"));
					history_check_box.click();

					WebElement send = driver.findElement(By.id("com.instagram.android:id/button_send"));
					send.click();

					/**
					 * Visualiza la history recien publicada
					 */
					WebElement history_finish = driver.findElement(By.id("com.instagram.android:id/avatar_image_view"));
					history_finish.click();

					/**
					 * Proceso de cerrar sesi�n
					 */
					/**
					 * Se dirige al perfil del usuario
					 */
					WebElement profile = driver.findElement(By.xpath("//android.widget.FrameLayout[@index='4']"));
					profile.click();

					/**
					 * Men� de opciones
					 */
					WebElement home_options = driver
							.findElement(By.id("com.instagram.android:id/action_bar_overflow_icon"));
					home_options.click();

					/**
					 * Se desliza hasta el final para encontrar cerrar sesi�n
					 */
					driver.swipe(330, 1530, 330, 300, 1000);
					System.out.println("1er swipe");
					driver.swipe(330, 1530, 330, 300, 1000);
					System.out.println("2er swipe");
					driver.swipe(330, 1530, 330, 300, 1000);
					System.out.println("3er swipe");
					driver.swipe(330, 1530, 330, 300, 1000);
					System.out.println("4er swipe");
					driver.swipe(330, 1530, 330, 300, 1000);
					System.out.println("5er swipe");
					/**
					 * Cierra sesi�n y confirma
					 */
					WebElement logout = driver.findElement(By.xpath("//android.widget.FrameLayout[@index='10']"));
					logout.click();
					WebElement logout_check = driver.findElement(By.id("com.instagram.android:id/button_positive"));
					logout_check.click();

				} catch (Exception e) {

					FLAG = false;
					System.out.println("Click incorrect login\n");
					/**
					 * Actualiza el data driven con login fallido y se dirige a
					 * la vista de sesi�n cerrada
					 */
					dataDrivenUsers.setStatus(i, XFILEPATH, "Incorrect");
					((AndroidDeviceActionShortcuts) driver).pressKeyCode(AndroidKeyCode.BACK);
					((AndroidDeviceActionShortcuts) driver).pressKeyCode(AndroidKeyCode.BACK);
					System.out.print("\n\t Se presento Excepci�n: Faild Login: \n" + e.getMessage());
					// e.get

				}
				/**
				 * Regresa el inicio
				 */
				if (FLAG) {
					WebElement new_login = driver.findElement(By.id("com.instagram.android:id/left_button"));
					new_login.click();
					System.out.println("Cerrar sesion");
				}
				if (username.isDisplayed()) {
					WebElement registro = driver.findElement(By.xpath("//android.widget.TextView[@index='2']"));
					registro.click();
					System.out.println("username displayed");
				}
				System.out.println("While");
				i++;
				credencial = dataDrivenUsers.getCellData(i);

			} while (credencial != null);
		} catch (Exception e) {
			System.out.print("\n\t Se presento Excepci�n: Element not found: \n" + e.getStackTrace());
			// System.out.print("\n\t Se presento Excepci�n " + e);
		}
	}
}

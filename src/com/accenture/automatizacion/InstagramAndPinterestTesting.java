package com.accenture.automatizacion;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;

import com.accenture.automatizacion.datadrive.DataDrivenUsers;
import com.accenture.automatizacion.dto.Credenciales;
import com.accenture.automatizacion.instagramTest.InstagramTest;
import com.accenture.automatizacion.instagramTest.PinterestTest;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

public class InstagramAndPinterestTesting {

	public static AppiumDriver<WebElement> driver;

	DesiredCapabilities capabilities = new DesiredCapabilities();
	String XFILEPATH = "C://Users//Administrator//Documents//workspace//Instagram//datadrivenlogin.xlsx";
	boolean FLAG = true;
	String option = "";
	InstagramTest instagramTest = new InstagramTest();
	PinterestTest pinterestTest = new PinterestTest();

	@Test
	public void myInstagramtest() throws MalformedURLException, InterruptedException {
		try {
			String packagename = "";
			String URL = "";
			String activityname = "";
			DesiredCapabilities capabilities = new DesiredCapabilities();
			option = "I";
//			int count = 0;
			
			String name ="";
			String pass ="";
			Credenciales credencial = new Credenciales("USUARIO", "CONTRASEÑA");
			Scanner scaner =new Scanner(System.in);
			DataDrivenUsers dataDrivenUsers = new DataDrivenUsers(XFILEPATH);
			int i = 0;
			credencial = dataDrivenUsers.getCellData(i,0);

			do {
				name=credencial.getUsername();
				pass=credencial.getPassword();
				System.out.println("Para la credencial: \nUsuario: "+name+"\nCOntraseña: "+pass+"\nIngrese P: Login en Pinterest\n\tI: Login en Instagram");
				option = scaner.nextLine().toLowerCase();
				switch (option) {

				case "i":
					packagename = "com.instagram.android";
					URL = "http://127.0.0.1:4723/wd/hub";
					activityname = "com.instagram.android.activity.MainTabActivity";

					capabilities.setCapability("deviceName", "device");
//					capabilities.setCapability("udid", "7N2UNB159P002533");
					capabilities.setCapability("udid", "192.168.3.210:5555");
					capabilities.setCapability("platformVersion", "6.0");
					capabilities.setCapability("platformName", "Android");
					capabilities.setCapability("appPackage", packagename);
					capabilities.setCapability("appActivity", activityname);
					driver = new AndroidDriver<WebElement>(new URL(URL), capabilities);
					driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
					instagramTest.myInstagramtest(driver,dataDrivenUsers,credencial,i);
					Assert.assertNotNull(driver.getContext());
					Thread.sleep(10000);
					driver.quit();
					i++;
					credencial = dataDrivenUsers.getCellData(i,0);
					break;
				case "p":
					packagename = "com.pinterest";
					URL = "http://127.0.0.1:4723/wd/hub";
					activityname = "com.pinterest.activity.PinterestActivity";
					capabilities.setCapability("deviceName", "device");
//					capabilities.setCapability("udid", "7N2UNB159P002533");
					capabilities.setCapability("udid", "7N2UNB159P002533");
					capabilities.setCapability("platformVersion", "6.0");
					capabilities.setCapability("platformName", "Android");
					capabilities.setCapability("appPackage", packagename);
					capabilities.setCapability("appActivity", activityname);
					driver = new AndroidDriver<WebElement>(new URL(URL), capabilities);
					driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
					credencial = dataDrivenUsers.getCellData(i,3);
					name=credencial.getUsername();
					pass=credencial.getPassword();
					System.out.println(i+" Para la credencial: \nUsuario: "+name+"\nCOntraseña: "+pass+"\nIngrese P: Login en Pinterest\n\tI: Login en Instagram");
					pinterestTest.myPinterestTest(driver,dataDrivenUsers,credencial,i);
					Thread.sleep(10000);
					driver.quit();
					i++;
					credencial = dataDrivenUsers.getCellData(i,0);
					break;
				default:
					System.out.println("Ingrese una opción correcta");
					break;
				}
//				count++;
//				option = "P";
				
				
			} while (credencial != null);
//			}while(count<2);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("---------------FIN DE LA PRUEBA-----------");
		
	}
	
}

package com.accenture.automatizacion.instagramTest;

import static org.testng.Assert.assertNotNull;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import com.accenture.automatizacion.datadrive.DataDrivenUsers;
import com.accenture.automatizacion.dto.Credenciales;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDeviceActionShortcuts;
import io.appium.java_client.android.AndroidKeyCode;

public class PinterestTest {
	

	public final String FILE_PATH = "C://Users//Administrator//workspace//TestSocialNet//credentialsFile.xlsx";
	private static final String SUCCESS_MESSAGE = "Success";
	private static final String FAIL_MESSAGE = "Fail";
	
	
	public PinterestTest() {

	}
	private WebElement findElement(By by, AppiumDriver<WebElement> driver ){
		WebElement element = null;
		try {
			 element = driver.findElement(by);
		} catch (NoSuchElementException e) {
			// TODO: handle exception
			//System.err.println("element not found");
		}
		
		
		return element;
	}

	public void myPinterestTest(AppiumDriver<WebElement> driver, DataDrivenUsers dataDrivenUser, Credenciales credential,int rowNum) throws InterruptedException {

		

		int startSwipeX = 380;
		int startSwipeY = 1050;
		int endSwipeX = 250;
		int endSwipeY = 450;
		int swipeDuration = 3000;

		
		

			WebElement txtSearchBar = null;
			WebElement email = null;
			
			try {
					
				Assert.assertNotNull(driver.getContext());

				System.out.println("Using "+credential.getUsername()+" credentials");
				
				email = driver.findElementById("com.pinterest:id/email_address");
				email.click();
				

				WebElement dimmissSuggestion = findElement(By.id("com.google.android.gms:id/cancel"),driver);
				if (null != dimmissSuggestion) {
					dimmissSuggestion.click();
				}
				email.click();
				email.clear();
				email.sendKeys(credential.getUsername());

				WebElement btnContinue = driver.findElement(By.id("com.pinterest:id/continue_email_bt"));
				btnContinue.click();

				WebElement chkShowPassword = findElement(By.id("com.pinterest:id/password_toggle_cb"),driver);
				if (null == chkShowPassword) {
					String message = FAIL_MESSAGE+": This account dont exist";
					System.err.println(message);
					dataDrivenUser.setStatus(rowNum,FILE_PATH,FAIL_MESSAGE,5);
					AndroidDeviceActionShortcuts shortcuts =(AndroidDeviceActionShortcuts) driver;
					shortcuts.pressKeyCode(AndroidKeyCode.BACK);
					shortcuts.pressKeyCode(AndroidKeyCode.BACK);
				}else{

					WebElement password = driver.findElement(By.id("com.pinterest:id/password"));
					password.click();
					password.sendKeys(credential.getPassword());
	
					WebElement btnLogin = driver.findElement(By.id("com.pinterest:id/next_bt"));
					btnLogin.click();
				}
				
				txtSearchBar = findElement(By.id("com.pinterest:id/menu_explore"),driver);
				if (txtSearchBar!=null) {
					txtSearchBar.click();
				}
				WebElement messageFail = findElement(By.id("com.pinterest:id/negative_bt"),driver);
				
				if (messageFail != null) {
					
					String message = FAIL_MESSAGE+": "+messageFail.getText();
					System.err.println(message);
					dataDrivenUser.setStatus(rowNum,FILE_PATH,FAIL_MESSAGE,5);
					AndroidDeviceActionShortcuts shortcuts =(AndroidDeviceActionShortcuts) driver;
					shortcuts.pressKeyCode(AndroidKeyCode.BACK);
					shortcuts.pressKeyCode(AndroidKeyCode.BACK);
							
				}else{
					
					txtSearchBar = driver.findElement(By.id("com.pinterest:id/search_tv"));
					txtSearchBar.click();

					WebElement queyInput = driver.findElement(By.id("com.pinterest:id/query_input"));
					queyInput.sendKeys("pastor australiano");
//					driver.tap(1, 660, 1125, 100);
					WebElement selected = driver.findElement(By.xpath("//android.widget.TextView[@text = 'pastor australiano']"));
					selected.click();
//					((AndroidDeviceActionShortcuts) driver).pressKeyCode(AndroidKeyCode.KEYCODE_SEARCH);
//					Thread.sleep(2000);
//					driver.swipe(startSwipeX, startSwipeY, endSwipeX, endSwipeY, swipeDuration);

					WebElement photos = driver.findElementById("com.pinterest:id/adapter_vw");
					System.out.println(photos.toString());

					WebElement photo = photos.findElement(By.xpath("//android.view.View[@index='2']"));
					photo.click();

					WebElement options = driver.findElement(By.id("com.pinterest:id/menu_pin_overflow"));
					options.click();

					WebElement btnDownload = driver.findElement(By.id("com.pinterest:id/value_tv"));
					btnDownload.click();

					WebElement userProfile = driver.findElement(By.id("com.pinterest:id/profile_icon"));
					userProfile.click();

					WebElement menu_create = driver.findElement(By.id("com.pinterest:id/menu_create"));
					menu_create.click();

					WebElement create_board = driver.findElement(By.id("com.pinterest:id/create_board"));
					create_board.click();

					WebElement board_name = driver.findElement(By.id("com.pinterest:id/board_name_edittext"));
					board_name.click();
					board_name.sendKeys("New Board");
					driver.hideKeyboard();

					WebElement btnCreate = driver.findElement(By.id("com.pinterest:id/create_btn"));
					btnCreate.click();
					
					WebElement btnSettings = driver.findElement(By.id("com.pinterest:id/menu_settings"));
					btnSettings.click();

					WebElement btnLogOut = driver.findElementByXPath(
							"//android.widget.TextView[@bounds='[0,657][655,740]']");
					btnLogOut.click();
					
					System.err.println(SUCCESS_MESSAGE);
					dataDrivenUser.setStatus(rowNum,FILE_PATH, SUCCESS_MESSAGE,5);
				}
				
				
			}catch (NoSuchElementException e) {
				e.printStackTrace();
			}
			

			Thread.sleep(10000);
		}


	
}

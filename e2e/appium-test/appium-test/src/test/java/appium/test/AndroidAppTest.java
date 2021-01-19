package appium.test;

import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class AndroidAppTest {

	public static URL url;
	public static DesiredCapabilities capabilities;
	public static AndroidDriver<MobileElement> driver;

	@Before
	public void setupAppium() throws MalformedURLException {
		final String URL_STRING = "http://127.0.0.1:4723/wd/hub";
		url = new URL(URL_STRING);
		capabilities = new DesiredCapabilities();
		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Android Device");

		File appDir = new File("src");
		File app = new File(appDir, "app-release.apk");

		capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
		capabilities.setCapability(MobileCapabilityType.NO_RESET, true);
		capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
		driver = new AndroidDriver<MobileElement>(url, capabilities);
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		driver.resetApp();
	}

	@After
	public void uninstallApp() throws InterruptedException {
		driver.removeApp("com.awesomeproject");
	}

	@Test
	public void testAwesomeApp() throws InterruptedException {
		MobileElement element = driver.findElementByXPath("//*[@text='Welcome to React']");
		assertNotNull(element);
		System.out.println(element.getText());
	}
}

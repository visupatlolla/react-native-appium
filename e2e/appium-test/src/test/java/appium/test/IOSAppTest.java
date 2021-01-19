package appium.test;

import static org.junit.Assert.assertNotNull;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.remote.MobileCapabilityType;

public class IOSAppTest {

	public static URL url;
	public static DesiredCapabilities capabilities;
	public static IOSDriver<IOSElement> driver;

	@Before
	public void setupAppium() throws MalformedURLException {
		final String URL_STRING = "http://127.0.0.1:4723/wd/hub";
		url = new URL(URL_STRING);

		capabilities = new DesiredCapabilities();
		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone Simulator");
		capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "iOS");
		capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "14.3");
		capabilities.setCapability(MobileCapabilityType.APP, "/Users/f404324/repo/AwesomeProject/ios/ddata/Build/Products/Debug-iphonesimulator/AwesomeProject.app");
		capabilities.setCapability(MobileCapabilityType.NO_RESET, true);
		capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "XCUITest");
		capabilities.setCapability("useNewWDA", false);

		driver = new IOSDriver<IOSElement>(url, capabilities);
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		driver.resetApp();
	}

	@After
	public void uninstallApp() throws InterruptedException {
		driver.removeApp("org.reactjs.native.example.AwesomeProject");
	}

	@Test
	public void myFirstTest() throws InterruptedException {
		MobileElement element = driver.findElementByXPath("//XCUIElementTypeOther[@name=\"Welcome to React\"]");
		assertNotNull(element);
		System.out.println(element.getTagName());
		System.out.println(element.getText());
	}
}

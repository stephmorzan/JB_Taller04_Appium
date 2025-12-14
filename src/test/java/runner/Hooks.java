package runner;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class Hooks {

    private AppiumDriver device;

    @Before
    public void beforeScenario() throws MalformedURLException {
        System.out.println("---Scenario begins---");
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("appium:deviceName", "emulator-5554");
        capabilities.setCapability("appium:platformVersion", "9.0");
        capabilities.setCapability("appium:appPackage","com.vrproductiveapps.whendo");
        capabilities.setCapability("appium:appActivity","com.vrproductiveapps.whendo.ui.HomeActivity");
        capabilities.setCapability("platformName","Android");
        capabilities.setCapability("appium:automationName","uiautomator2");

        device = new AndroidDriver(new URL("http://127.0.0.1:4723/"), capabilities);

        device.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

    }

    @After
    public void afterScenario() {
        System.out.println("---Scenario ends---");
        device.quit();
    }

    public AppiumDriver getDriver() {
        return device;
    }
}

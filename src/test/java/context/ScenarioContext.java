package context;

import io.appium.java_client.AppiumDriver;

public class ScenarioContext {

    private AppiumDriver driver;

    public void setDriver(AppiumDriver driver) {
        this.driver = driver;
    }

    public AppiumDriver getDriver() {
        return driver;
    }
}

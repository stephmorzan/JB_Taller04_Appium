package session;

import device.FactoryDevice;
import io.appium.java_client.AppiumDriver;

public class Session {

    private static Session instance = null;
    private AppiumDriver device;

    private Session(){
        device = FactoryDevice.make("android").create();
    }

    public static Session getInstance(){
        if (instance == null){
            instance = new Session();
        }
        return instance;
    }

    public void closeSession(){
        device.quit();
        instance = null;
    }


    public AppiumDriver getDevice() {
        return device;
    }

}

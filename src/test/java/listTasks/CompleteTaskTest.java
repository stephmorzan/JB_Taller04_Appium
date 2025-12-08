package listTasks;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;

public class CompleteTaskTest {

    private AppiumDriver device;

    @BeforeEach
    public void setup() throws MalformedURLException {
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

    @Test
    public void deleteTask() throws InterruptedException {

        createListOfTasks();
        WebDriverWait explicitWait = new WebDriverWait(device, Duration.ofSeconds(20));
        explicitWait.until(ExpectedConditions.elementToBeClickable(By.id("com.vrproductiveapps.whendo:id/fab")));
        swipeDownList();

        List<WebElement> checkboxes = device.findElements(By.xpath("(//android.widget.ImageButton[@content-desc=\"Mark Done\"])"));

        checkboxes.get(checkboxes.size()-1).click();

        device.findElement(By.xpath("//android.widget.ImageButton[@content-desc=\"Open navigation drawer\"]")).click();

        device.findElement(By.xpath("//android.widget.CheckedTextView[@text=\"Completed Tasks\"]")).click();

        List<WebElement> completedTasks = device.findElements(By.
                xpath("//android.widget.ListView[@resource-id=\"com.vrproductiveapps.whendo:id/notesList\"]/" +
                        "android.view.ViewGroup[2]/android.widget.LinearLayout/android.widget.LinearLayout"));

        Assertions.assertTrue(completedTasks.size() == 1, "Task was not marked and not completed");
    }

    private void createListOfTasks(){
        int count = 0;

        //click +
        device.findElement(By.id("com.vrproductiveapps.whendo:id/fab")).click();

        while (count < 12){
            String titulo = "AUTO" + System.currentTimeMillis();
            String note = "this is a note " + count;
            // type title
            device.findElement(By.id("com.vrproductiveapps.whendo:id/noteTextTitle")).sendKeys(titulo);
            // type notes
            device.findElement(By.id("com.vrproductiveapps.whendo:id/noteTextNotes")).sendKeys(note);
            if (count == 11){
                // click save
                device.findElement(By.id("com.vrproductiveapps.whendo:id/saveItem")).click();
            }else{
                //click save and continue
                device.findElement(By.id("com.vrproductiveapps.whendo:id/saveAndNew")).click();
            }
            count++;
        }
    }

    private void swipeDownList() throws InterruptedException {
        Point firstPoint = device.findElement(By.xpath("//android.widget.ListView[@resource-id=\"com.vrproductiveapps.whendo:id/notesList\"]/android.view.ViewGroup[1]")).getLocation();
        Point lastPoint = device.findElement(By.xpath("//android.widget.ListView[@resource-id=\"com.vrproductiveapps.whendo:id/notesList\"]/android.view.ViewGroup[last()]")).getLocation();

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence swipeAction = new Sequence(finger, 1);

        swipeAction.addAction(
                finger.createPointerMove(Duration.ofSeconds(1),
                        PointerInput.Origin.viewport(),
                        lastPoint.getX(),
                        lastPoint.getY())
        );

        swipeAction.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));

        swipeAction.addAction(
                finger.createPointerMove(Duration.ofSeconds(1),
                        PointerInput.Origin.viewport(),
                        firstPoint.getX(),
                        firstPoint.getY())
        );

        swipeAction.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        device.perform(Arrays.asList(swipeAction));
        Thread.sleep(5000);
    }

    @AfterEach
    public void close(){
        device.quit();
    }
}

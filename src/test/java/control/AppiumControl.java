package control;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import session.Session;

import java.util.List;

public class AppiumControl {

    protected By locator;
    protected WebElement control;
    protected WebDriverWait explicitWait;

    public AppiumControl(By locator){
        this.locator = locator;
    }

    public void findControl(){
        control = Session.getInstance().getDevice().findElement(this.locator);
    }

    public void click(){
        findControl();
        control.click();
    }

    public List<WebElement> findControls() {
        return Session.getInstance().getDevice().findElements(locator);
    }

    public boolean isControlDisplayed(){
        try{
            findControl();
            return control.isDisplayed();
        }catch (Exception e){
            return false;
        }
    }

    public String getText(){
        findControl();
        return control.getText();
    }

    public void waitToBeClickable(){
        findControl();
        explicitWait = new WebDriverWait(Session.getInstance().getDevice(), java.time.Duration.ofSeconds(20));
        explicitWait.until(ExpectedConditions.elementToBeClickable(control));
    }

    public void waitToBeVisible(){
        findControl();
        explicitWait = new WebDriverWait(Session.getInstance().getDevice(), java.time.Duration.ofSeconds(20));
        explicitWait.until(ExpectedConditions.visibilityOf(control));
    }
}

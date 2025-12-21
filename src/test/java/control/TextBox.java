package control;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

public class TextBox extends AppiumControl{
    public TextBox(By locator) {
        super(locator);
    }

    public void setText(String text){
        findControl();
        control.sendKeys(text);
    }

    public void setTextEnter(String text){
        findControl();
        control.sendKeys(text + Keys.ENTER);
    }

    public void cleanSetText(String text){
        findControl();
        control.clear();
        control.sendKeys(text);
    }

}

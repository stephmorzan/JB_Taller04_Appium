package control;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CheckboxList extends AppiumControl{

    public CheckboxList(By locator) {
        super(locator);
    }

    public List<WebElement> getCheckboxes() {
        findControls();
        return controls;
    }
}

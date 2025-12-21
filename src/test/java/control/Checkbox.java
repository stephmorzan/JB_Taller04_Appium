package control;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class Checkbox extends AppiumControl{

    private WebElement element;

    public Checkbox(By locator) {
        super(locator);
    }

    public Checkbox(WebElement element) {
        super(null);
        this.element = element;
    }

    public boolean isChecked() {
        findControl();
        String checked = control.getAttribute("checked");
        return "true".equalsIgnoreCase(checked) || "1".equals(checked);
    }
}

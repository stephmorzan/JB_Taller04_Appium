package control;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class CheckboxList {

    private final By itemsLocator;

    public CheckboxList(By itemsLocator) {
        this.itemsLocator = itemsLocator;
    }

    public List<Checkbox> getCheckboxes() {
        AppiumControl helper = new AppiumControl(itemsLocator) {};
        List<WebElement> elements = helper.findControls();
        List<Checkbox> result = new ArrayList<>(elements.size());
        for (WebElement el : elements) {
            result.add(new Checkbox(el));
        }
        return result;
    }

    public Checkbox getLastCheckbox(List<Checkbox> checkboxes){
        return checkboxes.get(checkboxes.size()-1);
    }
}

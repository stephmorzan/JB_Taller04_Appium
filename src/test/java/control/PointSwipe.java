package control;

import org.openqa.selenium.By;
import org.openqa.selenium.Point;

public class PointSwipe extends AppiumControl{

    public PointSwipe(By locator) {
        super(locator);
    }

    public Point getLocation() {
        findControl();
        return control.getLocation();
    }

    public int getX() {
        findControl();
        return control.getLocation().getX();
    }

    public int getY() {
        findControl();
        return control.getLocation().getY();
    }
}

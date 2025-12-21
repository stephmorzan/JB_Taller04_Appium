package activities.whenDo;

import control.Button;
import control.CheckboxList;
import control.Label;
import control.PointSwipe;
import org.openqa.selenium.By;

public class MyListScreen {

    public Button addTaskButton = new Button(By.id("com.vrproductiveapps.whendo:id/fab"));

    public PointSwipe firstPoint = new PointSwipe(By.xpath("//android.widget.ListView[@resource-id=\"com.vrproductiveapps.whendo:id/notesList\"]/android.view.ViewGroup[1]"));

    public PointSwipe lastPoint = new PointSwipe(By.xpath("//android.widget.ListView[@resource-id=\"com.vrproductiveapps.whendo:id/notesList\"]/android.view.ViewGroup[last()]"));

    public CheckboxList checkboxList = new CheckboxList(By.xpath("(//android.widget.ImageButton[@content-desc=\"Mark Done\"])"));

    public Button menuButton = new Button(By.xpath("//android.widget.ImageButton[@content-desc=\"Open navigation drawer\"]"));

    public Label getMenuOptionByTitle(String menuOptionName){
        return new Label(By.xpath("//android.widget.CheckedTextView[@text=\"" + menuOptionName + "\"]"));
    }
}

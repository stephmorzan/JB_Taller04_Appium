package activities.whenDo;

import control.CheckboxList;
import org.openqa.selenium.By;

public class CompletedTasksListScreen {

    public CheckboxList checkboxList = new CheckboxList(By.xpath("//android.widget.ListView[@resource-id=\"com.vrproductiveapps.whendo:id/notesList\"]/" +
            "android.view.ViewGroup[2]/android.widget.LinearLayout/android.widget.LinearLayout"));
}

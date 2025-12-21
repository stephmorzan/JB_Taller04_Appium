package activities.whenDo;

import control.Button;
import control.TextBox;
import org.openqa.selenium.By;

public class TaskDetailScreen {

    public TextBox titleTextBox = new TextBox(By.id("com.vrproductiveapps.whendo:id/noteTextTitle"));
    public TextBox notesTextBox = new TextBox(By.id("com.vrproductiveapps.whendo:id/noteTextNotes"));
    public Button saveButton = new Button(By.id("com.vrproductiveapps.whendo:id/saveItem"));
    public Button saveAndContinueButton = new Button(By.id("com.vrproductiveapps.whendo:id/saveAndNew"));
}

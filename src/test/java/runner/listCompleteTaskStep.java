package runner;

import activities.whenDo.CompletedTasksListScreen;
import activities.whenDo.MyListScreen;
import activities.whenDo.TaskDetailScreen;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import session.Session;

import java.time.Duration;
import java.util.List;

public class listCompleteTaskStep {

    MyListScreen myListScreen = new MyListScreen();
    TaskDetailScreen taskDetailScreen = new TaskDetailScreen();
    CompletedTasksListScreen completedTasksListScreen = new CompletedTasksListScreen();

    public listCompleteTaskStep() {
    }

    @Given("que he creado una lista con {int} tareas")
    public void queHeCreadoUnaListaConTareas(int countTasks) {
//        int count = 0;
//
//        //click +
//        device.findElement(By.id("com.vrproductiveapps.whendo:id/fab")).click();
//
//        while (count < countTasks){
//            String titulo = "AUTO" + System.currentTimeMillis();
//            String note = "this is a note " + count;
//            // type title
//            device.findElement(By.id("com.vrproductiveapps.whendo:id/noteTextTitle")).sendKeys(titulo);
//            // type notes
//            device.findElement(By.id("com.vrproductiveapps.whendo:id/noteTextNotes")).sendKeys(note);
//            if (count == countTasks-1){
//                // click save
//                device.findElement(By.id("com.vrproductiveapps.whendo:id/saveItem")).click();
//            }else{
//                //click save and continue
//                device.findElement(By.id("com.vrproductiveapps.whendo:id/saveAndNew")).click();
//            }
//            count++;
//        }
        int count = 0;
        myListScreen.addTaskButton.click();
        Task task = new Task();
        while (count < countTasks) {
            taskDetailScreen.titleTextBox.setText(task.generateTitle());
            taskDetailScreen.notesTextBox.setText(task.generateNote(count));
            if (count == countTasks - 1) {
                taskDetailScreen.saveButton.click();
            } else {
                taskDetailScreen.saveAndContinueButton.click();
            }
            count++;
        }
    }

    @And("la lista de tareas esta visible en la pantalla")
    public void laListaDeTareasEstaVisibleEnLaPantalla() {
//        WebDriverWait explicitWait = new WebDriverWait(device, Duration.ofSeconds(20));
//        explicitWait.until(ExpectedConditions.elementToBeClickable(By.id("com.vrproductiveapps.whendo:id/fab")));
        myListScreen.addTaskButton.waitToBeClickable();
    }

    @And("scrolleo al final de la lista")
    public void scrolleoAlFinalDeLaLista() throws InterruptedException {

        myListScreen.firstPoint.getLocation();
        myListScreen.lastPoint.getLocation();

        //Point firstPoint = device.findElement(By.xpath("//android.widget.ListView[@resource-id=\"com.vrproductiveapps.whendo:id/notesList\"]/android.view.ViewGroup[1]")).getLocation();
        //Point lastPoint = device.findElement(By.xpath("//android.widget.ListView[@resource-id=\"com.vrproductiveapps.whendo:id/notesList\"]/android.view.ViewGroup[last()]")).getLocation();

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence swipeAction = new Sequence(finger, 1);

        swipeAction.addAction(
                finger.createPointerMove(Duration.ofSeconds(1),
                        PointerInput.Origin.viewport(),
                        myListScreen.lastPoint.getX(),
                        myListScreen.lastPoint.getY())
//                        lastPoint.getX(),
//                        lastPoint.getY())
        );

        swipeAction.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));

        swipeAction.addAction(
                finger.createPointerMove(Duration.ofSeconds(1),
                        PointerInput.Origin.viewport(),
                        myListScreen.firstPoint.getX(),
                        myListScreen.firstPoint.getY())
//                        firstPoint.getX(),
//                        firstPoint.getY())
        );

        swipeAction.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        //device.perform(Arrays.asList(swipeAction));
        Session.getInstance().getDevice().perform(List.of(swipeAction));
        Thread.sleep(5000);
    }

    @When("marco la ultima tarea creada como completada")
    public void marcoLaUltimaTareaCreadaComoCompletada() {
        //List<WebElement> checkboxes = device.findElements(By.xpath("(//android.widget.ImageButton[@content-desc=\"Mark Done\"])"));
        //List<Checkbox> checkboxes = myListScreen.checkboxList.getCheckboxes();
        //myListScreen.checkboxList.getLastCheckbox(checkboxes).click();
        myListScreen.checkboxList.getLastControl().click();
    }

    @And("accedo a la seccion {string}")
    public void accedoALaSeccion(String menuOptionName) {
//        device.findElement(By.xpath("//android.widget.ImageButton[@content-desc=\"Open navigation drawer\"]")).click();
//
//        device.findElement(By.xpath("//android.widget.CheckedTextView[@text=\"" + menuOptionName + "\"]")).click();
        myListScreen.menuButton.click();
        myListScreen.getMenuOptionByTitle(menuOptionName).click();
    }

    @Then("solo deberia ver una tarea en la lista de tareas completadas")
    public void soloDeberiaVerUnaTareaEnLaListaDeTareasCompletadas() {
//        List<WebElement> completedTasks = device.findElements(By.
//                xpath("//android.widget.ListView[@resource-id=\"com.vrproductiveapps.whendo:id/notesList\"]/" +
//                        "android.view.ViewGroup[2]/android.widget.LinearLayout/android.widget.LinearLayout"));
//
//        Assertions.assertTrue(completedTasks.size() == 1, "Task was not marked and not completed");

        Assertions.assertEquals(1, completedTasksListScreen.checkboxList.getCheckboxes().size(), "Task was not marked and not completed");
    }
}

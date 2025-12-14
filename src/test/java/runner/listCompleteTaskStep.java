package runner;

import io.appium.java_client.AppiumDriver;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

public class listCompleteTaskStep {

    private final AppiumDriver device;

    public listCompleteTaskStep(Hooks hooks) {
        this.device = hooks.getDriver();
    }

    @Given("que he creado una lista con {int} tareas")
    public void queHeCreadoUnaListaConTareas(int countTasks) {
        int count = 0;

        //click +
        device.findElement(By.id("com.vrproductiveapps.whendo:id/fab")).click();

        while (count < countTasks){
            String titulo = "AUTO" + System.currentTimeMillis();
            String note = "this is a note " + count;
            // type title
            device.findElement(By.id("com.vrproductiveapps.whendo:id/noteTextTitle")).sendKeys(titulo);
            // type notes
            device.findElement(By.id("com.vrproductiveapps.whendo:id/noteTextNotes")).sendKeys(note);
            if (count == countTasks-1){
                // click save
                device.findElement(By.id("com.vrproductiveapps.whendo:id/saveItem")).click();
            }else{
                //click save and continue
                device.findElement(By.id("com.vrproductiveapps.whendo:id/saveAndNew")).click();
            }
            count++;
        }
    }

    @And("la lista de tareas esta visible en la pantalla")
    public void laListaDeTareasEstaVisibleEnLaPantalla() {
        WebDriverWait explicitWait = new WebDriverWait(device, Duration.ofSeconds(20));
        explicitWait.until(ExpectedConditions.elementToBeClickable(By.id("com.vrproductiveapps.whendo:id/fab")));
    }

    @And("scrolleo al final de la lista")
    public void scrolleoAlFinalDeLaLista() throws InterruptedException {
        Point firstPoint = device.findElement(By.xpath("//android.widget.ListView[@resource-id=\"com.vrproductiveapps.whendo:id/notesList\"]/android.view.ViewGroup[1]")).getLocation();
        Point lastPoint = device.findElement(By.xpath("//android.widget.ListView[@resource-id=\"com.vrproductiveapps.whendo:id/notesList\"]/android.view.ViewGroup[last()]")).getLocation();

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence swipeAction = new Sequence(finger, 1);

        swipeAction.addAction(
                finger.createPointerMove(Duration.ofSeconds(1),
                        PointerInput.Origin.viewport(),
                        lastPoint.getX(),
                        lastPoint.getY())
        );

        swipeAction.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));

        swipeAction.addAction(
                finger.createPointerMove(Duration.ofSeconds(1),
                        PointerInput.Origin.viewport(),
                        firstPoint.getX(),
                        firstPoint.getY())
        );

        swipeAction.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        device.perform(Arrays.asList(swipeAction));
        Thread.sleep(5000);
    }

    @When("marco la ultima tarea creada como completada")
    public void marcoLaUltimaTareaCreadaComoCompletada() {
        List<WebElement> checkboxes = device.findElements(By.xpath("(//android.widget.ImageButton[@content-desc=\"Mark Done\"])"));
        checkboxes.get(checkboxes.size()-1).click();
    }

    @And("accedo a la seccion {string}")
    public void accedoALaSeccion(String menuOptionName) {
        device.findElement(By.xpath("//android.widget.ImageButton[@content-desc=\"Open navigation drawer\"]")).click();

        device.findElement(By.xpath("//android.widget.CheckedTextView[@text=\"" + menuOptionName + "\"]")).click();
    }

    @Then("solo deberia ver una tarea en la lista de tareas completadas")
    public void soloDeberiaVerUnaTareaEnLaListaDeTareasCompletadas() {
        List<WebElement> completedTasks = device.findElements(By.
                xpath("//android.widget.ListView[@resource-id=\"com.vrproductiveapps.whendo:id/notesList\"]/" +
                        "android.view.ViewGroup[2]/android.widget.LinearLayout/android.widget.LinearLayout"));

        Assertions.assertTrue(completedTasks.size() == 1, "Task was not marked and not completed");
    }
}

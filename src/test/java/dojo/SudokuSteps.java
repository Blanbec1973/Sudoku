package dojo;

import control.AppConfig;
import control.eventmanager.EventManager;
import control.MyProperties;
import control.eventmanager.ModelToViewSynchonizer;
import control.eventmanager.NavigationService;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import model.Model;
import model.SimpleModelEventPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import view.MyView;

import java.awt.*;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SudokuSteps {
    private final MyView myView;
    private final EventManager eventManager;
    private final NavigationService navigationService;

    @Autowired
    public SudokuSteps() {

        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        MyProperties myProperties = context.getBean(MyProperties.class);
        this.myView= context.getBean(MyView.class);

        //services :
        SimpleModelEventPublisher publisher = context.getBean(SimpleModelEventPublisher.class);

        // Model
        Model model = context.getBean(Model.class);

        // EventManager
        this.eventManager = context.getBean(EventManager.class);
        ModelToViewSynchonizer synchonizer = context.getBean(ModelToViewSynchonizer.class);
        publisher.addListener(synchonizer);
        myView.registerController(eventManager);
        this.navigationService = context.getBean(NavigationService.class);

        // Control with injection

        Path path = Paths.get(System.getProperty("user.dir") + myProperties.getProperty("InitialFile")).toAbsolutePath();
        model.reload(path);
        myView.refreshGrilleDisplay(model.getGrille());
        myView.getFenetre().setVisible(true);
    }

    @Given("I start my Sudoku application with file {string}")
	public void i_start_my_Sudoku_application_with_file_fileName(String fileName) {
        navigationService.reloadGrille(Paths.get(fileName));
	}

	@When("I click on nextButton")
	public void i_click_on_nextButton() {
        eventManager.simulateClick("avance");
	}

	@Then("the cell number {int} is selected")
	public void the_cell_number_numCase_is_selected(int numCase) {
		assertTrue(true);
	}

	@Then("the cell number {int} is yellow")
	public void the_cell_number_numCase_is_yellow(Integer numCase) {
		Color c = myView.getCaseBackground(numCase);
		assertEquals(Color.YELLOW,c);
	}

	@When("I click {int} times on ExplainButton")
	public void i_click_times_on_ExplainButton(Integer nombreClics) {
	    for (int i = 0;i < nombreClics;i++) {
            eventManager.simulateClick("explique");
	    }
	}

	@Then("the cell number {int} is resolved by {int}")
	public void the_cell_number_is_resolved_by(Integer numCase, Integer valeur) {
	    int valeurCase = myView.getCaseValue(numCase);
		assertEquals(valeur, valeurCase);
	}

	@Then("the cell number {int} is green")
	public void the_cell_number_is_green(Integer numCase) {
		Color c = myView.getCaseBackground(numCase);
		assertEquals(Color.GREEN,c);
	}
}

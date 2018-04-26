package dtu.library.acceptance_tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import cucumber.api.java.en.*;
import dtu.library.app.*;

public class Work_On_Activity_Test {

	private Project project;
	private ArrayList <Worker> workers = new ArrayList<Worker>();
	private String errmsg;

	public Work_On_Activity_Test() {
		project = new Project("Proj");
	}

	@Given("^that I have a Worker \"([^\"]*)\"$")
	public void thatIHaveAWorker(String iD) throws Exception {
		workers.add(new Worker(iD));
		project.addWorker(workers.get(0));
	}

	@Given("^I have an activity with name \"([^\"]*)\" and time-estimate \"([^\"]*)\"$")
	public void iHaveAnActivityWithNameAndTimeEstimate(String name, int te) throws Exception {
		workers.add(0, new Worker("ProL"));
		project.setProjectLeader(workers.get(0), workers.get(0));
		project.addActivity(workers.get(0), new Activity(name, te, project));
	}

	@When("^Worker \"([^\"]*)\" takes activity \"([^\"]*)\"$")
	public void workerTakesActivity(String iD, String name) throws Exception {
		try {
			project.getActivities().get(0).addWorker(iD);
		} catch (Exception e) {
			errmsg = e.getMessage();
		}
	}
	
	@Then("^Worker \"([^\"]*)\" is working on activity \"([^\"]*)\"$")
	public void workerIsWorkingOnActivity(String iD, String name) throws Exception {
	    assertTrue(project.getActivities().get(0).getWorkers()[0].getID().equals(iD));
	}
	

}

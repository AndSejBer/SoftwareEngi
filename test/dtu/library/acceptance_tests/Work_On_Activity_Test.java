package dtu.library.acceptance_tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import cucumber.api.java.en.*;
import dtu.library.app.*;

public class Work_On_Activity_Test {

	private DataBase database;
	private ArrayList<Worker> workers = new ArrayList<Worker>();
	private String errmsg;

	public Work_On_Activity_Test() {
		database = new DataBase("drdtr");
		new Project("proj", database);
		//project = new Project("Proj");
	}

	@Given("^that I have a Worker \"([^\"]*)\"$")
	public void thatIHaveAWorker(String iD) throws Exception {
		workers.add(new Worker(iD));
		database.getProjects().get(0).addWorker(workers.get(workers.size() - 1));
	}

	@Given("^I have an activity with name \"([^\"]*)\" and time-estimate \"([^\"]*)\"$")
	public void iHaveAnActivityWithNameAndTimeEstimate(String name, double te) throws Exception {
		workers.add(0, new Worker("ProL"));
		database.getProjects().get(0).setProjectLeader(workers.get(0), workers.get(0));
		database.getProjects().get(0).addActivity(workers.get(0), new Activity(name, te, database.getProjects().get(0)));
	}

	@When("^Worker \"([^\"]*)\" takes activity \"([^\"]*)\"$")
	public void workerTakesActivity(String iD, String name) throws Exception {
		try {
			database.getProjects().get(0).getActivities().get(0).addWorker(iD);
		} catch (Exception e) {
			errmsg = e.getMessage();
		}
	}

	@Then("^Worker \"([^\"]*)\" is working on activity \"([^\"]*)\"$")
	public void workerIsWorkingOnActivity(String iD, String name) throws Exception {
		assertTrue(database.getProjects().get(0).getActivities().get(0).getWorkers()[0].getID().equals(iD));
	}

	@Then("^Worker \"([^\"]*)\" and Worker \"([^\"]*)\" is working on activity \"([^\"]*)\"$")
	public void workerAndWorkerIsWorkingOnActivity(String iD1, String iD2, String name) throws Exception {
		assertTrue(database.getProjects().get(0).getActivities().get(0).getWorkers()[0].getID().equals(iD1)
				&& database.getProjects().get(0).getActivities().get(0).getWorkers()[1].getID().equals(iD2));

	}

	@When("^Worker \"([^\"]*)\" works on the activity and add the time used: \"([^\"]*)\"$")
	public void IWorkOnTheActivityAndAddTheTimeUsed(String iD, double tid) throws Exception {
		try {
			database.getProjects().get(0).getActivities().get(0).addTimeSpent(tid, iD);
		} catch (Exception e) {
			errmsg = e.getMessage();
		}
	}

	@Then("^The activity has accumulated \"([^\"]*)\" amount of time$")
	public void theActivityHasAccumulatedAmountOfTime(double alltime) throws Exception {
		assertTrue(database.getProjects().get(0).getActivities().get(0).gettimeSpent() == (alltime));
	}

	@Then("^I get the Must be assigned to activity to add time error \"([^\"]*)\"$")
	public void IGetTheMustBeAssignedToActivityToAddTimeerror(String arg1) throws Exception {
		assertTrue(errmsg.equals(arg1));
	}
	
	@Then("^I get the Activity must exist to assign error \"([^\"]*)\"$")
	public void IgettheActivitymustexisttoassigerror(String arg1) throws Exception {
		assertTrue(errmsg.equals(arg1));
	}
}

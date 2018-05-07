package dtu.library.acceptance_tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import cucumber.api.java.en.*;
import dtu.library.app.*;

public class ActivityTest {

	private DataBase database;
	private Worker developer;
	private String errmsg = "";

	public ActivityTest() {
		developer = new Worker("Bria");
	}

	//Andreas
	@Given("^that i have a project with name \"([^\"]*)\"$")
	public void thatIHaveAProjectWithName(String name) throws Exception {
		database = new DataBase("drdtr");
		new Project (name,database);
	}

	//Andreas
	@Given("^that the projectleader is logged in$")
	public void thatTheProjectleaderIsLoggedIn() throws Exception {
		database.getProjects().get(0).setProjectLeader(developer, developer);
	}

	//Andreas
	@When("^i add the activity with name \"([^\"]*)\" and time - estimate \"([^\"]*)\"$")
	public void iAddTheActivityWithNameAndTimeEstimate(String name, int timeEstimate) throws Exception {
		database.getProjects().get(0).addActivity(developer, new Activity(name,timeEstimate, database.getProjects().get(0)));
	}

	//Andreas
	@Then("^activity with name \"([^\"]*)\" exists$")
	public void activityWithNameExists(String name) throws Exception {
		Boolean test = false;
		for (int i=0; i < database.getProjects().get(0).getActivities().size(); i++) {
			if(database.getProjects().get(0).getActivities().get(i).getName().equals(name)) {
				test = true;
			}
		}
		assertTrue(test);
	}

	//Andreas
	@When("^I add an activity with time - estimate \"([^\"]*)\"$")
	public void iAddAnActivityWithTimeEstimate(int timeEstimate) throws Exception {
		try {
			database.getProjects().get(0).addActivity(developer, new Activity(timeEstimate, database.getProjects().get(0)));
		} catch(Exception e) {
			errmsg = e.getMessage();
		}
	}

	//Andreas
	@Then("^I get the time-estimate error message \"([^\"]*)\"$")
	public void iGetThetimeEstimateErrorMessage(String error) throws Exception {
		assertTrue(errmsg.equals(error));
	}

	//Andreas
	@When("^I add the activity with name \"([^\"]*)\"$")
	public void iAddTheActivityWithName(String name) throws Exception {
		try {
			database.getProjects().get(0).addActivity(developer, new Activity(name, database.getProjects().get(0)));
		} catch (Exception e1) {
			errmsg = e1.getMessage();
		}
	}

	//Andreas
	@Then("^I get the name error message \"([^\"]*)\"$")
	public void iGetTheNameErrorMessage(String error) throws Exception {
		assertTrue(errmsg.equals(error));
	}
	
	//Andreas
	@Given("^that the projectleader is not logged in$")
	public void thatTheProjectleaderIsNotLoggedIn() throws Exception {
		//Do nothing, just don't use developer in the next bit
	}

	//Andreas
	@When("^I add an activity with name \"([^\"]*)\" and time - estimate \"([^\"]*)\"$")
	public void iAddAnActivityWithNameAndTimeEstimate(String name, int timeEstimate) throws Exception {
		Worker NotProjectLeader = new Worker("NotB");
		try {
			database.getProjects().get(0).addActivity(NotProjectLeader, new Activity(name, timeEstimate, database.getProjects().get(0)));
		} catch (Exception e2) {
			errmsg = e2.getMessage();
		}
	}
	
	//Andreas
	@Then("^I get the project leader error message \"([^\"]*)\"$")
	public void iGetTheProjectLeaderErrorMessage(String error) throws Exception {
		assertTrue(errmsg.equals(error));
	}

	//Andreas
	@When("^I add worker with ID \"([^\"]*)\" to activity with name \"([^\"]*)\" and time - estimate \"([^\"]*)\"$")
	public void iAddWorkerWithIDToActivityWithNameAndTimeEstimate(String iD, String name, int timeEstimate) throws Exception {
		database.getProjects().get(0).addActivity(developer, new Activity(name,timeEstimate, database.getProjects().get(0)));
		database.getProjects().get(0).getActivities().get(0).addWorker(new Worker(iD));
	}

	//Andreas
	@Then("^the worker with ID \"([^\"]*)\" is added to the activity$")
	public void theWorkerIsAddedToTheActivity(String iD) throws Exception {
		assertTrue(database.getProjects().get(0).getActivities().get(0).getWorkers()[0].getID().equals(iD));
	}

	//Andreas
	@When("^I add worker with ID \"([^\"]*)\" to activity with name \"([^\"]*)\"$")
	public void iAddWorkerWithIDToActivityWithName(String iD, String name) throws Exception {
		try {
			database.getProjects().get(0).addActivity(developer, new Activity(name, 10, database.getProjects().get(0)));
			database.getProjects().get(0).getActivities().get(0).addWorker(iD);
		} catch (Exception e3) {
			errmsg = e3.getMessage();
		}
	}

	//Andreas
	@Then("^I get the nonexistant worker error message \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\"$")
	public void iGetTheNonexistantWorkerErrorMessage(String error1, String error2, String error3) throws Exception {
		String error = error1 + "\"" +error2 + "\""+ error3;
		assertEquals(errmsg, error);
	}

	//Andreas
	@Given("^I have an activity with name \"([^\"]*)\" and time - estimate \"([^\"]*)\"$")
	public void iHaveAnActivityWithNameAndTimeEstimate(String name, int timeEstimate) throws Exception {
		database.getProjects().get(0).setProjectLeader(developer, developer);
		database.getProjects().get(0).addActivity(developer, new Activity(name, timeEstimate, database.getProjects().get(0)));
	}

	//Andreas
	@Given("^I have a worker with ID \"([^\"]*)\"$")
	public void iHaveAWorkerWithID(String iD) throws Exception {
		database.getProjects().get(0).addWorker(new Worker(iD));
	}

	
	//Andreas
	@When("^I add worker with ID \"([^\"]*)\" to the activity with name \"([^\"]*)\"$")
	public void iAddWorkerWithIDToTheActivityWithName(String iD, String name) throws Exception {
		try {
			database.getProjects().get(0).getActivities().get(0).addWorker(iD);
		} catch (Exception e4) {
			errmsg = e4.getMessage();
		}
	}

	//Andreas
	@Then("^the worker with ID \"([^\"]*)\" works on the activity$")
	public void theWorkerWorksOnTheActivity(String iD) throws Exception {
		assertTrue(database.getProjects().get(0).getActivities().get(0).getWorkers()[0].getID().equals(iD));
	}

	//Andreas
	@Then("^the worker with ID \"([^\"]*)\" and the worker with ID \"([^\"]*)\" works on the activity$")
	public void theWorkerWithIDAndTheWorkerWithIDWorksOnTheActivity(String iD1, String iD2) throws Exception {
		Boolean test = true;
		test = database.getProjects().get(0).getActivities().get(0).getWorkers()[0].getID().equals(iD1) &&
				database.getProjects().get(0).getActivities().get(0).getWorkers()[1].getID().equals(iD2);
		assertTrue(test);
	}

	//Andreas
	@Then("^I get the third worker error \"([^\"]*)\"$")
	public void iGetTheThirdWorkerError(String error) throws Exception {
		assertTrue(errmsg.equals(error));
	}
	
	//Andreas
	@Then("^I get the worker already on activity error \"([^\"]*)\"$")
	public void iGetTheWorkerAlreadyOnActivityError(String error) throws Exception {
	    assertTrue(errmsg.equals(error));
	}

	//Andreas
	@When("^I remove worker with ID \"([^\"]*)\" from activity$")
	public void iRemoveWorkerFromActivity(String iD) throws Exception {
		try {
			database.getProjects().get(0).getActivities().get(0).removeWorker(developer, iD);
		} catch (Exception e5) {
			errmsg = e5.getMessage();
		}
	}

	//Andreas
	@Then("^the worker with ID \"([^\"]*)\" is removed from the activity$")
	public void theWorkerIsRemovedFromTheActivity(String iD) throws Exception {
		Boolean test = true;
		for (int i = 0; i < 2; i++) {
			if(!(database.getProjects().get(0).getActivities().get(0).getWorkers()[i] == null)) {
				if (database.getProjects().get(0).getActivities().get(0).getWorkers()[i].getID().equals(iD)) {
					test = false;
				}
			}
		}
		assertTrue(test);
	}

	//Andreas
	@When("^worker with ID \"([^\"]*)\" removes himself from activity$")
	public void workerWithIDRemovesHimselfFromActivity(String iD) throws Exception {
		Worker worker = new Worker("NULL");
		for (int i = 0; i < database.getProjects().get(0).getWorkers().size(); i++) {
			if (database.getProjects().get(0).getWorkers().get(i).getID().equals(iD)) {
				worker = database.getProjects().get(0).getWorkers().get(i);
			}
		}
		database.getProjects().get(0).getActivities().get(0).removeWorker(worker, iD);
	}

	//Andreas
	@When("^worker with ID \"([^\"]*)\" removes worker with ID \"([^\"]*)\" from activity$")
	public void workerWithIDRemovesWorkerWithIDFromActivity(String iD1, String iD2) throws Exception {
		try {
			database.getProjects().get(0).getActivities().get(0).removeWorker(new Worker (iD1), iD2);
		} catch (Exception e6) {
			errmsg = e6.getMessage();
		}
	}

	//Andreas
	@Then("^I get the illegal removal error \"([^\"]*)\"$")
	public void iGetTheIllegalRemovalError(String error) throws Exception {
		assertTrue(errmsg.equals(error));
	}
}

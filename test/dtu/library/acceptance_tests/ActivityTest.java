package dtu.library.acceptance_tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import cucumber.api.java.en.*;
import dtu.library.app.*;

public class ActivityTest {

	private Project project;
	private Worker developer;
	private String errmsg = "";

	public ActivityTest() {
		developer = new Worker("Bria");
	}

	@Given("^that i have a project with name \"([^\"]*)\"$")
	public void thatIHaveAProjectWithName(String name) throws Exception {
		project = new Project(name);
	}

	@Given("^that the projectleader is logged in$")
	public void thatTheProjectleaderIsLoggedIn() throws Exception {
		project.setProjectLeader(developer, developer);
	}

	@When("^i add the activity with name \"([^\"]*)\" and time - estimate \"([^\"]*)\"$")
	public void iAddTheActivityWithNameAndTimeEstimate(String name, int timeEstimate) throws Exception {
		project.addActivity(developer, new Activity(name,timeEstimate, project));
	}

	@Then("^activity with name \"([^\"]*)\" exists$")
	public void activityWithNameExists(String name) throws Exception {
		Boolean test = false;
		for (int i=0; i < project.getActivities().size(); i++) {
			if(project.getActivities().get(i).getName().equals(name)) {
				test = true;
			}
		}
		assertTrue(test);
	}

	@When("^I add an activity with time - estimate \"([^\"]*)\"$")
	public void iAddAnActivityWithTimeEstimate(int timeEstimate) throws Exception {
		try {
			project.addActivity(developer, new Activity(timeEstimate, project));
		} catch(Exception e) {
			errmsg = e.getMessage();
		}
	}

	@Then("^I get the time-estimate error message \"([^\"]*)\"$")
	public void iGetThetimeEstimateErrorMessage(String error) throws Exception {
		assertTrue(errmsg.equals(error));
	}

	@When("^I add the activity with name \"([^\"]*)\"$")
	public void iAddTheActivityWithName(String name) throws Exception {
		try {
			project.addActivity(developer, new Activity(name, project));
		} catch (Exception e1) {
			errmsg = e1.getMessage();
		}
	}

	@Then("^I get the name error message \"([^\"]*)\"$")
	public void iGetTheNameErrorMessage(String error) throws Exception {
		assertTrue(errmsg.equals(error));
	}

	@Given("^that the projectleader is not logged in$")
	public void thatTheProjectleaderIsNotLoggedIn() throws Exception {
		//Do nothing, just don't use developer in the next bit
	}

	@When("^I add an activity with name \"([^\"]*)\" and time - estimate \"([^\"]*)\"$")
	public void iAddAnActivityWithNameAndTimeEstimate(String name, int timeEstimate) throws Exception {
		Worker NotProjectLeader = new Worker("NotB");
		try {
			project.addActivity(NotProjectLeader, new Activity(name, timeEstimate, project));
		} catch (Exception e2) {
			errmsg = e2.getMessage();
		}
	}
	@Then("^I get the project leader error message \"([^\"]*)\"$")
	public void iGetTheProjectLeaderErrorMessage(String error) throws Exception {
		assertTrue(errmsg.equals(error));
	}

	@When("^I add worker with ID \"([^\"]*)\" to activity with name \"([^\"]*)\" and time - estimate \"([^\"]*)\"$")
	public void iAddWorkerWithIDToActivityWithNameAndTimeEstimate(String iD, String name, int timeEstimate) throws Exception {
		project.addActivity(developer, new Activity(name,timeEstimate, project));
		project.getActivities().get(0).addWorker(new Worker(iD));
	}

	@Then("^the worker with ID \"([^\"]*)\" is added to the activity$")
	public void theWorkerIsAddedToTheActivity(String iD) throws Exception {
		assertTrue(project.getActivities().get(0).getWorkers()[0].getID().equals(iD));
	}

	@When("^I add worker with ID \"([^\"]*)\" to activity with name \"([^\"]*)\"$")
	public void iAddWorkerWithIDToActivityWithName(String iD, String name) throws Exception {
		try {
			project.addActivity(developer, new Activity(name, 10, project));
			project.getActivities().get(0).addWorker(iD);
		} catch (Exception e3) {
			errmsg = e3.getMessage();
		}
	}

	@Then("^I get the nonexistant worker error message \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\"$")
	public void iGetTheNonexistantWorkerErrorMessage(String error1, String error2, String error3) throws Exception {
		String error = error1 + "\"" +error2 + "\""+ error3;
		assertEquals(errmsg, error);
	}

	@Given("^I have an activity with name \"([^\"]*)\" and time - estimate \"([^\"]*)\"$")
	public void iHaveAnActivityWithNameAndTimeEstimate(String name, int timeEstimate) throws Exception {
		project.setProjectLeader(developer, developer);
		project.addActivity(developer, new Activity(name, timeEstimate, project));
	}

	@Given("^I have a worker with ID \"([^\"]*)\"$")
	public void iHaveAWorkerWithID(String iD) throws Exception {
		project.addWorker(new Worker(iD));
	}

	@When("^I add worker with ID \"([^\"]*)\" to the activity with name \"([^\"]*)\"$")
	public void iAddWorkerWithIDToTheActivityWithName(String iD, String name) throws Exception {
		try {
			project.getActivities().get(0).addWorker(iD);
		} catch (Exception e4) {
			errmsg = e4.getMessage();
		}
	}

	@Then("^the worker with ID \"([^\"]*)\" works on the activity$")
	public void theWorkerWorksOnTheActivity(String iD) throws Exception {
		assertTrue(project.getActivities().get(0).getWorkers()[0].getID().equals(iD));
	}

	@Then("^the worker with ID \"([^\"]*)\" and the worker with ID \"([^\"]*)\" works on the activity$")
	public void theWorkerWithIDAndTheWorkerWithIDWorksOnTheActivity(String iD1, String iD2) throws Exception {
		Boolean test = true;
		test = project.getActivities().get(0).getWorkers()[0].getID().equals(iD1) &&
				project.getActivities().get(0).getWorkers()[1].getID().equals(iD2);
		assertTrue(test);
	}

	@Then("^I get the third worker error \"([^\"]*)\"$")
	public void iGetTheThirdWorkerError(String error) throws Exception {
		assertTrue(errmsg.equals(error));
	}
	
	@Then("^I get the worker already on activity error \"([^\"]*)\"$")
	public void iGetTheWorkerAlreadyOnActivityError(String error) throws Exception {
	    assertTrue(errmsg.equals(error));
	}

	@When("^I remove worker with ID \"([^\"]*)\" from activity$")
	public void iRemoveWorkerFromActivity(String iD) throws Exception {
		try {
			project.getActivities().get(0).removeWorker(developer, iD);
		} catch (Exception e5) {
			errmsg = e5.getMessage();
		}
	}

	@Then("^the worker with ID \"([^\"]*)\" is removed from the activity$")
	public void theWorkerIsRemovedFromTheActivity(String iD) throws Exception {
		Boolean test = true;
		for (int i = 0; i < 2; i++) {
			if(!(project.getActivities().get(0).getWorkers()[i] == null)) {
				if (project.getActivities().get(0).getWorkers()[i].getID().equals(iD)) {
					test = false;
				}
			}
		}
		assertTrue(test);
	}

	@When("^worker with ID \"([^\"]*)\" removes himself from activity$")
	public void workerWithIDRemovesHimselfFromActivity(String iD) throws Exception {
		Worker worker = new Worker("NULL");
		for (int i = 0; i < project.getWorkers().size(); i++) {
			if (project.getWorkers().get(i).getID().equals(iD)) {
				worker = project.getWorkers().get(i);
			}
		}
		project.getActivities().get(0).removeWorker(worker, iD);
	}

	@When("^worker with ID \"([^\"]*)\" removes worker with ID \"([^\"]*)\" from activity$")
	public void workerWithIDRemovesWorkerWithIDFromActivity(String iD1, String iD2) throws Exception {
		try {
			project.getActivities().get(0).removeWorker(new Worker (iD1), iD2);
		} catch (Exception e6) {
			errmsg = e6.getMessage();
		}
	}

	@Then("^I get the illegal removal error \"([^\"]*)\"$")
	public void iGetTheIllegalRemovalError(String error) throws Exception {
		assertTrue(errmsg.equals(error));
	}
}

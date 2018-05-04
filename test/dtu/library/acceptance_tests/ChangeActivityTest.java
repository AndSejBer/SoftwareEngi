package dtu.library.acceptance_tests;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import cucumber.api.java.en.*;
import dtu.library.app.*;

public class ChangeActivityTest {
	
	private DataBase database;
	private Worker developer;
	private String errmsg = "";

	public ChangeActivityTest() {
		developer = new Worker("Kenny");
	}

	@Given("^that i have a change project with name \"([^\"]*)\"$")
	public void thatIHaveAChangeProjectWithName(String name) throws Exception {
		database = new DataBase("drdtr");
		database.CreateProject(new Project (name, database));
	}

	@Given("^that the projectleader logs in$")
	public void thatTheProjectleaderIsNowLoggedIn() throws Exception {
		database.getProjects().get(0).setProjectLeader(developer, developer);
	}
	
	@Given("^that the projectleader logs out$")
	public void thatTheProjectleaderIsNotLoggedIn() throws Exception {
		developer = new Worker ("NotB");
	}

	@Given("^the activity with name \"([^\"]*)\", time - estimate \"([^\"]*)\" , description \"([^\"]*)\", condition \"([^\"]*)\" and worker with ID \"([^\"]*)\" exists$")
	public void theActivityWithNameTimeEstimateDescriptionConditionAndWorker(String name, double timeEstimate, String description, String condition, String WorkeriD) throws Exception {
		database.getProjects().get(0).setProjectLeader(developer, developer);
		database.getProjects().get(0).addActivity(developer, new Activity(name, timeEstimate, database.getProjects().get(0), description, condition));
		database.getProjects().get(0).getActivities().get(0).addWorker(new Worker(WorkeriD));
	}
	
	@Given("^the activity with name \"([^\"]*)\" and time - estimate \"([^\"]*)\" exists$")
	public void theActivityWithNameTimeEstimate(String name, int timeEstimate) throws Exception {
		database.getProjects().get(0).setProjectLeader(developer, developer);
		database.getProjects().get(0).addActivity(developer, new Activity(name, timeEstimate, database.getProjects().get(0)));
	}
	
	//starts here
	@When("^i change the activity with ID \"([^\"]*)\" to have name \"([^\"]*)\"$")
	public void iChangeTheNameOfActivity(String activityid, String name) throws Exception {
		int ProjectID = Integer.parseInt(activityid.substring(activityid.length() - 1))-1;
		database.getProjects().get(0).getActivities().get(ProjectID).changeActivityName(developer, name);
	}

	@Then("^the activity with ID \"([^\"]*)\" now has the name \"([^\"]*)\"$")
	public void activityNameHasBeenChanged(String activityid, String name) throws Exception {
		int ProjectID = Integer.parseInt(activityid.substring(activityid.length() - 1))-1;
		Boolean test = false;
		if(database.getProjects().get(0).getActivities().get(ProjectID).getName().equals(name)) {
			test = true;
		}
		assertTrue(test);
	}
	
	//2nd
	@When("^i change the activity with ID \"([^\"]*)\" to not have a name$")
	public void iChangeActivityNameToBeNothing(String activityid) throws Exception {
		int ProjectID = Integer.parseInt(activityid.substring(activityid.length() - 1))-1;
		try {
			database.getProjects().get(0).getActivities().get(ProjectID).changeActivityName(developer, null);
		} catch(Exception e) {
			errmsg = e.getMessage();
		}
	}

	@Then("^I get the change activity name error message \"([^\"]*)\"$")
	public void iGetTheNameErrorMessage(String error) throws Exception {
		System.out.println(errmsg);
		assertTrue(errmsg.equals(error));
	}

	//3rd
	@When("^i change the activity with ID \"([^\"]*)\" to have name \"([^\"]*)\" without being projectleader$")
	public void iChangeActivityNameWhenNotProjectLeader(String activityid, String name) throws Exception {
		int ProjectID = Integer.parseInt(activityid.substring(activityid.length() - 1))-1;
		Worker NotProjectLeader = new Worker("NotB");
		try {
			database.getProjects().get(0).getActivities().get(ProjectID).changeActivityName(NotProjectLeader, name);
		} catch (Exception e1) {
			errmsg = e1.getMessage();
		}
	}
	@Then("^I get the change activity project leader error message \"([^\"]*)\"$")
	public void iGetTheChangeActivityProjectLeaderErrorMessage(String error) throws Exception {
		assertTrue(errmsg.equals(error));
	}

	//4th and 5th NEEDS SOME CHANGE
	@When("^i change the activity with ID \"([^\"]*)\" to have time - estimate \"([^\"]*)\"$")
	public void iChangeTheActivityToHaveTimeEstimate(String activityid, double time) throws Exception {
		int ProjectID = Integer.parseInt(activityid.substring(activityid.length() - 1))-1;
		try {
			database.getProjects().get(0).getActivities().get(ProjectID).changeActivityTime(developer, time);
		} catch(Exception e2) {
			errmsg = e2.getMessage();
		}
	}

	@Then("^the activity with ID \"([^\"]*)\" now has the time - estimate \"([^\"]*)\"$")
	public void theActivityNowHasTimeEstimate(String activityid, double time) throws Exception {
		int ProjectID = Integer.parseInt(activityid.substring(activityid.length() - 1))-1;
		Boolean test = false;
		Double activitytime = database.getProjects().get(0).getActivities().get(ProjectID).getTimeEstimate();
		Double timeestimate = time;
		if(activitytime.equals(timeestimate)) {
			test = true;
		}
		assertTrue(test);
	}

	@Then("^I get the 0 time-estimate error message \"([^\"]*)\"$")
	public void iGetTheZeroTimeEstimeError(String error) throws Exception {
		System.out.println(errmsg);
		assertEquals(errmsg, error);
	}

	//6th
	@When("^I change the activity with ID \"([^\"]*)\" to have description \"([^\"]*)\"$")
	public void iChangeTheActivityToHaveDescription(String activityid, String description) throws Exception {
		int ProjectID = Integer.parseInt(activityid.substring(activityid.length() - 1))-1;
		try {
			database.getProjects().get(0).getActivities().get(ProjectID).changeActivityDescription(developer, description);
		} catch(Exception e3) {
			errmsg = e3.getMessage();
		}
	}

	@Then("^the activity with ID \"([^\"]*)\" now has the description \"([^\"]*)\"$")
	public void theActivityNowHasDescription(String activityid, String description) throws Exception {
		int ProjectID = Integer.parseInt(activityid.substring(activityid.length() - 1))-1;
		Boolean test = false;
		if(database.getProjects().get(0).getActivities().get(ProjectID).getDescription().equals(description)) {
			test = true;
		}
		assertTrue(test);
	}	
	
	//7th - conditions
	@When("^I change the activity with ID \"([^\"]*)\" to have conditions \"([^\"]*)\"$")
	public void iChangeTheActivityToHaveCondition(String activityid, String condition) throws Exception {
		int ProjectID = Integer.parseInt(activityid.substring(activityid.length() - 1))-1;
		try {
			database.getProjects().get(0).getActivities().get(ProjectID).changeActivityCondition(developer, condition);
		} catch(Exception e3) {
			errmsg = e3.getMessage();
		}
	}

	@Then("^the activity with ID \"([^\"]*)\" now has the conditions \"([^\"]*)\"$")
	public void theActivityNowHasCondition(String activityid, String condition) throws Exception {
		int ProjectID = Integer.parseInt(activityid.substring(activityid.length() - 1))-1;
		Boolean test = false;
		if(database.getProjects().get(0).getActivities().get(ProjectID).getCondition().equals(condition)) {
			test = true;
		}
		assertTrue(test);
	}	
	
	//8th - workers
	@When("^I change the activity with ID \"([^\"]*)\" to have the description \"([^\"]*)\"$")
	public void iChangeTheActivityToHaveADescription(String activityid, String description) throws Exception {
		int ProjectID = Integer.parseInt(activityid.substring(activityid.length() - 1))-1;
		try {
			database.getProjects().get(0).getActivities().get(ProjectID).changeActivityDescription(developer,description);
		} catch(Exception e3) {
			errmsg = e3.getMessage();
		}
	}

	@Then("^the activity with ID \"([^\"]*)\" now actually has the description \"([^\"]*)\"$")
	public void theActivityNowHasADescription(String activityid, String description) throws Exception {
		int ProjectID = Integer.parseInt(activityid.substring(activityid.length() - 1))-1;
		Boolean test = false;
		if(database.getProjects().get(0).getActivities().get(ProjectID).getDescription().equals(description)) {
			test = true;
		}
		assertTrue(test);
	}	
	
}

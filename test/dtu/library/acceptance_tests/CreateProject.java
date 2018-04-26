package dtu.library.acceptance_tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import cucumber.api.java.en.*;
import dtu.library.app.*;

public class CreateProject {

	private Project project;
	private ArrayList <Worker> workers = new ArrayList<Worker>();
	private String errmsg;

	public CreateProject() {
	}

	@Given("^that a worker is logged in$")
	public void thatAWorkerIsLoggedIn() throws Exception {
		workers.add(new Worker("abcd"));
	}

	@When("^I create project with name \"([^\"]*)\"$")
	public void iCreateProjectWithName(String name) throws Exception {
		project = new Project(name);
	}

	@Then("^the projekt with name \"([^\"]*)\" and serial number \"([^\"]*)\" is created$")
	public void theProjectWithNameAndSerialNumberIsCreated(String name, String iD) {
		assertTrue(project.getName().equals(name) && project.getID().equals(iD));
	}

	@When("^I set worker with ID \"([^\"]*)\" as project leader$")
	public void iSetWorkerWithIDAsProjectLeader(String iD) throws Exception {
		Worker Brian = new Worker (iD);
		project.setProjectLeader(Brian, Brian);
	}

	@Then("^worker with ID \"([^\"]*)\" is project leader on project \"([^\"]*)\"$")
	public void workerWithIDIsProjectLeaderOnProject(String iD, String name) throws Exception {
		assertTrue(project.getProjectLeader().getID().equals(iD));
	}

	@When("^worker \"([^\"]*)\" sets worker \"([^\"]*)\" as project leader$")
	public void workerSetsWorkerAsProjectLeader(String iD1, String iD2) throws Exception {
		try {
			Worker Brian = new Worker(iD1);
			Worker Rasmus = new Worker(iD2);
			project.setProjectLeader(Brian, Rasmus);
		} catch (Exception e) {
			errmsg = e.getMessage();
		}
	}

	@Then("^I get the project leader change error \"([^\"]*)\"$")
	public void iGetTheProjectLeaderChangeError(String error) throws Exception {
		assertTrue(errmsg.equals(error));
	}

	@When("^I add worker with ID \"([^\"]*)\" to project with name \"([^\"]*)\"$")
	public void iAddWorkerWithIDToProjectWithName(String iD, String name) throws Exception {
		try {
			Worker Rasmus = new Worker(iD);
			project.addWorker(Rasmus);
		} catch (Exception e1) {
			errmsg = e1.getMessage();
		}
	}

	@Then("^worker \"([^\"]*)\" is working on project \"([^\"]*)\"$")
	public void workerIsWorkingOnProject(String iD, String name) throws Exception {
		assertTrue(project.getWorkers().get(0).getID().equals(iD));
	}

	@Then("^I get the allready working on that error \"([^\"]*)\"$")
	public void iGetTheAllreadyWorkingOnThatError(String error) throws Exception {
		assertTrue(errmsg.equals(error));
	}


}

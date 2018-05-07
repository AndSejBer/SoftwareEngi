package dtu.library.acceptance_tests;

import static org.junit.Assert.assertTrue;
import java.util.ArrayList;
import cucumber.api.java.en.*;
import dtu.library.app.*;

public class CreateProject {

	private DataBase database;
	private ArrayList <Worker> workers = new ArrayList<Worker>();
	private String errmsg;

	public CreateProject() {
	}

	//Andreas
	@Given("^that a worker is logged in$")
	public void thatAWorkerIsLoggedIn() throws Exception {
		workers.add(new Worker("abcd"));
	}

	//Andreas
	@When("^I create project with name \"([^\"]*)\"$")
	public void iCreateProjectWithName(String name) throws Exception {
		database = new DataBase("drdtr");
		new Project (name, database);
	}

	//Andreas
	@Then("^the projekt with name \"([^\"]*)\" and serial number \"([^\"]*)\" is created$")
	public void theProjectWithNameAndSerialNumberIsCreated(String name, String iD) {
		assertTrue(database.getProjects().get(0).getName().equals(name) && database.getProjects().get(0).getID().equals(iD));
	}

	//Andreas
	@When("^I set worker with ID \"([^\"]*)\" as project leader$")
	public void iSetWorkerWithIDAsProjectLeader(String iD) throws Exception {
		Worker Brian = new Worker (iD);
		database.getProjects().get(0).setProjectLeader(Brian, Brian);
	}

	//Andreas
	@Then("^worker with ID \"([^\"]*)\" is project leader on project \"([^\"]*)\"$")
	public void workerWithIDIsProjectLeaderOnProject(String iD, String name) throws Exception {
		assertTrue(database.getProjects().get(0).getProjectLeader().getID().equals(iD));
	}

	//Andreas
	@When("^worker \"([^\"]*)\" sets worker \"([^\"]*)\" as project leader$")
	public void workerSetsWorkerAsProjectLeader(String iD1, String iD2) throws Exception {
		try {
			Worker Brian = new Worker(iD1);
			Worker Rasmus = new Worker(iD2);
			database.getProjects().get(0).setProjectLeader(Brian, Rasmus);
		} catch (Exception e) {
			errmsg = e.getMessage();
		}
	}

	//Andreas
	@Then("^I get the project leader change error \"([^\"]*)\"$")
	public void iGetTheProjectLeaderChangeError(String error) throws Exception {
		assertTrue(errmsg.equals(error));
	}

	//Andreas
	@When("^I add worker with ID \"([^\"]*)\" to project with name \"([^\"]*)\"$")
	public void iAddWorkerWithIDToProjectWithName(String iD, String name) throws Exception {
		try {
			Worker Rasmus = new Worker(iD);
			database.getProjects().get(0).addWorker(Rasmus);
		} catch (Exception e1) {
			errmsg = e1.getMessage();
		}
	}

	//Andreas
	@Then("^worker \"([^\"]*)\" is working on project \"([^\"]*)\"$")
	public void workerIsWorkingOnProject(String iD, String name) throws Exception {
		assertTrue(database.getProjects().get(0).getWorkers().get(0).getID().equals(iD));
	}

	//Andreas
	@Then("^I get the allready working on that error \"([^\"]*)\"$")
	public void iGetTheAllreadyWorkingOnThatError(String error) throws Exception {
		assertTrue(errmsg.equals(error));
	}

	//Andreas
	@Given("^that the worker with ID \"([^\"]*)\" logs in$")
	public void thatTheWorkerWithIDLogsIn(String name) throws Exception {
		workers.add(new Worker(name));
	}
	
	//Andreas
	@Given("^that the project leader \"([^\"]*)\" logs in$")
	public void thatTheProjectLeaderLogsIn(String name) throws Exception {
		workers.add(new Worker(name));
	}
	
	//Andreas
	@And("^the project leader with ID \"([^\"]*)\" and the workers with ID \"([^\"]*)\" and ID \"([^\"]*)\" works on project with name \"([^\"]*)\"$")
	public void thatTheWorkersWithIDWorksOnProject(String bria, String liam, String rasm, String proj) throws Exception {
		database = new DataBase("drdtr");
		new Project(proj, database);
		//project = new Project(proj);
		Worker Brian = new Worker(bria);
		database.getProjects().get(0).setProjectLeader(Brian, Brian);
		Worker Liam = new Worker(liam);
		Worker Rasmus = new Worker(rasm);
		database.getProjects().get(0).addWorker(Liam);
		database.getProjects().get(0).addWorker(Rasmus);
	}

	//Andreas
	@When("^The worker with ID \"([^\"]*)\" removes the worker with ID \"([^\"]*)\"$")
	public void IRemoveWorker(String remover, String removewho) throws Exception {
		try {
			Worker removeworker = null;
			for(int i = 0; i < database.getProjects().get(0).getWorkers().size();i++) {
				if(database.getProjects().get(0).getWorkers().get(i).getID().equals(remover)) {
					removeworker = database.getProjects().get(0).getWorkers().get(i);
				} else if (database.getProjects().get(0).getProjectLeader().getID().equals(remover)) {
					removeworker = database.getProjects().get(0).getProjectLeader();
				}
			}
			if(!(removeworker==null)) {
				database.getProjects().get(0).removeWorker(removeworker, removewho);
			}
		} catch (Exception e2) {
			errmsg = e2.getMessage();
		}
	}

	//Andreas
	@Then("^the worker \"([^\"]*)\" is no longer working on project \"([^\"]*)\"$")
	public void theWorkerIsNoLongerWorkingOnProject(String name, String proj) {
		boolean searchresult = true;
		for(int i = 0; i < database.getProjects().get(0).getWorkers().size(); i++) {
			if (database.getProjects().get(0).getWorkers().get(i).getID().equals(name)) {
				searchresult = false;
			}
		}
		assertTrue(searchresult==true);
	}
	
	//Andreas
	@Then("^I get the cannot remove as non-project leader error \"([^\"]*)\"$")
	public void IGetTheCannotRemoveAsNonProjectLeaderError(String error) {
		assertTrue(errmsg.equals(error));
	}

	//Anders
	@Given("^that i make (\\d+) projects$")
	public void thatIMakeProjects(int arg1) throws Exception {
		database = new DataBase("drdtr");
		for (int i = 0; i <= 11; i++) {
			new Project (Integer.toString(i), database);
		}
	}
	
	//Anders
	@Then("^the last project has ID \"([^\"]*)\"$")
	public void theLastProjectHasID(String arg1) throws Exception {
		assertTrue(database.getProjects().get(11).getID().equals(arg1));
	}
	
	//Andreas
	@Then("^I get the no worker error \"([^\"]*)\"$")
	public void IGetNoWorkerWithIDError(String error) {
		assertTrue(errmsg.equals(error));
	}
}

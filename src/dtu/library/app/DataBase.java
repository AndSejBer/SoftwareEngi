package dtu.library.app; 

import java.util.ArrayList;

//lavet af Jesper Blak
public class DataBase {

	private ArrayList<Project> projectList = new ArrayList<Project>();
	private ArrayList<Worker> workerList = new ArrayList<Worker>();
	
	public DataBase(String test) {
		
	}
	
	public DataBase() {
		Worker Sage= new Worker("Sage");
		Worker Otto= new Worker("Otto");
		Worker Abel= new Worker("Abel");
		Worker Jack= new Worker("Jack");
		Worker Elsa= new Worker("Elsa");
		Project project1 = new Project("Create software project", this);
		Project project2 = new Project("fix bugs", this);
		addWorker(Sage);
		addWorker(Otto);
		
		try {
		project1.setProjectLeader(Sage,Sage);
		project2.setProjectLeader(Jack,Jack);
		
		project1.addActivity(Sage, new Activity("Get coffee", 0.5, project1));
		project1.getActivities().get(0).changeActivityCondition(Sage, "Fetched more coffee");
		project1.getActivities().get(0).changeActivityDescription(Sage, "We need coffee to live!");
		project1.addActivity(Sage, new Activity("Make features", 10, project1));
		project1.getActivities().get(1).changeActivityCondition(Sage, "Made all features");
		project1.getActivities().get(1).changeActivityDescription(Sage, "Make the features that we want in cucumber code");
		project1.addActivity(Sage, new Activity("Make tests", 8, project1));
		project1.getActivities().get(2).changeActivityCondition(Sage, "Made all relevant tests");
		project1.getActivities().get(2).changeActivityDescription(Sage, "Make tests for the cucumber code");
		project1.addActivity(Sage, new Activity("Make source code", 15, project1));
		project1.getActivities().get(3).changeActivityCondition(Sage, "Made the source code");
		project1.getActivities().get(3).changeActivityDescription(Sage, "Write the actual code");
		
		project2.addActivity(Jack, new Activity("Fix bugs", 2, project2));
		project2.getActivities().get(0).changeActivityCondition(Jack, "Fixed all bugs known to man");
		project2.getActivities().get(0).changeActivityDescription(Jack, "Fix the bugs so that the program works as expected");
		project2.addActivity(Jack, new Activity("Eat cake", 1, project2));
		project2.getActivities().get(1).changeActivityCondition(Jack, "Ate some good cake yo");
		project2.getActivities().get(1).changeActivityDescription(Jack, "We're done! Time for celebrations!");
		
		} catch (Exception asdf) {
			
		}

		workerList.add(Abel);
		workerList.add(Jack);
		workerList.add(Elsa);

	}

	public void addWorker(Worker worker) {
		workerList.add(worker);
	}

	public void CreateProject(Project project) {
		projectList.add(project);
	}

	public ArrayList<Project> getProjects() {
		return projectList;
	}

	public ArrayList<Worker> getWorkers() {
		return workerList;
	}
}

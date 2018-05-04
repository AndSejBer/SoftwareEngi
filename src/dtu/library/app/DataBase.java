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
		project1.addActivity(Sage, new Activity("Make features", 10, project1));
		project1.addActivity(Sage, new Activity("Make tests", 8, project1));
		project1.addActivity(Sage, new Activity("Make source code", 15, project1));
		
		project2.addActivity(Jack, new Activity("Fix bugs", 2, project2));
		project2.addActivity(Jack, new Activity("Eat cake", 1, project2));
		
		} catch (Exception asdf) {
			
		}
		
		workerList.add(Sage);
		workerList.add(Otto);
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

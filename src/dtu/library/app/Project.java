package dtu.library.app;

import java.time.Year;
import java.util.ArrayList;

public class Project {

	private DataBase database;
	private String name,iD;
	private Worker projectLeader;
	private ArrayList <Activity> activities = new ArrayList <Activity>();
	private ArrayList <Worker> workers = new ArrayList <Worker>();

	//Andreas
	//Running number should be corrected
	
	//Anders
	public Project(String name, DataBase database) {
		this.database = database;
		this.name = name;
		String iDNumber;
		if (database.getProjects().size()<10) {
			iDNumber = "0"+(database.getProjects().size()+1);
		} else {
			iDNumber = ""+(database.getProjects().size()+1);
		}	
		iD="" + Year.now().getValue() + iDNumber;
		database.CreateProject(this);
	}

	//Andreas
	public void setProjectLeader(Worker projectLeader, Worker developer) throws Exception{ 
		if (this.projectLeader == null) {
			this.projectLeader = developer;
		} else if(this.projectLeader.getID().equals(projectLeader.getID())) {
			this.projectLeader = developer;
		} else {
			throw new OperationNotAllowedException("Must be current project leader to change project leader");
		}
	}

	//Andreas
	public void addActivity(Worker developer, Activity activity) throws Exception{
		if (!(projectLeader == null) && developer.getID().equals(projectLeader.getID())) {
			activities.add(activity);
		}else {
			throw new OperationNotAllowedException("Must be project leader to create new activity");
		}
	}

	//Andreas
	public ArrayList <Activity> getActivities() {
		return activities;
	}

	//Andreas
	public ArrayList <Worker> getWorkers() {
		return workers;
	}

	//Andreas
	public void addWorker(Worker worker) throws Exception {
		for (int i = 0; i < workers.size(); i++) {
			if (workers.get(i).getID().equals(worker.getID())) {
				throw new OperationNotAllowedException("That worker is already working on this project");
			}
		}
		workers.add(worker);
	}

	//Andreas
	public void removeWorker(Worker developer, String iD) throws Exception{
		if(projectLeader.equals(developer) || developer.getID().equals(iD)) {
			for (int i = 0; i < workers.size(); i++) {
				if ( !(workers.get(i)==null) && workers.get(i).getID().equals(iD)) {
					workers.remove(i);
					return;
				}
			}
			throw new OperationNotAllowedException("No worker with ID \"" + iD + "\" exist");
		} else {
			throw new OperationNotAllowedException("Must be either project leader or the worker to remove from project");
		}
	}

	public Worker getProjectLeader() {
		return projectLeader;
	}

	public String getID() {
		return iD;
	}

	public String getName() {
		return name;
	}

}

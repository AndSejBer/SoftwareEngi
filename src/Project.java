package dtu.library.app;

import java.time.Year;
import java.util.ArrayList;

public class Project {

	private String name,iD;
	private Worker projectLeader;
	private ArrayList <Activity> activities = new ArrayList <Activity>();
	private int activityCounter = 0;
	private ArrayList <Worker> workers = new ArrayList <Worker>();

	//Running number should be corrected
	public Project(String name) {
		this.name = name;
		iD="" + Year.now().getValue() + "01";
	}

	public void setProjectLeader(Worker projectLeader, Worker developer) throws Exception{
		if (this.projectLeader == null) {
			this.projectLeader = developer;
		} else if(this.projectLeader.getID().equals(projectLeader.getID())) {
			this.projectLeader = developer;
		} else {
			throw new OperationNotAllowedException("Must be current project leader to change project leader");
		}
	}

	public void addActivity(Worker developer, Activity activity) throws Exception{
		if (!(projectLeader == null) && developer.getID().equals(projectLeader.getID())) {
			activities.add(activity);
			activityCounter++;
			activity.setIDForActivity(activityCounter);
		}else {
			throw new OperationNotAllowedException("Must be project leader to create new activity");
		}
	}

	public ArrayList <Activity> getActivities() {
		return activities;
	}

	public ArrayList <Worker> getWorkers() {
		return workers;
	}

	public void addWorker(Worker worker) throws Exception {
		for (int i = 0; i < workers.size(); i++) {
			if (workers.get(i).getID().equals(worker.getID())) {
				throw new OperationNotAllowedException("That worker is already working on this project");
			}
		}
		workers.add(worker);
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

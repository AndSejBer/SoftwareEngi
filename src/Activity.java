package dtu.library.app;

import java.time.LocalDate;

public class Activity {

	private String ID;
	private String name;
	private String description;
	private String condition;
	private int timeEstimate;
	private Worker workers[] = new Worker[2];
	private Project project;

	public Activity(String name, int timeEstimate, Project project) {
		this.name = name;
		this.timeEstimate = timeEstimate;
		this.project = project;
	}
	
	public Activity(String name, int timeEstimate, Project project, String description, String condition) {
		this.name = name;
		this.timeEstimate = timeEstimate;
		this.project = project;
		this.description = description;
		this.condition = condition;
	}

	public Activity(int timeEstimate, Project project) throws Exception {
		throw new OperationNotAllowedException("Name is required for an activity");
	}

	public Activity(String name, Project project) throws Exception {
		throw new OperationNotAllowedException("Time estimate is required for an activity");
	}
	
	public void setIDForActivity(int activityNumber) {
		ID ="" + LocalDate.now().getYear() + LocalDate.now().getMonthValue() + LocalDate.now().getDayOfMonth() + "-a" + activityNumber;
	}

	public String getID() {
		return ID;
	}
	public String getName() {
		return name;
	}
	public String getDescription() {
		return description;
	}
	public String getCondition() {
		return condition;
	}
	public String getTimeEstimate() {
		String sTime = "Time estimate is: " + Integer.toString(timeEstimate) + " half hours which is also " + Integer.toString(timeEstimate/2) + " hours";
		return sTime;
	}

	public void addWorker(Worker worker) throws Exception{
		if (workers[0] == null) {
			workers[0] = worker;
		} else if (workers[1] == null && !(workers[0].getID().equals(worker.getID()))) {
			workers[1] = worker;
		} else if (!(workers[0].getID().equals(worker.getID()))){
			throw new OperationNotAllowedException("Can only have two workers on an activity");
		} else {
			throw new OperationNotAllowedException("Worker already working on activity");
		}
	}

	public Worker[] getWorkers() {
		return workers;
	}

	public void addWorker(String iD) throws Exception {
		if (project.getWorkers().size() == 0) {
			throw new OperationNotAllowedException("No worker with ID \"" + iD + "\" exist");
		} else {
			for (int i = 0; i < project.getWorkers().size(); i++) {
				if (project.getWorkers().get(i).getID().equals(iD)) {
					addWorker(project.getWorkers().get(i));
					return;
				} 
			}
			throw new OperationNotAllowedException("No worker with ID \"" + iD + "\" exist");
		}
	}

	public void removeWorker(Worker developer, String iD) throws Exception{
		if(project.getProjectLeader().equals(developer) || developer.getID().equals(iD)) {
			if ( !(workers[0]==null) && workers[0].getID().equals(iD)) {
				workers[0] = null;
				if (workers[1] != null) {
					workers[0] = workers [1];
					workers[1] = null;
				}
			} else if(!(workers[1]==null) && workers[1].getID().equals(iD)) {
				workers[1] = null;
			} else {
				throw new OperationNotAllowedException("No worker with ID \"" + iD + "\" exist");
			}
		} else {
			throw new OperationNotAllowedException("Must be either project leader or the worker to remove from activity");
		}
	}
	
	public void changeActivity(Worker developer, String iD, String changeWhat, String change) throws Exception{
		if(project.getProjectLeader().equals(developer) || developer.getID().equals(iD) || !(change.equals(null))) {
			//Depending on changeWhat, the activity will be changed accordingly (such as activity name)
			if (changeWhat.equals("changeName")) {
				name = change;
			} else if (changeWhat.equals("changeTimeEstimate")) {
				timeEstimate = Integer.parseInt(change);
			} else if (changeWhat.equals("changeDescription")) {
				description = change;
			} else if (changeWhat.equals("changeCondition")) {
				condition = change;
			} else {
				throw new OperationNotAllowedException("Must be valid change command (changeName, changeTimeEstimate, changeDescription, changeCondition)");
			}
		} else {
			throw new OperationNotAllowedException("Must be project leader to make changes to activity");
		}
	}
}

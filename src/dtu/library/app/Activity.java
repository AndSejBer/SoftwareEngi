package dtu.library.app;

import java.time.LocalDate;

public class Activity {

	private String ID;
	private String name;
	private String description;
	private String condition;
	private double timeEstimate;
	private double timeSpent = 0;
	private Worker workers[] = new Worker[2];
	private Project project;

	public Activity(String name, double timeEstimate, Project project) {
		ID ="" + LocalDate.now().getYear() + LocalDate.now().getMonthValue() + LocalDate.now().getDayOfMonth() + "-a" + project.getActivities().size();
		this.name = name;
		this.timeEstimate = timeEstimate;
		this.project = project;
	}
	
	public Activity(String name, double timeEstimate, Project project, String description, String condition) {
		if(!(name==null)||!(timeEstimate==0.0)||!(description==null)||!(condition==null)) {
			this.name = name;
			this.timeEstimate = timeEstimate;
			this.project = project;
			this.description = description;
			this.condition = condition;
		}
	}

	public Activity(int timeEstimate, Project project) throws Exception {
		throw new OperationNotAllowedException("Name is required for an activity");
	}

	public Activity(String name, Project project) throws Exception {
		throw new OperationNotAllowedException("Time estimate is required for an activity");
	}

	public String getID() {
		return ID;
	}
	public String getName() {
		return name;
	}
	public double getTimeEstimate() {
		return timeEstimate;
	}
	public double gettimeSpent() {
		return timeSpent;
	}
	public String getDescription() {
		return description;
	}

	public String getCondition() {
		return condition;
	}
	
	public void addTimeSpent(double workedfrom, double workedtoo, String worker) throws Exception {
		addTimeSpent(workedtoo - workedfrom, worker);
	}

	public void addTimeSpent(double time, String iD) throws Exception {
		if (time < 0) {
			throw new OperationNotAllowedException("Can not work zero or negative amount of hours");
		} else if (((workers[0] == null) && (workers[1] == null))
				|| ((!(iD.equals(workers[0].getID()))))
				&& ((workers[1] == null) || (!(iD.equals(workers[1].getID()))))) {
			throw new OperationNotAllowedException("Must be assigned to activity to add time");
		} else {
			timeSpent += (time);
		}
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
	
	
	public void changeActivityName(Worker developer, String name) throws Exception{
		if(project.getProjectLeader().equals(developer)) {
			if (name!=null) {
				this.name = name;
			} else {
				throw new OperationNotAllowedException("Name is required for an activity");
			}
		} else {
			throw new OperationNotAllowedException("Must be project leader to make changes to activity");
		}
	}
	public void changeActivityTime(Worker developer, double time) throws Exception{
		if(project.getProjectLeader().equals(developer)) {
			if (time > 0) {
				timeEstimate = time;
			} else {
				throw new OperationNotAllowedException("Time-estimate cannot be 0 hours");
			}
		} else {
			throw new OperationNotAllowedException("Must be project leader to make changes to activity");
		}
	}
	public void changeActivityDescription(Worker developer, String description) throws Exception{
		if(project.getProjectLeader().equals(developer)) {
			if (!description.equals(null)) {
				this.description = description;
			} else {
				//do nothing
			}
		} else {
			throw new OperationNotAllowedException("Must be project leader to make changes to activity");
		}
	}
	public void changeActivityCondition(Worker developer, String condition) throws Exception{
		if(project.getProjectLeader().equals(developer)) {
			if (!condition.equals(null)) {
				this.condition = condition;
			} else {
				//do nothing
			}
		} else {
			throw new OperationNotAllowedException("Must be project leader to make changes to activity");
		}
	}
	
}


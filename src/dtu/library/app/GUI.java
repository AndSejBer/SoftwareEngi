package dtu.library.app;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

//Andreas
public class GUI extends JFrame implements ActionListener{//Initialization of most parts of the GUI:
	protected JTextArea informationLabel = new JTextArea();

	protected JTextArea projectsListAdditional = new JTextArea();

	protected DataBase dataBase = new DataBase();

	//The sheets of options
	protected MainSheet mainSheet = new MainSheet(this);
	protected ChangeOperationSheet changeOptSheet = new ChangeOperationSheet(this);
	protected adminOptionsSheet adminOpt = new adminOptionsSheet();
	protected ProjectLeadOptionsSheet projLeadOpt = new ProjectLeadOptionsSheet();

	//List of the projects
	protected ArrayList<Project> allCurrentProjects = dataBase.getProjects();
	protected ArrayList<JTextField> allCurrentProjectsList = new ArrayList<JTextField>();

	//The current worker
	protected Worker worker = dataBase.getWorkers().get(0);

	protected JLabel listOfProjectsL = new JLabel("All current projects:");
	protected JLabel currentWorkerID = new JLabel("Current worker: " + worker.getID());

	public GUI() {

		//Some setup log area etc.
		informationLabel.setEditable(false);
		informationLabel.setBackground(Color.LIGHT_GRAY);
		informationLabel.setName("Feedback log:");
		informationLabel.setForeground(Color.RED);
		informationLabel.setLineWrap(true);
		JScrollPane infoPane = new JScrollPane(informationLabel);


		//The layout of the main part is a grid layout with 2 horizontal slots
		getContentPane().setLayout(new GridLayout(1,2));

		//ActionListener is added to all buttons
		mainSheet.startNewProjectB.addActionListener(this);
		mainSheet.startNewActivityB.addActionListener(this);
		mainSheet.addTimeB.addActionListener(this);
		mainSheet.startOnProjectB.addActionListener(this);
		mainSheet.startOnActivityB.addActionListener(this);

		changeOptSheet.setProjectLeadB.addActionListener(this);
		changeOptSheet.changeActivityNameB.addActionListener(this);
		changeOptSheet.changeTimeEstimateB.addActionListener(this);
		changeOptSheet.changeActivityDescriptionB.addActionListener(this);
		changeOptSheet.changeActivityConditionB.addActionListener(this);

		projLeadOpt.chooseProjB.addActionListener(this);
		projLeadOpt.addWorkerB.addActionListener(this);
		projLeadOpt.checkTimeB.addActionListener(this);
		projLeadOpt.removeWorkerB.addActionListener(this);
		projLeadOpt.removeWorkerActB.addActionListener(this);
		projLeadOpt.compActivityB.addActionListener(this);

		adminOpt.makeNewWorkerB.addActionListener(this);
		adminOpt.changeToOtherWorkerB.addActionListener(this);
		adminOpt.listWorkersB.addActionListener(this);
		adminOpt.listProjectsB.addActionListener(this);

		//The list of all current projects is setup
		JPanel projectsList = new JPanel();
		projectsList.setLayout(new GridLayout(3,1));

		reDrawProjectList();

		JScrollPane projectsListScroll = new JScrollPane(projectsListAdditional);
		JPanel scrollAdd = new JPanel();
		scrollAdd.setLayout(new GridLayout(2,1));
		scrollAdd.add(currentWorkerID);
		scrollAdd.add(listOfProjectsL);
		projectsList.add(scrollAdd);
		projectsList.add(projectsListScroll);
		projectsList.add(infoPane);
		listOfProjectsL.setSize(50,100);
		projectsListAdditional.setSize(700, 150);

		JTabbedPane options = new JTabbedPane();
		options.add("Main options", mainSheet);
		options.add("Change options", changeOptSheet);
		options.add("Project lead options", projLeadOpt);
		options.add("DEV AND ADMIN", adminOpt);

		//Adding it all to the main GUI
		getContentPane().add(projectsList);
		getContentPane().add(options);

		//Close operation
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}

	public static void main(String[] args) {
		GUI gUI = new GUI();
		gUI.setTitle("Software project");
		gUI.setSize(1000, 800);
		gUI.setResizable(false);
		gUI.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == mainSheet.startNewProjectB) {
			if (mainSheet.newProjectNameF != null && !mainSheet.newProjectNameF.getText().equals("")) {
				allCurrentProjects.add(new Project(mainSheet.newProjectNameF.getText(), dataBase));
			} else {
				informationLabel.append("Must give a project a name \n");
			}

			if (mainSheet.newProjectLeadF != null && !mainSheet.newProjectLeadF.getText().equals("")) {
				try { 
					allCurrentProjects.get(allCurrentProjects.size()-1).setProjectLeader(getCurrentWorker(), findWorker(mainSheet.newProjectLeadF.getText()));
				} catch (Exception error) {
					informationLabel.append(error.getMessage() + "\n");
				}
			}
			mainSheet.newProjectNameF.setText("");
			mainSheet.newProjectLeadF.setText("");

			reDrawProjectList();

		}else if (e.getSource() == mainSheet.startNewActivityB) {
			Project current = null;

			for (int i = 0; i < allCurrentProjects.size(); i++) {
				if (allCurrentProjects.get(i).getID().equals(mainSheet.newActivityProjectF.getText()) || allCurrentProjects.get(i).getName().equals(mainSheet.newActivityProjectF.getText())) {
					current = allCurrentProjects.get(i);
				}
			}

			try {
				if (!mainSheet.newActivityNameF.getText().equals("")) {
					if (!mainSheet.newActivityTimeEstF.getText().equals("")) {
						current.addActivity(worker, new Activity(mainSheet.newActivityNameF.getText(), Double.parseDouble(mainSheet.newActivityTimeEstF.getText()), current));
					} else {
						throw new OperationNotAllowedException("Time estimate is required for an activity");
					}
				} else {
					throw new OperationNotAllowedException("Name is required for an activity");
				}
			} catch (Exception error) {
				informationLabel.append(error.getMessage() + "\n");
			}

			if (current == null) {
				informationLabel.append("No project with that ID or name found \n");
			} 

			reDrawProjectList();

			mainSheet.newActivityNameF.setText("");
			mainSheet.newActivityProjectF.setText("");
			mainSheet.newActivityTimeEstF.setText("");

		}else if (e.getSource() == mainSheet.addTimeB) {
			Activity currentA = null;

			for (int i = 0; i < allCurrentProjects.size(); i++) {
				for (int j = 0; j < allCurrentProjects.get(i).getActivities().size(); j++) {
					if (allCurrentProjects.get(i).getActivities().get(j).getName().equals(mainSheet.addTimeActivityF.getText())) {
						currentA = allCurrentProjects.get(i).getActivities().get(j);
						break;
					}
				}
			}
			try {
				currentA.addTimeSpent(Double.parseDouble(mainSheet.addTimeFromF.getText()), Double.parseDouble(mainSheet.addTimeToF.getText()), worker);
			} catch (Exception error) {
				if (currentA == null) {
					informationLabel.append("No activity found with name: " + mainSheet.addTimeActivityF.getText() + "\n");
				} else {
					informationLabel.append(error.getMessage() + "\n");
				}
			}

			reDrawProjectList();

			mainSheet.addTimeActivityF.setText("");
			mainSheet.addTimeFromF.setText("");
			mainSheet.addTimeToF.setText("");

		}else if (e.getSource() == mainSheet.startOnProjectB) {
			Project current = null;

			for (int i = 0; i < allCurrentProjects.size(); i++) {
				if (allCurrentProjects.get(i).getID().equals(mainSheet.workOnProjectProjectF.getText()) || allCurrentProjects.get(i).getName().equals(mainSheet.workOnProjectProjectF.getText())) {
					current = allCurrentProjects.get(i);
					try {
						current.addWorker(worker);
						informationLabel.append("Started working on project: " + mainSheet.workOnProjectProjectF.getText() + "\n");
					} catch (Exception error) {
						informationLabel.append(error.getMessage() + "\n");
					}
				}
			}

			if (current == null) {
				informationLabel.append("No project with that ID or name found \n");
			} 
			mainSheet.workOnProjectProjectF.setText("");

		}else if (e.getSource() == mainSheet.startOnActivityB) {
			Activity currentA = null;

			for (int i = 0; i < allCurrentProjects.size(); i++) {
				for (int j = 0; j < allCurrentProjects.get(i).getActivities().size(); j++) {
					if (allCurrentProjects.get(i).getActivities().get(j).getName().equals(mainSheet.workOnActivityActivityF.getText())) {
						currentA = allCurrentProjects.get(i).getActivities().get(j);
						break;
					}
				}
			}

			try {
				currentA.addWorker(worker);
				informationLabel.append("Started working on activity: " + mainSheet.workOnActivityActivityF.getText() + "\n");
			} catch (Exception error) {
				if (currentA == null) {
					informationLabel.append("No activity found with name: " + mainSheet.workOnActivityActivityF.getText() + "\n");
				} else {
					informationLabel.append(error.getMessage() + "\n");
				}
			}

			mainSheet.workOnActivityActivityF.setText("");
		} else if (e.getSource() == changeOptSheet.setProjectLeadB) {
			Project current = null;
			Worker currentW = null;

			for (int i = 0; i < allCurrentProjects.size(); i++) {
				if (allCurrentProjects.get(i).getID().equals(changeOptSheet.setProjectLeadProjectF.getText()) || allCurrentProjects.get(i).getName().equals(changeOptSheet.setProjectLeadProjectF.getText())) {
					current = allCurrentProjects.get(i);
				}
			}

			for (int i = 0; i < dataBase.getWorkers().size(); i++) {
				if (dataBase.getWorkers().get(i).getID().equals(changeOptSheet.setProjectLeadWorkerF.getText())) {
					currentW = dataBase.getWorkers().get(i);
				}
			}

			try {
				current.setProjectLeader(worker, currentW);
				informationLabel.append("Succesfully change projectleader \n");
			} catch (Exception error) {
				if (current == null) {
					informationLabel.append("No project with that ID/name found \n");
				} else if (currentW == null) {
					informationLabel.append("No worker with that ID found \n");
				} else {
					informationLabel.append(error.getMessage() + "\n");
				}
			}

			changeOptSheet.setProjectLeadWorkerF.setText("");
			changeOptSheet.setProjectLeadProjectF.setText("");

		} else if (e.getSource() == changeOptSheet.changeActivityNameB) {
			Activity currentA = null;

			for (int i = 0; i < allCurrentProjects.size(); i++) {
				for (int j = 0; j < allCurrentProjects.get(i).getActivities().size(); j++) {
					if (allCurrentProjects.get(i).getActivities().get(j).getName().equals(changeOptSheet.changeActivityNameActivityF.getText())) {
						currentA = allCurrentProjects.get(i).getActivities().get(j);
						break;
					}
				}
			}

			try {
				currentA.changeActivityName(worker, changeOptSheet.changeActivityNameNameF.getText());
				informationLabel.append("Succesfully changed activity name \n");
			} catch (Exception error) {
				if (currentA == null) {
					informationLabel.append("No activity found with name: " + changeOptSheet.changeActivityNameActivityF.getText() + "\n");
				} else {
					informationLabel.append(error.getMessage() + "\n");
				}
			}

			reDrawProjectList();

			changeOptSheet.changeActivityNameActivityF.setText("");
			changeOptSheet.changeActivityNameNameF.setText("");

		} else if (e.getSource() == changeOptSheet.changeTimeEstimateB) {
			Activity currentA = null;

			for (int i = 0; i < allCurrentProjects.size(); i++) {
				for (int j = 0; j < allCurrentProjects.get(i).getActivities().size(); j++) {
					if (allCurrentProjects.get(i).getActivities().get(j).getName().equals(changeOptSheet.changeTimeEstimateActivityF.getText())) {
						currentA = allCurrentProjects.get(i).getActivities().get(j);
						i = allCurrentProjects.size();
						break;
					}
				}
			}

			try {
				currentA.changeActivityTime(worker, Double.parseDouble(changeOptSheet.changeTimeEstimateTimeEstimateF.getText()));
				informationLabel.append("Succesfully updated the time estimate on the activity \n");
			} catch (Exception error) {
				informationLabel.append(error.getStackTrace().toString());
				if (currentA == null) {
					informationLabel.append("No activity found with name: " + changeOptSheet.changeTimeEstimateActivityF.getText() + "\n");
				} else {
					informationLabel.append(error.getMessage() + "\n");
				}
			}

			reDrawProjectList();

			changeOptSheet.changeTimeEstimateActivityF.setText("");
			changeOptSheet.changeTimeEstimateTimeEstimateF.setText("");

		} else if(e.getSource() == changeOptSheet.changeActivityDescriptionB) {
			Activity currentA = null;

			for (int i = 0; i < allCurrentProjects.size(); i++) {
				for (int j = 0; j < allCurrentProjects.get(i).getActivities().size(); j++) {
					if (allCurrentProjects.get(i).getActivities().get(j).getName().equals(changeOptSheet.changeActivityDescriptionActivityF.getText())) {
						currentA = allCurrentProjects.get(i).getActivities().get(j);
						break;
					}
				}
			}

			try {
				currentA.changeActivityDescription(worker, changeOptSheet.changeActivityDescriptionDescriptionF.getText());
				informationLabel.append("Succesfully updated the description for the activity \n");
			} catch (Exception error) {
				if (currentA == null) {
					informationLabel.append("No activity found with name: " + changeOptSheet.changeActivityDescriptionActivityF.getText() + "\n");
				} else {
					informationLabel.append(error.getMessage() + "\n");
				}
			}

			changeOptSheet.changeActivityDescriptionActivityF.setText("");
			changeOptSheet.changeActivityDescriptionDescriptionF.setText("");

			reDrawProjectList();

		} else if (e.getSource() == changeOptSheet.changeActivityConditionB) {
			Activity currentA = null;

			for (int i = 0; i < allCurrentProjects.size(); i++) {
				for (int j = 0; j < allCurrentProjects.get(i).getActivities().size(); j++) {
					if (allCurrentProjects.get(i).getActivities().get(j).getName().equals(changeOptSheet.changeActivityConditionActivityF.getText())) {
						currentA = allCurrentProjects.get(i).getActivities().get(j);
						break;
					}
				}
			}

			try {
				currentA.changeActivityCondition(worker, changeOptSheet.changeActivityConditionConditionF.getText());
				informationLabel.append("Succesfully change the condition for the activity \n");
			} catch (Exception error) {
				if (currentA == null) {
					informationLabel.append("No activity found with name: " + changeOptSheet.changeActivityDescriptionActivityF.getText() + "\n");
				} else {
					informationLabel.append(error.getMessage() + "\n");
				}
			}

			changeOptSheet.changeActivityConditionActivityF.setText("");
			changeOptSheet.changeActivityConditionConditionF.setText("");

			reDrawProjectList();

		} else if (e.getSource() == adminOpt.makeNewWorkerB) {
			dataBase.addWorker(new Worker(adminOpt.makeNewWorkerF.getText()));
			adminOpt.makeNewWorkerF.setText("");

		} else if (e.getSource() == adminOpt.changeToOtherWorkerB) {
			Worker foundW = null;
			for (int i = 0; i < dataBase.getWorkers().size(); i++) {
				if (dataBase.getWorkers().get(i).getID().equals(adminOpt.changeToOtherWorkerF.getText())) {
					foundW = dataBase.getWorkers().get(i);
					break;
				}
			}

			if(foundW == null) {
				informationLabel.append("No worker with that ID found \n");
			} else {
				worker = foundW;
				currentWorkerID.setText("Current worker: " + worker.getID());
			}

			adminOpt.changeToOtherWorkerF.setText("");

		} else if (e.getSource() == adminOpt.listWorkersB) {
			for (int i = 0; i < dataBase.getWorkers().size(); i++) {
				informationLabel.append("Worker " + i + " : " + dataBase.getWorkers().get(i).getID() + " ");
			}
			informationLabel.append("\n");

		} else if (e.getSource() == adminOpt.listProjectsB) {
			for (int i = 0; i < dataBase.getProjects().size(); i++) {
				informationLabel.append("Project " + i + " : " + dataBase.getProjects().get(i).getName() + " with ID: " + dataBase.getProjects().get(i).getID());
			}
			informationLabel.append("\n");
		} else if ( e.getSource() == projLeadOpt.chooseProjB) {
			Project currentW = null;
			for (int i = 0; i < allCurrentProjects.size(); i++) {
				if (allCurrentProjects.get(i).getID().equals(projLeadOpt.chooseProjProjF.getText()) || allCurrentProjects.get(i).getName().equals(projLeadOpt.chooseProjProjF.getText())) {
					currentW = allCurrentProjects.get(i);
				}
			}

			if (currentW != null) {
				if (currentW.getProjectLeader() == null ) {
					try {
						currentW.setProjectLeader(worker, worker);
					} catch (Exception error) {

					}
					projLeadOpt.chooseProjCurrFL.setText(projLeadOpt.chooseProjProjF.getText());
					projLeadOpt.setProject(currentW);
					informationLabel.append("Current project set \n");
				} else if (currentW.getProjectLeader().getID().equals(worker.getID())) {
					projLeadOpt.chooseProjCurrFL.setText(projLeadOpt.chooseProjProjF.getText());
					projLeadOpt.setProject(currentW);
					informationLabel.append("Current project set \n");
				} else {
					informationLabel.append("Must be project leader to edit project \n");
				}
			} else {
				informationLabel.append("No project with that name found \n");
			}

		} else if (e.getSource() == projLeadOpt.addWorkerB) {
			if (projLeadOpt.selected != null) {
				Worker foundW = null;
				for (int i = 0; i < dataBase.getWorkers().size(); i++) {
					if (dataBase.getWorkers().get(i).getID().equals(projLeadOpt.addWorkerWorkerF.getText())) {
						foundW = dataBase.getWorkers().get(i);
						break;
					}
				}

				if (foundW == null) {
					informationLabel.append("No worker with that ID found \n");
				} else {
					try {
						projLeadOpt.selected.addWorker(foundW);
						informationLabel.append("Worker added to the project \n");
					} catch (Exception error) {
						informationLabel.append(error.getMessage() + "\n");
					}
				}

			} else {
				informationLabel.append("No project selected, please select the project to edit \n");
			}

			projLeadOpt.addWorkerWorkerF.setText("");

		} else if (e.getSource() == projLeadOpt.checkTimeB) {
			if (projLeadOpt.selected != null) {
				double timeused = 0;
				for (int i = 0; i < projLeadOpt.selected.getActivities().size(); i++) {
					timeused += projLeadOpt.selected.getActivities().get(i).gettimeSpent();
				}

				informationLabel.append("Time used on project: " + timeused + "\n");
			} else {
				informationLabel.append("No project selected, please select the project to edit \n");
			}

		} else if (e.getSource() == projLeadOpt.removeWorkerB) {
			if (projLeadOpt.selected != null) {
				try {
					projLeadOpt.selected.removeWorker(worker, projLeadOpt.removeWorkerWorkerF.getText());
					informationLabel.append("Worker was succesfully removed \n");
				} catch (Exception error) {
					informationLabel.append(error.getMessage() + "\n");
				}
			} else {
				informationLabel.append("No project selected, please select the project to edit \n");
			}

			projLeadOpt.removeWorkerWorkerF.setText("");

		} else if (e.getSource() == projLeadOpt.removeWorkerActB) {
			Activity currentA = null;
			for (int j = 0; j < projLeadOpt.selected.getActivities().size(); j++) {
				if (projLeadOpt.selected.getActivities().get(j).getName().equals(projLeadOpt.removeWorkerWorkerActF.getText())) {
					currentA = projLeadOpt.selected.getActivities().get(j);
					break;
				}
			}

			try {
				if (currentA != null) {
					currentA.removeWorker(worker, worker.getID());
					informationLabel.append("Worker removed \n");
				} else {
					informationLabel.append("No activity found with that name \n");
				}
			} catch (Exception error) {
				informationLabel.append(error.getMessage() + "\n");
			}

		} else if (e.getSource() == projLeadOpt.compActivityB) {
			if (projLeadOpt.selected != null) {
				Activity currentA = null;
				for (int j = 0; j < projLeadOpt.selected.getActivities().size(); j++) {
					if (projLeadOpt.selected.getActivities().get(j).getName().equals(projLeadOpt.comActActF.getText())) {
						currentA = projLeadOpt.selected.getActivities().get(j);
						break;
					}
				}

				try {
					if (currentA != null) {
						currentA.changeActivityName(worker, currentA.getName() + " (COMPLETED)");
						informationLabel.append("Activity completed. Nice! \n");
					} else {
						informationLabel.append("No activity found with that name \n");
					}
				} catch (Exception error) {
					informationLabel.append(error.getMessage() + "\n");
				}
			} else {
				informationLabel.append("No project selected, please select the project to edit \n");
			}

			projLeadOpt.comActActF.setText("");
			reDrawProjectList();

		}
	}

	private Worker findWorker(String iD) throws Exception {
		for (int i = 0; i < dataBase.getWorkers().size(); i++) {
			if (dataBase.getWorkers().get(i).getID().equals(iD)) {
				return dataBase.getWorkers().get(i);
			}
		}
		throw new OperationNotAllowedException("Cannot find worker with iD: " + iD);
	}

	private void reDrawProjectList() {
		projectsListAdditional.setText("");
		for (int i = 0; i < allCurrentProjects.size(); i++) {
			projectsListAdditional.append(allCurrentProjects.get(i).getName() + " with ID: " + allCurrentProjects.get(i).getID() + "\n");
			projectsListAdditional.append("With activities: \n");
			for (int j = 0; j < allCurrentProjects.get(i).getActivities().size(); j++) {
				projectsListAdditional.append("    *" + allCurrentProjects.get(i).getActivities().get(j).getName() + " with: " + allCurrentProjects.get(i).getActivities().get(j).gettimeSpent() + " amount of time used out of " + allCurrentProjects.get(i).getActivities().get(j).getTimeEstimate() + "\n");
				projectsListAdditional.append("        With Description: " + allCurrentProjects.get(i).getActivities().get(j).getDescription() +"\n");
				projectsListAdditional.append("        With condition for completion: " + allCurrentProjects.get(i).getActivities().get(j).getCondition() + "\n");
			}
			projectsListAdditional.append("\n");
		}
	}

	public Worker getCurrentWorker() {
		return worker;
	}
}

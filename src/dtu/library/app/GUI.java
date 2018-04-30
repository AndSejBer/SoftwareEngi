package dtu.library.app;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

public class GUI extends JFrame implements ActionListener{//Initialization of most parts of the GUI:
	protected JTextArea informationLabel = new JTextArea();
	protected JLabel listOfProjectsL = new JLabel("All current projects:");
	
	protected JTextArea projectsListAdditional = new JTextArea();

	
	protected static GUI gUI = new GUI();
	
	//The sheets of options
	protected MainSheet mainSheet = new MainSheet(this);
	protected ChangeOperationSheet changeOptSheet = new ChangeOperationSheet(this);
	

	//List of the projects
	protected ArrayList<Project> allCurrentProjects = new ArrayList<Project>();
	protected ArrayList<JTextField> allCurrentProjectsList = new ArrayList<JTextField>();

	//The current worker
	protected Worker worker = new Worker("curr");

	//List of available workers
	protected ArrayList<Worker> availableWorkers= new ArrayList<Worker>();

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

		//The list of all current projects is setup
		JPanel projectsList = new JPanel();
		projectsList.setLayout(new GridLayout(3,1));

		for (int i = 0; i < allCurrentProjects.size(); i++) {
			projectsListAdditional.append(allCurrentProjects.get(i).getName() + " with ID: " + allCurrentProjects.get(i).getID() + "\n");
			projectsListAdditional.append("With activities: \n");
			for (int j = 0; j < allCurrentProjects.get(i).getActivities().size(); j++) {
				projectsListAdditional.append("    *" + allCurrentProjects.get(i).getActivities().get(j).getName() + "\n");
			}
		}

		JScrollPane projectsListScroll = new JScrollPane(projectsListAdditional);
		projectsList.add(listOfProjectsL);
		projectsList.add(projectsListScroll);
		projectsList.add(infoPane);
		listOfProjectsL.setSize(50,100);
		projectsListAdditional.setSize(700, 150);
		//informationLabel.setSize(100, 500);

		JTabbedPane options = new JTabbedPane();
		options.add("Main options", mainSheet);
		options.add("Change options", changeOptSheet);
		
		//Adding it all to the main GUI
		getContentPane().add(projectsList);
		getContentPane().add(options);

		//A bit of setup
		availableWorkers.add(worker);
	}

	public static void main(String[] args) {

		gUI.setTitle("Software project");
		gUI.setSize(1000, 800);
		gUI.setResizable(false);
		gUI.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == mainSheet.startNewProjectB) {
			if (mainSheet.newProjectNameF != null && !mainSheet.newProjectNameF.getText().equals("")) {
				allCurrentProjects.add(new Project(mainSheet.newProjectNameF.getText()));
				projectsListAdditional.append(allCurrentProjects.get(allCurrentProjects.size()-1).getName() + " with ID: " + allCurrentProjects.get(allCurrentProjects.size()-1).getID() + "\n");
				projectsListAdditional.append("With activities: \n");
			} else {
				informationLabel.append("Must give a project a name \n");
			}

			if (mainSheet.newProjectLeadF != null && !mainSheet.newProjectLeadF.getText().equals("")) {
				try { 
					allCurrentProjects.get(allCurrentProjects.size()-1).setProjectLeader(getCurrentWorker(), findWorker(mainSheet.newProjectLeadF.getText()));
				} catch (Exception error) {
					informationLabel.setText(error.getMessage() + "\n");
				}
			}
			mainSheet.newProjectNameF.setText("");
			mainSheet.newProjectLeadF.setText("");


		}else if (e.getSource() == mainSheet.startNewActivityB) {
			Project current = null;

			for (int i = 0; i < allCurrentProjects.size(); i++) {
				if (allCurrentProjects.get(i).getID().equals(mainSheet.newActivityProjectF.getText()) || allCurrentProjects.get(i).getName().equals(mainSheet.newActivityProjectF.getText())) {
					current = allCurrentProjects.get(i);
					try {
						current.addActivity(worker, new Activity(mainSheet.newActivityNameF.getText(), Double.parseDouble(mainSheet.newActivityTimeEstF.getText()), current));
					} catch (Exception error) {
						informationLabel.setText(error.getMessage() + "\n");
					}
				}
			}

			if (current == null) {
				informationLabel.setText("No project with that ID or name found \n");
			} 
			projectsListAdditional.setText("");

			for (int i = 0; i < allCurrentProjects.size(); i++) {
				projectsListAdditional.append(allCurrentProjects.get(i).getName() + " with ID: " + allCurrentProjects.get(i).getID() + "\n");
				projectsListAdditional.append("With activities: \n");
				for (int j = 0; j < allCurrentProjects.get(i).getActivities().size(); j++) {
					projectsListAdditional.append("    *" + allCurrentProjects.get(i).getActivities().get(j).getName() + " with time used: " + allCurrentProjects.get(i).getActivities().get(j).gettimeSpent() + " of " + allCurrentProjects.get(i).getActivities().get(j).getTimeEstimate() + "\n");
				}
			}

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
				currentA.addTimeSpent(Double.parseDouble(mainSheet.addTimeFromF.getText()), Double.parseDouble(mainSheet.addTimeToF.getText()));
			} catch (Exception error) {
				if (currentA == null) {
					informationLabel.setText("No activity found with name: " + mainSheet.addTimeActivityF.getText() + "\n");
				} else {
					informationLabel.setText(error.getMessage() + "\n");
				}
			}

			projectsListAdditional.setText("");

			for (int i = 0; i < allCurrentProjects.size(); i++) {
				projectsListAdditional.append(allCurrentProjects.get(i).getName() + " with ID: " + allCurrentProjects.get(i).getID() + "\n");
				projectsListAdditional.append("With activities: \n");
				for (int j = 0; j < allCurrentProjects.get(i).getActivities().size(); j++) {
					projectsListAdditional.append("    *" + allCurrentProjects.get(i).getActivities().get(j).getName() + " with time used: " + allCurrentProjects.get(i).getActivities().get(j).gettimeSpent() + " of " + allCurrentProjects.get(i).getActivities().get(j).getTimeEstimate() + "\n");
				}
			}
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
						informationLabel.setText("Started working on project: " + mainSheet.workOnProjectProjectF.getText() + "\n");
					} catch (Exception error) {
						informationLabel.setText(error.getMessage() + "\n");
					}
				}
			}

			if (current == null) {
				informationLabel.setText("No project with that ID or name found \n");
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
				informationLabel.setText("Started working on activity: " + mainSheet.workOnActivityActivityF.getText() + "\n");
			} catch (Exception error) {
				if (currentA == null) {
					informationLabel.setText("No activity found with name: " + mainSheet.workOnActivityActivityF.getText() + "\n");
				} else {
					informationLabel.setText(error.getMessage() + "\n");
				}
			}
			
			mainSheet.workOnActivityActivityF.setText("");
		}
	}

	private Worker findWorker(String iD) throws Exception {
		for (int i = 0; i < availableWorkers.size(); i++) {
			if (availableWorkers.get(i).getID().equals(iD)) {
				return availableWorkers.get(i);
			}
		}
		throw new OperationNotAllowedException("Cannot find worker with iD: " + iD);
	}

	public Worker getCurrentWorker() {
		return worker;
	}
}

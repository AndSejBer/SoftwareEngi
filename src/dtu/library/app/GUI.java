package dtu.library.app;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

public class GUI extends JFrame implements ActionListener{
	
	//Initialization of most parts of the GUI:
	protected JButton startNewProjectB = new JButton("Start a new project");
	protected JButton startNewActivityB = new JButton("Start a new activity");
	protected JButton addTimeB = new JButton("Add your work time");
	protected JButton startOnProjectB = new JButton("Start on a project");
	protected JButton startOnActivityB = new JButton("Start on activity");
	
	protected JTextField newProjectNameF = new JTextField();
	protected JTextField newProjectLeadF = new JTextField();
	protected JTextField newActivityNameF = new JTextField();
	protected JTextField newActivityTimeEstF = new JTextField();
	protected JTextField addTimeFromF = new JTextField();
	protected JTextField addTimeToF = new JTextField();
	protected JTextField newActivityProjectF = new JTextField();
	protected JTextField addTimeActivityF = new JTextField();
	protected JTextField workOnActivityActivityF = new JTextField();
	protected JTextField workOnProjectProjectF = new JTextField();
	
	protected JLabel newProjectNameL = new JLabel("Name:");
	protected JLabel newProjectLeadL = new JLabel("Project lead:");
	protected JLabel newActivityNameL = new JLabel("Name:");
	protected JLabel newActivityTimeEstL = new JLabel("Time estimate:");
	protected JLabel addTimeFromL = new JLabel("From:");
	protected JLabel addTimeToL = new JLabel("To:");
	protected JLabel newActivityProjectL = new JLabel("Project:");
	protected JLabel addTimeActivityL = new JLabel("Activity:");
	protected JLabel informationLabel = new JLabel();
	protected JLabel listOfProjectsL = new JLabel("All current projects:");
	
	JTextArea projectsListAdditional = new JTextArea();
	
	protected static GUI gUI = new GUI();
	
	//List of the projects
	protected ArrayList<Project> allCurrentProjects = new ArrayList<Project>();
	protected ArrayList<JTextField> allCurrentProjectsList = new ArrayList<JTextField>();
	
	//The current worker
	protected Worker worker = new Worker("curr");
	
	//List of available workers
	protected ArrayList<Worker> availableWorkers= new ArrayList<Worker>();
	
	public GUI() {
		//The layout of the main part is a grid layout with 2 horizontal slots
		getContentPane().setLayout(new GridLayout(1,2));
		
		//ActionListener is added to all buttons
		startNewProjectB.addActionListener(this);
		startNewActivityB.addActionListener(this);
		addTimeB.addActionListener(this);
		startOnProjectB.addActionListener(this);
		startOnActivityB.addActionListener(this);
		
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
		projectsList.add(informationLabel);
		listOfProjectsL.setSize(50,100);
		projectsListAdditional.setSize(700, 150);
		informationLabel.setSize(50, 100);
		
		
		//The options pane is setup
		JPanel options = new JPanel();
		JPanel startNewProject = new JPanel();
		JPanel startNewActivity = new JPanel();
		JPanel addTimeOptions = new JPanel();
		JPanel workOnProject = new JPanel();
		JPanel workOnActivity = new JPanel();
		
		//Further setup of the individual options:
		//Start new project section:
		startNewProject.setLayout(new GridLayout(2,1));
		JPanel startNewProjectAdditional = new JPanel();
		startNewProjectAdditional.setLayout(new GridLayout(2,2));
		startNewProjectAdditional.add(newProjectNameL);
		startNewProjectAdditional.add(newProjectLeadL);
		startNewProjectAdditional.add(newProjectNameF);
		startNewProjectAdditional.add(newProjectLeadF);
		startNewProject.add(startNewProjectB);
		startNewProject.add(startNewProjectAdditional);
		
		//Start new activity section:
		startNewActivity.setLayout(new GridLayout(2,1));
		JPanel startNewActivityAdditional = new JPanel();
		startNewActivityAdditional.setLayout(new GridLayout(3,2));
		startNewActivityAdditional.add(newActivityProjectL);
		startNewActivityAdditional.add(newActivityNameL);
		startNewActivityAdditional.add(newActivityProjectF);
		startNewActivityAdditional.add(newActivityNameF);
		startNewActivityAdditional.add(newActivityTimeEstL);
		startNewActivityAdditional.add(newActivityTimeEstF);
		startNewActivity.add(startNewActivityB);
		startNewActivity.add(startNewActivityAdditional);
		
		//Add time section:
		addTimeOptions.setLayout(new GridLayout(2,1));
		JPanel addTimeAdditional = new JPanel();
		addTimeAdditional.setLayout(new GridLayout(3,2));
		addTimeAdditional.add(addTimeFromL);
		addTimeAdditional.add(addTimeToL);
		addTimeAdditional.add(addTimeFromF);
		addTimeAdditional.add(addTimeToF);
		addTimeAdditional.add(addTimeActivityL);
		addTimeAdditional.add(addTimeActivityF);
		addTimeOptions.add(addTimeB);
		addTimeOptions.add(addTimeAdditional);
		
		//Start on project section:
		workOnProject.setLayout(new GridLayout(2,1));
		JPanel workOnProjectAdditional = new JPanel();
		workOnProjectAdditional.setLayout(new GridLayout(1,1));
		workOnProjectAdditional.add(workOnProjectProjectF);
		workOnProject.add(startOnProjectB);
		workOnProject.add(workOnProjectAdditional);
		
		//Start on project section:
		workOnActivity.setLayout(new GridLayout(2,1));
		JPanel workOnActivityAdditional = new JPanel();
		workOnActivityAdditional.setLayout(new GridLayout(1,1));
		workOnActivityAdditional.add(workOnActivityActivityF);
		workOnActivity.add(startOnActivityB);
		workOnActivity.add(workOnActivityAdditional);
		
		//Collecting the options pane:
		options.setLayout(new GridLayout(5,1));
		options.add(startNewProject);
		options.add(startNewActivity);
		options.add(addTimeOptions);
		options.add(workOnProject);
		options.add(workOnActivity);
		
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
		if (e.getSource() == startNewProjectB) {
			if (newProjectNameF != null && !newProjectNameF.getText().equals("")) {
				allCurrentProjects.add(new Project(newProjectNameF.getText()));
				projectsListAdditional.append(allCurrentProjects.get(allCurrentProjects.size()-1).getName() + " with ID: " + allCurrentProjects.get(allCurrentProjects.size()-1).getID() + "\n");
				projectsListAdditional.append("With activities: \n");
			} else {
				informationLabel.setText("Must give a project a name");
			}
			
			if (newProjectLeadF != null && !newProjectLeadF.getText().equals("")) {
				try { 
					allCurrentProjects.get(allCurrentProjects.size()-1).setProjectLeader(getCurrentWorker(), findWorker(newProjectLeadF.getText()));
				} catch (Exception error) {
					informationLabel.setText(error.getMessage());
				}
			}
			newProjectNameF.setText("");
			newProjectLeadF.setText("");
			
			
		}else if (e.getSource() == startNewActivityB) {
			Project current = null;
			
			for (int i = 0; i < allCurrentProjects.size(); i++) {
				if (allCurrentProjects.get(i).getID().equals(newActivityProjectF.getText()) || allCurrentProjects.get(i).getName().equals(newActivityProjectF.getText())) {
					current = allCurrentProjects.get(i);
					try {
					current.addActivity(worker, new Activity(newActivityNameF.getText(), Double.parseDouble(newActivityTimeEstF.getText()), current));
					} catch (Exception error) {
						informationLabel.setText(error.getMessage());
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
					projectsListAdditional.append("    *" + allCurrentProjects.get(i).getActivities().get(j).getName() + "\n");
				}
			}
			
			newActivityNameF.setText("");
			newActivityProjectF.setText("");
			newActivityTimeEstF.setText("");
			
		}else if (e.getSource() == addTimeB) {
			
		}else if (e.getSource() == startOnProjectB) {
			
		}else if (e.getSource() == startOnActivityB) {
			
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

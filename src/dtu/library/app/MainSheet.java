package dtu.library.app;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MainSheet extends JPanel{
	protected GUI parent;
	
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

	public MainSheet(GUI parent) {
		this.parent = parent;

		//The options pane is setup
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
		this.setLayout(new GridLayout(5,1));
		this.add(startNewProject);
		this.add(startNewActivity);
		this.add(addTimeOptions);
		this.add(workOnProject);
		this.add(workOnActivity);


	}
}

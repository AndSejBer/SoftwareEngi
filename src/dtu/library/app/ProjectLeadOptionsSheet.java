package dtu.library.app;

import java.awt.GridLayout;

import javax.swing.*;

//Andreas
public class ProjectLeadOptionsSheet extends JPanel {
	protected JButton chooseProjB = new JButton("Choose project");
	protected JButton addWorkerB = new JButton("Add a coworker to the project");
	protected JButton checkTimeB = new JButton("Check time used");
	protected JButton removeWorkerB = new JButton("Remove a coworker from the project");
	protected JButton compActivityB = new JButton("Finish an activity");
	
	protected JLabel chooseProjProjL = new JLabel("Project: ");
	protected JLabel chooseProjCurrL = new JLabel("Currently on: ");
	protected JTextField chooseProjProjF = new JTextField();
	protected JLabel chooseProjCurrFL = new JLabel();
	
	protected JLabel addWorkerWorkerL = new JLabel("Worker: ");
	protected JTextField addWorkerWorkerF = new JTextField();
	
	protected JLabel removeWorkerWorkerL = new JLabel("Worker: ");
	protected JTextField removeWorkerWorkerF = new JTextField();
	
	protected JLabel compActActL = new JLabel("Activity: ");
	protected JTextField comActActF = new JTextField();
	
	protected Project selected = null;
	
	public ProjectLeadOptionsSheet() {
		JPanel chooseProjP = new JPanel();
		JPanel addWorkerP = new JPanel();
		JPanel checkTimeP = new JPanel();
		JPanel removeWorkerP = new JPanel();
		JPanel compActivityP = new JPanel();
		
		chooseProjP.setLayout(new GridLayout(2,1));
		JPanel chooseProjAddP = new JPanel();
		chooseProjAddP.setLayout(new GridLayout(2,2));
		chooseProjAddP.add(chooseProjProjL);
		chooseProjAddP.add(chooseProjCurrL);
		chooseProjAddP.add(chooseProjProjF);
		chooseProjAddP.add(chooseProjCurrFL);
		chooseProjP.add(chooseProjB);
		chooseProjP.add(chooseProjAddP);
		
		addWorkerP.setLayout(new GridLayout(2,1));
		JPanel addWorkerAddP = new JPanel();
		addWorkerAddP.setLayout(new GridLayout(2,1));
		addWorkerAddP.add(addWorkerWorkerL);
		addWorkerAddP.add(addWorkerWorkerF);
		addWorkerP.add(addWorkerB);
		addWorkerP.add(addWorkerAddP);
		
		checkTimeP.setLayout(new GridLayout(1,1));
		checkTimeP.add(checkTimeB);
		
		removeWorkerP.setLayout(new GridLayout(2,1));
		JPanel removeWorkerAddP = new JPanel();
		removeWorkerAddP.setLayout(new GridLayout(2,1));
		removeWorkerAddP.add(removeWorkerWorkerL);
		removeWorkerAddP.add(removeWorkerWorkerF);
		removeWorkerP.add(removeWorkerB);
		removeWorkerP.add(removeWorkerAddP);
		
		compActivityP.setLayout(new GridLayout(2,1));
		JPanel compActAddP = new JPanel();
		compActAddP.setLayout(new GridLayout(2,1));
		compActAddP.add(compActActL);
		compActAddP.add(comActActF);
		compActivityP.add(compActivityB);
		compActivityP.add(compActAddP);
		
		this.setLayout(new GridLayout(5,1));
		this.add(chooseProjP);
		this.add(addWorkerP);
		this.add(checkTimeP);
		this.add(removeWorkerP);
		this.add(compActivityP);
	}
	
	public void setProject(Project project) {
		selected = project;
	}
}

package dtu.library.app;

import java.awt.GridLayout;

import javax.swing.*;

public class adminOptionsSheet extends JPanel{
	protected JButton makeNewWorkerB = new JButton("Make add new worker");
	protected JButton changeToOtherWorkerB = new JButton("Change to other coworker");
	protected JButton listWorkersB = new JButton("List all workers");
	protected JButton listProjectsB = new JButton("List all projects");
	
	protected JLabel makeNewWorkerWorkerL = new JLabel("Worker name: ");
	protected JTextField makeNewWorkerF = new JTextField();
	
	protected JLabel changeToOtherWorkerL = new JLabel("Worker name: ");
	protected JTextField changeToOtherWorkerF = new JTextField();
	
	public adminOptionsSheet() {
		JPanel newWorkerP = new JPanel();
		JPanel changeWorkerP = new JPanel();
		JPanel listWorkersP = new JPanel();
		JPanel listProjectsP = new JPanel();
		
		newWorkerP.setLayout(new GridLayout(2,1));
		JPanel newWorkerAddP = new JPanel();
		newWorkerAddP.setLayout(new GridLayout(2,1));
		newWorkerAddP.add(makeNewWorkerWorkerL);
		newWorkerAddP.add(makeNewWorkerF);
		newWorkerP.add(makeNewWorkerB);
		newWorkerP.add(newWorkerAddP);
		
		changeWorkerP.setLayout(new GridLayout(2,1));
		JPanel changeWorkerAddP = new JPanel();
		changeWorkerAddP.setLayout(new GridLayout(2,1));
		changeWorkerAddP.add(changeToOtherWorkerL);
		changeWorkerAddP.add(changeToOtherWorkerF);
		changeWorkerP.add(changeToOtherWorkerB);
		changeWorkerP.add(changeWorkerAddP);
		
		listWorkersP.add(listWorkersB);
		
		listProjectsP.add(listProjectsB);
		
		
		this.setLayout(new GridLayout(4,1));
		this.add(newWorkerP);
		this.add(changeWorkerP);
		this.add(listWorkersP);
		this.add(listProjectsP);
	}
	
}

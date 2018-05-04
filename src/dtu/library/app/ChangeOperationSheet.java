package dtu.library.app;

import java.awt.GridLayout;
import javax.swing.*;

//Andreas
public class ChangeOperationSheet extends JPanel {
	protected GUI parent;
	
	protected JButton setProjectLeadB = new JButton("Set project Leader");
	protected JButton changeActivityNameB = new JButton("Change activity name");
	protected JButton changeTimeEstimateB = new JButton("Change time estimate");
	protected JButton changeActivityDescriptionB = new JButton("Change activity description");
	protected JButton changeActivityConditionB = new JButton("Change activity condition");
	
	protected JTextField setProjectLeadProjectF = new JTextField();
	protected JTextField setProjectLeadWorkerF = new JTextField();
	
	protected JLabel setProjectLeadProjectL = new JLabel("Project: ");
	protected JLabel setProjectLeadWorkerL = new JLabel("New projectlead: ");
	
	protected JTextField changeActivityNameActivityF = new JTextField();
	protected JTextField changeActivityNameNameF = new JTextField();
	
	protected JLabel changeActivityNameActivityL = new JLabel("Activity: ");
	protected JLabel changeActivityNameNameL = new JLabel("New name: ");
	
	protected JTextField changeTimeEstimateActivityF = new JTextField();
	protected JTextField changeTimeEstimateTimeEstimateF = new JTextField();
	
	protected JLabel changeTimeEstimateActivityL = new JLabel("Activity: ");
	protected JLabel changeTimeEstimateTimeEstimateL = new JLabel("New time estimate: ");
	
	protected JTextField changeActivityDescriptionActivityF = new JTextField();
	protected JTextField changeActivityDescriptionDescriptionF = new JTextField();
	
	protected JLabel changeActivityDescriptionActivityL = new JLabel("Activity: ");
	protected JLabel changeActivityDescriptionDescriptionL = new JLabel("New Description");
	
	protected JTextField changeActivityConditionActivityF = new JTextField();
	protected JTextField changeActivityConditionConditionF = new JTextField();
	
	protected JLabel changeActivityConditionActivityL = new JLabel("Activity: ");
	protected JLabel changeActivityConditionConditionL = new JLabel("New completion condition: ");
	
	
	public ChangeOperationSheet(GUI parent) {
		this.parent = parent;
		
		//Panels with options is set up:
		JPanel setProjLead = new JPanel();
		JPanel changeActName = new JPanel();
		JPanel changeTimeEst = new JPanel();
		JPanel changeActDesc = new JPanel();
		JPanel changeCompCond = new JPanel();
		
		//Set up of individual panels, same procedure as with MainSheet
		setProjLead.setLayout(new GridLayout(2,1));
		JPanel setProjLeadAdd = new JPanel();
		setProjLeadAdd.setLayout(new GridLayout(2,2));
		setProjLeadAdd.add(setProjectLeadProjectL);
		setProjLeadAdd.add(setProjectLeadWorkerL);
		setProjLeadAdd.add(setProjectLeadProjectF);
		setProjLeadAdd.add(setProjectLeadWorkerF);
		setProjLead.add(setProjectLeadB);
		setProjLead.add(setProjLeadAdd);
		
		changeActName.setLayout(new GridLayout(2,1));
		JPanel changeActNameAdd = new JPanel();
		changeActNameAdd.setLayout(new GridLayout(2,2));
		changeActNameAdd.add(changeActivityNameActivityL);
		changeActNameAdd.add(changeActivityNameNameL);
		changeActNameAdd.add(changeActivityNameActivityF);
		changeActNameAdd.add(changeActivityNameNameF);
		changeActName.add(changeActivityNameB);
		changeActName.add(changeActNameAdd);
		
		changeTimeEst.setLayout(new GridLayout(2,1));
		JPanel changeTimeEstAdd = new JPanel();
		changeTimeEstAdd.setLayout(new GridLayout(2,2));
		changeTimeEstAdd.add(changeTimeEstimateActivityL);
		changeTimeEstAdd.add(changeTimeEstimateTimeEstimateL);
		changeTimeEstAdd.add(changeTimeEstimateActivityF);
		changeTimeEstAdd.add(changeTimeEstimateTimeEstimateF);
		changeTimeEst.add(changeTimeEstimateB);
		changeTimeEst.add(changeTimeEstAdd);
		
		changeActDesc.setLayout(new GridLayout(2,1));
		JPanel changeActDescAdd = new JPanel();
		changeActDescAdd.setLayout(new GridLayout(2,2));
		changeActDescAdd.add(changeActivityDescriptionActivityL);
		changeActDescAdd.add(changeActivityDescriptionDescriptionL);
		changeActDescAdd.add(changeActivityDescriptionActivityF);
		changeActDescAdd.add(changeActivityDescriptionDescriptionF);
		changeActDesc.add(changeActivityDescriptionB);
		changeActDesc.add(changeActDescAdd);
		
		changeCompCond.setLayout(new GridLayout(2,1));
		JPanel changeCompCondAdd = new JPanel();
		changeCompCondAdd.setLayout(new GridLayout(2,2));
		changeCompCondAdd.add(changeActivityConditionActivityL);
		changeCompCondAdd.add(changeActivityConditionConditionL);
		changeCompCondAdd.add(changeActivityConditionActivityF);
		changeCompCondAdd.add(changeActivityConditionConditionF);
		changeCompCond.add(changeActivityConditionB);
		changeCompCond.add(changeCompCondAdd);
		
		this.setLayout(new GridLayout(5,1));
		this.add(setProjLead);
		this.add(changeActName);
		this.add(changeTimeEst);
		this.add(changeActDesc);
		this.add(changeCompCond);
	}
}

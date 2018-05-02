Feature: Work on activity 
	Description: A worker works on an activity and adds his time to it
	Actors: Worker

Scenario: Worker works on an activity 
	Given that I have a Worker "abc1" 
	And I have an activity with name "Act1" and time-estimate "20" 
	When Worker "abc1" takes activity "Act1" 
	Then Worker "abc1" is working on activity "Act1" 
	
Scenario: Worker takes an activity and adds time once 
	Given that I have a Worker "abc1" 
	And I have an activity with name "Act1" and time-estimate "10" 
	And Worker "abc1" takes activity "Act1" 
	When Worker "abc1" works on the activity and add the time used: "5" 
	Then The activity has accumulated "5" amount of time 
	
Scenario: Worker works on an not assigned activity and adds time once 
	Given that I have a Worker "abc1" 
	And I have an activity with name "Act1" and time-estimate "10" 
	When Worker "abc1" works on the activity and add the time used: "3" 
	Then I get the Must be assigned to activity to add time error "Must be assigned to activity to add time" 
	
	
Scenario: Worker works two times on an activity 
	Given that I have a Worker "abc1" 
	And I have an activity with name "Act1" and time-estimate "10" 
	And Worker "abc1" takes activity "Act1" 
	And Worker "abc1" works on the activity and add the time used: "6" 
	When Worker "abc1" works on the activity and add the time used: "4" 
	Then The activity has accumulated "10" amount of time 
	
Scenario: Worker adds other worker to help 
	Given that I have a Worker "abc1" 
	And I have an activity with name "Act1" and time-estimate "10" 
	And Worker "abc1" takes activity "Act1" 
	And that I have a Worker "abc2" 
	When Worker "abc2" takes activity "Act1" 
	Then Worker "abc1" and Worker "abc2" is working on activity "Act1" 
	
Scenario: Worker gets help from another worker 
	Given that I have a Worker "abc1" 
	And I have an activity with name "Atc1" and time-estimate "10" 
	And Worker "abc1" takes activity "Atc1" 
	And Worker "abc1" works on the activity and add the time used: "3" 
	And that I have a Worker "abc2" 
	And Worker "abc2" takes activity "Atc1" 
	When Worker "abc2" works on the activity and add the time used: "2" 
	Then The activity has accumulated "5" amount of time 
	
Scenario: Worker gets help from another worker not assigned to help 
	Given that I have a Worker "abc1" 
	And I have an activity with name "Atc1" and time-estimate "10" 
	And Worker "abc1" takes activity "Atc1" 
	And that I have a Worker "abc2" 
	And Worker "abc1" works on the activity and add the time used: "1" 
	When Worker "abc2" works on the activity and add the time used: "3" 
	Then I get the Must be assigned to activity to add time error "Must be assigned to activity to add time" 
		
#Scenario: Worker assign to a non existing activity
#	Given that I have a Worker "abc1"
#	When Worker "abc1" takes activity "Atc1"
#	Then I get the Activity must exist to assign error "Activity must exist to assign" 
	
	
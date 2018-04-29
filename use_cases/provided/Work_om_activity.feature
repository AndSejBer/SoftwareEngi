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
	When I work on the activity and add the time used: "5" 
	Then The activity has accumulated "5" amount of time 
	
Scenario: Worker works on an not assigned activity and adds time once 
	Given that I have a Worker "abc1" 
	And I have an activity with name "Act1" and time-estimate "10" 
	When I work on the activity and add the time used: "5" 
	Then I get the Must be assigned to activity to add time error "Must be assigned to activity to add time" 
	
	
Scenario: Worker works two times on an activity 
	Given that I have a Worker "abc1" 
	And I have an activity with name "Act1" and time-estimate "10"
	And Worker "abc1" takes activity "Act1"
	And I work on the activity and add the time used: "5" 
	When I work on the activity and add the time used: "3" 
	Then The activity has accumulated "8" amount of time 
	
	##Scenario: Worker adds other worker to help
	##Given that I have a Worker "abc1"
	##And Worker "abc1" takes activity ""
	##And I have a Worker "abc2"
	##And I have an activity with name "" and time-estimate ""
	##When I add Worker "abc2" to help on activity 
	##Then Worker "abc2" is added to the activity
	
	##Scenario: Worker gets help from another worker
	##Given that I have a Worker "abc1"
	##And Worker "abc1" takes activity ""
	##And I have a Worker "abc2"
	##And I add Worker "abc2" to help on activity 
	##And I have an activity with name "" and time-estimate ""
	##And Worker "abc1" works on an activity "" and add the time used: ""
	##When Worker "abc2" works on an activity "" and add the time used: ""
	##Then The activity has accumulated "" amount of time
	
	##Scenario: Worker gets help from another worker not assigned to help
	#	#Given that I have a Worker "abc1"
	#	#And Worker "abc1" takes activity ""
	#	#And I have a Worker "abc2"
	# 	#And I have an activity with name "" and time-estimate ""
	# 	#And Worker "abc1" works on an activity "" and add the time used: ""
	# 	#When Worker "abc2" works on an activity "" and add the time used: ""
	# 	#Then I get the error "Must be assigned to activity to add time"
	# 	
	#Scenario: Worker works on an non existing activity and adds time once
	# 	Given that I have a Worker "abc1"
	#	When I work on the activity and add the time used: "123"
	#	Then I get the Activity must exist before time can be added error "Activity must exist before time can be added"
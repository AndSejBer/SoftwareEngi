Feature: Change activitity 
Description: A project leader can change or delete the activities in a project he is managing 
Actors: Project leaders 
	
Background: There's a project with a project leader  
Given that i have a change project with name "ProjChange"
Given that the projectleader logs in
And the activity with name "Design Add", time - estimate "10.0" , description "Design add poster", condition "Well made poster so customer is happy" and worker with ID "Kenny" exists
And the activity with name "Find Slogan" and time - estimate "7" exists

Scenario: Change the name of an activity 
Given that the projectleader logs in
When i change the activity with ID "20170429a1" to have name "Designing the Add"  
Then the activity with ID "20170429a1" now has the name "Designing the Add"  

Scenario: Change the name of an activity to be nothing
Given that the projectleader logs in
When i change the activity with ID "20170429a1" to not have a name
Then I get the change activity name error message "Name is required for an activity"  

Scenario: Change the name of an activity when project leader not logged in
Given that the projectleader logs out
When i change the activity with ID "20170429a1" to have name "Designing the Add" without being projectleader
Then I get the change activity project leader error message "Must be project leader to make changes to activity"  
	
Scenario: Change the time - estimate of an activity 
Given that the projectleader logs in
When i change the activity with ID "20170429a1" to have time - estimate "96"  
Then the activity with ID "20170429a1" now has the time - estimate "96"  

Scenario: Change the time - estimate of an activity to be 0 hours
Given that the projectleader logs in 
When i change the activity with ID "20170429a1" to have time - estimate "0"  
Then I get the 0 time-estimate error message "Time-estimate cannot be 0 hours"   

Scenario: Change the description of an activity 
Given that the projectleader logs in
When I change the activity with ID "20171303a1" to have description "Designing the Add poster"  
Then the activity with ID "20171303a1" now has the description "Designing the Add poster"  

Scenario: Change the conditions of an activity 
Given that the projectleader logs in
When I change the activity with ID "20171303a1" to have conditions "Satisfactory and well made Add"  
Then the activity with ID "20171303a1" now has the conditions "Satisfactory and well made Add"  
		
Scenario: Change a blank conditions of an activity
Given that the projectleader logs in
When I change the activity with ID "20171303a2" to have the description "Find a good Slogan"
Then the activity with ID "20171303a2" now actually has the description "Find a good Slogan"





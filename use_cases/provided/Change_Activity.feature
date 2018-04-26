Feature: Change activitity 
Description: A project leader can change or delete the activities in a project he is managing 
Actors: Project leaders 
	
Background: There's a project with a project leader  
Given that the project leader is logged in 
And these activities are contained in the project | 20171303 a001 | Design Add | 14 days | Design the add poster | Well made Add that the customer is satisfied with | Kenny | | 20171304 a002 | Find Slogan | 7 days | 
And the project leader logs out 

Scenario: Change the name of an activity 
Given that the project leader is logged in 
When I change the activity with ID "20171303a001" to have name "Designing the Add"  
Then the activity with ID "20171303a001" now has the name "Designing the Add"  
	
Scenario: Change the time - estimate of an activity 
Given that the project leader is logged in 
When I change the activity with ID "20171303a001" to have time - estimate "96 hours"  
Then the activity with ID "20171303a001" now has the time - estimate "96 hours"  
	
#Scenario: Change the description of an activity 
#Given that the project leader is logged in 
#When I change the activity with ID "20171303a001" to have description "Designing the Add poster"  
#Then the activity with ID "20171303a001" now has the description "Designing the Add poster"  
#
#Scenario: Change the conditions of an activity 
#Given that the project leader is logged in 
#When I change the activity with ID "20171303a001" to have conditions "Satisfactory and well made Add"  
#Then the activity with ID "20171303a001" now has the conditions "Satisfactory and well made Add"  
#	
#Scenario: Change the workers of an activity 
#Given that the project leader is logged in 
#When I change the activity with ID "20171303a001" to have workers "Kenny, Rasmus"  
#Then the activity with ID "20171303a001" now has the workers "Kenny, Rasmus"  
#	
#Scenario: Change an activity when the project leader is not logged in 
#Given that the project leader is not logged in 
#And there is an activity with ID "20171304a002"  
#When I change the activity with ID "20171303a001" to have name "Find the Slogan", time - estimate "6 days"  
#Then I get the error message "project-leader login required"  
#	
#Scenario: Change an blank conditions of an activity 
#Given that the project leader is logged in 
#And there is an activity with ID "20171304a002"  
#When I change the activity with ID "20171303a002" to have description "Find a good Slogan", workers "Kenny, Rasmus"  
#Then the activity with ID "20171303a002" now has the description "Find a good Slogan"  and workers "Kenny, Rasmus"  

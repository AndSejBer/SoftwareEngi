## Now runs under Create_Project.feature and Activity.feature, and is no longer run here.



#Feature: check status of activity 
#Description: An activity is added 
#Actors: project leader 

#Scenario: Check time - estimate 
#Given that the project - leader is logged in 
#And I have an activity with name "activity-name" and time - estimate "time-estimate"  
#When I check time - estimate 
#Then I receive time - estimate 
	
#Scenario: Check time - estimate failure 
#Given that the project - leader is not logged in 
#And I have an activity with name "activity-name" and time - estimate "time-estimate"  
#When I check time - estimate 
#Then I get the error message "project-leader login required"  

#Scenario: Check time elapsed 
#Given that the project - leader is logged in 
#And I have an activity with name "activity-name" and time - estimate "time-estimate"  
#When I check time elapsed 
#Then I receive time - spent of time - estimate 

#Scenario: Check time elapsed failure 
#Given that the project - leader is not logged in 
#And I have an activity with name "activity-name" and time - estimate "time-estimate"  
#When I check time elapsed 
#Then I get the error message "project-leader login required"  

#Scenario: Check percentage done 
#Given that the project - leader is logged in 
#And I have an activity with name "activity-name" and time - estimate "time-estimate"  
#When I check percentage 
#Then I receive percentage done 

#Scenario: Check percentage done failure 
#Given that the project - leader is not logged in 
#And I have an activity with name "activity-name" and time - estimate "time-estimate"  
#When I check percentage 
#Then I get the error message "project-leader login required"  
#
#Scenario: Check worker status 
#Given that the project - leader is logged in 
#And I have a worker with ID "worker ID"  
#When I check workers schedule 
#Then I receive workers schedule  
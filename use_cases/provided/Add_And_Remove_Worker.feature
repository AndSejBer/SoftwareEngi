## Now runs in Activity.feature, and it's no longer run here.



#Feature: Tilføj og fjern medarbejder 
#Description: a worker is added or removed from projekt 
#Actor: project leader 

#Scenario: add worker successfully 
#Given that the project - leader is logged in 
#And I have a worker with name "Bob"  
#When I add the worker 
#Then the worker with name "Bob" is added to the projekt 
	
#Scenario: add worker when projekt - leader is not logged in 
#Given that the administrator is not logged in 
#And I have a worker with name "Bob"  
#When I add the worker 
#Then I get error message "projekt-leader login required"  
#	
#Scenario: remove worker successfully 
#Given that the project - leader is logged in 
#And there is a worker with name "Bob"  
#When I remove the worker 
#Then the worker with name "Bob" is removed from the projekt 
#
#Scenario: remove worker when projekt - leader is not logged in 
#Given that the administrator is not logged in 
#And I have a worker with name "Bob"  
#When I remove the worker 
#Then I get error message "projekt-leader login required"
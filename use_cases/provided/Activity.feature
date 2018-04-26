Feature: Activity 
Description: An activity is added 
Actors: project leader and workers 

Background: There is a project
Given that i have a project with name "Proj1"

Scenario: Add an activity successfully 
Given that the projectleader is logged in  
When i add the activity with name "activity-name" and time - estimate "10"  
Then activity with name "activity-name" exists  

Scenario: Add an activity unsuccessfully(without name) 
Given that the projectleader is logged in 
When I add an activity with time - estimate "10"  
Then I get the time-estimate error message "Name is required for an activity"  

Scenario: Add an activity unsuccessfully(without time - estimate) 
Given that the projectleader is logged in 
When I add the activity with name "activity-name"  
Then I get the name error message "Time estimate is required for an activity"  

Scenario: Add an activity when the projectleader is not logged in 
Given that the projectleader is not logged in 
When I add an activity with name "activity-name" and time - estimate "10"  
Then I get the project leader error message "Must be project leader to create new activity"  
	
Scenario: Projectleader adds worker to an activity 
Given that the projectleader is logged in 
When I add worker with ID "Will" to activity with name "activity-name" and time - estimate "10"  
Then the worker with ID "Will" is added to the activity 

Scenario: Projectleader tries to add nonexistent worker 
Given that the projectleader is logged in 
And I add an activity with name "activity-name" and time - estimate "10"  
When I add worker with ID "NAN" to activity with name "activity - name" 
Then I get the nonexistant worker error message "No worker with ID " "NAN" " exist"  

Scenario: Worker adds themself to an activity 
Given I have an activity with name "activity-name" and time - estimate "10"
And I have a worker with ID "Will"
When I add worker with ID "Will" to the activity with name "activity-name"
Then the worker with ID "Will" works on the activity 

Scenario: two workers add themselves to an activity
Given I have an activity with name "activity-name" and time - estimate "10"
And I have a worker with ID "Will"
And I add worker with ID "Will" to the activity with name "activity-name"
And I have a worker with ID "Liam"
When I add worker with ID "Liam" to the activity with name "activity-name"
Then the worker with ID "Will" and the worker with ID "Liam" works on the activity 

Scenario: add 3 workers unsuccesfully
Given I have an activity with name "activity-name" and time - estimate "10"
And I have a worker with ID "Will"
And I add worker with ID "Will" to the activity with name "activity-name"
And I have a worker with ID "Liam"
And I add worker with ID "Liam" to the activity with name "activity-name"
And I have a worker with ID "NotG"
When I add worker with ID "NotG" to the activity with name "activity-name"
Then I get the third worker error "Can only have two workers on an activity"

Scenario: add the same worker to an activity twice unsuccesfully
Given I have an activity with name "activity-name" and time - estimate "10"
And I have a worker with ID "Will"
And I add worker with ID "Will" to the activity with name "activity-name"
When I add worker with ID "Will" to the activity with name "activity-name"
Then I get the worker already on activity error "Worker already working on activity"

Scenario: Try to add a worker, but not have the ID found in the project
Given I have an activity with name "activity-name" and time - estimate "10"
And I have a worker with ID "Will"
When I add worker with ID "Liam" to the activity with name "activity-name"
Then I get the nonexistant worker error message "No worker with ID " "Liam" " exist"

Scenario: Projectleader removes worker from an activity 
Given that the projectleader is logged in 
And I have an activity with name "activity-name" and time - estimate "10"  
And I have a worker with ID "Will"  
And I add worker with ID "Will" to activity with name "activity-name" 
When I remove worker with ID "Will" from activity 
Then the worker with ID "Will" is removed from the activity 
	
Scenario: Worker removes themself from an activity 
Given I have a worker with ID "Will" 
And I have an activity with name "activity-name" and time - estimate "10"  
And I add worker with ID "Will" to activity with name "activity-name" 
When worker with ID "Will" removes himself from activity 
Then the worker with ID "Will" is removed from the activity  

Scenario: Have two workers on an activity, worker 2 removes self
Given I have a worker with ID "Will" 
And I have a worker with ID "Liam"
And I have an activity with name "activity-name" and time - estimate "10"  
And I add worker with ID "Liam" to activity with name "activity-name" 
And I add worker with ID "Will" to activity with name "activity-name" 
When worker with ID "Will" removes himself from activity 
Then the worker with ID "Will" is removed from the activity 

Scenario: Have two workers on an activity, remove the first worker, the second should then become the primary worker
Given I have a worker with ID "Will" 
And I have a worker with ID "Liam"
And I have an activity with name "activity-name" and time - estimate "10"  
And I add worker with ID "Liam" to activity with name "activity-name" 
And I add worker with ID "Will" to activity with name "activity-name" 
When worker with ID "Liam" removes himself from activity 
Then the worker with ID "Liam" is removed from the activity 

Scenario: Worker unsuccesfully tries to remove other worker
Given I have a worker with ID "Will" 
And I have an activity with name "activity-name" and time - estimate "10"  
And I add worker with ID "Will" to activity with name "activity-name" 
When worker with ID "Liam" removes worker with ID "Will" from activity 
Then I get the illegal removal error "Must be either project leader or the worker to remove from activity"

Scenario: project leader tries to remove worker, not working on activity
Given that the projectleader is logged in 
And I have an activity with name "activity-name" and time - estimate "10"  
When I remove worker with ID "Will" from activity 
Then I get the nonexistant worker error message "No worker with ID " "Will" " exist"

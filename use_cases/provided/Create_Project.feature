Feature: Create project
Description: a project is created 
Actor: worker 

Scenario: create a projekt 
Given that a worker is logged in 
When I create project with name "Proj1"  
Then the projekt with name "Proj1" and serial number "201801" is created 

Scenario: set a project leader
Given that a worker is logged in
And I create project with name "Proj1"
When I set worker with ID "Bria" as project leader
Then worker with ID "Bria" is project leader on project "Proj1"

Scenario: set a new project leader succesfully
Given that a worker is logged in
And I create project with name "Proj1"
And I set worker with ID "Bria" as project leader
When worker "Bria" sets worker "Rasm" as project leader
Then worker with ID "Rasm" is project leader on project "Proj1"

Scenario: set a new project leader unsuccesfully
Given that a worker is logged in
And I create project with name "Proj1"
And I set worker with ID "Bria" as project leader
When worker "Rasm" sets worker "Rasm" as project leader
Then I get the project leader change error "Must be current project leader to change project leader"

Scenario: add worker to project as project worker
Given that a worker is logged in
And I create project with name "Proj1"
When I add worker with ID "Liam" to project with name "Proj1"
Then worker "Liam" is working on project "Proj1"

Scenario: add worker already on the project to the project
Given that a worker is logged in
And I create project with name "Proj1"
And I add worker with ID "Liam" to project with name "Proj1"
When I add worker with ID "Liam" to project with name "Proj1"
Then I get the allready working on that error "That worker is already working on this project"

Scenario: remove yourself from project (as regular worker)
Given that the worker with ID "Liam" logs in
And the project leader with ID "Bria" and the workers with ID "Liam" and ID "Rasm" works on project with name "Proj1"
When The worker with ID "Liam" removes the worker with ID "Liam"
Then the worker "Liam" is no longer working on project "Proj1"

Scenario: remove workers from project (as project leader)
Given that the project leader "Bria" logs in
And the project leader with ID "Bria" and the workers with ID "Liam" and ID "Rasm" works on project with name "Proj1"
When The worker with ID "Bria" removes the worker with ID "Liam"
Then the worker "Liam" is no longer working on project "Proj1"

Scenario: remove worker from project (failure)
Given that a worker is logged in
And the project leader with ID "Bria" and the workers with ID "Liam" and ID "Rasm" works on project with name "Proj1"
When The worker with ID "Rasm" removes the worker with ID "Liam"
Then I get the cannot remove as non-project leader error "Must be either project leader or the worker to remove from project"

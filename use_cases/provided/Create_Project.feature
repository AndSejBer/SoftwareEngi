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

# Scenario: create a project
# Given that a worker is logged in 
# When i create project with name "Proj1" and project leader "Brian"
# Then the project with name "Proj1" and serial number "201801" and projektleader "Brian" is created  
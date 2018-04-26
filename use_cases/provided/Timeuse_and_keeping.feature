Feature: Work on activity
	Description: A worker keeps track of his time
	Actors: Worker

Background: There is a Worker and an activity
	Given that I have a Worker "abc1"
	And I have an activity with name "Act1" and time-estimate "10"
	And Worker "abc1" takes activity "Act1"

Scenario: Worker adds time succesfully
 	When Worker "abc1" adds time from "10" to "12"
 	Then Worker has worked "2" time
 	
# Scenario: Project-leader checks time used on activity succesfully
# 	Given Worker "abc1" adds time from "" to "" to activity ""
# 	And I have a project-leader "Brian"
# 	When project-leader "Brian" checks time spent on activity ""
# 	Then he gets "" time
# 	
# Scenario: Project-leader checks time used on activity succesfully
# 	Given Worker "abc1" adds time from "" to ""  to activity ""
# 	And Worker has worked "" time
# 	And I have an activity with name "act2" and time-estimate""
# 	And Worker "abc1" adds time from "" to "" to activity "act2"
# 	And I have a project-leader "Brian"
# 	When project-leader "Brian" checks time spent on project ""
# 	Then he gets "" time 	
# 	
#Scenario: Worker sets up a timesheet
##Note that a simpler timesheet is used, consisting of only monday.
#	When Worker "abc1" sets his timesheet to " , Arrive & Meeting , Meeting , Meeting , Lunch & Meeting , , , , , Leave , ,"
#	Then Worker has timesheet " , Arrive & Meeting , Meeting , Meeting , Lunch & Meeting , , , , , Leave , ,"
#	
#Scenario: Project-leader request timesheet for a Worker
#	Given Worker "abc1" sets his timesheet to " , Arrive & Meeting , Meeting , Meeting , Lunch & Meeting , , , , , Leave , ,"
#	And I have project-leader "Brian"
#	When project-leader "Brian" requests Worker "abc1" timesheet
#	Then I get " , Arrive & Meeting , Meeting , Meeting , Lunch & Meeting , , , , , Leave , ,"
#
##Scenario: Worker wants to sign out, but has time not accounted for
##	Given Worker works from "" to ""
##	When Worker signs out
##	Then I get Error "You have time not accounted for!"
#
##Scenario: Worker wants to sign out, but has some time not accounted for
##	Given Worker works from "10" to "15"
##	And Worker adds time from "10" to "12"
##	When Worker signs out
##	Then I get Error "You have time not accounted for!"

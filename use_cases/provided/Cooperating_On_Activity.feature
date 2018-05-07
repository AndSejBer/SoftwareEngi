#Feature: Working together on an activity 
#Descrip-tion: Two or more workers should be able to work together on an activity, both being able to register their work hours and a worker can ask another for help.
#Actors: Workers 

#Background: There's 2 activities in a project   
#Given these activities are contained in the project | 20171303 a001 | Design Add | 80 workhours | Design the add poster | Well made and satisfactory Add | Kenny, Rasmus | | 20171304 a002 | Find Slogan | 40 workhours | Find a good fitting slogan | Sounds good | Kenny | 

#Scenario: Two workers registers their workhours 
#Given that the worker "Kenny" has logged in 
#And that the worker "Rasmus" has logged in  
#And worker "Kenny" registers time from "10" to "12" on activity with ID "20171303a001"  
#When worker "Rasmus" registers time from "10" to "12" on activity with ID "20171303a001"
#Then the activity with ID "20171303a001" now shows "4" hours work  
	
#Scenario: Seeking help from a co - worker 
#Given that the worker "Kenny" has logged in 
#When Worker "Kenny" seeks help from worker "Rasmus" on activity with ID "20171303a002"  
#Then the worker "Rasmus" is helping with on activity with ID "20171303a002"  

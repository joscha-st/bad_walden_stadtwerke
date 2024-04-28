# Code for Bad Walden Stadtwerke

This directory contains all the code files for the utility service management software SignUp process for the city of Bad Walden. [Code](bad_walden_stadtwerke)

## Main file structure

The file [Code/bad_walden_stadtwerke/pom.xml](bad_walden_stadtwerke/pom.xml) is needed for starting the program. <br>
In the folder [Code/bad_walden_stadtwerke/src/main](bad_walden_stadtwerke/src/main) is the main code of the program. <br>
In the folder [Code/bad_walden_stadtwerke/src/test](bad_walden_stadtwerke/src/test) are the JUnit tests. 

## Main folder

The main folder contains a [Code/bad_walden_stadtwerke/main/java](bad_walden_stadtwerke/main/java) folder and a [Code/bad_walden_stadtwerke/main/resources](bad_walden_stadtwerke/main/resources) folder. <br>
The resources folder contains code to make shure that each page of the same kind, has the same setup. This folder also contains the properties for the German and the English version. 
The main folder contains several folders: <br>
[xxx] for the components folder for error handling as well as the main application and for the controller folder<br>
[xxx] for the sales folder that contains the class for the tariffs <br>
[xxx] for everything that we mocked<br>
[xxx] for the logic of our program<br>
[xxx] for the communication between ???<br>


## Test folder

The test folder contains all JUnit tests that we did. We have 4 different classes:<br>


## Programming Decisions

After trying JavaFX and finding it too complex, we changed our programming language to JavaFXML as it is easier to integrate and there is a clear separation between logic and design.<br>
We used following method for coding:<br>
The main programm invokes a FXML loader that parses a FXML document and builds the scene graph. The FXML document defines the user interface and the FXML controller. The FXML controller handles mouse and keyboard events. This controller is instantiated by the FXML loader which also performs field injection and calls controller's initialize() method.

## How to start the code?




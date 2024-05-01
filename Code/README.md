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


## How to run the program and see our code?

1. **Install an IDE** - We recommend **IntelliJ IDEA**. This tutorial will be for IntelliJ.  
   Download it from [IntelliJ IDEA Download](https://www.jetbrains.com/idea/download/?fromIDE=&section=windows).

2. **Navigate through the IntelliJ Install Wizard** - Keep the preselected options during installation.

3. **Launch IntelliJ** - After installation, launch IntelliJ. Skip the initial setup until you see "Welcome to IntelliJ IDEA." You should have three options: "New Project," "Open," and "Get from VCS."

4. **Choose "Get from VCS"** - VCS stands for Version Control System.

5. **Clone the Repository** - Paste this URL (https://github.com/joscha-st/bad_walden_stadtwerke) into the URL field. **Select a desired folder location**.

6. **Click on "Clone"** -in the bottom right corner.

7. **Click on "Trust the Project"** - in the popup that appears.

8. **Load Maven Build Script** - You should now see the project opened in IntelliJ. In the bottom right corner, there will be a popup saying: "Maven build script found." **Click "Load"** and wait until the loading bar in the bottom right corner disappears.

9. **Navigate to the launcher class** - Use this Link: [Launcher.java](Code/bad_walden_stadtwerke/src/main/java/com/bad_walden_stadtwerke/Launcher.java). Alternatively navigate manually in the project structure on the right of the screen: Expand the folder "Code" then "bad_walden_stadtwerke" then "src" then "main" then "java" then "com.bad_walden_stadtwerke" and then double click the "Launcher" class.

10. **Set Up the Project JDK** - At the top of the class, there will be a yellow warning saying "Project JDK is not defined". Click "Setup JDK" and select "22 Oracle Open JDK 22" (Or the first choice you get recomended by IntelliJ). Wait for it to load. Refresh by double-clicking on another class (for example the "Main" class) and then double-clicking "Launcher" again in the project structure.

11. **Run the Application** - Press the green triangle on the top right of the screen to start the application.




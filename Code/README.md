# Code for Bad Walden Stadtwerke

This directory contains all the code files for the utility service management software SignUp process for the city of Bad Walden. [Code](bad_walden_stadtwerke)

## Main file structure

The file [Code/bad_walden_stadtwerke/pom.xml](bad_walden_stadtwerke/pom.xml) is needed for starting the program. <br>
In the folder [Code/bad_walden_stadtwerke/src/main](bad_walden_stadtwerke/src/main) is the main code of the program. <br>
In the folder [Code/bad_walden_stadtwerke/src/test](bad_walden_stadtwerke/src/test) are the JUnit tests. 

## Main folder

The main folder contains a [Code/bad_walden_stadtwerke/main/java](bad_walden_stadtwerke/main/java) folder and a [Code/bad_walden_stadtwerke/main/resources](bad_walden_stadtwerke/main/resources) folder. <br>
The resources folder contains all used images as well as the language bundles that enable the multilingual nature of the program. Furthermore, it contains all FXML files, separating the UI from the rest of the code.
The main folder contains several folders: <br>
[xxx] for the components folder for error handling as well as the main application and for the controller folder<br>
[xxx] for the sales folder that contains the class for the tariffs <br>
[xxx] for everything that we mocked<br>
[xxx] for the logic of our program<br>
[xxx] for the communication between ???<br>


## Test folder

The test folder contains all JUnit tests that we did. We have 4 different classes:<br>


## Programming Decisions

### Usage of JavaFXML
We decided to use JavaFXML instead of JavaFX because it offers us the possibility to clearly separate our UI from our logic. In JavaFXML, we have an FXML file that contains the UI, and a so-called "controller file" that handles UI events and the logic behind the UI. As a result, we have a clear separation between logic and design.
We also used JavaFx for some custom components like the Side Bar because in these special cases it was much faster to implement.

### How a Program Runs with JavaFXML
JavaFXML, used with JavaFX, separates the UI design from application logic using an FXML file for the layout and a controller class for event handling:

1. **FXML Loader**: Initializes and parses the FXML file to build the UI.
2. **Controller Linking**: Connects UI components to the controller class for handling events and managing UI updates.
3. **UI Rendering and Interaction**: JavaFX renders the UI, and user interactions are managed via event handlers in the controller.
4. **Lifecycle Management**: JavaFX handles application startup, running, and shutdown processes.

This structure helps maintain a clean separation between the UI and the underlying logic, enhancing maintainability and scalability.

### Codestyle and Conventions
We decided to use the typical camel case java conventions with Classes starting with a capital letter and variables etc. starting with a lower case letter. Additionally, we agreed to use .this when referring to the current instant of a class. For documentation, we utilize JavaDoc to ensure that all code elements are adequately described, facilitating easier maintenance and understanding. We employ JUnit for writing and executing unit tests, ensuring that our codebase remains robust and error-free.




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

10. **Set Up the Project JDK** - At the top of the screen, there will be a yellow warning saying "Project JDK is not defined". Click "Setup JDK" and select "22 Oracle Open JDK 22" (Or the first choice you get recomended by IntelliJ). Wait for it to load. Refresh by double-clicking on another class (for example the "Main" class) and then double-clicking "Launcher" again in the project structure.

11. **Run the Application** - Press the green triangle on the top right of the screen to start the application.




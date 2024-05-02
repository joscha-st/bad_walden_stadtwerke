# Code for Bad Walden Stadtwerke

This directory contains all the code files for the utility service management software SignUp process for the city of Bad Walden. [Code](bad_walden_stadtwerke)

## File structure

### The [java](Code/bad_walden_stadtwerke/src/main/java) folder
Contains the code, categorized into components, controllers, mock, model and utility. It also contains the [Launcher Class](Code/bad_walden_stadtwerke/src/main/java/com/bad_walden_stadtwerke/Launcher.java) and the [MainApplication Class](Code/bad_walden_stadtwerke/src/main/java/com/bad_walden_stadtwerke/MainApplication.java) that are needed to run the programm.

### The [resources](Code/bad_walden_stadtwerke/src/main/resources) foulder
Contains all used images as well as the language bundles that enable the multilingual nature of the program. Furthermore, it contains all FXML files, separating the UI from the rest of the code.

### Test folder
The [test folder](Code/bad_walden_stadtwerke/src/test) contains all JUnit tests that we did.


## Programming Decisions

### Architectural decisions
We decided to implement the client side of the sign-up process while mocking the server using Mockito. Our client sends out real working requests and is also able to receive and handle the mocked server inputs.

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
We decided to use the typical **camel case** java conventions with Classes starting with a capital letter and variables etc. starting with a lower case letter. Additionally, we agreed to use **.this** when referring to the current instant of a class. For documentation, we utilize **JavaDoc** to ensure that all code elements are adequately described, facilitating easier maintenance and understanding. We employ **JUnit** for writing and executing unit tests, ensuring that our codebase remains robust and error-free.

### Use of Maven
We decidet to use maven in our project.
[Maven](https://maven.apache.org/) is a powerful project management and  build automation tool that is widely used in Java projects. It simplifies the build process like compiling code, packaging binaries, and managing dependencies through a central piece of configuration called the Project Object Model (POM) file.

### Restrictive use of dependencies
We used dependencies very restrictively only including:
**JavaFX Controls**
Group ID: org.openjfx
Artifact ID: javafx-controls
Version: 17.0.6
**JavaFX FXML**
Group ID: org.openjfx
Artifact ID: javafx-fxml
Version: 17.0.6
**JUnit Jupiter API**
Group ID: org.junit.jupiter
Artifact ID: junit-jupiter-api
Version: Defined by the property ${junit.version}, which is 5.10.0
Scope: test
**JUnit Jupiter Engine**
Group ID: org.junit.jupiter
Artifact ID: junit-jupiter-engine
Version: Defined by the property ${junit.version}, which is 5.10.0
Scope: test
**Mockito Core**
Group ID: org.mockito
Artifact ID: mockito-core
Version: 5.11.0

### Further Plans: 
In an expanded feature, we aim to include the ability to load a previous state into the signup process. As data is saved on the server to minimize loss, users should be able to retrieve their data when signing up again. We were previously unable to implement this due to time constraints.



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


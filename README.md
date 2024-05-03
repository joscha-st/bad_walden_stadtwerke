# Group project "Bad Walden": *Stadtwerke*

HWG Ludwigshafen IBAIT ATdIT 2, Project Group 3   

***Building a utility service management software for the fictional city of Bad Walden.***  

Lecturers: *Dr. Britta Jung and Patrick Gutgesell*  
Group Members: *Hakon Rosenberger, Samuel Sonnenwald, Joscha Stähle, Lisa Sterner, Lukas Strickler, Leo Waigel*

---
![Stadtwerke2](https://github.com/joscha-st/bad_walden_stadtwerke/assets/149787964/2bb7f47d-668d-4e84-b8e9-a89fd3b257cd)

  
## Modelling

### General introduction to modelling files

In order to effectively build a utility service management software, we need to start by modeling key processes and software elements. <br>   
The BPMN modellings to understand the processes, can be found in [Documents/Application Software](Documents/Application%20Software). For an explanation please open the following file: [Documents/Application Software/README.md](Documents/Application%20Software/README.md). <br>
The Datamodel that shows the structure of our database can be found under [Documents/Datamodel](Documents/Datamodel). An explanation is written in the [Documents/Datamodel/README.md](Documents/Datamodel/README.md) file. <br>
The UI modelling can be found under [Documents/UI](Documents/UI). This folder is structured in the three subfolder [Buerger](Documents/UI/Buerger), [Login](Documents/UI/Login) and [Poweruser](Documents/UI/Poweruser). In each subfolder there are the excalidraw files, which is an extension in Visual Studio, that we used to create the UI models. And there is also an folder "Exports" where every part of the UI is exported and saved as a svg file. To get an complete overview of the UI, there is no need to go into any of the subfolders. The complete UI is shown in the main folder: "Stadtwerke_UI". It is available as a svg or excalidraw file. An explanation including pictures of the UI can be found here: [Documents/UI/README.md](Documents/UI/README.md). To view the UI as pngs please follow this folder: [Documtens/UI/PNGs](Documents/UI/PNGs). The structure of this folder is again split into "Bürger", "Login" and "Poweruser". <br>
All of those documents will serve as the foundation for the development process.

### Directory to most important files

We want to give you a directory to the files that build the base for what we implemented. We decided to implement the SignUp process. This process is for data collection of customers that log into their account for the first time. Here they can enter their information and choose the tariffs that they want. Further information regarding the modelling can be found in the README files that are mentioned above.

BPMN: [Documents/Application Software/SignUp Process BPMN_Final.png](Documents/Application%20Software/SignUp%20Process%20BPMN_Final.png)<br>
UI: [Documents/UI/PNGs/Login/SignUp.png](Documents/UI/PNGs/Login/SignUp.png)<br>
Datamodel: [Documents/Datamodel/db_EER.png](Documents/Datamodel/db_EER_old.png) and  [Documents/Datamodel/db_structure.plantuml](Documents/Datamodel/db_structure.plantuml)<br>

---

## Coding

### Finding the code and understanding the file structure

The code can be found in the code folder for "Stadtwerke Bad Walden": [Code/bad_walden_stadtwerke](Code/bad_walden_stadtwerke/)<br>
In this folder are files that to you need to be able to run the code. Further explanation on how to run the code can be found below. And there is also a file with further explanations of the code [Code/bad_walden/stadtwerke/README.md](Code/bad_walden_stadtwerke/README.md), as well as a "src" folder.<br>
This folder contains 2 subfolders. One of them contains the code [Code/bad_walden_stadtwerke/src/main](Code/bad_walden_stadtwerke/src/main) and the other one contains the tests [Code/bad_walden_stadtwerke/src/test](Code/bad_walden_stadtwerke/src/test). Please read the README.md file in the Code folder for further explanation of the code.<br>

### Our scope
For the signup process we only coded the client. We mocked the server, and it's http responses, with mockito. Also, our signup process starts with the first pop-up after the first login. We decided that the login itself, that happens beforehand, is out of scope.

| Scope                  | In-Scope                                                                             | Out-of-Scope                                                            |
|------------------------|--------------------------------------------------------------------------------------|-------------------------------------------------------------------------|
| Signup Process         | UI/views, Controller, Client-side-logic, Handling of outgoing and incoming API calls | Server-side-request handling, login and user-session-handling           |
| Other Parts of program | Program container with sidebar but no content.                                       | All other parts of the program are out of scope and are at most mocked. |

## How to open our repository and run our program

1. **Install an IDE** - We recommend **IntelliJ IDEA**. This tutorial will be for IntelliJ.  
   Download it from [IntelliJ IDEA Download](https://www.jetbrains.com/idea/download/?fromIDE=&section=windows).

2. **Navigate through the IntelliJ Install Wizard** - Keep the preselected options during installation.

3. **Launch IntelliJ** - After installation, launch IntelliJ. Skip the initial setup until you see "Welcome to IntelliJ IDEA." You should have three options: "New Project," "Open," and "Get from VCS."

4. **Choose "Get from VCS"** - VCS stands for Version Control System.

5. **Clone the Repository** - Paste this URL (https://github.com/joscha-st/bad_walden_stadtwerke) into the URL field. **Select a desired folder location**.

6. **Click on "Clone"** -in the bottom right corner.

7. **Click on "Trust the Project"** - in the popup that appears.

8. **Load Maven Build Script** - You should now see the project opened in IntelliJ. In the bottom right corner, there will be a popup saying: "Maven build script found." **Click "Load"** and wait until the loading bar in the bottom right corner disappears.

9. **Set Up the Project JDK**
Click on the three bars in the top left corner of the screen and select "Project Structure...". A pop-up should appear showing you a tab called "Project". There you need to expand (click on the downward-facing arrowhead) the "SDK:" field. Click "Download JDK". A pop-up should appear. Expand "Vendor:" and select "SAP SapMachine". Click "Download". Then click "OK" to leave the Project Structure settings.

10. **Navigate to the launcher class** - Use this Link: [Launcher.java](Code/bad_walden_stadtwerke/src/main/java/com/bad_walden_stadtwerke/Launcher.java). Alternatively navigate manually in the project structure on the right of the screen: Expand the folder "Code" then "bad_walden_stadtwerke" then "src" then "main" then "java" then "com.bad_walden_stadtwerke" and then double click the "Launcher" class.

11. **Run the Application** - Press the green triangle on the top right of the screen to start the application.

---

## Presentation

The presentation can be found here: [Documents/Stadtwerke_Bad_Walden.pdf](Documents/Stadtwerke_Bad_Walden.pdf)

---

## Any questions left?

Please contact us directly in case of further questions: *hakon.rosenberger@studmail.hwg-lu.de, samuel.sonnenwald@studmail.hwg-lu.de, joscha.staehle@studmail.hwg-lu.de, lisa.sterner@studmail.hwg-lu.de, lukas.strickler@studmail.hwg-lu.de, leo.waigel@studmail.hwg-lu.de*


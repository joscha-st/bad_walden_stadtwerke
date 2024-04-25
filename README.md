# Group project "Bad Walden": *Stadtwerke*

HWG Ludwigshafen IBAIT ATdIT 2, Project Group 3   

***Building a utility service management software for the fictional city of Bad Walden.***  

Lecturers: *Dr. Britta Jung and Patrick Gutgesell*  
Group Members: *Hakon Rosenberger, Samuel Sonnenwald, Joscha Stähle, Lisa Sterner, Lukas Strickler, Leo Waigel*

---
  
## Modelling

### General introduction to modelling files

In order to effectively build a utility service management software, we need to start by modeling key processes and software elements.  
The BPMN modellings to understand the processes, can be found in [Documents/Application Software](Documents/Application%20Software). For an explanation please open the following file: [Documents/Application Software/readme.md](Documents/Application%20Software/readme.md).
The Datamodel that shows the structure of our database can be found under [Documents/Datamodel](Documents/Datamodel). An explanation is written in the [Documents/Datamodel/readme.md](Documents/Datamodel/readme.md) file.
The UI modelling can be found under [Documents/UI](Documents/UI). This folder is structured in the three subfolder [Buerger](Documents/UI/Buerger), [Login](Documents/UI/Login) and [Poweruser](Documents/UI/Poweruser). In each subfolder there are the excalidraw files, which is an extension in Visual Studio, that we used to create the UI models. And there is also an folder "Exports" where every part of the UI is exported and saved as a svg file. To get an complete overview of the UI, there is no need to go into any of the subfolders. The complete UI is shown in the main folder: "Stadtwerke_UI". It is available as a svg or excalidraw file. An explanation including pictures of the UI can be found here: [Documents/UI/README.md](Documents/UI/README.md). To view the UI as pngs please follow this folder: [Documtens/UI/PNGs](Documents/UI/PNGs). The structure of this folder is again split into "Bürger", "Login" and "Poweruser".
All of those documents will serve as the foundation for the development process.

### Directory to most important files

We want to give you a directory to the files that build the base for what we implemented. We decided to implement the SignUp process. This process is for data collection of customers that log into their account for the first time. Here they can enter their information and choose the tariffs that they want. Further information regarding the modelling can be found in the readme files that are mentioned above.

BPMN: [Documents/Application Software/SignUp Process BPMN_Final.png](Documents/Application%20Software/SignUp%20Process%20BPMN_Final.png)
UI: [Documents/UI/PNGs/Login/SignUp.png](Documents/UI/PNGs/Login/SignUp.png)
Datamodel: [Documents/Datamodel/db_EER_markup.png](Documents/Datamodel/db_EER_markup.png) and  [Documents/Datamodel/db_structure.plantuml](Documents/Datamodel/db_structure.plantuml)

---

## Coding of the SIGNUP PROCESS

### Finding the code and understanding the file structure

The code can be found in the code folder for "Stadtwerke Bad Walden": [Code/bad_walden_stadtwerke](Code/bad_walden_stadtwerke)
In this folder are files that to you need to be able to run the code. Further explanation on how to run the code can be found below. And there is also a file with further explanations of the code [Code/bad_walden/stadtwerke/readme.md](Code/bad_walden_stadtwerke/readme.md), as well as a "src" folder.
This folder contains 2 subfolders. One of them contains the main code [Code/bad_walden_stadtwerke/src/main](Code/bad_walden_stadtwerke/src/main) and the other one contains the tests [Code/bad_walden_stadtwerke/src/test](Code/bad_walden_stadtwerke/src/test). Please read the readme.md file in the Code folder for further explanation of the code.


### How to run the code?



---

## Any questions left?
Please contact us directly in case of further questions: *hakon.rosenberger@studmail.hwg-lu.de , samuel.sonnenwald@studmail.hwg-lu.de , joscha.staehle@studmail.hwg-lu.de , lisa.sterner@studmail.hwg-lu.de , lukas.strickler@studmail.hwg-lu.de , leo.waigel@studmail.hwg-lu.de*
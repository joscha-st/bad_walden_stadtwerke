# Modelling
## Application Software

This repository contains BPMN diagrams created for modeling various processes within the context of public utilities. BPMN (Business Process Model and Notation) is a standardized graphical notation for representing business processes, enabling the visualization and analysis of complex workflows. 


### Structure

We display general cases (= GC) such as classification in the basic supply, tariff changes and relocation. We have also created diagrams specially tailored to the user interface (= UI), which describe the login process, processes for customers such as a consumption overview and processes for power users such as customer management.

## Processes

### General cases

#### Classification in basic supply
This process shows the classification of a customer into the basic supply.
![Classification in basic supply](GC_Fall_in_Grundversorgung.png)

#### Tariff changes
This process shows what will happen, if the customer wants to change their tariff.
![Tariff changes](GC_Tarifwechsel)


#### Relocation
This process shows what will happen, if the customer relocates and therefore is no longer a customer of our public utility.
![Relocation](GC_Umzug.png)

### User Interface

#### Customer: Inital Sign Up

![Customer: Inital Sign Up](Sign_Up_Server_BPMN.png)

#### Customer: Login process
The Login Page can be used to login into the account. By typing in the correct username and password the user will be led to the Home Page.
![Customer: Login process](UI_Einlogprozess.png)

#### Customer: Invoice
This process gives the user an overview of all their invoices. They can filter their invoices by date and utilities.
![Customer: Invoice](UI_Kunde_Rechnungen.png)

#### Customer: Tariff details gas
This process gives the user a closer look at their gas consumption and they have the possibility to view their tariff and change their gas tariff.
![Customer: Tariff details gas](UI_Kunde_Tarifdetails_GAS.png)

#### Customer: Tariff details water
This process gives the user a closer look at their water consumption and they have the possibility to view their tariff and change their water tariff.
![Customer: Tariff details water](UI_Kunde_Tarifdetails_WASSER.png)

#### Customer: Tariff details electrictiy
This process gives the user a closer look at their electrictiy consumption and they have the possibility to view their tariff and change their electrictiy tariff.
![Customer: Tariff details electricity](UI_Kunde_Vertragsdetails_STROM.png)

#### Poweruser: Tariff details gas
We modeled this process exemplary for gas, since it is the same process for every segment. This process gives the poweruser a closer look at all tariffs relating to gas. Poweruser can change, add and delete the documents.
![Poweruser: Tariff details gas](UI_Poweruser_Tarifdetails_GAS.png)

#### Poweruser: Customer management
This process gives the poweruser an overview of all customers and their tariffs. Instead of filtering through the segments, the individual customer's tariff are in the focus. Powerusers can change, add and delete the documents.
![Poweruser: Customer management](UI_Poweruser_Kundenmanagement.png)





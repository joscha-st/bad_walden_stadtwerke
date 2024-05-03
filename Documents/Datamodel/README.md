# Modelling

# Stadtwerke Database Schema Documentation

## Overview
This documentation provides details on the database schema used for managing services like electricity, gas, water, and heat pump provisions by a utility company. The schema includes several tables designed to handle multilingual content, customer and contract management, meter readings, billing, and role-based access control.

## Tables Description

### Tariffs Tables
- **tariffs** & **tariffs_en**: Stores tariff details including names, descriptions, categories (e.g., water, electricity), and prices per unit. The 'tariffs_en' table is specifically for English descriptions.

### Contract Types Tables
- **contract_types** & **contract_types_en**: Lists available contract types linked to tariffs. These tables also support multiple languages, indicated by the '_en' suffix for English.

### Household Table
- **household**: Represents the households that are customers, containing fields for street, number, city, and a status indicating completion of signup.

### Citizens Table
- **citizens**: Contains information about individuals, such as first name, last name, birthdate, and their association with households.

### Contracts Table
- **contracts**: Central to the system, this table manages contract details including start and cancellation dates, price guarantees, associated meter ID, and household ID.

### Meter Readings Table
- **meter_readings**: Tracks utility consumption with meter readings recorded along with dates and linked contract IDs.

### Invoicings Table
- **invoicings**: Manages invoicing details like amounts due, amounts paid, and the energy consumed per contract.

### Roles and Authentication Tables
- **roles**: Defines different user roles within the system.
- **login_data**: Manages login credentials with fields for username and password.
- **role_assignment**: Links users with specific roles, ensuring appropriate access control.

## Key Features

### Multilingual Support
Support for multiple languages in key descriptive tables like tariffs and contract types helps cater to a diverse customer base.

### Contract Management
Comprehensive management of customer contracts from initiation to cancellation, with support for various utilities and contract types.

### Meter Management
Systematic recording and tracking of meter readings essential for accurate billing and usage monitoring.

### Billing and Payments
Facilitates accurate and timely billing based on actual consumption with detailed records of financial transactions.

### User Roles and Security
Role-based access control ensures system integrity and security, restricting access and functionalities based on user roles.

## Usage Scenarios

### Customer Signup and Contract Initiation
New customers are added to the system, set up with appropriate contracts based on selected tariffs and contract types.

### Billing Cycle Management
Meter readings are utilized to compute bills, reflected in the invoicings table, ensuring customers are billed based on actual usage.

### Role-Based Access
Functionalities of the system are accessible based on predefined roles, critical for maintaining operational security and integrity.



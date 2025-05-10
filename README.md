# Insurance Claim Processing System

# Overview
This Java application simulates a batch processing insurance system. It reads client and claim data from a file and processes claims based on date rules. The system models three main domain classes: Claim, InsuredPerson, and Mainframe, with a driver class called InsuranceApplication.

The goal is to demonstrate object-oriented design principles including encapsulation, class relationships, and data validation through a real-world simulation.

# Project Structure
InsuranceApplication.java     # Main class with program logic

Mainframe.java                # Manages InsuredPerson and Claim objects

InsuredPerson.java            # Represents a person with insurance claims

Claim.java                    # Represents an individual insurance claim

data.txt                      # Input file with client and claim info

README.md                     # This file

# Input File: data.txt
The input file contains insured persons and claims. It is read programmatically using an OS-independent path.

Each insured person:

Has a full name and date of birth

May have multiple insurance claims, each with:

Date of claim

Value of claim

## Classes and Their Responsibilities
# Claim
Models an insurance claim. Includes:

Unique ID (claimNo)

Filing date and processing date

Value and disbursement status

Optional notes for invalid claims

# InsuredPerson
Models a person with multiple claims.

# Mainframe
Handles the processing of all claims and manages the list of insured clients.

# InsuranceApplication
The driver class that:

Loads the input file.

Creates a Mainframe object.

Populates it with InsuredPerson and Claim objects.

Processes claims.

Outputs the full report.

# Key Features
Encapsulation: All data fields are private with public accessors.

Validation: Claims are only disbursed if they’re less than or equal to 60 days old and not in the future.

Auto-generated Claim IDs using insured person initials.

Detailed reporting of claim status and disbursement total.

# How to Compile & Run (BlueJ Recommended)
Open the project folder in BlueJ.

Compile all classes.

Run the InsuranceApplication main class.



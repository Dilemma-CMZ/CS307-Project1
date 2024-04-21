-- This script enables all triggers in the database
Alter Table Lines Enable TRIGGER ALL;
Alter Table Stations Enable TRIGGER ALL;
Alter Table Line_details Enable TRIGGER ALL;
Alter Table Entrances Enable TRIGGER ALL;
Alter Table Buildings Enable TRIGGER ALL;
Alter Table Bus_Names Enable TRIGGER ALL;
Alter Table Bus_Lines Enable TRIGGER ALL;
Alter Table Cards Enable TRIGGER ALL;
Alter Table Users Enable TRIGGER ALL;
Alter Table User_Rides Enable TRIGGER ALL;
Alter Table Card_Rides Enable TRIGGER ALL;

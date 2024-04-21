-- Purpose: Disable all triggers in the database.
Alter Table Lines DISABLE TRIGGER ALL;
Alter Table Stations DISABLE TRIGGER ALL;
Alter Table Line_details DISABLE TRIGGER ALL;
Alter Table Entrances DISABLE TRIGGER ALL;
Alter Table Buildings DISABLE TRIGGER ALL;
Alter Table Bus_Names DISABLE TRIGGER ALL;
Alter Table Bus_Lines DISABLE TRIGGER ALL;
Alter Table Cards DISABLE TRIGGER ALL;
Alter Table Users DISABLE TRIGGER ALL;
Alter Table User_Rides DISABLE TRIGGER ALL;
Alter Table Card_Rides DISABLE TRIGGER ALL;
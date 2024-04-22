        DROP TABLE IF EXISTS Card_Rides;
        DROP TABLE IF EXISTS User_Rides;
        DROP TABLE IF EXISTS Cards;
        DROP TABLE IF EXISTS Users;
        DROP TABLE IF EXISTS Bus_Lines;
        DROP TABLE IF EXISTS Bus_Names;
        DROP TABLE IF EXISTS Buildings;
        DROP TABLE IF EXISTS Entrances;
        DROP TABLE IF EXISTS Line_details;
        DROP TABLE IF EXISTS Stations;
        DROP TABLE IF EXISTS Lines;
CREATE TABLE if not exists Stations ( Station_id    int not null primary key, English_name  varchar(900) not null, Chinese_name  varchar(900) not null, District      varchar(900) not null, Introduction  text, constraint Stations_uq1 unique (English_name, Chinese_name) );
Alter Table Stations DISABLE TRIGGER ALL;
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
CREATE TABLE if not exists Lines (Line_id serial not null, line_name varchar(10) not null, start_time varchar(10) not null, end_time varchar(10) not null, intro text, mileage varchar(10) not null, color varchar(10) not null, first_opening date not null, url varchar(100) not null, primary key(Line_id), constraint Lines_uq2 unique (start_time, end_time, mileage, color, first_opening, url) );
CREATE TABLE if not exists Stations ( Station_id serial not null primary key, English_name  varchar(900) not null, Chinese_name  varchar(900) not null, District      varchar(900) not null, Introduction  text, constraint Stations_uq1 unique (English_name, Chinese_name) );
CREATE TABLE if not exists Line_details ( Line_id int not null, Station_id int not null, line_num int not null, primary key (Line_id, Station_id), constraint Line_detail_fk1 foreign key (Line_id) references Lines(Line_id), constraint Line_detail_fk2 foreign key (Station_id) references Stations(Station_id) );
CREATE TABLE if not exists Entrances ( Entrance_id serial not null primary key, station_id int not null, Entrance_name varchar(255), constraint Entrances_fk1 foreign key (station_id) references stations(station_id) );
CREATE TABLE if not exists Buildings ( Building_id serial not null, Entrance_id INT NOT NULL, Entrance VARCHAR(255), PRIMARY KEY (Building_id), CONSTRAINT buildings_fk1 FOREIGN KEY (Entrance_id) REFERENCES Entrances (Entrance_id) );
CREATE TABLE if not exists Bus_Names ( BusName_id serial not null primary key, Entrance_id int not null, BusName varchar(255), constraint Bus_Names_connection1 foreign key(Entrance_id) references entrances(Entrance_id) );
CREATE TABLE if not exists Bus_Lines ( BusLine_id serial not null, BusName_id int not null, BusLine varchar(255) not null, constraint Bus_Lines_fk1 foreign key(BusName_id) references Bus_Names(BusName_id) );
CREATE TABLE if not exists Cards ( Card_number varchar(10) primary key not null, Money float, Create_time varchar(255) );
CREATE TABLE if not exists Users ( User_id_number varchar(18) primary key not null, Name varchar(20) not null, Phone varchar(11), Gender char(1), District varchar(18), constraint Users_uq1 unique (Name, Phone) );
CREATE TABLE if not exists User_Rides ( Ride_id serial not null, User_id varchar(18) not null, From_station int, To_station int, Price float, Start_time varchar(255), End_time varchar(255), constraint User_Rides_uq1 unique (Ride_id, User_id), constraint User_Rides_fk1 foreign key(From_station) references Stations(station_id), constraint User_Rides_fk2 foreign key(To_station) references Stations(station_id), constraint User_Rides_fk3 foreign key(User_id) references Users(user_id_number) );
CREATE TABLE if not exists Card_Rides ( Ride_id serial not null, Card_id varchar(10) not null, From_station int, To_station int, Price float, Start_time varchar(255), End_time varchar(255), constraint Card_Rides_uq1 unique (Ride_id, Card_id), constraint Card_Rides_fk1 foreign key(From_station) references Stations(station_id), constraint Card_Rides_fk2 foreign key(To_station) references Stations(station_id), constraint Card_Rides_fk3 foreign key(Card_id) references cards(card_number) );


import json
import psycopg2 
from psycopg2 import sql 
from datetime import datetime, timedelta
import time

init_sql = open('../Process_Data/Definition.sql').read()
dis_triggers = open('../Process_Data/disable_triggers.sql').read()
en_triggers = open('../Process_Data/enable_triggers.sql').read()
read_Buildings = open('../Process_Data/Buildings.sql').read()
read_Bus_Line = open('../Process_Data/Bus_Line.sql').read()
read_Bus_Name = open('../Process_Data/Bus_Name.sql').read()
read_Card_Rides = open('../Process_Data/Card_Rides.sql').read()
read_Cards = open('../Process_Data/Cards.sql').read()
read_Entrances = open('../Process_Data/Entrance.sql').read()
read_Lines_Detail = open('../Process_Data/Lines_Detail.sql').read()
read_Lines = open('../Process_Data/Lines.sql').read()
read_Stations = open('../Process_Data/Stations.sql').read()
read_User_Rides = open('../Process_Data/User_Rides.sql').read()
read_Users = open('../Process_Data/Users.sql').read()

db = ['localhost', '5432', 'serendipity', 'chenmingzhi2004', 'project1']


Start_Date = datetime.strptime('2000-01-01 00:00:00', '%Y-%m-%d %H:%M:%S')
End_Date = datetime.strptime('2022-12-31 23:59:59', '%Y-%m-%d %H:%M:%S')

def init_table():
    init_conn = psycopg2.connect(host=db[0], port=db[1], user=db[2], password=db[3], database=db[4])
    init_cur = init_conn.cursor()
    init_cur.execute(init_sql)
    init_conn.commit()
    init_cur.close()
    init_conn.close()

def import_all():
    start_time = time.time()

    conn = psycopg2.connect(host=db[0], port=db[1], user=db[2], password=db[3], database=db[4])
    cur = conn.cursor()

    #cur.execute(dis_triggers)
    #cur.execute(en_triggers)
    cur.execute(read_Lines);
    cur.execute(read_Stations);
    cur.execute(read_Lines_Detail);
    cur.execute(read_Entrances);
    cur.execute(read_Buildings);
    cur.execute(read_Bus_Name);
    cur.execute(read_Bus_Line);
    cur.execute(read_Users);
    cur.execute(read_Cards);
    cur.execute(read_User_Rides);
    cur.execute(read_Card_Rides);

    conn.commit()
    cur.close()
    conn.close()
    end_time = time.time()
    print(end_time - start_time)

def import_buildings():
    start_time = time.time()

    conn = psycopg2.connect(host=db[0], port=db[1], user=db[2], password=db[3], database=db[4])
    cur = conn.cursor()
    cur.execute("DROP TABLE IF EXISTS Buildings;")
    cur.execute("CREATE TABLE if not exists Buildings ( Building_id INT NOT NULL, Entrance_id INT NOT NULL, Entrance VARCHAR(255), PRIMARY KEY (Building_id), CONSTRAINT buildings_fk1 FOREIGN KEY (Entrance_id) REFERENCES Entrances (Entrance_id) );");
    #cur.execute(dis_triggers)
    #cur.execute(en_triggers)
    cur.execute(read_Buildings);
    conn.commit()
    cur.close()
    conn.close()
    end_time = time.time()
    print(end_time - start_time)

if __name__ == '__main__':
    # Case1: Load All Data
    # init_table()
    # import_all()
    # Case2: Load Building Data
    # import_buildings()
    pass
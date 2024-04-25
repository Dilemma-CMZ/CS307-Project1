import json
import psycopg2 
from psycopg2 import sql 
from datetime import datetime, timedelta
import time
import concurrent.futures

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

def execute_sql_file(filename):
    sql = open(filename).read()
    conn = psycopg2.connect(host=db[0], port=db[1], user=db[2], password=db[3], database=db[4])
    cur = conn.cursor()
    cur.execute(sql)
    conn.commit()
    cur.close()
    conn.close()

def multi_thread_import():
    start_time = time.time()

    sql_files = [
        '../Process_Data/Definition.sql',
        '../Process_Data/disable_triggers.sql',
        '../Process_Data/Buildings.sql',
        '../Process_Data/Bus_Line.sql',
        '../Process_Data/Bus_Name.sql',
        '../Process_Data/Card_Rides.sql',
        '../Process_Data/Cards.sql',
        '../Process_Data/Entrance.sql',
        '../Process_Data/Lines_Detail.sql',
        '../Process_Data/Lines.sql',
        '../Process_Data/Stations.sql',
        '../Process_Data/User_Rides.sql',
        '../Process_Data/Users.sql',
    ]

    with concurrent.futures.ThreadPoolExecutor() as executor:
        print("Importing all data...")
        executor.map(execute_sql_file, sql_files)

    print(f"Imported all in {time.time() - start_time} seconds")

def import_all():
    start_time = time.time()

    conn = psycopg2.connect(host=db[0], port=db[1], user=db[2], password=db[3], database=db[4])
    cur = conn.cursor()
    sum = 0
    #cur.execute(dis_triggers)
    #cur.execute(en_triggers)
    print("Lines.sql is being loaded...")
    cur.execute(read_Lines);
    print("Successfully loaded Lines")
    print("Stations.sql is being loaded...")
    cur.execute(read_Stations);
    print("Successfully loaded Stations")
    print("Lines_Detail.sql is being loaded...")
    cur.execute(read_Lines_Detail);
    print("Successfully loaded Lines_Detail")
    print("Entrance.sql is being loaded...")
    cur.execute(read_Entrances);
    print("Successfully loaded Entrances")
    print("Buildings.sql is being loaded...")
    cur.execute(read_Buildings);
    print("Successfully loaded Buildings")
    print("Bus_Name.sql is being loaded...")
    cur.execute(read_Bus_Name);
    print("Successfully loaded Bus_Name")
    print("Bus_Line.sql is being loaded...")
    cur.execute(read_Bus_Line);
    print("Successfully loaded Bus_Line")
    print("Users.sql is being loaded...")
    cur.execute(read_Users);
    print("Successfully loaded Users")
    print("Cards.sql is being loaded...")
    cur.execute(read_Cards);
    print("Successfully loaded Cards")
    print("User_Rides.sql is being loaded...")
    cur.execute(read_User_Rides);
    print("Successfully loaded User_Rides")
    print("Card_Rides.sql is being loaded...")
    cur.execute(read_Card_Rides);
    print("Successfully loaded Card_Rides")

    conn.commit()
    cur.close()
    conn.close()
    end_time = time.time()
    print(end_time - start_time)
    print(f"Total rows imported: {sum}")
    print(f"Instructions per second: {sum / (end_time - start_time)}")

def import_buildings():
    start_time = time.time()

    conn = psycopg2.connect(host=db[0], port=db[1], user=db[2], password=db[3], database=db[4])
    cur = conn.cursor()
    cur.execute("DROP TABLE IF EXISTS Buildings;")
    cur.execute("CREATE TABLE if not exists Buildings ( Building_id INT NOT NULL, Entrance_id INT NOT NULL, Entrance VARCHAR(255), PRIMARY KEY (Building_id), CONSTRAINT buildings_fk1 FOREIGN KEY (Entrance_id) REFERENCES Entrances (Entrance_id) );");
    #cur.execute(dis_triggers)
    #cur.execute(en_triggers)
    print("Buildings.sql is being loaded...")
    cur.execute(read_Buildings);
    print("Successfully loaded Buildings")
    conn.commit()
    cur.close()
    conn.close()
    end_time = time.time()
    print(end_time - start_time)

if __name__ == '__main__':
    # Case1: Load All Data
    #init_table()
    #import_all()
    # Case2: Load Building Data
    # import_buildings()
    # Case3: Multi-thread Import All Data with disable triggers
    multi_thread_import();
    pass
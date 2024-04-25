import json
import psycopg2 
from psycopg2 import sql 
from datetime import datetime, timedelta
import time
import concurrent.futures

init_sql = open('Definition.sql').read()
read_Stations1 = open('stations_n=10000.sql').read()
read_Stations2 = open('stations_n=50000.sql').read()
read_Stations3 = open('stations_n=100000.sql').read()
read_Stations4 = open('stations_n=250000.sql').read()
read_Stations5 = open('stations_n=500000.sql').read()


db = ['localhost', '5432', 'serendipity', 'chenmingzhi2004', 'project1']

def init_db():
    init_conn = psycopg2.connect(host=db[0], port=db[1], user=db[2], password=db[3], database=db[4])
    init_cur = init_conn.cursor()
    init_cur.execute(init_sql)
    init_conn.commit()
    init_cur.close()
    init_conn.close()

def execute_sql_file(filename):
    start_time = time.time()
    sql = open(filename).read()
    conn = psycopg2.connect(host=db[0], port=db[1], user=db[2], password=db[3], database=db[4])
    cur = conn.cursor()
    print("Executing SQL file: " + filename)
    cur.execute(sql)
    print("SQL file executed: " + filename)
    conn.commit()
    cur.close()
    conn.close()
    end_time = time.time()
    print(f"Time taken to execute {filename}: {end_time - start_time:.2f} seconds")


if __name__ == '__main__':
    init_db()
    execute_sql_file('stations_n=10000.sql')

    init_db()
    execute_sql_file('stations_n=50000.sql')

    init_db()
    execute_sql_file('stations_n=100000.sql')

    init_db()
    execute_sql_file('stations_n=250000.sql')

    init_db()
    execute_sql_file('stations_n=500000.sql')


    
    
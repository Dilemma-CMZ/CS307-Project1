# Loader Note

Here we use the loader to load the Buildings.sql.

## Tested on macOS

- MacBook Pro 14-inch 2021
- Apple M1 Pro, 16GB RAM, 512GB SSD
- macOS Monterey 12.4
- Intellij IDEA 2023.1.5

### PostgreSQL 16

#### Full Loader

```agsl
Successfully connected to the database project1 as postgres
Lines.sql: insert 16 data successfully!
Stations.sql: insert 306 data successfully!
Lines_Detail.sql: insert 359 data successfully!
Entrance.sql: insert 1291 data successfully!
Buildings.sql: insert 4706 data successfully!
Bus_Name.sql: insert 906 data successfully!
Bus_Line.sql: insert 7220 data successfully!
Users.sql: insert 10000 data successfully!
Cards.sql: insert 10000 data successfully!
Card_Rides.sql: insert 36497 data successfully!
User_Rides.sql: insert 63503 data successfully!
134804 records successfully loaded
Loading speed : 6097 records/s
Loading time : 22.108 s
```

#### Full Loader, triggers disabled

```agsl
Successfully connected to the database project1 as postgres
disable_triggers.sql: insert 12 data successfully!
Lines.sql: insert 16 data successfully!
Stations.sql: insert 306 data successfully!
Lines_Detail.sql: insert 359 data successfully!
Entrance.sql: insert 1291 data successfully!
Buildings.sql: insert 4706 data successfully!
Bus_Name.sql: insert 906 data successfully!
Bus_Line.sql: insert 7220 data successfully!
Users.sql: insert 10000 data successfully!
Cards.sql: insert 10000 data successfully!
Card_Rides.sql: insert 36497 data successfully!
User_Rides.sql: insert 63503 data successfully!
enable_triggers.sql: insert 12 data successfully!
134828 records successfully loaded
Loading speed : 6849 records/s
Loading time : 19.683 s
```

#### Loader Awful

```agsl
insert 1000 data successfully!
insert 1000 data successfully!
insert 1000 data successfully!
insert 1000 data successfully!
4706 records successfully loaded
Loading speed : 99 records/s
```

#### Loader Connect

```agsl
Successfully connected to the database project1 as postgres
Successfully connected to the database project1 as postgres
insert 1000 data successfully!
insert 1000 data successfully!
insert 1000 data successfully!
insert 1000 data successfully!
4706 records successfully loaded
Loading speed : 1153 records/s
```

#### Loader Prepare

```agsl
Successfully connected to the database project1 as postgres
Successfully connected to the database project1 as postgres
insert 1000 data successfully!
insert 1000 data successfully!
insert 1000 data successfully!
insert 1000 data successfully!
4706 records successfully loaded
Loading speed : 6072 records/s
```

#### Loader Transaction

```agsl
Successfully connected to the database project1 as postgres
Successfully connected to the database project1 as postgres
insert 1000 data successfully!
insert 1000 data successfully!
insert 1000 data successfully!
insert 1000 data successfully!
4706 records successfully loaded
Loading speed : 16570 records/s
```

#### Loader Batch

Batch Size 1000.

```agsl
Successfully connected to the database project1 as postgres
Successfully connected to the database project1 as postgres
insert 1000 data successfully!
insert 1000 data successfully!
insert 1000 data successfully!
insert 1000 data successfully!
insert 1000 data successfully!
insert 706 data successfully!
4706 records successfully loaded
Loading speed : 16989 records/s
```

#### Loader Batch with Trigger Disabled

1. Batch Size 1200

```agsl
Successfully connected to the database project1 as postgres
Successfully connected to the database project1 as postgres
insert 1200 data successfully!
insert 1200 data successfully!
insert 1200 data successfully!
insert 1200 data successfully!
insert 1106 data successfully!
4706 records successfully loaded
Loading speed : 19130 records/s
```

2. Batch Size 1000

```agsl
Successfully connected to the database project1 as postgres
Successfully connected to the database project1 as postgres
insert 1000 data successfully!
insert 1000 data successfully!
insert 1000 data successfully!
insert 1000 data successfully!
insert 1000 data successfully!
insert 706 data successfully!
4706 records successfully loaded
Loading speed : 23297 records/s
```

3. Batch Size 800

```agsl
Successfully connected to the database project1 as postgres
Successfully connected to the database project1 as postgres
insert 800 data successfully!
insert 800 data successfully!
insert 800 data successfully!
insert 800 data successfully!
insert 800 data successfully!
insert 800 data successfully!
insert 706 data successfully!
4706 records successfully loaded
Loading speed : 22093 records/s
```

4. Batch Size 600

```agsl
Successfully connected to the database project1 as postgres
Successfully connected to the database project1 as postgres
insert 600 data successfully!
insert 600 data successfully!
insert 600 data successfully!
insert 600 data successfully!
insert 600 data successfully!
insert 600 data successfully!
insert 600 data successfully!
insert 600 data successfully!
insert 506 data successfully!
4706 records successfully loaded
Loading speed : 21488 records/s
```

5. Batch Size 400

```agsl
Successfully connected to the database project1 as postgres
Successfully connected to the database project1 as postgres
insert 400 data successfully!
insert 400 data successfully!
insert 400 data successfully!
insert 400 data successfully!
insert 400 data successfully!
insert 400 data successfully!
insert 400 data successfully!
insert 400 data successfully!
insert 400 data successfully!
insert 400 data successfully!
insert 400 data successfully!
insert 400 data successfully!
insert 306 data successfully!
4706 records successfully loaded
Loading speed : 22516 records/s
```

#### Loader Multi Threads, also Batched, and with Trigger Disabled

1. 3 threads, Batch Size 400

```agsl
Connection 0, successfully connected to the database project1 as postgres
Connection 1, successfully connected to the database project1 as postgres
Connection 2, successfully connected to the database project1 as postgres
No. 2 thread, insert 400 data successfully!
No. 0 thread, insert 400 data successfully!
No. 1 thread, insert 400 data successfully!
No. 0 thread, insert 400 data successfully!
No. 2 thread, insert 400 data successfully!
No. 1 thread, insert 400 data successfully!
No. 2 thread, insert 400 data successfully!
No. 0 thread, insert 400 data successfully!
No. 1 thread, insert 400 data successfully!
No. 2 thread, insert 306 data successfully!
No. 1 thread, insert 306 data successfully!
No. 0 thread, insert 306 data successfully!
3 threads, use all together 124ms
Loading speed : 37952 records/s
```

2. 5 threads, Batch Size 400

```agsl
Connection 0, successfully connected to the database project1 as postgres
Connection 1, successfully connected to the database project1 as postgres
Connection 2, successfully connected to the database project1 as postgres
Connection 3, successfully connected to the database project1 as postgres
Connection 4, successfully connected to the database project1 as postgres
No. 3 thread, insert 400 data successfully!
No. 1 thread, insert 400 data successfully!
No. 4 thread, insert 400 data successfully!
No. 0 thread, insert 400 data successfully!
No. 2 thread, insert 400 data successfully!
No. 0 thread, insert 400 data successfully!
No. 4 thread, insert 400 data successfully!
No. 3 thread, insert 400 data successfully!
No. 1 thread, insert 400 data successfully!
No. 2 thread, insert 400 data successfully!
No. 0 thread, insert 306 data successfully!
No. 3 thread, insert 306 data successfully!
No. 1 thread, insert 306 data successfully!
No. 4 thread, insert 306 data successfully!
No. 2 thread, insert 306 data successfully!
5 threads, use all together 99ms
Loading speed : 47535 records/s
```

3. 7 threads, Batch Size 400

```agsl
Connection 0, successfully connected to the database project1 as postgres
Connection 1, successfully connected to the database project1 as postgres
Connection 2, successfully connected to the database project1 as postgres
Connection 3, successfully connected to the database project1 as postgres
Connection 4, successfully connected to the database project1 as postgres
Connection 5, successfully connected to the database project1 as postgres
Connection 6, successfully connected to the database project1 as postgres
No. 6 thread, insert 400 data successfully!
No. 2 thread, insert 400 data successfully!
No. 0 thread, insert 400 data successfully!
No. 1 thread, insert 400 data successfully!
No. 5 thread, insert 400 data successfully!
No. 4 thread, insert 400 data successfully!
No. 3 thread, insert 400 data successfully!
No. 6 thread, insert 306 data successfully!
No. 0 thread, insert 306 data successfully!
No. 4 thread, insert 306 data successfully!
No. 1 thread, insert 306 data successfully!
No. 3 thread, insert 306 data successfully!
No. 5 thread, insert 306 data successfully!
No. 2 thread, insert 306 data successfully!
7 threads, use all together 89ms
Loading speed : 52876 records/s
```

4. 9 threads, Batch Size 400

```agsl
Connection 0, successfully connected to the database project1 as postgres
Connection 1, successfully connected to the database project1 as postgres
Connection 2, successfully connected to the database project1 as postgres
Connection 3, successfully connected to the database project1 as postgres
Connection 4, successfully connected to the database project1 as postgres
Connection 5, successfully connected to the database project1 as postgres
Connection 6, successfully connected to the database project1 as postgres
Connection 7, successfully connected to the database project1 as postgres
Connection 8, successfully connected to the database project1 as postgres
No. 1 thread, insert 400 data successfully!
No. 6 thread, insert 400 data successfully!
No. 2 thread, insert 400 data successfully!
No. 8 thread, insert 400 data successfully!
No. 0 thread, insert 400 data successfully!
No. 5 thread, insert 400 data successfully!
No. 4 thread, insert 400 data successfully!
No. 7 thread, insert 400 data successfully!
No. 3 thread, insert 400 data successfully!
No. 1 thread, insert 306 data successfully!
No. 5 thread, insert 306 data successfully!
No. 4 thread, insert 306 data successfully!
No. 8 thread, insert 306 data successfully!
No. 6 thread, insert 306 data successfully!
No. 2 thread, insert 306 data successfully!
No. 0 thread, insert 306 data successfully!
No. 7 thread, insert 306 data successfully!
No. 3 thread, insert 306 data successfully!
9 threads, use all together 85ms
Loading speed : 55365 records/s
```

5. 11 threads, Batch Size 400

```agsl
Connection 0, successfully connected to the database project1 as postgres
Connection 1, successfully connected to the database project1 as postgres
Connection 2, successfully connected to the database project1 as postgres
Connection 3, successfully connected to the database project1 as postgres
Connection 4, successfully connected to the database project1 as postgres
Connection 5, successfully connected to the database project1 as postgres
Connection 6, successfully connected to the database project1 as postgres
Connection 7, successfully connected to the database project1 as postgres
Connection 8, successfully connected to the database project1 as postgres
Connection 9, successfully connected to the database project1 as postgres
Connection 10, successfully connected to the database project1 as postgres
No. 10 thread, insert 400 data successfully!
No. 8 thread, insert 400 data successfully!
No. 0 thread, insert 400 data successfully!
No. 2 thread, insert 400 data successfully!
No. 5 thread, insert 400 data successfully!
No. 6 thread, insert 400 data successfully!
No. 4 thread, insert 400 data successfully!
No. 3 thread, insert 400 data successfully!
No. 9 thread, insert 400 data successfully!
No. 7 thread, insert 400 data successfully!
No. 1 thread, insert 400 data successfully!
No. 10 thread, insert 306 data successfully!
No. 5 thread, insert 306 data successfully!
No. 6 thread, insert 306 data successfully!
No. 7 thread, insert 306 data successfully!
No. 0 thread, insert 306 data successfully!
No. 2 thread, insert 306 data successfully!
No. 8 thread, insert 306 data successfully!
No. 3 thread, insert 306 data successfully!
No. 4 thread, insert 306 data successfully!
No. 9 thread, insert 306 data successfully!
No. 1 thread, insert 306 data successfully!
11 threads, use all together 96ms
Loading speed : 49021 records/s
```

6. 13 threads, Batch Size 400

```agsl
Connection 0, successfully connected to the database project1 as postgres
Connection 1, successfully connected to the database project1 as postgres
Connection 2, successfully connected to the database project1 as postgres
Connection 3, successfully connected to the database project1 as postgres
Connection 4, successfully connected to the database project1 as postgres
Connection 5, successfully connected to the database project1 as postgres
Connection 6, successfully connected to the database project1 as postgres
Connection 7, successfully connected to the database project1 as postgres
Connection 8, successfully connected to the database project1 as postgres
Connection 9, successfully connected to the database project1 as postgres
Connection 10, successfully connected to the database project1 as postgres
Connection 11, successfully connected to the database project1 as postgres
Connection 12, successfully connected to the database project1 as postgres
No. 8 thread, insert 306 data successfully!
No. 10 thread, insert 306 data successfully!
No. 11 thread, insert 306 data successfully!
No. 0 thread, insert 306 data successfully!
No. 6 thread, insert 306 data successfully!
No. 2 thread, insert 306 data successfully!
No. 1 thread, insert 306 data successfully!
No. 3 thread, insert 306 data successfully!
No. 7 thread, insert 306 data successfully!
No. 4 thread, insert 306 data successfully!
No. 12 thread, insert 306 data successfully!
No. 5 thread, insert 306 data successfully!
No. 9 thread, insert 306 data successfully!
13 threads, use all together 128ms
Loading speed : 36766 records/s
```

7. 15 threads, Batch Size 400

```agsl
Connection 0, successfully connected to the database project1 as postgres
Connection 1, successfully connected to the database project1 as postgres
Connection 2, successfully connected to the database project1 as postgres
Connection 3, successfully connected to the database project1 as postgres
Connection 4, successfully connected to the database project1 as postgres
Connection 5, successfully connected to the database project1 as postgres
Connection 6, successfully connected to the database project1 as postgres
Connection 7, successfully connected to the database project1 as postgres
Connection 8, successfully connected to the database project1 as postgres
Connection 9, successfully connected to the database project1 as postgres
Connection 10, successfully connected to the database project1 as postgres
Connection 11, successfully connected to the database project1 as postgres
Connection 12, successfully connected to the database project1 as postgres
Connection 13, successfully connected to the database project1 as postgres
Connection 14, successfully connected to the database project1 as postgres
No. 13 thread, insert 306 data successfully!
No. 1 thread, insert 306 data successfully!
No. 4 thread, insert 306 data successfully!
No. 0 thread, insert 306 data successfully!
No. 9 thread, insert 306 data successfully!
No. 12 thread, insert 306 data successfully!
No. 7 thread, insert 306 data successfully!
No. 14 thread, insert 306 data successfully!
No. 8 thread, insert 306 data successfully!
No. 5 thread, insert 306 data successfully!
No. 2 thread, insert 306 data successfully!
No. 6 thread, insert 306 data successfully!
No. 3 thread, insert 306 data successfully!
No. 11 thread, insert 306 data successfully!
No. 10 thread, insert 306 data successfully!
15 threads, use all together 108ms
Loading speed : 43574 records/s
```

8. 5 threads, Batch Size 800

```agsl
Connection 0, successfully connected to the database project1 as postgres
Connection 1, successfully connected to the database project1 as postgres
Connection 2, successfully connected to the database project1 as postgres
Connection 3, successfully connected to the database project1 as postgres
Connection 4, successfully connected to the database project1 as postgres
No. 4 thread, insert 800 data successfully!
No. 0 thread, insert 800 data successfully!
No. 1 thread, insert 800 data successfully!
No. 3 thread, insert 800 data successfully!
No. 2 thread, insert 800 data successfully!
No. 1 thread, insert 706 data successfully!
No. 3 thread, insert 706 data successfully!
No. 4 thread, insert 706 data successfully!
No. 0 thread, insert 706 data successfully!
No. 2 thread, insert 706 data successfully!
5 threads, use all together 88ms
Loading speed : 53477 records/s
```

9. 5 threads, Batch Size 200

```agsl
Connection 0, successfully connected to the database project1 as postgres
Connection 1, successfully connected to the database project1 as postgres
Connection 2, successfully connected to the database project1 as postgres
Connection 3, successfully connected to the database project1 as postgres
Connection 4, successfully connected to the database project1 as postgres
No. 2 thread, insert 200 data successfully!
No. 1 thread, insert 200 data successfully!
No. 0 thread, insert 200 data successfully!
No. 4 thread, insert 200 data successfully!
No. 3 thread, insert 200 data successfully!
No. 1 thread, insert 200 data successfully!
No. 0 thread, insert 200 data successfully!
No. 2 thread, insert 200 data successfully!
No. 3 thread, insert 200 data successfully!
No. 4 thread, insert 200 data successfully!
No. 1 thread, insert 200 data successfully!
No. 0 thread, insert 200 data successfully!
No. 2 thread, insert 200 data successfully!
No. 3 thread, insert 200 data successfully!
No. 4 thread, insert 200 data successfully!
No. 1 thread, insert 200 data successfully!
No. 0 thread, insert 200 data successfully!
No. 2 thread, insert 200 data successfully!
No. 3 thread, insert 200 data successfully!
No. 4 thread, insert 200 data successfully!
No. 1 thread, insert 106 data successfully!
No. 0 thread, insert 106 data successfully!
No. 3 thread, insert 106 data successfully!
No. 2 thread, insert 106 data successfully!
No. 4 thread, insert 106 data successfully!
5 threads, use all together 104ms
Loading speed : 45250 records/s
```

### MySQL 8

#### Full Loader

```agsl
Successfully connected to the database project1 as root
Lines.sql: insert 16 data successfully!
Stations.sql: insert 306 data successfully!
Lines_Detail.sql: insert 359 data successfully!
Entrance.sql: insert 1291 data successfully!
Buildings.sql: insert 4706 data successfully!
Bus_Name.sql: insert 906 data successfully!
Bus_Line.sql: insert 7220 data successfully!
Users.sql: insert 10000 data successfully!
Cards.sql: insert 10000 data successfully!
Card_Rides.sql: insert 36497 data successfully!
User_Rides.sql: insert 63503 data successfully!
134804 records successfully loaded
Loading speed : 3540 records/s
Loading time : 38.075 s
```

#### Full loader, triggers disabled

```agsl
Successfully connected to the database project1 as root
disable_triggers_MySQL.sql: insert 12 data successfully!
Lines.sql: insert 16 data successfully!
Stations.sql: insert 306 data successfully!
Lines_Detail.sql: insert 359 data successfully!
Entrance.sql: insert 1291 data successfully!
Buildings.sql: insert 4706 data successfully!
Bus_Name.sql: insert 906 data successfully!
Bus_Line.sql: insert 7220 data successfully!
Users.sql: insert 10000 data successfully!
Cards.sql: insert 10000 data successfully!
Card_Rides.sql: insert 36497 data successfully!
User_Rides.sql: insert 63503 data successfully!
enable_triggers_MySQL.sql: insert 12 data successfully!
134828 records successfully loaded
Loading speed : 4209 records/s
Loading time : 32.029 s
```

#### Loader Awful

```agsl
insert 1000 data successfully!
insert 1000 data successfully!
insert 1000 data successfully!
insert 1000 data successfully!
4706 records successfully loaded
Loading speed : 123 records/s
```

#### Loader Connect

```agsl
Successfully connected to the database project1 as root
Successfully connected to the database project1 as root
insert 1000 data successfully!
insert 1000 data successfully!
insert 1000 data successfully!
insert 1000 data successfully!
4706 records successfully loaded
Loading speed : 4964 records/s
```

#### Loader Prepare

```agsl
Successfully connected to the database project1 as root
Successfully connected to the database project1 as root
insert 1000 data successfully!
insert 1000 data successfully!
insert 1000 data successfully!
insert 1000 data successfully!
4706 records successfully loaded
Loading speed : 6111 records/s
```

#### Loader Transaction

```agsl
Successfully connected to the database project1 as root
Successfully connected to the database project1 as root
insert 1000 data successfully!
insert 1000 data successfully!
insert 1000 data successfully!
insert 1000 data successfully!
4706 records successfully loaded
Loading speed : 14987 records/s
```

#### Loader Batch

Batch Size 1000.

```agsl
Successfully connected to the database project1 as root
Successfully connected to the database project1 as root
insert 1000 data successfully!
insert 1000 data successfully!
insert 1000 data successfully!
insert 1000 data successfully!
insert 1000 data successfully!
insert 706 data successfully!
4706 records successfully loaded
Loading speed : 14614 records/s
```

#### Loader Batch with Trigger Disabled

Batch Size 1000.

```agsl
Successfully connected to the database project1 as root
Successfully connected to the database project1 as root
insert 1000 data successfully!
insert 1000 data successfully!
insert 1000 data successfully!
insert 1000 data successfully!
insert 1000 data successfully!
insert 706 data successfully!
4706 records successfully loaded
Loading speed : 14752 records/s
```

#### Loader Multi Threads, also Batched, and with Trigger Disabled

1. 5 threads, batch size 400.

```agsl
Connection 0, successfully connected to the database project1 as root
Connection 1, successfully connected to the database project1 as root
Connection 2, successfully connected to the database project1 as root
Connection 3, successfully connected to the database project1 as root
Connection 4, successfully connected to the database project1 as root
No. 4 thread, insert 400 data successfully!
No. 3 thread, insert 400 data successfully!
No. 0 thread, insert 400 data successfully!
No. 1 thread, insert 400 data successfully!
No. 2 thread, insert 400 data successfully!
No. 3 thread, insert 400 data successfully!
No. 4 thread, insert 400 data successfully!
No. 0 thread, insert 400 data successfully!
No. 2 thread, insert 400 data successfully!
No. 1 thread, insert 400 data successfully!
No. 3 thread, insert 306 data successfully!
No. 4 thread, insert 306 data successfully!
No. 0 thread, insert 306 data successfully!
No. 2 thread, insert 306 data successfully!
No. 1 thread, insert 306 data successfully!
5 threads, use all together 145ms
Loading speed : 32455 records/s
```

2. 7 threads, batch size 400.

```agsl
Connection 0, successfully connected to the database project1 as root
Connection 1, successfully connected to the database project1 as root
Connection 2, successfully connected to the database project1 as root
Connection 3, successfully connected to the database project1 as root
Connection 4, successfully connected to the database project1 as root
Connection 5, successfully connected to the database project1 as root
Connection 6, successfully connected to the database project1 as root
No. 0 thread, insert 400 data successfully!
No. 2 thread, insert 400 data successfully!
No. 3 thread, insert 400 data successfully!
No. 1 thread, insert 400 data successfully!
No. 6 thread, insert 400 data successfully!
No. 4 thread, insert 400 data successfully!
No. 5 thread, insert 400 data successfully!
No. 2 thread, insert 306 data successfully!
No. 0 thread, insert 306 data successfully!
No. 3 thread, insert 306 data successfully!
No. 1 thread, insert 306 data successfully!
No. 6 thread, insert 306 data successfully!
No. 4 thread, insert 306 data successfully!
No. 5 thread, insert 306 data successfully!
7 threads, use all together 146ms
Loading speed : 32233 records/s
```

3. 9 threads, batch size 400.

```agsl
Connection 0, successfully connected to the database project1 as root
Connection 1, successfully connected to the database project1 as root
Connection 2, successfully connected to the database project1 as root
Connection 3, successfully connected to the database project1 as root
Connection 4, successfully connected to the database project1 as root
Connection 5, successfully connected to the database project1 as root
Connection 6, successfully connected to the database project1 as root
Connection 7, successfully connected to the database project1 as root
Connection 8, successfully connected to the database project1 as root
No. 3 thread, insert 400 data successfully!
No. 2 thread, insert 400 data successfully!
No. 8 thread, insert 400 data successfully!
No. 1 thread, insert 400 data successfully!
No. 3 thread, insert 306 data successfully!
No. 5 thread, insert 400 data successfully!
No. 0 thread, insert 400 data successfully!
No. 6 thread, insert 400 data successfully!
No. 4 thread, insert 400 data successfully!
No. 1 thread, insert 306 data successfully!
No. 8 thread, insert 306 data successfully!
No. 2 thread, insert 306 data successfully!
No. 7 thread, insert 400 data successfully!
No. 5 thread, insert 306 data successfully!
No. 6 thread, insert 306 data successfully!
No. 4 thread, insert 306 data successfully!
No. 7 thread, insert 306 data successfully!
No. 0 thread, insert 306 data successfully!
9 threads, use all together 142ms
Loading speed : 33141 records/s
```

4. 5 threads, batch size 800.

```agsl
Connection 0, successfully connected to the database project1 as root
Connection 1, successfully connected to the database project1 as root
Connection 2, successfully connected to the database project1 as root
Connection 3, successfully connected to the database project1 as root
Connection 4, successfully connected to the database project1 as root
No. 1 thread, insert 800 data successfully!
No. 2 thread, insert 800 data successfully!
No. 3 thread, insert 800 data successfully!
No. 4 thread, insert 800 data successfully!
No. 0 thread, insert 800 data successfully!
No. 1 thread, insert 706 data successfully!
No. 4 thread, insert 706 data successfully!
No. 3 thread, insert 706 data successfully!
No. 2 thread, insert 706 data successfully!
No. 0 thread, insert 706 data successfully!
5 threads, use all together 140ms
Loading speed : 33614 records/s
```

## Tested on Windows

- Windows PC
- Intel Core i7-13700F, 24GB RAM, 1TB SSD
- Windows 11 22H2
- Intellij IDEA 2023.3.2

### PostgreSQL 16

#### Full Loader

```agsl
Successfully connected to the database project1 as postgres
Lines.sql: insert 16 data successfully!
Stations.sql: insert 306 data successfully!
Lines_Detail.sql: insert 359 data successfully!
Entrance.sql: insert 1291 data successfully!
Buildings.sql: insert 4706 data successfully!
Bus_Name.sql: insert 906 data successfully!
Bus_Line.sql: insert 7220 data successfully!
Users.sql: insert 10000 data successfully!
Cards.sql: insert 10000 data successfully!
Card_Rides.sql: insert 36497 data successfully!
User_Rides.sql: insert 63503 data successfully!
134804 records successfully loaded
Loading speed : 9415 records/s
Loading time : 14.318 s
```

#### Full Loader, triggers disabled

```agsl
Successfully connected to the database project1 as postgres
disable_triggers.sql: insert 12 data successfully!
Lines.sql: insert 16 data successfully!
Stations.sql: insert 306 data successfully!
Lines_Detail.sql: insert 359 data successfully!
Entrance.sql: insert 1291 data successfully!
Buildings.sql: insert 4706 data successfully!
Bus_Name.sql: insert 906 data successfully!
Bus_Line.sql: insert 7220 data successfully!
Users.sql: insert 10000 data successfully!
Cards.sql: insert 10000 data successfully!
Card_Rides.sql: insert 36497 data successfully!
User_Rides.sql: insert 63503 data successfully!
enable_triggers.sql: insert 12 data successfully!
134828 records successfully loaded
Loading speed : 11036 records/s
Loading time : 12.217 s
```

#### Loader Awful

```agsl
insert 1000 data successfully!
insert 1000 data successfully!
insert 1000 data successfully!
insert 1000 data successfully!
4706 records successfully loaded
Loading speed : 41 records/s
```

#### Loader Connect

```agsl
Successfully connected to the database project1 as postgres
Successfully connected to the database project1 as postgres
insert 1000 data successfully!
insert 1000 data successfully!
insert 1000 data successfully!
insert 1000 data successfully!
4706 records successfully loaded
Loading speed : 8448 records/s
```

#### Loader Prepare

```agsl
Successfully connected to the database project1 as postgres
Successfully connected to the database project1 as postgres
insert 1000 data successfully!
insert 1000 data successfully!
insert 1000 data successfully!
insert 1000 data successfully!
4706 records successfully loaded
Loading speed : 10142 records/s
```

#### Loader Transaction

```agsl
Successfully connected to the database project1 as postgres
Successfully connected to the database project1 as postgres
insert 1000 data successfully!
insert 1000 data successfully!
insert 1000 data successfully!
insert 1000 data successfully!
4706 records successfully loaded
Loading speed : 13640 records/s
```

#### Loader Batch, triggers disabled

Batch size 1000.

```agsl
Successfully connected to the database project1 as postgres
insert 1000 data successfully!
insert 1000 data successfully!
insert 1000 data successfully!
insert 1000 data successfully!
insert 1000 data successfully!
insert 706 data successfully!
4706 records successfully loaded
Loading speed : 20550 records/s
```

#### Loader Multi-threads, triggers disabled, batched

1. Batch size 800, 5 threads.

```agsl
Connection 0, successfully connected to the database project1 as postgres
Connection 1, successfully connected to the database project1 as postgres
Connection 2, successfully connected to the database project1 as postgres
Connection 3, successfully connected to the database project1 as postgres
Connection 4, successfully connected to the database project1 as postgres
No. 4 thread, insert 800 data successfully!
No. 3 thread, insert 800 data successfully!
No. 1 thread, insert 800 data successfully!
No. 2 thread, insert 800 data successfully!
No. 0 thread, insert 800 data successfully!
No. 2 thread, insert 706 data successfully!
No. 0 thread, insert 706 data successfully!
No. 4 thread, insert 706 data successfully!
No. 3 thread, insert 706 data successfully!
No. 1 thread, insert 706 data successfully!
5 threads, use all together 91ms
Loading speed : 51714 records/s
```

2. Batch size 400, 5 threads.

```agsl
Connection 0, successfully connected to the database project1 as postgres
Connection 1, successfully connected to the database project1 as postgres
Connection 2, successfully connected to the database project1 as postgres
Connection 3, successfully connected to the database project1 as postgres
Connection 4, successfully connected to the database project1 as postgres
No. 3 thread, insert 400 data successfully!
No. 2 thread, insert 400 data successfully!
No. 0 thread, insert 400 data successfully!
No. 4 thread, insert 400 data successfully!
No. 1 thread, insert 400 data successfully!
No. 1 thread, insert 400 data successfully!
No. 3 thread, insert 400 data successfully!
No. 0 thread, insert 400 data successfully!
No. 4 thread, insert 400 data successfully!
No. 2 thread, insert 400 data successfully!
No. 1 thread, insert 306 data successfully!
No. 4 thread, insert 306 data successfully!
No. 0 thread, insert 306 data successfully!
No. 3 thread, insert 306 data successfully!
No. 2 thread, insert 306 data successfully!
5 threads, use all together 92ms
Loading speed : 51152 records/s
```

3. Batch size 400, 7 threads.

```agsl
Connection 0, successfully connected to the database project1 as postgres
Connection 1, successfully connected to the database project1 as postgres
Connection 2, successfully connected to the database project1 as postgres
Connection 3, successfully connected to the database project1 as postgres
Connection 4, successfully connected to the database project1 as postgres
Connection 5, successfully connected to the database project1 as postgres
Connection 6, successfully connected to the database project1 as postgres
No. 6 thread, insert 400 data successfully!
No. 3 thread, insert 400 data successfully!
No. 4 thread, insert 400 data successfully!
No. 2 thread, insert 400 data successfully!
No. 1 thread, insert 400 data successfully!
No. 0 thread, insert 400 data successfully!
No. 5 thread, insert 400 data successfully!
No. 2 thread, insert 306 data successfully!
No. 1 thread, insert 306 data successfully!
No. 4 thread, insert 306 data successfully!
No. 6 thread, insert 306 data successfully!
No. 0 thread, insert 306 data successfully!
No. 3 thread, insert 306 data successfully!
No. 5 thread, insert 306 data successfully!
7 threads, use all together 69ms
Loading speed : 68203 records/s
```

4. Batch size 400, 9 threads.

```agsl
Connection 0, successfully connected to the database project1 as postgres
Connection 1, successfully connected to the database project1 as postgres
Connection 2, successfully connected to the database project1 as postgres
Connection 3, successfully connected to the database project1 as postgres
Connection 4, successfully connected to the database project1 as postgres
Connection 5, successfully connected to the database project1 as postgres
Connection 6, successfully connected to the database project1 as postgres
Connection 7, successfully connected to the database project1 as postgres
Connection 8, successfully connected to the database project1 as postgres
No. 5 thread, insert 400 data successfully!
No. 0 thread, insert 400 data successfully!
No. 4 thread, insert 400 data successfully!
No. 3 thread, insert 400 data successfully!
No. 1 thread, insert 400 data successfully!
No. 6 thread, insert 400 data successfully!
No. 2 thread, insert 400 data successfully!
No. 8 thread, insert 400 data successfully!
No. 7 thread, insert 400 data successfully!
No. 0 thread, insert 306 data successfully!
No. 2 thread, insert 306 data successfully!
No. 5 thread, insert 306 data successfully!
No. 4 thread, insert 306 data successfully!
No. 3 thread, insert 306 data successfully!
No. 1 thread, insert 306 data successfully!
No. 6 thread, insert 306 data successfully!
No. 8 thread, insert 306 data successfully!
No. 7 thread, insert 306 data successfully!
9 threads, use all together 61ms
Loading speed : 77148 records/s
```

5. Batch size 400, 11 threads.

```agsl
Connection 0, successfully connected to the database project1 as postgres
Connection 1, successfully connected to the database project1 as postgres
Connection 2, successfully connected to the database project1 as postgres
Connection 3, successfully connected to the database project1 as postgres
Connection 4, successfully connected to the database project1 as postgres
Connection 5, successfully connected to the database project1 as postgres
Connection 6, successfully connected to the database project1 as postgres
Connection 7, successfully connected to the database project1 as postgres
Connection 8, successfully connected to the database project1 as postgres
Connection 9, successfully connected to the database project1 as postgres
Connection 10, successfully connected to the database project1 as postgres
No. 6 thread, insert 400 data successfully!
No. 0 thread, insert 400 data successfully!
No. 10 thread, insert 400 data successfully!
No. 1 thread, insert 400 data successfully!
No. 3 thread, insert 400 data successfully!
No. 4 thread, insert 400 data successfully!
No. 5 thread, insert 400 data successfully!
No. 2 thread, insert 400 data successfully!
No. 7 thread, insert 400 data successfully!
No. 8 thread, insert 400 data successfully!
No. 9 thread, insert 400 data successfully!
No. 5 thread, insert 306 data successfully!
No. 0 thread, insert 306 data successfully!
No. 1 thread, insert 306 data successfully!
No. 3 thread, insert 306 data successfully!
No. 4 thread, insert 306 data successfully!
No. 2 thread, insert 306 data successfully!
No. 10 thread, insert 306 data successfully!
No. 7 thread, insert 306 data successfully!
No. 6 thread, insert 306 data successfully!
No. 8 thread, insert 306 data successfully!
No. 9 thread, insert 306 data successfully!
11 threads, use all together 54ms
Loading speed : 87148 records/s
```

6. Batch size 400, 13 threads.

```agsl
Connection 0, successfully connected to the database project1 as postgres
Connection 1, successfully connected to the database project1 as postgres
Connection 2, successfully connected to the database project1 as postgres
Connection 3, successfully connected to the database project1 as postgres
Connection 4, successfully connected to the database project1 as postgres
Connection 5, successfully connected to the database project1 as postgres
Connection 6, successfully connected to the database project1 as postgres
Connection 7, successfully connected to the database project1 as postgres
Connection 8, successfully connected to the database project1 as postgres
Connection 9, successfully connected to the database project1 as postgres
Connection 10, successfully connected to the database project1 as postgres
Connection 11, successfully connected to the database project1 as postgres
Connection 12, successfully connected to the database project1 as postgres
No. 12 thread, insert 306 data successfully!
No. 1 thread, insert 306 data successfully!
No. 6 thread, insert 306 data successfully!
No. 11 thread, insert 306 data successfully!
No. 2 thread, insert 306 data successfully!
No. 4 thread, insert 306 data successfully!
No. 5 thread, insert 306 data successfully!
No. 7 thread, insert 306 data successfully!
No. 0 thread, insert 306 data successfully!
No. 3 thread, insert 306 data successfully!
No. 8 thread, insert 306 data successfully!
No. 10 thread, insert 306 data successfully!
No. 9 thread, insert 306 data successfully!
13 threads, use all together 53ms
Loading speed : 88792 records/s
```

7. Batch size 400, 15 threads.

```agsl
Connection 0, successfully connected to the database project1 as postgres
Connection 1, successfully connected to the database project1 as postgres
Connection 2, successfully connected to the database project1 as postgres
Connection 3, successfully connected to the database project1 as postgres
Connection 4, successfully connected to the database project1 as postgres
Connection 5, successfully connected to the database project1 as postgres
Connection 6, successfully connected to the database project1 as postgres
Connection 7, successfully connected to the database project1 as postgres
Connection 8, successfully connected to the database project1 as postgres
Connection 9, successfully connected to the database project1 as postgres
Connection 10, successfully connected to the database project1 as postgres
Connection 11, successfully connected to the database project1 as postgres
Connection 12, successfully connected to the database project1 as postgres
Connection 13, successfully connected to the database project1 as postgres
Connection 14, successfully connected to the database project1 as postgres
No. 5 thread, insert 306 data successfully!
No. 9 thread, insert 306 data successfully!
No. 1 thread, insert 306 data successfully!
No. 0 thread, insert 306 data successfully!
No. 6 thread, insert 306 data successfully!
No. 4 thread, insert 306 data successfully!
No. 11 thread, insert 306 data successfully!
No. 7 thread, insert 306 data successfully!
No. 2 thread, insert 306 data successfully!
No. 14 thread, insert 306 data successfully!
No. 3 thread, insert 306 data successfully!
No. 12 thread, insert 306 data successfully!
No. 8 thread, insert 306 data successfully!
No. 13 thread, insert 306 data successfully!
No. 10 thread, insert 306 data successfully!
15 threads, use all together 53ms
Loading speed : 88792 records/s
```
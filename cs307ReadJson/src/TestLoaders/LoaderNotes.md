# Loader Note

Here we use the loader to load the Buildings.sql.

## Tested on macOS by Q

- MacBook Pro 14-inch 2021
- Apple M1 Pro, 16GB RAM, 512GB SSD
- macOS Monterey 12.4
- PostgreSQL 16
- Intellij IDEA 2023.1.5

### Loader Awful

```agsl
insert 1000 data successfully!
insert 1000 data successfully!
insert 1000 data successfully!
insert 1000 data successfully!
4706 records successfully loaded
Loading speed : 99 records/s
```

### Loader Connect

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

### Loader Prepare

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

### Loader Transaction

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

### Loader Batch

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

### Loader Batch with Trigger Disabled

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

### Loader Multi Threads, also Batched, and with Trigger Disabled

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

6. 5 threads, Batch Size 800

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

7. 5 threads, Batch Size 200

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
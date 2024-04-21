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
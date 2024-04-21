-- Q1(1) The number of stations in each district
select district, count(*) as num_stations
from stations
group by district;
-- Q1(2) The number of stations on each line
select line_id, count(*) as num_stations
from line_details
group by line_id
order by line_id;
-- Q1(3) The number of stations in total
select count(*) as num_stations from stations;

-- Q2 Number of female passengers and male passengers respectively
SELECT 'Female' AS gender, COUNT(*) AS num_passengers
FROM users
WHERE users.gender = 'F'
UNION ALL
SELECT 'Male' AS gender, COUNT(*) AS num_passengers
FROM users
WHERE users.gender = 'M';

-- Q3 List the number of passengers from Mainland China, Hong Kong, Macao, and Taiwan.
select 'Mainland China' as country, count(*) as num_passengers
from users
where district= 'Chinese Mainland'
union all
select 'Hong Kong' as country, count(*) as num_passengers
from users
where district= 'Chinese Hong Kong'
union all
select 'Macao' as country, count(*) as num_passengers
from users
where district= 'Chinese Macao'
union all
select 'Taiwan' as country, count(*) as num_passengers
from users
where district= 'Chinese Taiwan';

-- Q4 List the buses, buildings, or landmarks near a specific station exit.
select entrances.station_id, bus_names.entrance_id, bus_names from bus_names
join entrances on bus_names.entrance_id = entrances.entrance_id
where entrances.station_id = 1;
select entrances.station_id, buildings.entrance_id, buildings.entrance as building from buildings
join entrances on buildings.entrance_id = entrances.entrance_id
where entrances.station_id = 1;

-- Q5 List all information about a specific passenger's journey, including passenger name, entry station, exit station, date, and time.
select users.name, user_rides.from_station, user_rides.to_station, user_rides.start_time, user_rides.end_time from user_rides
join users on user_rides.user_id = users.user_id_number
where user_id_number = '140121195012160804';

-- Q6 List all journey records for a specific travel card, including card number, entry station, exit station, date, and time.
select card_number, card_rides.from_station, card_rides.to_station, card_rides.start_time, card_rides.end_time from card_rides
join cards on card_rides.card_id = cards.card_number
where card_number = '883545979';

-- Q7 Query information about a specific subway station, including Chinese name, English name,
-- number of exits, the district it is located in, and the subway line it belongs to.
select tmp.station_id, tmp.chinese_name, tmp.english_name, tmp.entrances_cnt, tmp.district, array_agg(line_details.line_id) as line_id
       from (select stations.station_id, stations.chinese_name, stations.english_name, entrances_cnt, district from stations
join (select entrances.station_id, count(*) as entrances_cnt from entrances
group by entrances.station_id) sub on stations.station_id = sub.station_id) tmp
join line_details on tmp.station_id = line_details.station_id
group by tmp.station_id, tmp.chinese_name, tmp.english_name, tmp.entrances_cnt, tmp.district;

-- Q8 Query information about a specific subway line, including start time, end time, first opening
-- time, number of stations, and an introduction
select tmp.line_id, tmp.start_time, tmp.end_time, tmp.first_opening, count(*) as num_stations, tmp.intro
from (select line_id, start_time, end_time, first_opening, intro
from lines) tmp
join line_details on line_details.line_id = tmp.line_id
group by tmp.line_id, tmp.start_time, tmp.end_time, tmp.first_opening, tmp.intro;

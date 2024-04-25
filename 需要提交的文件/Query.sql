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
select count(*) as num_stations
from stations;

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
where district = 'Chinese Mainland'
union all
select 'Hong Kong' as country, count(*) as num_passengers
from users
where district = 'Chinese Hong Kong'
union all
select 'Macao' as country, count(*) as num_passengers
from users
where district = 'Chinese Macao'
union all
select 'Taiwan' as country, count(*) as num_passengers
from users
where district = 'Chinese Taiwan';

-- Q4 List the buses, buildings, or landmarks near a specific station exit.
-- Q4(1) List the buses near a specific station exit.
with bus_info as (select entrances.station_id, entrances.entrance_id, busname_id, bus_names.busname
                  from bus_names
                           join entrances on bus_names.entrance_id = entrances.entrance_id
                  where entrances.entrance_id = 11),
     step2 as (select entrance_id, station_id, busname, busline
               from bus_info
                        join bus_lines on bus_info.busname_id = bus_lines.busname_id)
select entrance_id, stations.station_id, english_name, chinese_name, busname, busline
from stations
         join step2 on step2.station_id = stations.station_id;
-- Q4(2) List the buildings / landmarks near a specific station exit.
select stations.chinese_name, sub.entrance_id, sub.building from (select entrances.station_id, buildings.entrance_id, buildings.entrance as building
from buildings
         join entrances on buildings.entrance_id = entrances.entrance_id
where entrances.entrance_id = 10) sub join stations on sub.station_id = stations.station_id;

-- Q5 List all information about a specific passenger's journey, including passenger name, entry station, exit station, date, and time.
select subbb.name, subbb.from_station, ss.chinese_name as to_station, subbb.start_time, subbb.end_time
from (select subb.name, s.chinese_name as from_station, subb.to_station, subb.start_time, subb.end_time
      from (select users.name,
                   user_rides.from_station,
                   user_rides.to_station,
                   user_rides.start_time,
                   user_rides.end_time
            from user_rides
                     join users on user_rides.user_id = users.user_id_number
            where user_id_number = '140121195012160804') subb
               join stations s on s.station_id = subb.from_station) subbb
         join stations ss on subbb.to_station = ss.station_id;


-- Q6 List all journey records for a specific travel card, including card number, entry station, exit station, date, and time.
select card_number, subb.from_station, ss.chinese_name as to_station, subb.start_time, subb.end_time
from (select card_number, s.chinese_name as from_station, sub.to_station, sub.start_time, sub.end_time
      from (select card_number,
                   card_rides.from_station,
                   card_rides.to_station,
                   card_rides.start_time,
                   card_rides.end_time
            from card_rides
                     join cards on card_rides.card_id = cards.card_number
            where card_number = '883545979') sub
               join stations s on sub.from_station = s.station_id) subb
         join stations ss on subb.to_station = ss.station_id;

-- Q7 Query information about a specific subway station, including Chinese name, English name,
-- number of exits, the district it is located in, and the subway line it belongs to.
select tmp.station_id,
       tmp.chinese_name,
       tmp.english_name,
       tmp.entrances_cnt,
       tmp.district,
       array_agg(line_details.line_id) as line_id
from (select stations.station_id, stations.chinese_name, stations.english_name, entrances_cnt, district
      from stations
               join (select entrances.station_id, count(*) as entrances_cnt
                     from entrances
                     group by entrances.station_id) sub on stations.station_id = sub.station_id) tmp
         join line_details on tmp.station_id = line_details.station_id
where tmp.station_id = 3
group by tmp.station_id, tmp.chinese_name, tmp.english_name, tmp.entrances_cnt, tmp.district;

-- Q8 Query information about a specific subway line, including start time, end time, first opening
-- time, number of stations, and an introduction
select tmp.line_id, tmp.start_time, tmp.end_time, tmp.first_opening, count(*) as num_stations, tmp.intro
from (select line_id, start_time, end_time, first_opening, intro
      from lines) tmp
         join line_details on line_details.line_id = tmp.line_id
where tmp.line_id = 1
group by tmp.line_id, tmp.start_time, tmp.end_time, tmp.first_opening, tmp.intro


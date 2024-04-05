import json

# read json file
with open('resource/stations.json', 'r', encoding='utf-8') as file:
    data = json.load(file)

for station_name, station_info in data.items():
    print("Station name:", station_name)
    print("district:", station_info['district'])
    print("introduce:", station_info['intro'])

    # print bus info
    print("bus info:")
    for bus_info in station_info['bus_info']:
        print("- chukou:", bus_info['chukou'])
        for bus_out_info in bus_info['busOutInfo']:
            print("  - busInfo:", bus_out_info['busInfo'])
            print("  - busName:", bus_out_info['busName'])

    # print outt info
    print("outt info:")
    for out_info in station_info['out_info']:
        print("- outt:", out_info['outt'])
        print("- textt:", out_info['textt'])

    print("\n")


# read JSON file
with open('resource/cards.json', 'r', encoding='utf-8') as file:
    data = json.load(file)

for record in data:
    print("code:", record['code'])
    print("money:", record['money'])
    print("create_time:", record['create_time'])

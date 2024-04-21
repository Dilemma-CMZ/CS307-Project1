#include <iostream>
#include <fstream>
#include <sstream>
#include <pqxx/pqxx>
#include <chrono>

std::string files[] = {"../Process_Data/Definition.sql", "../Process_Data/Lines.sql", "../Process_Data/Stations.sql", 
"../Process_Data/Lines_Detail.sql", "../Process_Data/Entrance.sql", "../Process_Data/Buildings.sql", "../Process_Data/Bus_Name.sql", 
"../Process_Data/Bus_Line.sql", "../Process_Data/Users.sql", "../Process_Data/Cards.sql", "../Process_Data/User_Rides.sql",
 "../Process_Data/Card_Rides.sql"
};

void load_data(pqxx::connection& C, const std::string& file) {
    // Open the SQL file
    std::ifstream sql_file(file);
    if (!sql_file) {
        std::cerr << "Can't open SQL file" << std::endl;
        return;
    }

    // Read the SQL file line by line
    std::string line;
    while (std::getline(sql_file, line)) {
        // Start a transaction
        pqxx::work W(C);
        // Execute the SQL command
        W.exec(line);
        // Commit the transaction
        W.commit();
    }
}

int main() {
    try {
        pqxx::connection C("dbname=project1 user=serendipity password=chenmingzhi2004 hostaddr=127.0.0.1 port=5432");
        if (C.is_open()) {
            std::cout << "Opened database successfully: " << C.dbname() << std::endl;
        } else {
            std::cout << "Can't open database" << std::endl;
            return 1;
        }

        auto start = std::chrono::high_resolution_clock::now();
        load_data(C, files[0]);
        //load_data(C, "../Process_Data/disable_triggers.sql");
        //load_data(C, " ../Process_Data/enable_triggers.sql");
        for (int i = 1; i < 12; i++) {
            printf("i = %d Loading %s\n", i, files[i].c_str());
            load_data(C, files[i]);
            printf("Successfully loaded %s\n", files[i].c_str());
        }
        auto end = std::chrono::high_resolution_clock::now();
        auto duration = std::chrono::duration_cast<std::chrono::milliseconds>(end - start);
        std::cout << "Execution time: " << 1.0 * duration.count() / 1000 << " s" << std::endl;
    } catch (const std::exception &e) {
        std::cerr << e.what() << std::endl;
        return 1;
    }
    return 0;
}
//g++ -std=c++17 -o program Loader.cpp -L/System/Volumes/Data/opt/homebrew/Cellar/libpqxx/7.9.0/lib -lpqxx 编译命令
//./program 运行命令
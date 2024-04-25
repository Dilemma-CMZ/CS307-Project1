#include <cstdio>
#include <string>
#include <vector>
#include <algorithm>
#include <ctime>
std::string district[] = {"罗湖区", "福田区", "南山区", "宝安区", "龙岗区", "盐田区", "龙华区", "坪山区", "光明区"};
std::vector<std::string> surname = {
    "赵", "钱", "孙", "李", "周", "吴", "郑", "王", "冯", "陈", "褚", "卫", "蒋", "沈", "韩", "杨", "朱", "秦", "尤", "许",
    "何", "吕", "施", "张", "孔", "曹", "严", "华", "金", "魏", "陶", "姜", "戚", "谢", "邹", "喻", "柏", "水", "窦", "章",
    "云", "苏", "潘", "葛", "奚", "范", "彭", "郎", "鲁", "韦", "昌", "马", "苗", "凤", "花", "方", "俞", "任", "袁", "柳",
    "酆", "鲍", "史", "唐", "费", "廉", "岑", "薛", "雷", "贺", "倪", "汤", "滕", "殷", "罗", "毕", "郝", "邬", "安", "常",
    "乐", "于", "时", "傅", "皮", "卞", "齐", "康", "伍", "余", "元", "卜", "顾", "孟", "平", "黄", "和", "穆", "萧", "尹"
};

std::vector<std::string> name = {
    "伟", "芳", "娜", "敏", "静", "秀英", "丽", "强", "磊", "军", "洋", "勇", "艳", "杰", "娟", "涛", "超", "明", "霞", "秀兰",
    "刚", "平", "燕", "辉", "玲", "桂英", "丹", "萍", "鹏", "华", "红", "玉兰", "飞", "桂兰", "英", "梅", "鑫", "波", "斌", "莉",
    "宇", "浩", "凯", "秀珍", "健", "俊", "帆", "雪", "帅", "慧", "旭", "宁", "婷", "玉梅", "龙", "林", "玉珍", "凤英", "晶", "欢",
    "玉英", "颖", "红梅", "佳", "倩", "阳", "建华", "亮", "成", "琴", "兰英", "畅", "建", "云", "洁", "峰", "建国", "建军", "柳", "淑珍",
    "春梅", "海燕", "晨", "冬梅", "秀荣", "瑞", "桂珍", "莹", "秀云", "桂荣", "志强", "秀梅", "丽娟", "婷婷", "玉华", "兵", "雷", "东", "琳", "雪梅"
};
std::string random_English_name(int len) {
    std::string res;
    for (int i = 0; i < len; ++i) {
        res.push_back('a' + rand() % 26);
    }
    return res;
}
std::string random_Chinese_name() {
    return surname[rand() % surname.size()] + name[rand() % name.size()];
}
std::string random_District() {
    return district[rand() % 9];
}
std::string random_Introduction(int len) {
    std::string res;
    for (int i = 0; i < len; ++i) {
        res.push_back('a' + rand() % 26);
    }
    return res;
}
int main() {
    srand(time(0));
    freopen("stations_n=50000.sql", "w", stdout);
    int n = 50000;
    for (int i = 1; i <= n; ++i) {
        int station_id = i;
        std::string English_name = random_English_name(20);
        std::string Chinese_name = random_Chinese_name();
        std::string District = random_District();
        std::string Introduction = random_Introduction(1000);
        printf("INSERT INTO stations(station_id, English_name, Chinese_name, District, Introduction) VALUES(%d, '%s', '%s', '%s', '%s');\n", 
        station_id, English_name.c_str(), Chinese_name.c_str(), District.c_str(), Introduction.c_str());
    }
    return 0;
}
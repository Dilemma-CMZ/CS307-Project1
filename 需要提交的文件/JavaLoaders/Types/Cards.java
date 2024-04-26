package Types;

import java.time.LocalDateTime;

public class Cards {
    private String code;
    private double money;
    private LocalDateTime create_time;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public LocalDateTime getCreate_time() {
        return create_time;
    }

    public void setCreate_time(LocalDateTime create_time) {
        this.create_time = create_time;
    }

    public Cards(String code, double money, LocalDateTime create_time) {
        this.code = code;
        this.money = money;
        this.create_time = create_time;
    }

    @Override
    public String toString() {
        return "Cards{" +
                "code='" + code + '\'' +
                ", money=" + money +
                ", create_time=" + create_time +
                '}';
    }
}

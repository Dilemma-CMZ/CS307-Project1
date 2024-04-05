import java.time.LocalDateTime;

public class Ride {
    private String user;
    private String startStation;
    private String endStation;
    private double price;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public Ride(String user, String startStation, String endStation, double price, LocalDateTime startTime, LocalDateTime endTime) {
        this.user = user;
        this.startStation = startStation;
        this.endStation = endStation;
        this.price = price;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getStartStation() {
        return startStation;
    }

    public void setStartStation(String startStation) {
        this.startStation = startStation;
    }

    public String getEndStation() {
        return endStation;
    }

    public void setEndStation(String endStation) {
        this.endStation = endStation;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "Ride{" +
                "user='" + user + '\'' +
                ", startStation='" + startStation + '\'' +
                ", endStation='" + endStation + '\'' +
                ", price=" + price +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }
}

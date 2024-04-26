package Types;

public class Passengers {
    private String name;
    private String id_number;
    private String phone_number;
    private char gender; // F or M
    private String district;

    public Passengers(String name, String id_number, String phone_number, String gender, String district) {
        this.name = name;
        this.id_number = id_number;
        this.phone_number = phone_number;
        if (gender.equals("男")) this.gender = 'M';
        else this.gender = 'F';
        this.district = district;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId_number() {
        return id_number;
    }

    public void setId_number(String id_number) {
        this.id_number = id_number;
    }

    public String getPhone() {
        return phone_number;
    }

    public void setPhone(String phone) {
        this.phone_number = phone;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(String gender) {
        if (gender.equals("男")) this.gender = 'M';
        else this.gender = 'F';
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    @Override
    public String toString() {
        return "Passengers{" +
                "name='" + name + '\'' +
                ", id_number='" + id_number + '\'' +
                ", phone='" + phone_number + '\'' +
                ", gender=" + gender +
                ", district='" + district + '\'' +
                '}';
    }
}

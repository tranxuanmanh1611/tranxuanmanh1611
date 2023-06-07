package model;

public class Citizen {
    private String id;
    private String name;
    private String birthday;
    private String house_id;

    public Citizen(String id, String name, String birthday, String house_id) {
        this.id = id;
        this.name = name;
        this.birthday = birthday;
        this.house_id = house_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getHouse_id() {
        return house_id;
    }

    public void setHouse_id(String house_id) {
        this.house_id = house_id;
    }
}

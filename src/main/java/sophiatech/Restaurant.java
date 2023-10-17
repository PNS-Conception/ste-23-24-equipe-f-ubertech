package sophiatech;

public class Restaurant {

    private String name;
    private String location;
    private Hours hours;

    public Restaurant(String name, String location, Hours hours) {
        this.name = name;
        this.location = location;
        this.hours = hours;
    }

    public Hours getHours() {
        return this.hours;
    }

    public String getName() {
        return this.name;
    }
}
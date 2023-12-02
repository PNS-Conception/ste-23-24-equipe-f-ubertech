package sophiatech.AppUsers;

public class User {
    private int index=0;
    private String name;
    private String first_name;


    public User(int nb, String name, String first_name) {
        this.index = nb;
        this.name = name;
        this.first_name = first_name;
    }

    public int getIndex() {
        return this.index;
    }

    public void setIndex(int nb) {
        this.index = nb;
    }

    public void clicks() {
        // clicks
    }
}

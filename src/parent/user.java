package parent;

public class user {
    private String name;
    private String email;
    private String password;
    int id;

    // Constructor
    public user(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    // Getter dan Setter
    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
    public int getId(){
        return id;
    }

}

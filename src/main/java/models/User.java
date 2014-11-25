package models;

/**
 * Created by Мадина on 21.11.2014.
 */
public class User {
    private String email;

    public User(String email){
        this.email=email;
    };

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

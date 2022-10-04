import java.io.Serializable;

public class Account implements Serializable {
    String username;
    String password;
    String location;

    public Account(String username, String password, String location) {
        this.username = username;
        this.password = password;
        this.location = location;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getLocation() {
        return location;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getInfo() {
        return "<html>Account:<br/>Location: " + getLocation() 
                + "<br/>Username: " + getUsername() 
                + "<br/>Password: " + getPassword() + "<br/><br/>" + "</html>";
    }

    public boolean equals(Account account) {
        if (username.equals(account.getUsername()) && password.equals(account.getPassword()) && location.equals(account.getLocation())) {
            return true;
        }
        return false;
    }
}
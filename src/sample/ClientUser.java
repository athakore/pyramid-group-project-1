package sample;

public class ClientUser extends User {

    public ClientUser() {
        super();
    }

    public ClientUser(String username, String password) {
        super(username, password);
    }

    public ClientUser(String userID, String username, String encryptedPassword) {
        super(userID, username, encryptedPassword);
    }
}

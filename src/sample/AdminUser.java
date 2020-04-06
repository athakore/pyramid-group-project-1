package sample;

public class AdminUser extends User {

    public AdminUser(String username, String password) {
        super(username, password);
    }

    public AdminUser(String userID, String username, String encryptedPassword) {
        super(userID, username, encryptedPassword);
    }

    public void ChangeUsername(String username, String newUsername) {

    }
}

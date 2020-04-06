package sample;

import java.util.UUID;

public abstract class User {
    private UUID userID;
    private String username;
    private String encryptedPassword;
    private String password;

    public User() {
        this.userID = UUID.randomUUID();
        this.username = "";
        this.encryptedPassword = "";
        this.password = "";
    }

    public User(String username, String password) {
        this.userID = UUID.randomUUID();
        this.username = username;
        this.password = password;
        this.encryptedPassword = encryptPassword(password);
    }

    public User(String userID, String username, String encryptedPassword) {
        this.userID = UUID.fromString(userID);
        this.username = username;
        this.encryptedPassword = encryptedPassword;
        this.password = decryptPassword(encryptedPassword);
    }

    public UUID getUserID() {
        return userID;
    }

    public void setUserID(UUID userID) {
        this.userID = userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
        System.out.println(Main.userFile);
    }

    public String encryptPassword(String password) {
        String result = "";
        for(int i = 0; i < password.length(); i++) {
            if(Character.isUpperCase(password.charAt(i))) result += (char)(((int)password.charAt(i) - 62) % 26 + 65);
            else result +=  (char)(((int)password.charAt(i) - 94) % 26 + 97);
        }
        return result;
    }

    public static String decryptPassword(String password) {
        String result = "";
        for(int i = 0; i < password.length(); i++) {
            if(Character.isUpperCase(password.charAt(i))) result += (char)(((int)password.charAt(i) - 42) % 26 + 65);
            else result +=  (char)(((int)password.charAt(i) - 74) % 26 + 97);
        }
        return result;
    }

    @Override
    public String toString() {
        return String.format("%s:%s:%s",userID.toString(),username,encryptedPassword);
    }
}

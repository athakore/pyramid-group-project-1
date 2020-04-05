package sample;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;

public class AdminUser extends User {
    public boolean isAdmin;

    public AdminUser(int userID, String userNm, String userPsswd) throws IOException {
        super(userID, userNm, userPsswd);
        this.userID = new Random().nextInt();
        fileReader = new FileReader(file);
        fileWriter = new FileWriter(file);

    }

    public static void DeleteUser(){

    }

    //method that allows admin users to edit permissions of new and/or existing users found in our target file
    public static void EditPermissions(){

    }

    //method that allows admin user to create and add users to a given group --> may be forced to forgo this method due
    //to deadline restrictions
    public static void CreateGroup(){

    }
}

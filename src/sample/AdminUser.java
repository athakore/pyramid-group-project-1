package sample;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Random;

public class AdminUser extends User {
    public boolean isAdmin;

    public AdminUser(String userNm, String userPsswd) throws IOException {
        super(userNm, userPsswd);
        this.userID = new Random().nextInt();
        fileReader = new FileReader(file);
        fileWriter = new FileWriter(file);
        userInfoMap = new HashMap<>();
        passwordMap = new HashMap<Integer, String>();
    }

    @Override
    public void Login(String loginName, String loginPasswd){

    }

    @Override
    public void Register(String regName, String regPasswd){

    }

    public static void DeleteUser(){

    }

    public static void EditPermissions(){

    }

    public static void CreateGroup(){

    }
}

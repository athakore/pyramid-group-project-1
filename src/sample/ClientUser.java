package sample;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class ClientUser extends User {

    public ClientUser(int userID, String userNm, String userPsswd) throws IOException {
        super(userID, userNm, userPsswd);
        this.userID = new Random().nextInt();
        fileReader = new FileReader(file);
        fileWriter = new FileWriter(file);
    }
    @Override
    public void Login(String loginName, String loginPasswd){

    }

    @Override
    public void Register(String regName, String regPasswd){

    }
}

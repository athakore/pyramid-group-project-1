package sample;

import java.io.IOException;
import java.util.Random;

public class Test {
    public static void main(String[] args) throws IOException {
        int testID = new Random().nextInt();
        String testName = "Jefe11";
        String testPasswd = "TestingTest";
        //String testEncryption = User.EncryptPassword(testPasswd);


        System.out.println(System.getProperty("user.dir"));
        //new User(testID, testName, testEncryption);
        new User(testID, testName,testPasswd);
        //User u = new User(testID, testName, testEncryption);
        User u = new User(testID, testName, testPasswd);
        u.WriteToFile();
        u.ReadFromFile();
    }

}

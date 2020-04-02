import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Random;

public class ClientUser extends User {

    public ClientUser(String userNm, String userPsswd) throws IOException {
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
}

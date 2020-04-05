package sample;

/*pertinent imports used throughout the user class below*/
import java.io.*;
import java.util.*;

//creation of the User class
//used as a base/super class which is extended into the AdminUser and ClientUser classes based on the respective
//users given permissions
public class User {

    public String userName;
    public int userID;
    private String userPassword;
    public static int loginAttempts;
    public boolean isAdmin;

    //File related variables below
    public final String filePath = System.getProperty("user.dir");  //be sure to change file path once pushed tpo git repo
    public File file = new File(filePath + "\\src\\UserInfo.csv");
    public FileWriter fileWriter = new FileWriter(file);
    public FileReader fileReader = new FileReader(file);
    public BufferedReader buffReader;
    public BufferedWriter buffWriter;

    //constructor used for initialization in the main method
    //assuming user input is passed through and used as the parameters in the main method
    //this constructors parameters are essentially written and later read from the file
    public User(int userID, String userNm, String userPsswd) throws IOException {
        this.userID = new Random().nextInt();
        this.userName = userNm;
        this.userPassword = userPsswd;
        this.userID = new Random().nextInt(2000);
    }

    //method for users to login to our portal if a userName, userPassword matching the user's input can
    //be found in our target file
    public void Login(String loginName, String loginPasswd){
        //int loginAttempts = 0;
        this.userName = loginName;
        this.userPassword = DecryptPassword(loginPasswd); //EncryptPassword(loginPasswd);
    }


    //method to register/create a new user if userID, userName, doesn't preexist in our target file
    public void Register(String regName, String regPasswd){
        this.userName = regName;
        this.userPassword = EncryptPassword(regPasswd);
        this.userID = new Random().nextInt();
        //if new userName to be created doesn't already exist in file, allow the name to be added --> this logic is to
        //be implemented in our main method I would presume
    }

    //method used to encrypt the plaintext version of the users password (userPassword)
    //used in the WriteToFileMethod to encrypt the password once its ready to be stored/written to the target file
    public static String EncryptPassword(String userPassword){
        String encryptedPasswd = "";
        char ch;
        int key = 3; //key value for Caesar Shift
        for(int i = 0; i < userPassword.length(); ++i){
            ch = userPassword.charAt(i);

            if(ch >= 'a' && ch <= 'z'){
                ch = (char)(ch + key);
                if(ch > 'z'){
                    ch = (char)(ch - 'z' + 'a' - 1);
                }
                encryptedPasswd += ch;
            }

            else if(ch >= 'A' && ch <= 'Z'){
                ch = (char)(ch + key);
                if(ch > 'Z'){
                    ch = (char)(ch - 'Z' + 'A' - 1);
                }
                encryptedPasswd += ch;
            }

            else {
                encryptedPasswd += ch;
            }
        }

        return encryptedPasswd;
    }

    //method used to decrypt the password (stored in the file); used to decrypt password stored at its respective index
    //in the ReadFromFile method with the use of the bufferedReader object
    public String DecryptPassword(String encryptedPasswd ){
        String passwdToDecrypt =  encryptedPasswd;  //must grab encrypted password from file, then decrypt
        String decryptedPasswd = "";
        char ch;
        int key = 3;  //original value used to encrypt plaintext password

        for(int i = 0; i < passwdToDecrypt.length(); ++i){
            ch = passwdToDecrypt.charAt(i);

            if(ch >= 'a' && ch <= 'z'){
                ch = (char)(ch - key);
                if(ch < 'a'){
                    ch = (char)(ch + 'z' - 'a' + 1);
                }
                decryptedPasswd += ch;
            }

            else if(ch >= 'A' && ch <= 'Z'){
                ch = (char)(ch - key);
                if(ch < 'A'){
                    ch = (char)(ch + 'Z' - 'A' + 1);
                }
                decryptedPasswd += ch;
            }

            else {
                decryptedPasswd += ch;
            }
        }
        return decryptedPasswd;
    }

    //method to write userID, userName, and encrypted password to our target file
    public void WriteToFile()  {
        this.userID = userID;
        this.userName = userName;
        this.userPassword = userPassword;

        buffWriter = new BufferedWriter(fileWriter);
        try{
            buffWriter.write(userID + ":" + userName + ":" + EncryptPassword(userPassword));
            buffWriter.newLine();
            buffWriter.flush();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try{
                buffWriter.close();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //method to get/read passwordMap from target file
    public void ReadFromFile() {
        this.userID = userID;
        this.userName = userName;
        this.userPassword = DecryptPassword((userPassword));  //worked with my [test] main; possibly redundant, may have to alter this if issues arise in the actual main method used

        buffReader = new BufferedReader(fileReader);
        Scanner readScan = null;
        String line = "";


        try {
            readScan = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while(readScan.hasNextLine()){
            //try block that grabs the encrypted password
            // from its line index in the file
            // and then decrypts said given [encrypted] password
            try {
                DecryptPassword(buffReader.readLine().split(":")[2]);

            }
            catch (IOException e){
                e.printStackTrace();
            }

        }

    }
//
}

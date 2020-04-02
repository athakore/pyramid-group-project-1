import java.io.*;
import java.util.*;

public class User {

    public String userName;
    public int userID;
    private String userPassword;
    public static int loginAttempts;

    //File related variables below
    public final String filePath = System.getProperty("user.dir");  //be sure to change file path once pushed tpo git repo
    public File file = new File(filePath + "pyramid-group-project\\src\\UserInfo.csv");
    public FileWriter fileWriter = new FileWriter(file);
    public FileReader fileReader = new FileReader(file);
    public BufferedReader buffReader;
    public BufferedWriter buffWriter;

    //HashMap related variables below
    public HashMap<Integer,String> userInfoMap;  //userID,userName --> k,v
    public HashMap<Integer, String> passwordMap;  //userID,userPassword --> k,v

    //should we store the userName and userPassword fields to a hashmap, and then send AND/OR read the ENCRYPTED password from the file?
    //public HashMap<String,String> hashMap = new HashMap<String, String>();   /*userName,userPassword*/
    //OR
    //save userID, userName to hashmap --> save to userNames file
    //save encrypted password to userPasswords file || save userID,encryptedUserPsswd to userPassword file


    public User(String userNm, String userPsswd) throws IOException {
        this.userName = userNm;
        this.userPassword = userPsswd;
        this.userID = new Random().nextInt();
        //fileReader = new FileReader(file);
        //fileWriter = new FileWriter(file);

//        userInfoMap = new HashMap<>();
//        passwordMap = new HashMap<>();
    }

    public void Login(String loginName, String loginPasswd){
        //int loginAttempts = 0;
        this.userName = loginName;
        this.userPassword = EncryptPassword(loginPasswd);
        this.userID = userID;
        //System.out.println("Enter/type login if you already have an account /*OR enter/type register if you're a new user*/");
        //userInfoMap.put(userID,loginName);
        //passwordMap.put(userID,loginPasswd);

//        for(loginAttempts = 0; loginAttempts < 5; loginAttempts++){
////            if (loginAttempts > 5){
////                System.out.println("Too many failed login attempts pal");
////            }
////            else{
////                fileWriter.write();
//            }

        }


    public void Register(String regName, String regPasswd){
        this.userName = regName;
        this.userPassword = EncryptPassword(regPasswd);
        this.userID = userID;
        //if new userName to be created doesn't already exist in file, allow the name to be added
        //userInfoMap.put(userID,regName);
        //passwordMap.put(userID,EncryptPassword(regPasswd));
    }

    public String EncryptPassword(String userPassword){
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

    public String DecryptPassword(String encryptedPasswd ){
        String passwdToDecrypt = EncryptPassword(encryptedPasswd);
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

    //method to store passwordMap to file
    public void WriteToFile()  {
        this.userName = userName;
        this.userPassword = EncryptPassword(userPassword);
        //userInfoMap.put(userID,userName);
        passwordMap.put(userID,EncryptPassword(userPassword));
        buffWriter = new BufferedWriter(fileWriter);
        try{
            for (Map.Entry<Integer, String> entry : passwordMap.entrySet()) {
                buffWriter.write(entry.getKey() + ":" + entry.getValue());
                buffWriter.newLine();
            }
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
    public void ReadFromFile()  {
        this.userName = userName;
        this.userPassword = EncryptPassword(userPassword);
        //userInfoMap.put(userID,userName);
        passwordMap.put(userID,DecryptPassword(userPassword));
        buffReader = new BufferedReader(fileReader);
        Scanner readScan = null;

        HashMap<String, String> testReadMap = new HashMap<>();
        ArrayList <String[]> testReadList = new ArrayList<>();
        try {
            readScan = new Scanner(file);
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }

        while (readScan.hasNextLine()){
            testReadList.add(readScan.nextLine().split(":"));

        }

        for (String [] i: testReadList){
            testReadMap.put(i[0], testReadMap.get(i[1]));

        }
//            if(testReadMap.containsKey(i[0])){
//                testReadMap.put(i[0], testReadMap.get(i[0])+ Integer.parseInt(i[3]));
//                if(testReadMap.containsKey(i[1])){
//                    testReadMap.put(i[1], testReadMap.get(i[3]) + Integer.parseInt(i[3]));
//                }
//                else {
//                    testReadMap.put(i[1], Integer.parseInt(i[3]));
//                }
//            }
//            else if(testReadMap.containsKey(i[1])){
//                testReadMap.put(i[1], testReadMap.get(i[1] + Integer.parseInt(i[3])));
//                if(testReadMap.containsKey(i[0])){
//                    testReadMap.put(i[0], testReadMap.get(i[0])+ Integer.parseInt(i[3]));
//                }
//                else {
//                    testReadMap.put(i[0], Integer.parseInt(i[3]));
//                }
//            }
//            else {
//                testReadMap.put(i[0], Integer.parseInt(i[3]));
//                testReadMap.put(i[1], Integer.parseInt(i[3]));
//            }
//
//        }
//
//        testReadMap.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).forEachOrdered(x-> sratMap.put(x.getKey(), x.getValue()));
//
//    }
    }
}

package sample;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import java.awt.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main extends Application {

    Stage window;
    private static DateTimeFormatter SHORT_TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss");
    public static void main(String[] args) {
        launch(args);
    }

    public static final String userFile = System.getProperty("user.dir") + "\\src\\UserInfo.csv";
    public static User activeUser = new ClientUser();

    @Override
    public void start(Stage primaryStage) throws Exception{

        window = primaryStage;
        window.setTitle("Company Portal");

        GridPane register = new GridPane();
        Scene regscene = new Scene(register, 570 , 420);

        GridPane grid = new GridPane();
        Scene scene1 = new Scene(grid, 570, 420);   //width, height for login

        GridPane profile = new GridPane();
        Scene sceneProfile = new Scene(profile, 570, 420);   //width, height for profile

        ////////////////////////////////////////////////////////////////////////////////////////////////////
        //REGISTER GRID

        register.setAlignment(Pos.CENTER);
        register.setHgap(10);           //gap manages spacing between row/col
        register.setVgap(10);
        register.setPadding(new Insets(25, 25, 25, 25));    //25px padding

        //combobox, the dropdown box in registry page
        ComboBox accesslevel = new ComboBox();
        accesslevel.getItems().addAll(
                "sample.User",
                "Admin"
        );
        register.add(accesslevel, 2, 1);

        Image image = new Image("file:mount.jpg");
        ImageView iv = new ImageView();
        iv.setImage(image);
        //text, label, fields
        Text regtile = new Text("Register");
        regtile.setId("registertext");
        register.add(regtile, 0, 0, 2,1); //row 0

        Label wantedusername = new Label("username: ");
        register.add(wantedusername, 0, 1);   //space between scenetile text and username
        TextField regtext = new TextField();
        regtext.setPromptText("Enter a name");
        register.add(regtext, 1, 1);   //row 1

        Label wantedpass = new Label("password: ");
        register.add(wantedpass, 0, 2);
        PasswordField regpass = new PasswordField();
        regpass.setPromptText("Enter a password");
        register.add(regpass, 1, 2);   //row 2

        //'go back to login' button
        Button gotologin = new Button("Back to login");
        gotologin.setOnAction(e -> window.setScene(scene1));
        //button to submit the user/pass
        Button registering = new Button("Create Account");
        registering.setOnAction(e -> {
            boolean temp = false;
            try {
                temp = Register(regtext.getText(), regpass.getText(), accesslevel.getValue().equals("Admin"));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            if(temp) window.setScene(scene1);
            else {
                regtext.setPromptText(String.format("The User '%s' already exists!", regtext.getText()));
                regtext.clear();
                regpass.clear();
            }
        });

        HBox hb2 = new HBox(10);             //the placement for the buttons
        hb2.setAlignment(Pos.BOTTOM_RIGHT);
        hb2.getChildren().addAll(gotologin, registering);
        register.add(hb2, 1, 4);

        ////////////////////////////////////////////////////////////////////////////////////////////////////
        //LOGIN GRID

        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);           //gap manages spacing between row/col
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));    //25px padding

        //text, label, fields
        Text scenetile = new Text("Login");
        scenetile.setId("logintext");                                               //IMPORTANT****** <- unique id to manipulate specifically in the css file
        grid.add(scenetile, 0, 0, 2,1); //row 0

        Label username = new Label("username: ");
        grid.add(username, 0, 1);   //space between scenetile text and username
        TextField usertext = new TextField();
        usertext.setPromptText("Enter username");
        grid.add(usertext, 1, 1);   //row 1

        Label pw = new Label("password: ");
        grid.add(pw, 0, 2);
        PasswordField pass = new PasswordField();
        pass.setPromptText("Enter password");
        grid.add(pass, 1, 2);   //row 2

        //register button
        Button b2 = new Button("Don't have an Account?");
        b2.setOnAction(e -> window.setScene(regscene));
        //login button
        Button b1 = new Button("login");
        b1.setOnAction(e -> {
            try {
                activeUser = Login(usertext.getText(), pass.getText());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            if(activeUser.getUsername() == "") {
                usertext.clear();
                pass.clear();
                usertext.setPromptText("Username or password is incorrect!");
            }
            else window.setScene(sceneProfile);
        });

        HBox hb = new HBox(10);             //the placement for the buttons
        hb.setAlignment(Pos.BOTTOM_RIGHT);
        hb.getChildren().addAll(b2, b1);
        grid.add(hb, 1, 4);


        //<- the stylesheets have to be in the src folder
        scene1.getStylesheets().add("styles.css");
        window.setScene(scene1);
        regscene.getStylesheets().add("styles2.css");
        window.show();

        ////////////////////////////////////////////////////////////////////////////////////////////////////
        //USER PROFILE GRID

        profile.setAlignment(Pos.TOP_LEFT);
        profile.setHgap(10);           //gap manages spacing between row/col
        profile.setVgap(10);
        profile.setPadding(new Insets(25, 25, 25, 25));    //25px padding

        String welcome = "Welcome to Pyramid Academy, " + activeUser.getUsername() + "\n" +
                "Today is " + new SimpleDateFormat("EEEEE dd MMMMM yyyy").format(new Date()) + "\n";

        String profileString = welcome + "Username:" + activeUser.getUsername() + "\n" +
                "Gender: Male\n" +
                "Birthday: 9/26/1961\n" +
                "Country: USA\n" +
                "Friends: 5 friends\n" +
                "Online Now: no\n" +
                "Member Center Viewed: 0\n" +
                "Language: Not Set\n" +
                "Referer ID: 123\n";

        Label profileLabel = new Label(profileString);
        profile.add(profileLabel, 0, 0, 2,1); //row 0

        //go back to login page
        Button profileLogOut = new Button("LOG OUT ");
        profileLogOut.setOnAction(e -> window.setScene(scene1));
        profile.add(profileLogOut, 1, 2);   //row 2

        sceneProfile.getStylesheets().add("styles3.css");
    }

    public static boolean Register(String username, String password, boolean isAdmin) throws IOException {
        if(!FindUser(username)) {
            User user = isAdmin ? new AdminUser(username, password) : new ClientUser(username, password);
            Files.write(Paths.get(userFile), String.format("%s%s:%s", System.lineSeparator(), user.toString(), isAdmin).getBytes(), StandardOpenOption.APPEND);
            return true;
        }
        else {
            System.out.println("The User '" + username + "' already exists!");
            return false;
        }
    }

    public static User Login(String username, String password) throws IOException {
        String result = Files.lines(Paths.get(userFile)).filter(user -> {
            String[] temp = user.split(":");
            return temp[1].equals(username) && decrypt(temp[2]).equals(password);
        }).findAny().orElse("");
        return result.equals("") ?
                new ClientUser() :
                result.split(":")[3].equals("true") ?
                        new AdminUser(result.split(":")[0], result.split(":")[1], result.split(":")[2]) :
                        new ClientUser(result.split(":")[0], result.split(":")[1], result.split(":")[2]);
    }

    public static boolean FindUser(String username)  throws IOException {
        return Files.lines(Paths.get(userFile)).anyMatch(user -> user.split(":")[1].equals(username));
    }

    public static String decrypt(String password) {
        String result = "";
        for(int i = 0; i < password.length(); i++) {
            if(Character.isUpperCase(password.charAt(i))) result += (char)(((int)password.charAt(i) - 42) % 26 + 65);
            else result +=  (char)(((int)password.charAt(i) - 74) % 26 + 97);
        }
        return result;
    }
}
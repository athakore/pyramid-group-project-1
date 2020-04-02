package sample;

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

import java.awt.*;

public class Main extends Application {

    Stage window;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{

        window = primaryStage;
        window.setTitle("project title thing");

        GridPane register = new GridPane();
        Scene regscene = new Scene(register, 570 , 420);

        GridPane grid = new GridPane();
        Scene scene1 = new Scene(grid, 570, 420);   //width, height for login

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


        HBox hb = new HBox(10);             //the placement for the buttons
        hb.setAlignment(Pos.BOTTOM_RIGHT);
        hb.getChildren().addAll(b2, b1);
        grid.add(hb, 1, 4);


        //<- the stylesheets have to be in the src folder
        scene1.getStylesheets().add("styles.css");
        window.setScene(scene1);
        regscene.getStylesheets().add("styles2.css");
        window.show();

    }






}

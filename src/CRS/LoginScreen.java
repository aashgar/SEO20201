/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CRS;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author aashgar
 */
public class LoginScreen extends Application{
    private Button buttonSubmit, buttonClear;
    private TextField textFieldLoginName;
    private PasswordField passwordField;
    private Label labelTitle, labelError;
    @Override
    public void start(Stage primaryStage) throws Exception {
        FlowPane pane = new FlowPane();
        pane.setAlignment(Pos.CENTER);
        pane.setHgap(10); 
        labelTitle = new Label("Login Infromation");
        labelTitle.setId("labelTitle");
        textFieldLoginName = new TextField();
        textFieldLoginName.setPromptText("Login Name");
        passwordField = new PasswordField();
        passwordField.setPromptText("Password");
        labelError = new Label();
        labelError.setStyle("-fx-text-fill: red;");
        VBox vBox1 = new VBox();
        vBox1.setSpacing(10);
        vBox1.setAlignment(Pos.CENTER);
        vBox1.getChildren().addAll(labelTitle,textFieldLoginName, passwordField, labelError);
        
        buttonSubmit = new Button("Submit");
        buttonClear = new Button("Clear");
        HBox hBox1 = new HBox();
        hBox1.setSpacing(10);
        hBox1.setAlignment(Pos.CENTER);
        hBox1.getChildren().addAll(buttonSubmit, buttonClear);
        MyEventHandler myEventHandler = new MyEventHandler();
        buttonSubmit.setOnAction(myEventHandler);
        buttonClear.setOnAction(myEventHandler);
        
        VBox vBox2 = new VBox();
        vBox2.setSpacing(10);
        vBox2.setAlignment(Pos.CENTER); 
        vBox2.setMinSize(300, 250);
        vBox2.getChildren().addAll(vBox1, hBox1);
        
        pane.getChildren().addAll(vBox2);        
        Scene scene = new Scene(pane, 350,300);
        scene.getStylesheets().add(getClass().getResource("styles1.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setTitle("CRS Application");
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
        
    }
    private class MyEventHandler implements EventHandler<ActionEvent>{
        
        @Override
        public void handle(ActionEvent event) {
            if(event.getSource() == buttonSubmit){
             String loginName = textFieldLoginName.getText();
             String password = passwordField.getText();
                try {
                    Scanner scanner =  new Scanner(
                            new File("./src/CRS/password.txt"));
                    while(scanner.hasNextLine()){
                        String name = scanner.next();
                        String psw = scanner.next();
                        if(loginName.equals(name) && password.equals(psw))
                        {
                            StudentEntryScreen ses= new StudentEntryScreen();
                            ses.show();
                            labelError.setText("Valid User ...");   
                            break;
                        }
                        else                           
                        labelError.setText("Invalid user name or password");
                    }
                    scanner.close();
                    textFieldLoginName.setText("");
                    passwordField.setText("");
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                }
            }
            else if(event.getSource() == buttonClear){
               textFieldLoginName.setText("");
               passwordField.setText("");
            }
        }        
    }    
}

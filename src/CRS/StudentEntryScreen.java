/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CRS;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author aashgar
 */
public class StudentEntryScreen extends Stage{
    private Button buttonSave, buttonClear, buttonShow;
    private TextField textFieldName, textFieldMajor, textFieldGrade;
    private TextArea textAreaView1;

    public StudentEntryScreen() {
        FlowPane pane = new FlowPane();
        pane.setAlignment(Pos.CENTER);
        pane.setHgap(10);        
        textFieldName = new TextField();
        //textFieldName.setMinWidth(20);
        textFieldName.setPromptText("Name");
        textFieldMajor = new TextField();
        //textFieldMajor.setMinWidth(20);
        textFieldMajor.setPromptText("Major");
        textFieldGrade = new TextField();
        //textFieldGrade.setMinWidth(20);
        textFieldGrade.setPromptText("Grade");
        VBox vBox1 = new VBox();
        vBox1.setSpacing(10);
        vBox1.setAlignment(Pos.CENTER);
        vBox1.setMaxWidth(150);
        vBox1.getChildren().addAll(textFieldName, textFieldMajor, textFieldGrade);
        
        buttonSave = new Button("Save");
        buttonClear = new Button("Clear");
        buttonShow = new Button("Show");
        HBox hBox1 = new HBox();
        hBox1.setSpacing(10);
        hBox1.setAlignment(Pos.CENTER);
        hBox1.getChildren().addAll(buttonSave, buttonShow, buttonClear);
        MyEventHandler myEventHandler = new MyEventHandler();
        buttonSave.setOnAction(myEventHandler);
        buttonClear.setOnAction(myEventHandler);
        buttonShow.setOnAction(myEventHandler);
        textAreaView1 = new TextArea();
        textAreaView1.setMaxWidth(250);
        textAreaView1.setPromptText("View Student Data");       
        VBox vBox2 = new VBox();
        vBox2.setSpacing(10);
        vBox2.setAlignment(Pos.CENTER); 
        vBox2.getChildren().addAll(textAreaView1, hBox1);
        
        pane.getChildren().addAll(vBox1,vBox2);        
        Scene scene = new Scene(pane, 550,400);
        scene.getStylesheets().add(getClass().getResource("styles2.css").toExternalForm());
        setScene(scene);
        setTitle("Student Entry Screen");
    }
    
     private class MyEventHandler implements EventHandler<ActionEvent>{
        
        @Override
        public void handle(ActionEvent event) {
            if(event.getSource() == buttonSave){
                Student student = new Student();
                student.setName(textFieldName.getText());
                student.setMajor(textFieldMajor.getText());
                student.setGrade(Double.parseDouble(textFieldGrade.getText()));
                try {
                    FileWriter fileWriter = new FileWriter("./src/CRS/students.txt", true);
                    fileWriter.write(student.getName() + " " + student.getMajor() +
                            " " + student.getGrade()+"\n");
                    fileWriter.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                textFieldName.setText("");
                textFieldMajor.setText("");
                textFieldGrade.setText("");
                textAreaView1.setText("");
            }
            else if(event.getSource() == buttonShow){
                try {
                    Scanner scanner = new Scanner(new File("./src/CRS/students.txt"));
                    while(scanner.hasNextLine()){
                        textAreaView1.appendText(scanner.nextLine()+"\n");
                    }
                    scanner.close();
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                }
            }
            else if(event.getSource() == buttonClear){
               textFieldName.setText("");
                textFieldMajor.setText("");
                textFieldGrade.setText("");
                textAreaView1.setText("");
            }
        }        
    }    
    
}

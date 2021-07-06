package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;
import javafx.scene.layout.StackPane;
import javafx.scene.control.*;

public class Main extends Application {

    //application idea so far
    //Design
    /*
    Date: __________ - Data entered: String date

    TITLE TODAY ----------------------------------------------
    Tasks left: __________ - Must be split into two catagories,
        forward pallet      - Integer
        p pallet            - Integer
        total pallet        - Integer
    Inbound Left to unload _______ - Integer
    Outbounds left to load _______ - Intefer
    Regular labor hours left _______ - Double
    OT Labor hours left ________ - Double
        NOTE: This could potentially have many people listed.
        Suggested working idea:
        How many regular labor hours do you want to report? Enter integer of workers
            New prompt appears with Name (String) and hours (Double)
        Same for OT Labor hours

    TITLE TOMORROW --------------------------------------------
    Inbounds scheduled __________ - Integer
    FLOOR loaded inbounds scheduled __________ - Integer
    Railcars ______________ - Integer
    Full Pallet picks _____________ - Integer
    Partial Pallet Picks ____________ - Integer
    Palletes on Floor ___________ - Integer
     */
    @Override
    public String textToSave = "Nothing";
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");

        //creating textfields


        // create a textfield
        TextField b = new TextField("First Name");
        TextField c = new TextField("Last Name");

        TilePane tp = new TilePane();
        Label l = new Label("no text");
        Label fn = new Label("Enter First Name:");
        Label ln = new Label("Enter Last Name: ");
        tp.getChildren().add(fn);


        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
                l.setText(b.getText());
                System.out.println(b.getText());
                textToSave = b.getText();
            }
        };
        // create a stack pane
        //StackPane r = new StackPane();
        b.setOnAction(event);

        // add textfield
        tp.getChildren().add(b);
        tp.getChildren().add(ln);
        tp.getChildren().add(c);
        tp.getChildren().add(new Label("You entered: "));
        tp.getChildren().add(l);
        // add textfield
        //r.getChildren().add(b);

        Scene sc = new Scene(tp,200,200);
        //primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.setScene(sc);


        System.out.println(textToSave);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}

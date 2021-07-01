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
    private String textToSave = "Nothing";
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");

        // create a textfield
        TextField b = new TextField("Name");

        TilePane tp = new TilePane();
        Label l = new Label("no text");
        Label f = new Label("Enter Name:");
        tp.getChildren().add(f);

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

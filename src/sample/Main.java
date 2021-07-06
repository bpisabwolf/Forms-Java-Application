package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;
import javafx.scene.layout.StackPane;
import javafx.scene.control.*;
import org.w3c.dom.Text;

public class Main extends Application {



    public String textToSave = "Nothing";
    public int fp, pp, totalP;
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");

        //creating textfields


        // create a textfield
        TextField b = new TextField("First Name");
        TextField c = new TextField("Last Name");


        //Date Label and TextField
        Label cd = new Label("Today's Date");
        TextField dateEntry = new TextField("dd/mm/yyyy");
        //Tasks
        Label fptl = new Label("FP Tasks Left: ");
        TextField fpEntry = new TextField("# of FP tasks");
        Label pptil = new Label("PP Tasks Left");
        TextField ppEntry = new TextField("# of PP Tasks");
        //Total tasks will be calculated later
        Label totalTasksReported;

        //Inbount and outbound entries
        Label ib = new Label("Inbound Left to Unload: ");
        TextField inboundEntry = new TextField("# to unload");
        Label ob = new Label("Outbound Left o Load: ");
        TextField outboundEntry = new TextField("# to load");

        Label hl = new Label("Regular and OT Hours Left");
        TextField employeeHourEntry =  new TextField("# of people");
        //will be drop down where people can be selected from server
        //must set up database for this

        //FIELDS FOR TOMORROW SECTION
        Label inSh = new Label("Inbounds Scheduled: ");
        TextField inSchedEntry = new TextField("# of scheduled");

        Label flInSh =  new Label("FLOOR loaded inbounds scheudled");
        TextField floorInEntry =  new TextField("# of scheduled");

        Label rc = new Label("Railcars");
        TextField railcarEntry = new TextField("# of cars");

        Label plf = new Label("Pallets on Floor: ");
        TextField palletEntry = new TextField("# on Floor");

        //Gonna try GridPane
        GridPane g = new GridPane();
        //TilePane tp = new TilePane();
        g.setPadding(new Insets(10, 10, 10, 10));
        g.setMinSize(300, 300);
        g.setVgap(5);
        g.setHgap(5);

        g.add(cd, 0, 0);
        g.add(dateEntry, 1, 0);


        //getChildren works mostly with stack pains
        //g.getChildren().add(cd);
        //g.getChildren().add(dateEntry);
        /*

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
           */
        //Scene sc = new Scene(tp,600,600);
        //primaryStage.setScene(new Scene(root, 300, 275));
        Scene sc = new Scene(g,300,600);
        primaryStage.setScene(sc);


        System.out.println(textToSave);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}

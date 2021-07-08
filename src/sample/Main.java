package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

public class Main extends Application implements Fields{



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
        Label cdConfirm = new Label("TEST");
        TextField dateEntry = new TextField("dd/mm/yyyy");
        //Tasks
        Label fptl = new Label("FP Tasks Left: ");
        Label fptlConfirm = new Label("");
        TextField fpEntry = new TextField("# of FP tasks");
        Label pptl = new Label("PP Tasks Left");
        Label pptlConfirm = new Label("");
        TextField ppEntry = new TextField("# of PP Tasks");
        //Total tasks will be calculated later
        Label totalTasksReported;

        //Inbount and outbound entries
        Label ib = new Label("Inbound Left to Unload: ");
        Label ibConfirm = new Label("");
        TextField inboundEntry = new TextField("# to unload");
        Label ob = new Label("Outbound Left o Load: ");
        Label obConfirm = new Label("");
        TextField outboundEntry = new TextField("# to load");

        Label hl = new Label("Regular and OT Hours Left");
        Label hlConfirm = new Label("");
        TextField employeeHourEntry =  new TextField("# of people");
        //will be drop down where people can be selected from server
        //must set up database for this

        //FIELDS FOR TOMORROW SECTION
        Label inSh = new Label("Inbounds Scheduled: ");
        Label inShConfirm = new Label("");
        TextField inSchedEntry = new TextField("# of scheduled");

        Label flSched =  new Label("FLOOR loaded inbounds scheduled");
        Label flSchedConfirm = new Label("");
        TextField floorInEntry =  new TextField("# of scheduled");

        Label rc = new Label("Railcars");
        Label rcComfirm = new Label("");
        TextField railcarEntry = new TextField("# of cars");

        Label plf = new Label("Pallets on Floor: ");
        Label plfConfirm = new Label("");
        TextField palletEntry = new TextField("# on Floor");

        //Gonna try GridPane
        GridPane g = new GridPane();
        //TilePane tp = new TilePane();
        g.setPadding(new Insets(10, 10, 10, 10));
        g.setMinSize(300, 500);
        g.setVgap(5);
        g.setHgap(5);


        //added Date Entry and labels
        g.add(cd, 0, 0);
        g.add(dateEntry, 1, 0);
        g.add(cdConfirm,2,0);

        //Added Forward pallet
        g.add(fptl, 0, 1);
        g.add(fpEntry, 1, 1);
        g.add(fptlConfirm, 2, 1);

        //add P Pallet
        g.add(pptl, 0, 2);
        g.add(ppEntry, 1, 2);
        g.add(pptlConfirm, 2, 2);

        //inbounds
        g.add(ib, 0, 3);
        g.add(inboundEntry, 1, 3);
        g.add(ibConfirm, 2, 3);

        //outbounds
        g.add(ob, 0, 4);
        g.add(outboundEntry, 1, 4);
        g.add(obConfirm, 2, 4);

        //hours left
        g.add(hl, 0, 5);
        g.add(employeeHourEntry, 1, 5);
        g.add(hlConfirm, 2, 5);

        ObservableList<String> options =
                FXCollections.observableArrayList(
                        //this will eventually retrieve info from database
                        "John Doe",
                        "Jane Deer",
                        "John Smith"
                );
        final ComboBox comboBox = new ComboBox(options);
        g.add(comboBox, 0, 6);

        g.add(inSh, 0, 7);
        g.add(inSchedEntry, 1, 7);
        g.add(inShConfirm, 2, 7);

        g.add(flSched, 0, 8);
        g.add(floorInEntry, 1, 8);
        g.add(flSchedConfirm, 2, 8);

        g.add(plf, 0, 9);
        g.add(palletEntry, 1, 9);
        g.add(plfConfirm, 3, 9);



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

       /* EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
                cdConfirm.setText(dateEntry.getText());
                System.out.println(dateEntry.getText());
                textToSave = dateEntry.getText();
            }
        };*/

        dateEntry.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                textToSave = dateEntry.getText();
                cdConfirm.setText(textToSave);
                fpEntry.requestFocus();;
            }
        });

        fpEntry.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                textToSave = fpEntry.getText();
                fptlConfirm.setText(textToSave);
                ppEntry.requestFocus();;
            }
        });

        ppEntry.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                textToSave = ppEntry.getText();
                pptlConfirm.setText(textToSave);
                inboundEntry.requestFocus();;
            }
        });

        inboundEntry.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                textToSave = inboundEntry.getText();
                ibConfirm.setText(textToSave);
                outboundEntry.requestFocus();;
            }
        });

        outboundEntry.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                textToSave = outboundEntry.getText();
                obConfirm.setText(textToSave);
                employeeHourEntry.requestFocus();;
            }
        });

        inSchedEntry.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                textToSave = inSchedEntry.getText();
                inShConfirm.setText(textToSave);
                floorInEntry.requestFocus();;
            }
        });

        floorInEntry.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                textToSave = floorInEntry.getText();
                flSchedConfirm.setText(textToSave);
                railcarEntry.requestFocus();;
            }
        });

        railcarEntry.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                textToSave = railcarEntry.getText();
                rcComfirm.setText(textToSave);
                palletEntry.requestFocus();;
            }
        });

        palletEntry.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                textToSave = palletEntry.getText();
                plfConfirm.setText(textToSave);
                //floorInEntry.requestFocus();;
            }
        });


        Scene sc = new Scene(g,500,600);
        primaryStage.setScene(sc);


        System.out.println(textToSave);
        primaryStage.show();
    }

    @Override
    public void fullyAutomated(String[] lTexts, String[] lConfTexts, String[] txtFldText) {
        
    }

    public static void main(String[] args) {
        launch(args);
    }
}

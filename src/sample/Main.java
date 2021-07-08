package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;
import javafx.scene.layout.StackPane;
import javafx.scene.control.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Main extends Application implements Fields{


    public int fieldsAdded = 0;
    public String textToSave = "Nothing";
    public int fp, pp, totalP;
    public Boolean DEFAULTS = false;


    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");

        ArrayList<FormRow> entryRows = new ArrayList<>();
        //creating textfields
        TextField txtField;

        LocalDateTime ldt = LocalDateTime.now();
        DateTimeFormatter formatToSave = DateTimeFormatter.ofPattern("MMddyyyy");
        //save it with slashes, it will not be touched
        DateTimeFormatter formatToDislay = DateTimeFormatter.ofPattern("MM/dd/yyyy");

        String timeString = ldt.format(formatToDislay);

        String defLabels[] = {"Today's Date", "FP Tasks Left", "PP Tasks Left", "Inbounds Scheduled",
                "# of People", "FlOOR loaded inbounds scheduled", "Railcars", "Pallets on Floor"};
        String defTextEntry[] = {timeString, "# of FP Tasks Left", "# of PP Tasks Left", "# of Scheduled",
                "# of People", "# of scheduled", "# of Cars", "# of Pallets"};
        String defConfLabels[] = {"-----", "-----", "-----", "-----", "-----", "-----", "-----", "-----"};

        // create a textfield
       // TextField b = new TextField("First Name");
        //TextField c = new TextField("Last Name");
        Button defButton = new Button("Load Default Form");
        Button custButton = new Button("Load Custom Fields");
        Button submit = new Button("Submit Form");
        int rowPos = 0;

       // GridPane g = runDefaultForm(entryRows,defLabels, defTextEntry, defConfLabels);



        //Tasks

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
        g.add(defButton, 0, rowPos);
        //TilePane tp = new TilePane();
        g.setPadding(new Insets(10, 10, 10, 10));
        g.setMinSize(300, 500);
        g.setVgap(5);
        g.setHgap(5);

        rowPos++;
        //added Date Entry and labels
        //Date Label and TextField
        Label cd = new Label("Today's Date");
        Label cdConfirm = new Label("TEST");
        TextField dateEntry = new TextField(timeString);
        g.add(cd, 0, rowPos);
        g.add(dateEntry, 1, rowPos);
        g.add(cdConfirm,2,rowPos);

        rowPos++;
        cd = new Label("FP Tasks Left: ");
        cdConfirm = new Label("");
        dateEntry = new TextField("# of FP tasks");
        //Added Forward pallet
        g.add(cd, 0, rowPos);
        g.add(dateEntry, 1, rowPos);
        g.add(cdConfirm, 2, rowPos);

        rowPos++;
        //add P Pallet
        g.add(pptl, 0, rowPos);
        g.add(ppEntry, 1, rowPos);
        g.add(pptlConfirm, 2, rowPos);

        rowPos++;
        //inbounds
        g.add(ib, 0, rowPos);
        g.add(inboundEntry, 1, rowPos);
        g.add(ibConfirm, 2, rowPos);

        rowPos++;
        //outbounds
        g.add(ob, 0, rowPos);
        g.add(outboundEntry, 1, rowPos);
        g.add(obConfirm, 2, rowPos);

        rowPos++;
        //hours left
        g.add(hl, 0, rowPos);
        g.add(employeeHourEntry, 1, rowPos);
        g.add(hlConfirm, 2, rowPos);


        rowPos++;
        ObservableList<String> options =
                FXCollections.observableArrayList(
                        //this will eventually retrieve info from database
                        "John Doe",
                        "Jane Deer",
                        "John Smith"
                );
        final ComboBox comboBox = new ComboBox(options);
        g.add(comboBox, 0, rowPos);

        rowPos++;
        g.add(inSh, 0, rowPos);
        g.add(inSchedEntry, 1, rowPos);
        g.add(inShConfirm, 2, rowPos);

        rowPos++;
        g.add(flSched, 0, rowPos);
        g.add(floorInEntry, 1, rowPos);
        g.add(flSchedConfirm, 2, rowPos);

        rowPos++;
        g.add(rc, 0, rowPos);
        g.add(railcarEntry, 1, rowPos);
        g.add(rcComfirm, 2, rowPos);

        rowPos++;
        g.add(plf, 0, rowPos);
        g.add(palletEntry, 1, rowPos);
        g.add(plfConfirm, 3, rowPos);


        //UNECCESSARY BUT USE FOR REFERENCE
        /*
        //getChildren works mostly with stack pains
        //g.getChildren().add(cd);
        //g.getChildren().add(dateEntry);

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

        //OLD SCENE STUFF

        //Scene sc = new Scene(tp,600,600);
        //primaryStage.setScene(new Scene(root, 300, 275));

        //DEFUNCT EVENT HANDLERS
       /* EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
                cdConfirm.setText(dateEntry.getText());
                System.out.println(dateEntry.getText());
                textToSave = dateEntry.getText();
            }
        };*/


       //EVENT HANDlERS
       EventHandler<ActionEvent> EnterHandler = event -> {
           defButton.setText("Accepted");
           event.consume();
       };

       defButton.setOnAction(new EventHandler<ActionEvent>() {
           @Override
           public void handle(ActionEvent actionEvent) {
               DEFAULTS = true;
           }
       });

       /*
       EventHandler<ActionEvent> fieldEnterHandler = event -> {
           dateEntry.requestFocus();
           event.consume();
       };
       /*

        dateEntry.setOnAction(event -> {
            textToSave = dateEntry.getText();
            cdConfirm.setText(textToSave);
            fpEntry.requestFocus();
        });

        /*fpEntry.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                textToSave = fpEntry.getText();
                fptlConfirm.setText(textToSave);
                ppEntry.requestFocus();
            }
        });*/

        ppEntry.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                textToSave = ppEntry.getText();
                pptlConfirm.setText(textToSave);
                inboundEntry.requestFocus();
            }
        });

        inboundEntry.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                textToSave = inboundEntry.getText();
                ibConfirm.setText(textToSave);
                outboundEntry.requestFocus();
            }
        });

        outboundEntry.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                textToSave = outboundEntry.getText();
                obConfirm.setText(textToSave);
                employeeHourEntry.requestFocus();
            }
        });

        inSchedEntry.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                textToSave = inSchedEntry.getText();
                inShConfirm.setText(textToSave);
                floorInEntry.requestFocus();
            }
        });

        floorInEntry.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                textToSave = floorInEntry.getText();
                flSchedConfirm.setText(textToSave);
                railcarEntry.requestFocus();
            }
        });

        railcarEntry.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                textToSave = railcarEntry.getText();
                rcComfirm.setText(textToSave);
                palletEntry.requestFocus();
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


    public GridPane runDefaultForm(ArrayList<FormRow> allRows, String labels[], String entries[], String confirms[]){
        GridPane newGrid;
        allRows = fullyAutomated(labels, entries, confirms);
        newGrid = autoAdd(allRows);
        return newGrid;
    }

    //creates a grid pane that will be sent back to start with added fields
    public GridPane autoAdd(ArrayList<FormRow> formRows){
        int rowp = 0;
        GridPane g = new GridPane();
       // g.add(defButton, 0, rowPos);
        //TilePane tp = new TilePane();
        g.setPadding(new Insets(10, 10, 10, 10));
        g.setMinSize(300, 500);
        g.setVgap(5);
        g.setHgap(5);

        for(FormRow f: formRows){
            g.add(f.getLabel(), 0, rowp);
            g.add(f.getTextField(), 1, rowp);
            g.add(f.getConfirmLabel(), 2, rowp);

            //depending on
            rowp++;
        }
        fieldsAdded = rowp;
        return g;
    }

    //creates FormRow objects out of all strings passed and saves it in Arraylist
    //Arraylist is returned of all FormRows
   // @Override
    public ArrayList<FormRow> fullyAutomated(String[] lTexts,  String[] txtFldText, String[] lConfTexts) {
        ArrayList<FormRow> temp = new ArrayList<>();
        for(int i = 0; i < lTexts.length; i++){
            temp.add(createEntryField(lTexts[i], txtFldText[i], lConfTexts[i]));
        }
       return temp;
    }

    public FormRow createEntryField(String labelText, String entryText, String confirmText){
        FormRow temp = new FormRow(labelText, entryText, confirmText, false, "");
        return temp;
    }


   // public void getEntryAndLabel(GridPane g, final int r, final int c){

    //}
    public Node getNodeFromGrid(GridPane g, final int r, final int c){
        Node result = null;
        ObservableList<Node> childrens = g.getChildren();

        for(Node n: childrens){
            if(GridPane.getRowIndex(n) == r &&
            GridPane.getColumnIndex(n) == c){
                result = n;
                break;
            }
        }
        return result;
    }
    public void setEntryEventHandlers(GridPane g){
        int row = 0, col = 0;
        TextField curTextEntry = null;
        Label curConfLabel = null;
        for(int i = 0; i < fieldsAdded; i++){
            curTextEntry = (TextField) getNodeFromGrid(g, i, col + 1);
            curConfLabel = (Label)getNodeFromGrid(g, i, col + 2);

            //Implement EVENT HANDLER
            EventHandler
        }
        //Label confLabelFromGrid = getNodeFromGrid(g, )
    }

   /* public EventHandler getTextHandler(){
        EventHandler<ActionEvent> EnterHandler = event -> {
            txtField.setText("Accepted");
            event.consume();
        };
        return EnterHandler;
    }*/

    public static void main(String[] args) {
        launch(args);
    }

    private class FormRow{
        private Label lblTxt;
        private TextField entryText;
        private Label confTxt;
        private  String field;

        //Creates a class comprising of a row in the Form
        //Each Row has a label, text field, and comfirmation text.
        //Also option to add String denoting what type of field it is
        //i.e. Field for date
        public FormRow(String lt, String et, String ct, Boolean b, String fieldName){
            lblTxt =  new Label(lt);
            entryText = new TextField(et);
            confTxt = new Label(ct);
            if(b == true){
                field = fieldName;
            }
        }


        //returns label
        public Label getLabel(){
            return this.lblTxt;
        }

        //returns textfield
        public TextField getTextField(){
            return this.entryText;
        }

        public Label getConfirmLabel(){
            return this.confTxt;
        }

        public Label getLabel(String fieldName){
            return this.lblTxt;
        }
    }
}

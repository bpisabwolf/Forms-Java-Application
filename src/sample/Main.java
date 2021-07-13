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
import javafx.util.Pair;

import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import static java.lang.Integer.numberOfTrailingZeros;
import static java.lang.Integer.parseInt;


public class Main extends Application implements Fields{

    Boolean DEBUG = false;
    public int fieldsAdded = 0;
    public int selRow, selCol;
    public String textToSave = "Nothing";
    public int fp, pp, totalP;
    public Boolean DEFAULTS = false;
    public int totalNodes = 0;
    public int fieldsSoFar = 0;
    public int employeeFields=0;
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

       GridPane g = runDefaultForm(entryRows,defLabels, defTextEntry, defConfLabels);
       setEntryEventHandlers(g);






        Scene sc = new Scene(g,500,600);
        primaryStage.setScene(sc);


        System.out.println(textToSave);
        primaryStage.show();
    }




    //Creates a Gridpane
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
        return new FormRow(labelText, entryText, confirmText, false, "");
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
        TextField nextTextEntry = null;
        Label curConfLabel = null;
        final int[] curFields = {0};

        for(int i = 0; i < fieldsAdded; i++){
            curTextEntry = (TextField) getNodeFromGrid(g, i, col + 1);
            nextTextEntry = (TextField)getNodeFromGrid(g,i + 1, col + 1 );
            curConfLabel = (Label)getNodeFromGrid(g, i, col + 2);

            //Implement EVENT HANDLER
            TextField finalCurTextEntry = curTextEntry;
            Label finalCurConfLabel = curConfLabel;
            TextField finalNextTextEntry = nextTextEntry;
            boolean finalNode = false;
            int finalRow =  i;
            int finalCol = col + 1;

            finalCurTextEntry.setOnAction(new EventHandler<ActionEvent>() {
                /*Current problem, as of now, when trying to automate everything...
                it works well if a person presses enter ONCE and never comes back to a field.
                if they go back to a field (or skip a field) and press enter, the form breaks
                This is due to the automatization process only counting fields and assigning
                event handlers via the fields "position"
                This is not handy
                Easy/brute force method.... have a hardcoded, seperate event hander for every field
                alternative, get label when in field and choose event on that (may or may not work)
                also the alternative may not be scalable
                 */
                @Override
                public void handle(ActionEvent event) {
                    textToSave = finalCurTextEntry.getText();
                    finalCurConfLabel.setText(textToSave);
                    System.out.println(fieldsSoFar);
                    System.out.println(fieldsAdded);
                    if(fieldsSoFar < fieldsAdded-1){
                        if(fieldsSoFar == 4){
                            addEmployeeFields(g, parseInt(finalCurConfLabel.getText()), finalRow, finalCol);
                        }
                        else {
                            finalNextTextEntry.requestFocus();
                        }
                    }

                   fieldsSoFar++;
                }
            });
        }
    }

    public void addEmployeeFields(GridPane g, int numEmployee, int curRow, int curCol){
        System.out.println("Adding Employee fields");
        System.out.print("Cur Rows: " + curRow + ", and cur Col: " + curCol);
        int newAdded = 0;
        if(numEmployee < 1){
            return;
        }
        ArrayList<FormRow> savedFields = shiftFields(g, curRow);
        ArrayList<Pair<String, Double>> employeeAndHours = getEmployeeInformation();

        ArrayList<ComboBox> employeeEntrees = createComboBoxFields(employeeAndHours, numEmployee);
        for(int i = 0; i < numEmployee; i++){
            int realRow = (curRow + 1) + i;
            g.add(employeeEntrees.get(i), 0, realRow);
            g.add(new TextField("Employee Hours"), 1,realRow);
            newAdded++;
        }


        //adding the rest of the fields back


    }

    public ArrayList<ComboBox> createComboBoxFields(ArrayList<Pair<String, Double>> emp, int numEmp){
        ArrayList<ComboBox> res = new ArrayList<>();
        ArrayList<String> keys = new ArrayList<>();
        ComboBox temp;
        //getting strings from pairs, and then making lists to add to combo box
        for(Pair<String, Double> e: emp){
            keys.add(e.getKey());
        }
        for(int i = 0; i < numEmp; i++){
            temp = new ComboBox(FXCollections.observableList(keys));
            res.add(temp);
        }
        return res;
    }

    public ArrayList<Pair<String, Double>> getEmployeeInformation(){
        //Dummy example
        ArrayList<Pair<String, Double>> example = new ArrayList<>();
        example.add(new Pair<>("John Doe", 8.0));
        example.add(new Pair<>("Jane deer", 7.0));
        example.add(new Pair<>("Tom Smith", 5.5));
        example.add(new Pair<>("Mary Sue", 6.5));
        return example;
    }

    public ArrayList<FormRow> shiftFields(GridPane g, int row){
        Label l;
        TextField entry;
        Label conf;

        ArrayList<FormRow> retrievedFields = new ArrayList<>();

        System.out.println("Starting shifting fields");
        System.out.println("Current Fields added:  " + fieldsAdded);
        System.out.println("Field number at: " + row);

        for(int i = row+1; i < fieldsAdded;i++){
            l = (Label)getNodeFromGrid(g, i, 0);
            entry = (TextField)getNodeFromGrid(g, i, 1);
            conf = (Label)getNodeFromGrid(g, i, 2);
            System.out.println("Checking text: " + l.getText());
            retrievedFields.add(new FormRow(l, entry, conf));
            //deleting what is left
            System.out.println("Current i: " + i);
          //  g.getChildren().remove(0, i);
            g.getChildren().remove(l);
            g.getChildren().remove(entry);
            g.getChildren().remove(conf);
        }
        return retrievedFields;
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

        public FormRow(Label l, TextField t, Label c){
            lblTxt = l;
            entryText = t;
            confTxt = c;
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

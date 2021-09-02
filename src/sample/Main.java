package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
//import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
//import javafx.scene.layout.TilePane;
import javafx.stage.Popup;
import javafx.stage.Stage;
//import javafx.scene.layout.StackPane;
import javafx.scene.control.*;
import javafx.util.Pair;

//import java.lang.reflect.Array;
import java.text.Normalizer;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

//import static java.lang.Integer.numberOfTrailingZeros;
import static java.lang.Integer.parseInt;


public class Main extends Application implements Fields{

    //GLOBAL VARIABLES
 //   Boolean DEBUG = false;//For debugging purposes
    public int fieldsAdded = 0;//Keeps track of how many fields/rows were added to form. Useful in any function
    public int selRow, selCol;//Unused, Consider deleting
    public String textToSave = "Nothing";//Text To Save that will be used by the entry fields
    public int fp, pp, totalP;
 //   public Boolean DEFAULTS = false;
  //  public int totalNodes = 0;
    public int fieldsSoFar = 0;
    public final int DEFAULT_FIELDS = 8;
    public int employeeFields = 0;
    String employeeLabel = "# of Employees Working";//String for employees working field, to keep track of database use
    String teminationLabel = "Pallets on Floor";//Very last label, act as a termination label
    public TextField userSelectedTextField;
    public Label userSelectedLabel;
    public Label userSelectedConfLabel;
    public TextField userSelectedNextTextEntry;
    public int[] userSelectedCoordinates;
    public String[] savedInformation = new String[DEFAULT_FIELDS];

    public int rowForEmployees = 0;

    @Override
    public void start(Stage primaryStage) throws Exception{
        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Warehouse Form");

        ArrayList<FormRow> entryRows = new ArrayList<>();
        //creating textfields
        //TextField txtField;

        LocalDateTime ldt = LocalDateTime.now();
        DateTimeFormatter formatToSave = DateTimeFormatter.ofPattern("MMddyyyy");
        //save it with slashes, it will not be touched
        DateTimeFormatter formatToDislay = DateTimeFormatter.ofPattern("MM/dd/yyyy");

        String timeString = ldt.format(formatToDislay);




        String[] defLabels = {"Today's Date", "FP Tasks Left", "PP Tasks Left", "Inbounds Scheduled",
                employeeLabel, "FlOOR loaded inbounds scheduled", "Railcars", "Pallets on Floor"};
        String[] defTextEntry = {timeString, "# of FP Tasks Left", "# of PP Tasks Left", "# of Scheduled",
                "# of People", "# of scheduled", "# of Cars", "# of Pallets"};
        String[] defConfLabels = {"-----", "-----", "-----", "-----", "-----", "-----", "-----", "-----"};

        // create a textfield
       // TextField b = new TextField("First Name");
        //TextField c = new TextField("Last Name");
       // Button defButton = new Button("Load Default Form");
      //  Button custButton = new Button("Load Custom Fields");
      //  Button submit = new Button("Submit Form");
      //  int rowPos = 0;

       GridPane g = runDefaultForm(entryRows,defLabels, defTextEntry, defConfLabels);
       final Scene sc = new Scene(g,500,600);


        /*
        Button button = new Button("popup");

        // create a tile pane
        //TilePane tilepane = new TilePane();

        // create a label
        Label label = new Label("This is a Popup");

        // create a popup
        Popup popup = new Popup();

        // set background
        label.setStyle(" -fx-background-color: white;");

        // add the label
        popup.getContent().add(label);

        // set size of label
        label.setMinWidth(80);
        label.setMinHeight(50);

        // set auto hide
        popup.setAutoHide(true);

        // action event
        EventHandler<ActionEvent> event =
                new EventHandler<ActionEvent>() {
                    public void handle(ActionEvent e)
                    {
                        if (!popup.isShowing())
                            popup.show(primaryStage);
                    }
                };


        // when button is pressed
        button.setOnAction(event);

        // add button
        g.getChildren().add(button);

         */

        //setEntryEventHandlers(g, sc, primaryStage, entryRows);
        primaryStage.setScene(sc);


        //System.out.println(textToSave);
        primaryStage.show();
    }


    //Creates a Gridpane, ran first
    public GridPane runDefaultForm(ArrayList<FormRow> allRows, String labels[], String entries[], String confirms[]){
        GridPane newGrid;
        allRows = fullyAutomated(labels, entries, confirms); //
        newGrid = autoAdd(allRows);//goes to
        return newGrid;
    }

    //creates FormRow objects out of all strings passed and saves it in Arraylist of FormRow objects
    //Takes a lists of strings for labels, textfields, and initial confirmation feels respectively
    //Arraylist is returned of all FormRow objects (Label, TextField, Label)
    //see FormRow object declaration at bottom of file for more info
    //returns the resulting Arraylist of FormRow objects
    // @Override
    public ArrayList<FormRow> fullyAutomated(String[] lTexts,  String[] txtFldText, String[] lConfTexts) {
        ArrayList<FormRow> temp = new ArrayList<>();
        for(int i = 0; i < lTexts.length; i++){
            temp.add(createEntryField(lTexts[i], txtFldText[i], lConfTexts[i]));//look at FormRow object
        }
        return temp;//filled arraylist of FormRow objects
    }

    //creates a grid pane that will be sent back to start with added fields
    //grid pane add nodes from FormRow object passed in
    //In this version, event handlers will be sent in
    public GridPane autoAdd(ArrayList<FormRow> formRows){
        int rowp = 0;
        GridPane g = new GridPane();
       // g.add(defButton, 0, rowPos);
        //TilePane tp = new TilePane();
        g.setPadding(new Insets(10, 10, 10, 10));
        g.setMinSize(300, 500);
        g.setVgap(5);
        g.setHgap(5);


        ListIterator<FormRow> li = formRows.listIterator();
        FormRow nextOne;
        //
        while(li.hasNext()){
            FormRow cur = li.next();
            g = addNode(g,cur, rowp);
            cur.setCoordinates(0, 1, 2, rowp);
            if(li.hasNext()){
                nextOne = li.next();
            }
            else{
                nextOne = null;
            }
            setEventListenerIndividually(g, g.getScene(),cur, nextOne, rowp);
            if(nextOne != null) {
                li.previous();
            }

            rowp++;
        }

        fieldsAdded = rowp;
        return g;
    }

    public GridPane addNode(GridPane g, FormRow cur, int r){
        g.add(cur.getLabel(), 0, r);
        g.add(cur.getTextField(), 1, r);
        g.add(cur.getConfirmLabel(), 2, r);
        return g;
    }

    public GridPane addEvent(GridPane g, Node n){

        return null;
    }



    public FormRow createEntryField(String labelText, String entryText, String confirmText){
        return new FormRow(labelText, entryText, confirmText, false, "");
    }


    //WHY DOES FR.GETY FAIL? SHOULD IT NOT GET THE Y POS EVERYTIME (SAME ONE). IT BREAKS WHEN ADDING EMPLOYEE FIELDS AHGAIN A
    //AFTER ONE TIME
    //Attempt at creating an event listener one by one, depending on what the TextField is (before it is added to the GridPane)
    //Since different textfields perform slightly differently, better to set up like this
    private void setEventListenerIndividually(GridPane g, Scene sc, FormRow fr, FormRow nextFr, int r){
        //int row = 0, col = 0;
        Label curLabel = null;
        TextField curTextEntry = null;
        TextField nextTextEntry = null;
        //TextField userSelectedNextTextEntry = null;
        Label curConfLabel = null;
       // Label curLabel = (Label)getNodeFromGrid(g, r, 0);//Gets Label from coordinates;
        curLabel = fr.getLabel();
        curTextEntry = fr.getTextField();
        curConfLabel = fr.getConfirmLabel();
        if(nextFr != null) {
            nextTextEntry = nextFr.getTextField();
        }

        if((curLabel.getText()).equals(employeeLabel)){
            //Do routine that adds nodes
            System.out.println("First option succeeds");
            TextField finalCurTextEntry = curTextEntry;
            curTextEntry.setOnKeyPressed(evt -> {
                if (evt.getCode().equals(KeyCode.ENTER)) {
                    int constantRow;
                    System.out.println("entered employees");
                    String entered = finalCurTextEntry.getText();
                    //if(isNaN(Integer.parseInt(finalCurTextEntry.getText()))){

                    //}
                    try{
                        employeeFields = Integer.parseInt(finalCurTextEntry.getText());
                        fieldsSoFar++;
                    }
                    catch (Exception e){
                        System.out.println("************************************************");
                        System.out.println("Error with EMPLOYEE NUMBER, expected integer but error: " + e.toString());
                        System.out.println("************************************************");
                    }
                    finally{

                    }

                    //because shit might break
                    try{

                        System.out.println("Adding employees at row " + fr.getY());
                        constantRow = fr.getY();
                        //rearagning fields
                        System.out.println("Employee Fields is " + employeeFields);
                        System.out.println("Constant row is " + constantRow);
                        System.out.println("Current row is " + (constantRow + employeeFields));
                       // fieldsAdded = constantRow + employeeFields;
                        System.out.println("TOTAL FIELDS ADDED AS OF RIGHT NOW IS: " + fieldsAdded);
                        //ArrayList<FormRow> retrieved = shiftFields(g,(constantRow + employeeFields));
                        //add them back outside of employee
                        rowForEmployees = constantRow;
                        addEmployeeFields(g,employeeFields,constantRow, 2);
                        //readdedFields(g,(constantRow + employeeFields),retrieved);

                    }
                    catch (Exception e){
                        System.out.println("Error in ADDING EMPLOYEE reported row was: " + fr.getY() + ", with error" + e.toString());
                    }
                    finally {
                        constantRow = fr.getY();
                    }
                    System.out.println("");

                }
            });

        }
        else{
            //STANDARD ENTRY SAVE FIELD
            //do standard if pressed enter, move to next node
            TextField finalCurTextEntry = curTextEntry;
            TextField finalNextTextEntry = nextTextEntry;
            Label finalCurConfLabel = curConfLabel;
            // saveInfo = null;
            Boolean outerSaveInfo = false;
            //general event handler option
            //
            if(nextTextEntry != null) {
               // System.out.println("Event handler set for " + curTextEntry.getText() + ", will move to " + nextTextEntry.getText());

                curTextEntry.setOnKeyPressed(evt -> {
                            if (evt.getCode().equals(KeyCode.ENTER)) {
                                System.out.println("entered");
                                finalNextTextEntry.requestFocus();
                                finalCurConfLabel.setText(finalCurTextEntry.getText());
                            }

                        });
            }
            else{
              //  System.out.println("Event handler set for " + curTextEntry.getText() + " and is final node");
                curTextEntry.setOnKeyPressed(evt -> {
                    if (evt.getCode().equals(KeyCode.ENTER)) {
                        System.out.println("entered Final");
                    }
                });
            }
        }

    }

    //DEPRECATED
    //This function continually sets the Event handlers for every Textfield
    //If every textfield was the same, it would be much simple
    //But a certain textfield (Employees) requires dynamic readjusting

    public void setEntryEventHandlers(GridPane g, Scene sc, Stage st, ArrayList<FormRow> formRows){
        int row = 0, col = 0;
        TextField curTextEntry = null;
        TextField nextTextEntry = null;
        //TextField userSelectedNextTextEntry = null;
        Label curConfLabel = null;
        Label curLabel = null;
       // final int[] curFields = {0};


        //Iterate through every field and set event handler, goes down via row
        for(int i = 0; i < fieldsAdded; i++){
            curLabel = (Label)getNodeFromGrid(g, i, col);//Gets Label from coordinates
            curTextEntry = (TextField) getNodeFromGrid(g, i, col + 1);//Gets Entry Textfield from Coordinates
            nextTextEntry = (TextField)getNodeFromGrid(g,i + 1, col + 1 );//Gets the Next Text Entry from coordinates
            curConfLabel = (Label)getNodeFromGrid(g, i, col + 2);//

            //checking user selected field

/*
            try {
                //userSelectedLabel = (Label) getNodeFromGrid(g, userSelectedCoordinates[0], userSelectedCoordinates[1] - 1);
            }
            catch (Exception e){
                if(userSelectedLabel)
                //System.out.println("Problem: " + userSelectedTextField.getText());
                //System.out.println("Current Entry Textfield: " + userSelectedTextField.getText());
            }
            finally {
               // System.out.println("At entry field: " + userSelectedTextField.getText());
                //Platform.exit();
            }
            */

            //userSelectedNextTextEntry = (TextField)getNodeFromGrid(g, userSelectedCoordinates[0] + 1, userSelectedCoordinates[1] );
            //userSelectedConfLabel = (Label)getNodeFromGrid(g, userSelectedCoordinates[0], userSelectedCoordinates[1] + 1);


            TextField tempFocus = getFocusFromGrid(g, sc, formRows, userSelectedCoordinates);
            if(tempFocus == null){
                System.out.println("TEXTFIELD IS NULL BABY");
            }
            else{
                System.out.println("ALL IS GOOD");
            }
            try{
                System.out.println("Current Textfield" + tempFocus.getText());
            }
            catch (Exception e){
                System.out.println("********************************************************");
                System.out.println("Error getting initial Textfield: " + e);
                System.out.println("********************************************************");
            }
            finally{

            }

            //Implement EVENT HANDLER
            Label finalCurLabel = curLabel;
            TextField finalCurTextEntry = curTextEntry;
            Label finalCurConfLabel = curConfLabel;
            TextField finalNextTextEntry = nextTextEntry;
            TextField finalUserSelectedNextTextEntry = userSelectedNextTextEntry;
            //boolean finalNode = false;
            int finalRow =  i;
            int finalCol = col + 1;
            //int customAddRow = 0;
            //int customAddCol = 0;
            //Scene myScene = g.getScene();
            //int[] neededVals = {0,0};

            finalCurTextEntry.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {

                    textToSave = finalCurTextEntry.getText();
                    finalCurConfLabel.setText(textToSave);
                    System.out.println(fieldsSoFar);
                    System.out.println(fieldsAdded);


                    //IF YOU CHANGE THIS, THEN YOU MUST CHANGE STRING I
                    if((finalCurLabel.getText()).equals(employeeLabel) || (userSelectedLabel.getText()).equals(employeeLabel)){
                        if((userSelectedLabel.getText()).equals(employeeLabel)) {
                            addEmployeeFields(g, parseInt(userSelectedConfLabel.getText()), finalRow, finalCol);
                            try{
                                userSelectedNextTextEntry = (TextField)getNodeFromGrid(g, userSelectedCoordinates[0] + 1, userSelectedCoordinates[1] );
                            }
                            catch (Exception e){
                                System.out.println("********************************************************");
                                System.out.println("Unable to focus to next entry. Error: " + e.toString());
                                System.out.println("********************************************************");

                            }
                        }
                        else {
                            addEmployeeFields(g, parseInt(finalCurConfLabel.getText()), finalRow, finalCol);
                        }
                    }
                    else if(finalCurLabel.equals(teminationLabel)){
                        //FIGURE OUT HOW TO DELETE FIELDS AND ADD FIELDS FOR EMPLOYEE

                    }
                    else{
                        try{
                            finalNextTextEntry.requestFocus();
                        }
                        catch(Exception e){
                            System.out.println("********************************************************");
                            System.out.print("Cannot Complete: Moving to Next Field");
                            System.out.print("Maybe Field Doesn't exist");
                            System.out.print("Megh: " + e);
                            System.out.println("********************************************************");

                        }
                        finally {
                            Popup notice = PopUpMessage();
                            EventHandler<ActionEvent> pEvent =
                                    new EventHandler<ActionEvent>() {
                                        public void handle(ActionEvent e)
                                        {
                                            if (!notice.isShowing())
                                                notice.show(st);
                                        }
                                    };
                            finalCurTextEntry.setOnAction(pEvent);

                        }
                    }

                   fieldsSoFar++;
                }
            });
        }
    }


    //returns null if what was clicked was not a textfield.
    public TextField getFocusFromGrid(GridPane g, Scene sc, ArrayList<FormRow> rows, int[] v){

        Node tempNode = sc.getFocusOwner();
        //if(tempNode instanceof  TextField){


        if(g.getChildren().contains(tempNode)){
            System.out.println("FOUND THIS");
        }
        else{
            System.out.println("NOTHING WAS FOUND");
        }


            //v[0] = GridPane.getRowIndex(tempNode);
            //v[1] = GridPane.getColumnIndex(tempNode);
        return (TextField)tempNode;
        //}
        //else{
        //    return null;
        //}
    }

    public Node getNodeFromGrid(GridPane g, final int row, final int col){
        Node result = null;
        ObservableList<Node> childrens = g.getChildren();

        for(Node n: childrens){
            if(GridPane.getRowIndex(n) == row &&
                    GridPane.getColumnIndex(n) == col){
                result = n;
                break;
            }
        }
        return result;
    }

    public Node getNodeFromGrid(GridPane g, Node n){
        Node result = null;
        ObservableList<Node> childrens = g.getChildren();

        for(Node nc: childrens){

        }
        return null;
    }

    /*
    public Label getFocusLabelFromGrid(GridPane g, int[] v){
        Scene myScene = g.getScene();
        //TextField userField = tf;
        Node tempnode = getNodeFromGrid(g, v[0],v[1]);
        return n;
    }*/


    public Popup PopUpMessage(){
        Label label = new Label("If You Are Done With the Form,\n please press Submit");

        // create a popup
        Popup popup = new Popup();

        // set background
        label.setStyle(" -fx-background-color: white;");

        // add the label
        popup.getContent().add(label);

        // set size of label
        label.setMinWidth(80);
        label.setMinHeight(50);

        // set auto hide
        popup.setAutoHide(true);
        return popup;
    }

    public void addEmployeeFields(GridPane g, int numEmployee, int curRow, int curCol){
        System.out.println("-----------Adding EMPLOYEE fields------------");
        System.out.println("Cur Rows: " + curRow + ", and cur Col: " + curCol);
        int newAdded = 0;
        if(numEmployee < 1){
            return;
        }
        ArrayList<FormRow> savedFields = shiftFields(g, curRow);
        readdedFields(g,curRow + numEmployee,savedFields);
        ArrayList<Pair<String, Double>> employeeAndHours = getEmployeeInformation();

        ArrayList<ComboBox> employeeEntrees = createComboBoxFields(employeeAndHours, numEmployee);
        for(int i = 0; i < numEmployee; i++){
            int realRow = (curRow + 1) + i;
            g.add(employeeEntrees.get(i), 0, realRow);
            g.add(new TextField("Employee Hours"), 1,realRow);
            newAdded++;
        }
        System.out.println("-----------------EMPLOYEE DONE--------------");


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

    public void readdedFields(GridPane g, int rowToAdd, ArrayList<FormRow> toAdd){
        ListIterator<FormRow> li = toAdd.listIterator();
        FormRow cur;
        while(li.hasNext()) {
            cur = li.next();
            g = addNode(g, cur, rowToAdd+1);
            System.out.println("Added Node to: " + rowToAdd + 1);
            rowToAdd++;
        }
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

    private static class FormRow{
        private Label lblTxt;
        private TextField entryText;
        private Label confTxt;
        private String field;
        private int xCoorLabel;
        private int xCoorEntry;
        private int xCoorConf;
        private int ycoordinate;

        //Creates a class comprising of a row in the Form
        //Each Row has a label, text field, and comfirmation text.
        //Also option to add String denoting what type of field it is
        //i.e. Field for date
        public FormRow(String lt, String et, String ct, Boolean b, String fieldName){
            lblTxt =  new Label(lt);
            entryText = new TextField(et);
            confTxt = new Label(ct);
            if(b){
                field = fieldName;
            }
        }

        public FormRow(Label l, TextField t, Label c){
            lblTxt = l;
            entryText = t;
            confTxt = c;
        }


        //CREATE FUNCTION IN ORIGINAL CALL LOCATION TO LOOP THROUGH FORMS COMPARING
        //check if node passed in matches node from row
        public boolean compareRowEntryNode(Node n){
            TextField tf = (TextField)n;
            String curText = entryText.getText();
            String nodeText = tf.getText();

            if(curText.equals(nodeText)){
                System.out.println("Found match between: " + curText + " and " + nodeText);
                return true;
            }
            else{
                System.out.println("Not found");
                return false;
            }

        }

        //sets only y coordinate for a row of nodes
        public void setYcoordinate(int y){
            this.ycoordinate = y;
        }

        //sets x and y coordinates for object, sets xcoordinates for nodes in row
        public void setCoordinates(int xL, int xE, int xC, int y){
            this.xCoorLabel = xL;
            this.xCoorEntry = xE;
            this.xCoorConf = xC;
            this.ycoordinate = y;
        }

        public int getY(){
            return this.ycoordinate;
        }



       /* public TextField getPassedNode(Node n){
            return entryText;
        }*/


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

    private static class RowAndColInfo{
        private int row;
        private int col;
        public RowAndColInfo(int r, int c){
            this.row = r;
            this.col = c;
        }

        public int getRow()
        {
            return this.row;
        }
        public int getCol()
        {
            return this.col;
        }
    }
}

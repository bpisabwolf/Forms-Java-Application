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

import java.util.ArrayList;

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


        //How juan explains it.
        There is a schedule for people for everyday
        The idea is that the form sends date information to a database,
        where it can retrieve who is working there.
        From there, it will autofill fields for the name of employees
        whoever is filling the form can manually put hours left after each employee
        (maybe have employees be selected from a drop down if can't autofill)
    TITLE TOMORROW --------------------------------------------
    Inbounds scheduled __________ - Integer
    FLOOR loaded inbounds scheduled __________ - Integer
    Railcars ______________ - Integer
    Full Pallet picks _____________ - Integer
    Partial Pallet Picks ____________ - Integer
    Palletes on Floor ___________ - Integer
     */

public interface Fields {

    ArrayList<Label> labels = new ArrayList<>();
    ArrayList<Label> confLabels = new ArrayList<>();
    ArrayList<TextField> dataEntry = new ArrayList<>();


    private void createLabels(){
        //Date Label and TextField
        labels.add(new Label("Today's Date"));
        confLabels.add(new Label("TEST"));
        dataEntry.add(new TextField("dd/mm/yyyy"));
        //Tasks
        labels.add(new Label("FP Tasks Left: "));
        confLabels.add(new Label(""));
        dataEntry.add(new TextField("# of FP tasks"));


        labels.add(new Label("PP Tasks Left"));
        confLabels.add(new Label(""));
        dataEntry.add(new TextField("# of PP Tasks"));
        //Total tasks will be calculated later
        Label totalTasksReported;

        //Inbount and outbound entries
        labels.add(new Label("Inbound Left to Unload: "));
        confLabels.add(new Label(""));
        dataEntry.add(new TextField("# to unload"));

        labels.add(new Label("Outbound Left o Load: "));
        confLabels.add(new Label(""));
        new TextField("# to load");

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
    }

    //public void IterfacefullyAutomated(String lTexts[], String lConfTexts[], String txtFldText[]);


}

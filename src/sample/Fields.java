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

public class Fields {

    private ArrayList<Label> labels = new ArrayList<>();
    private ArrayList<TextField> dataEntry = new ArrayList<>();
    public Fields(){

    }

    private void createLabels(){
        Label Date = new Label("Today's Date");
        labels.add(Date);
        Label Tasks = new Label("Tasks Left");
    }

}

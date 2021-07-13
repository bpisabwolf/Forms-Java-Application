package sample;
import javafx.beans.property.*;
public class FormResult {
    private final StringProperty date =  new SimpleStringProperty(this, "Date");
    public StringProperty dateProperty(){
        return date;
    }
    public final String getDate(){
        return dateProperty().get();
    }
    public final void setDate(String date){
        dateProperty().set(date);
    }

    private final IntegerProperty fpTasks = new SimpleIntegerProperty(this,"fpTasks");
    public IntegerProperty fpTasksProperty(){
        return fpTasks;
    }
    public final int getFPTasks(){
        return fpTasksProperty().get();
    }
    public final void setFpTasks(int fpTasks){
        fpTasksProperty().set(fpTasks);
    }

    private final IntegerProperty ppTasks = new SimpleIntegerProperty(this,"ppTasks");
    public IntegerProperty ppTasksProperty(){
        return ppTasks;
    }
    public final int getPPTasks(){
        return ppTasksProperty().get();
    }
    public final void setPpTasks(int ppTasks){
        ppTasksProperty().set(ppTasks);
    }

    private final IntegerProperty totalTasks = new SimpleIntegerProperty(this, "totalTasks");
    public IntegerProperty totalTasksProperty(){
        return totalTasks;
    }
    public final int getTotalTasks(){
        return totalTasksProperty().get();
    }
    public final void setTotalTasks(int totalTasks){
        totalTasksProperty().set(totalTasks);
    }

    private final IntegerProperty inboundsToUnload = new SimpleIntegerProperty(this, "inboundsToUnload");
    public IntegerProperty inboundsToUnloadProperty(){
        return inboundsToUnload;
    }
    public final int getInboundsToUnload(){
        return inboundsToUnloadProperty().get();
    }
    public final void setInboundsToUnload(int intou){
        inboundsToUnloadProperty().set(intou);
    }

    private final IntegerProperty outboundsToLoad = new SimpleIntegerProperty(this, "outboundsToLoad");
    public IntegerProperty outboundsToLoadProperty(){
        return outboundsToLoad;
    }
    public final int getOutboundsToLoad(){
        return outboundsToLoadProperty().get();
    }
    public final void setOutboundsToLoad(int otl){
        inboundsToUnloadProperty().set(otl);
    }

    private final IntegerProperty employeeWorkingToday = new SimpleIntegerProperty(this, "employeeWorkingToday");
    public IntegerProperty employeeWorkingTodayProperty(){
        return employeeWorkingToday;
    }
    public final int getEmployeeWorkingToday(){
        return employeeWorkingTodayProperty().get();
    }
    public final void setEmployeeWorkingToday(int empWorking){
        employeeWorkingTodayProperty().set(empWorking);
    }

    private final IntegerProperty inboundsScheduled = new SimpleIntegerProperty(this,"inboundsScheduled");
    public IntegerProperty inboundsScheduledProperty(){
        return inboundsScheduled;
    }
    public final int getInboundsScheduled(){
        return inboundsScheduledProperty().get();
    }
    public final void setInboundsScheduled(int is){
        inboundsScheduledProperty().set(is);
    }

    private final IntegerProperty floorLoadedInScheduled =  new SimpleIntegerProperty(this, "floorScheduled");
    public IntegerProperty floorSheduledProperty(){
        return floorLoadedInScheduled;
    }
    public final int getFloorScheduled(){
        return floorSheduledProperty().get();
    }
    public final void setFloorScheduled(int fs){
        floorSheduledProperty().set(fs);
    }

    private final IntegerProperty numRailcars = new SimpleIntegerProperty(this, "numRailcars");
    public IntegerProperty numRailcarsProperty(){
        return numRailcars;
    }
    public final int getNumRailcars(){
        return numRailcarsProperty().get();
    }
    public final void setNumRailcars(int nr){
        numRailcarsProperty().set(nr);
    }

    private final IntegerProperty palletsOnFloor = new SimpleIntegerProperty(this, "palletsOnFloor");
    public IntegerProperty palletsOnfloorProperty(){
        return palletsOnFloor;
    }
    public final int getPalletsOnFloor(){
        return palletsOnfloorProperty().get();
    }
    public final void setPalletsOnFloor(int pof){
        palletsOnfloorProperty().set(pof);
    }

    public FormResult(){}

    public FormResult(String date,
                      int fp, int pp,
                      int itou, int otl,
                      int ew, int ins, int fls,
                      int pof){
        setDate(date);
        setFpTasks(fp);
        setPpTasks(pp);
        setInboundsToUnload(itou);
        setOutboundsToLoad(otl);
        setEmployeeWorkingToday(ew);
        setInboundsScheduled(ins);
        setFloorScheduled(fls);
        setPalletsOnFloor(pof);
    }

}

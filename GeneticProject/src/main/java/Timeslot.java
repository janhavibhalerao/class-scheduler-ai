public class Timeslot {



    //declaring variables
    private final int timeslotId;
    private final String timeslot;



    //initialising variables
    public Timeslot(int timeslotId, String timeslot){
        this.timeslotId = timeslotId;
        this.timeslot = timeslot;
    }



    public int getTimeslotId(){
        return this.timeslotId;
    }

    public String getTimeslot(){
        return this.timeslot;
    }
}

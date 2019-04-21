public class Professor {


    //declaring variables
    private final int professorId;
    private final String professorName;
    private int preferedroom = 0;
    private int preferedtime = 0;



    //initializing variables

    public Professor(int professorId, String professorName){
        this.professorId = professorId;
        this.professorName = professorName;
    }

    public Professor(int professorId, String professorName, int preferedroom){
        this.professorId = professorId;
        this.professorName = professorName;
        this.preferedroom = preferedroom;
    }

    public Professor(int professorId, String professorName, int preferedroom, int preferedtime){
        this.preferedroom = preferedroom;
        this.professorName = professorName;
        this.professorId = professorId;
        this.preferedtime = preferedtime;
    }
    public int getProfessorId(){
        return this.professorId;
    }

    public String getProfessorName(){
        return this.professorName;
    }

    public int getPreferedroom(){
        return  this.preferedroom;
    }

    public int getPreferedtime(){return this.preferedtime;}

}

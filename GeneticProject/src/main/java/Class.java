public class Class
{

    //declaring variables

    private final int classId;
    private final int studentGroupId;
    private final int courseId;
    private int professorId;
    private int timeslotId;
    private int roomId;

    //chromosome

    public Class(int classId, int studentGroupId, int courseId) {
        this.classId = classId;
        this.courseId = courseId;
        this.studentGroupId = studentGroupId;
    }

    //getters and setters

    public void setProfessor(int professorId) {
        this.professorId = professorId;
    }

    public void setTimeslot(int timeslotId) {
        this.timeslotId = timeslotId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public int getClassId() {
        return this.classId;
    }

    public int getGroupId() {
        return this.studentGroupId;
    }

    public int getCourseId() {
        return this.courseId;
    }

    public int getProfessorId() {
        return this.professorId;
    }

    public int getTimeslotId() {
        return this.timeslotId;
    }

    public int getRoomId() {
        return this.roomId;
    }

}

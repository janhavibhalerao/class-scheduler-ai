import java.util.List;

public class ScheduleAlgo {


    public static Schedule initializeSchedule() {

        Schedule schedule = new Schedule();


        schedule.addRoom(1, "A", 15);
        schedule.addRoom(2, "B", 30);
        schedule.addRoom(4, "C", 20);
        schedule.addRoom(5, "D", 25);
        schedule.addRoom(3, "E", 20);


        schedule.addTimeslot(1, "Monday 9:00 - 11:00");
        schedule.addTimeslot(2, "Monday 11:00 - 13:00");
        schedule.addTimeslot(3, "Monday 13:00 - 15:00");
        schedule.addTimeslot(4, "Tuesday 9:00 - 11:00");
        schedule.addTimeslot(5, "Tuesday 11:00 - 13:00");
        schedule.addTimeslot(6, "Tuesday 13:00 - 15:00");
        schedule.addTimeslot(7, "Wednesday 9:00 - 11:00");
        schedule.addTimeslot(8, "Wednesday 11:00 - 13:00");
        schedule.addTimeslot(9, "Wednesday 13:00 - 15:00");
        schedule.addTimeslot(10, "Thursday 9:00 - 11:00");
        schedule.addTimeslot(11, "Thursday 11:00 - 13:00");
        schedule.addTimeslot(12, "Thursday 13:00 - 15:00");
        schedule.addTimeslot(13, "Friday 9:00 - 11:00");
        schedule.addTimeslot(14, "Friday 11:00 - 13:00");
        schedule.addTimeslot(15, "Friday 13:00 - 15:00");


        schedule.addProfessor(1, "Kal Bugrara", 2,8);
        schedule.addProfessor(2, "Robin Hilyard",2);
        schedule.addProfessor(3, "Yusuf Ozbek",3);
        schedule.addProfessor(4, "Vishal Chawla",1);
        schedule.addProfessor(5, "Amuthan", 5, 6);
        schedule.addProfessor(6, "Tejas Parikh", 1, 10);


        schedule.addCourse(1, "AA", "Algorithm", new int[] { 1, 2 ,4});
        schedule.addCourse(2, "DD", "Database", new int[] { 1, 3 ,5});
        schedule.addCourse(3, "CC", "Cloud Computing", new int[] { 1, 2 ,5});
        schedule.addCourse(4, "WD", "Web Development", new int[] { 3, 4 ,6});
        schedule.addCourse(5, "AE", "Application Engineering", new int[] { 4 });
        schedule.addCourse(6, "DS", "Data Science", new int[] { 1, 4,6 });
        schedule.addCourse(7, "BI", "Business Intelligence", new int[]{2,5,6});


        schedule.addGroup(1, 10, new int[] { 1, 3, 4 });
        schedule.addGroup(2, 30, new int[] { 2, 3, 5, 6 });
        schedule.addGroup(3, 18, new int[] { 3, 4, 5 });
        schedule.addGroup(4, 25, new int[] { 1, 4 ,7});
        schedule.addGroup(5, 20, new int[] { 2, 3, 5 });
        schedule.addGroup(6, 22, new int[] { 1, 4, 5 });
        schedule.addGroup(7, 16, new int[] { 1, 3 });
        schedule.addGroup(8, 18, new int[] { 2, 6 ,7});
        schedule.addGroup(9, 24, new int[] { 1, 6 });
        schedule.addGroup(10, 25, new int[] { 3, 4 ,7});

        return schedule;
    }


    public static void PrintClassAll(Schedule schedule){
        Class classes[] = schedule.getClasses();
        int classIndex = 1;
        for (Class bestClass : classes) {
            System.out.println("CLASS " + classIndex + ":");
            System.out.println("COURSE: " + schedule.getCourse(bestClass.getCourseId()).getCourseName());
            System.out.println("STUDENT GROUP: " + schedule.getGroup(bestClass.getGroupId()).getGroupId());
            System.out.println("ROOM: " + schedule.getRoom(bestClass.getRoomId()).getRoomNumber());
            System.out.println("PROFESSOR: " + schedule.getProfessor(bestClass.getProfessorId()).getProfessorName());
            System.out.println("TIMESLOT: " + schedule.getTimeslot(bestClass.getTimeslotId()).getTimeslot());
            System.out.println("*****************************************************************");
            classIndex++;
        }
    }



    public static List<Class> getClasses(Schedule schedule, String type, int id) {
        switch (type) {
            case "ROOM":
                return schedule.getRoomMap().get(id);
            case "PROF":
                return schedule.getProfMap().get(id);
            case "MODULE":
                return schedule.getCourseMap().get(id);
            case "GROUP":
                return schedule.getGroupMap().get(id);
            default:
                return null;
        }
    }



    public static void PrintClasses(Schedule schedule, String type, int id){
        List<Class> classes = getClasses(schedule, type, id);
        for (Class bestClass: classes){
            printClass(schedule, bestClass);
        }
    }


    public static void printClass(Schedule schedule, Class bestClass) {
        System.out.println("COURSE: " + schedule.getCourse(bestClass.getCourseId()).getCourseName());
        System.out.println("STUDENT GROUP: " + schedule.getGroup(bestClass.getGroupId()).getGroupId());
        System.out.println("CLASSROOM: " + schedule.getRoom(bestClass.getRoomId()).getRoomNumber());
        System.out.println("PROFESSOR: " + schedule.getProfessor(bestClass.getProfessorId()).getProfessorName());
        System.out.println("TIMESLOT: " + schedule.getTimeslot(bestClass.getTimeslotId()).getTimeslot());
        System.out.println("*********************************************************************");
    }


    public static void main(String[] args) {

        Schedule schedule = initializeSchedule();


        GeneticAlgorithm ga = new GeneticAlgorithm(1000, 0.01, 0.9, 2, 5);


        Population population = ga.initializingPopulation(schedule);


        ga.calcPopulation(population, schedule);


        int generation = 1;



        while (ga.isTerminating(generation, 100) == false && ga.isTerminating(population) == false) {

            System.out.println("Generation No." + generation + " Best fitness: " + population.getFittest(0).getFitness());


            population = ga.crossoverPopulation(population);


            population = ga.mutatingPopulation(population, schedule);


            ga.calcPopulation(population, schedule);

            generation++;
        }

        // Print fitness
        schedule.createClasses(population.getFittest(0));
        System.out.println();
        System.out.println("Solution found in " + generation + " generations");
        System.out.println("Final solution fitness: " + population.getFittest(0).getFitness());
        System.out.println("Clashes: " + schedule.calcClashes(100));

        PrintClassAll(schedule);

    }
}
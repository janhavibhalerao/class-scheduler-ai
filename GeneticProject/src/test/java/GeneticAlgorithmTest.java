import org.junit.Test;

import static org.junit.Assert.*;


public class GeneticAlgorithmTest {

    //checking one gene

    @Test
    public void testGeneExpression()throws Exception{
        Schedule schedule = ScheduleAlgo.initializeSchedule();
        GeneticAlgorithm ga = new GeneticAlgorithm(100, 0.01, 0.9, 2, 5);
        Population population = ga.initializingPopulation(schedule);
        ga.calcPopulation(population, schedule);
        String s1=population.getIndividual(0).toString();
        System.out.println(s1);
    }

  // evaluating population
    @Test
    public void testCalcPopulation() throws Exception{
        Schedule schedule = new Schedule();

        // adding data
        schedule.addRoom(1, "A", 15);
        schedule.addTimeslot(1,"Monday 9:00 - 11:00");
        schedule.addProfessor(1,"Kal Bugrara",1);
        schedule.addCourse(1,"AA","Algorithm", new int[]{1});
        schedule.addGroup(1,10,new int[]{1});

        GeneticAlgorithm ga = new GeneticAlgorithm(100, 0.01, 0.9, 2, 5);
        Population population = ga.initializingPopulation(schedule);
        ga.calcPopulation(population, schedule);
        int generation = 1;
        while (ga.isTerminating(generation, 1000) == false && ga.isTerminating(population) == false) {

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

        ScheduleAlgo.PrintClassAll(schedule);
        assert population.getFittest(0).getFitness() == 1.01 : "not eval";
    }


    @Test
    public void testMutating() throws Exception{
        Schedule schedule = new Schedule();
        schedule.addRoom(1, "A1", 15);
        schedule.addRoom(2, "B1", 20);
        schedule.addTimeslot(1,"Tue 9:00 - 11:00");
        schedule.addTimeslot(2,"Tue 13:00 - 15:00");
        schedule.addProfessor(1,"proftest1");
        schedule.addProfessor(2,"proftest2");
        schedule.addCourse(1,"cs1","computer science", new int[]{1,2});
        schedule.addCourse(2, "cs2", "Algrothim", new int[]{1,2});
        schedule.addGroup(1,10,new int[]{1,2});
        schedule.addGroup(2,10,new int[]{1,2});


        GeneticAlgorithm ga = new GeneticAlgorithm(2, 2, 0, 0, 5);
        Population population = ga.initializingPopulation(schedule);
        ga.calcPopulation(population, schedule);
        int generation = 1;
        String s1, s2, s1m, s2m;
        System.out.println("Before Mutation");
        s1=population.getIndividual(0).toString();
        s2=population.getIndividual(1).toString();
        System.out.println(s1);
        System.out.println(s2);

        population = ga.mutatingPopulation(population, schedule);
        System.out.println("After Mutation");
        s1m=population.getIndividual(0).toString();
        s2m=population.getIndividual(1).toString();
        System.out.println(s1m);
        System.out.println(s2m);

        ga.calcPopulation(population, schedule);

        generation++;
        schedule.createClasses(population.getFittest(0));

        System.out.println(s1.compareTo(s1m));
        System.out.println(s2.compareTo(s2m));

        assert !s2.equals(s2m) : "Mutation failed!";
    }

    //checking crossover
    @Test
    public void testCrossover() throws Exception{
        Schedule schedule = new Schedule();
        schedule.addRoom(1, "A", 15);
        schedule.addRoom(2, "B", 20);
        schedule.addTimeslot(1,"Tuesday 9:00 - 11:00");
        schedule.addTimeslot(2,"Tuesday 13:00 - 15:00");
        schedule.addProfessor(1,"Harry");
        schedule.addProfessor(2,"Larry");
        schedule.addCourse(1,"c1","Literature", new int[]{1,2});
        schedule.addCourse(2, "c2", "Poetry", new int[]{1,2});
        schedule.addGroup(1,10,new int[]{1,2});
        schedule.addGroup(2,10,new int[]{1,2});


        GeneticAlgorithm ga = new GeneticAlgorithm(10, 0, 0.9, 0, 5);
        Population population = ga.initializingPopulation(schedule);
        ga.calcPopulation(population, schedule);
        int generation = 1;

        System.out.println("Before Crossover");
        String sc1 =population.getIndividual(0).toString();
        String sc2 =population.getIndividual(1).toString();
        System.out.println(sc1);
        System.out.println(sc2);

        population = ga.crossoverPopulation(population);
        System.out.println("After Crossover");
        String s1=population.getIndividual(0).toString();
        String s2=population.getIndividual(1).toString();
        System.out.println(s1);
        System.out.println(s2);

        ga.calcPopulation(population, schedule);

        generation++;
        schedule.createClasses(population.getFittest(0));

        System.out.println(s1.compareTo(sc1));
        System.out.println(s2.compareTo(sc2));


    }


    @Test
    public void testPrintProfTable() throws Exception{
        Schedule schedule = ScheduleAlgo.initializeSchedule();

        GeneticAlgorithm ga = new GeneticAlgorithm(100, 0.01, 0.9, 2, 5);

        // TODO: Initialize population
        Population population = ga.initializingPopulation(schedule);

        // TODO: Evaluate population
        ga.calcPopulation(population, schedule);


        int generation = 1;


        while (ga.isTerminating(generation, 200) == false && ga.isTerminating(population) == false) {

            population = ga.crossoverPopulation(population);


            population = ga.mutatingPopulation(population, schedule);


            ga.calcPopulation(population, schedule);

            generation++;
        }
        schedule.createClasses(population.getFittest(0));
        ScheduleAlgo.PrintClasses(schedule, "PROF", 3);
    }


    @Test
    public void testPrintGroupTable() throws Exception{
        Schedule schedule = ScheduleAlgo.initializeSchedule();

        GeneticAlgorithm ga = new GeneticAlgorithm(100, 0.01, 0.9, 2, 5);


        Population population = ga.initializingPopulation(schedule);


        ga.calcPopulation(population, schedule);


        int generation = 1;


        while (ga.isTerminating(generation, 300) == false && ga.isTerminating(population) == false) {

            population = ga.crossoverPopulation(population);


            population = ga.mutatingPopulation(population, schedule);


            ga.calcPopulation(population, schedule);

            generation++;
        }
        schedule.createClasses(population.getFittest(0));
        ScheduleAlgo.PrintClasses(schedule, "GROUP", 3);
    }


    @Test
    public void testPrintRoomTable() throws Exception{
        Schedule schedule = ScheduleAlgo.initializeSchedule();

        GeneticAlgorithm ga = new GeneticAlgorithm(100, 0.01, 0.9, 2, 5);


        Population population = ga.initializingPopulation(schedule);


        ga.calcPopulation(population, schedule);


        int generation = 1;


        while (ga.isTerminating(generation, 400) == false && ga.isTerminating(population) == false) {

            population = ga.crossoverPopulation(population);


            population = ga.mutatingPopulation(population, schedule);


            ga.calcPopulation(population, schedule);

            generation++;
        }
        schedule.createClasses(population.getFittest(0));
        ScheduleAlgo.PrintClasses(schedule, "ROOM", 3);
    }
}
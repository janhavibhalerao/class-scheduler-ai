public class Individual {

    //declaring variables
    private int[] chromosome;
    private double fitness = -1;

    //individual chromosome

    public Individual(int[] chromosome) {

        this.chromosome = chromosome;
    }

    //random individual

    public Individual(int chromosomeLength) {

        int[] individual;
        individual = new int[chromosomeLength];

        for (int gene = 0; gene < chromosomeLength; gene++) {
            individual[gene] = gene;
        }

        this.chromosome = individual;
    }

    public Individual(Schedule schedule) {

        int numClasses = schedule.getNumClasses();
        int chromosomeLength = numClasses * 3;
        int newChromosome[] = new int[chromosomeLength];
        int chromosomeIndex = 0;

        // Loop through studet groups and each courses
        for (Studentgroup group : schedule.getGroupsAsArray()) {

            for (int courseId : group.getCourseIds()) {

                int timeslotId = schedule.getRandomTimeslot().getTimeslotId();
                newChromosome[chromosomeIndex] = timeslotId;
                chromosomeIndex++;


                int roomId = schedule.getRandomRoom().getRoomId();
                newChromosome[chromosomeIndex] = roomId;
                chromosomeIndex++;

                Course course = schedule.getCourse(courseId);
                newChromosome[chromosomeIndex] = course.getRandomProfessorId();
                chromosomeIndex++;



            }
        }

        this.chromosome = newChromosome;
    }

    public int[] getChromosome() {
        return this.chromosome;
    }

    public int getChromosomeLength() {
        return this.chromosome.length;
    }

    public void setGene(int offset, int gene) {
        this.chromosome[offset] = gene;
    }

    public int getGene(int offset) {
        return this.chromosome[offset];
    }

    public void setFitness(double fitness) {
        this.fitness = fitness;
    }

    public double getFitness() {
        return this.fitness;
    }

    public String toString() {
        String output = "";
        for (int gene = 0; gene < this.chromosome.length; gene++) {
            output += this.chromosome[gene];
        }
        return output;
    }

    public boolean containsGene(int gene) {
        for (int i = 0; i < this.chromosome.length; i++) {
            if (this.chromosome[i] == gene) {
                return true;
            }
        }
        return false;
    }
}

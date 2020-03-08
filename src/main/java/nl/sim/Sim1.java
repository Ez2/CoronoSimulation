package nl.sim;

import java.util.ArrayList;
import java.util.List;

public class Sim1 {
    private final static int DAYS = 365;
    private final static int X_PERCENT_DIES = 3;
    private final static PoorMansRandom IS_IN_X_PERCENT_THAT_DIES = new PoorMansRandom(X_PERCENT_DIES);
    private static final int DUTCH_POPULATION_SIZE = 17_000_000;

    private static Person[] persons = new Person[DUTCH_POPULATION_SIZE];

    private static int totalDeaths = 0;
    private static int noLongerInfectedTotal = 0;

    public static void main(String[] args) {

        /*
        Rules:
        - a person gets sick and contagious after 1 week
        - a person infects 2 other persons
        - after a month, a person has 3% chance of dying or recovering
        - if on a day more
         */


        Person case0 = new Person(0);
        persons[0] = case0;

        int d=0;
        List<Person> newlyInfected = new ArrayList<>();
        int newlyInfectedNumber = 0;
        int noLongerInfected = 0;
        int infected = 0;
        int totalAffected;
        for (d=1; d<DAYS; d++) {
            int previousRoundInfected = infected;
            for (int i=0; i<=previousRoundInfected; i++) { //Person person : persons) {
                if (persons[i].isAlive() && persons[i].isContagious()) {
                    if (persons[i].wasInfectedXDaysAgoFromToday(7, d)) {
                        Person newCase = new Person(d);
                        newlyInfectedNumber++;
                        newlyInfected.add(newCase);
                    } else if (persons[i].wasInfectedXDaysAgoFromToday(7, d)) {
                        Person newCase = new Person(d);
                        newlyInfectedNumber++;
                        newlyInfected.add(newCase);
                    } else if (persons[i].wasInfectedXDaysAgoFromToday(8, d)) {
                        Person newCase = new Person(d);
                        newlyInfectedNumber++;
                        newlyInfected.add(newCase);
                    } else if (persons[i].wasInfectedXDaysAgoFromToday(9, d)) {
                        Person newCase = new Person(d);
                        newlyInfectedNumber++;
                        newlyInfected.add(newCase);
                    } else if (persons[i].wasInfectedXDaysAgoFromToday(30, d)) {
                        if (IS_IN_X_PERCENT_THAT_DIES.getNext()) {
                            persons[i].setAlive(false);
                        }
                        persons[i].setContagious(false);
                        noLongerInfected++;
                    }
                }
            }
            System.out.printf("%d\tAdding %d newly infected\n", d, newlyInfectedNumber);
            for (Person p : newlyInfected) {
                if (infected < DUTCH_POPULATION_SIZE - 1) {
                    persons[++infected] = p;
                } else {
                    break;
                }
            }
            System.out.printf("%d\tRemoving %d no longer infected\n\n", d, noLongerInfected);
            noLongerInfectedTotal += noLongerInfected;
            newlyInfectedNumber = 0;
            noLongerInfected = 0;
            newlyInfected.clear();
            totalAffected = infected + noLongerInfectedTotal;
            if (totalAffected > DUTCH_POPULATION_SIZE) {
                System.out.printf("Total number infected and no longer infected exceeds Dutch population size %d\n\n", totalAffected);
                for (Person person : persons) {
                    if (IS_IN_X_PERCENT_THAT_DIES.getNext()) {
                        person.setAlive(false);
                    }
                }
                break;
            }
        }

        System.out.println("Calculating total deceased");

        for (Person person : persons) {
            if (!person.isAlive()) totalDeaths+=1;
        }

        System.out.printf("\nTotal who got sick after %d days: %d\n", d, DUTCH_POPULATION_SIZE);
        System.out.printf("Total deaths after %d days: %d\n", d, totalDeaths);

    }

//    private static boolean isInXPercentThatSurvives() {
//        float nextFloat = RANDOM_NUMBER_GENERATOR.nextFloat();
//        int percentage = Math.round(nextFloat * 100);
//        return percentage > X_PERCENT_DIES;
//    }



}

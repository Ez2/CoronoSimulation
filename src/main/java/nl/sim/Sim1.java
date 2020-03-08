package nl.sim;

import java.util.ArrayList;
import java.util.List;

public class Sim1 {
    private final static int DAYS = 365;
    private final static int X_PERCENT_DIES = 3;
    private final static PoorMansRandom IS_IN_X_PERCENT_THAT_DIES = new PoorMansRandom(X_PERCENT_DIES);
    private static final int DUTCH_POPULATION_SIZE = 10_000_000;

    private static List<Person> persons = new ArrayList<>();

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
        persons.add(case0);

        int d=0;
        List<Person> newlyInfected = new ArrayList<>();
        List<Person> noLongerInfected = new ArrayList<>();
        for (; d<DAYS; d++) {
            for (Person person : persons) {
                if (person.isAlive()) {
                    if (person.wasInfectedXDaysAgoFromToday(7, d)) {
                        Person newCase = new Person(d);
                        newlyInfected.add(newCase);
                    } else if (person.wasInfectedXDaysAgoFromToday(7, d)) {
                        Person newCase = new Person(d);
                        newlyInfected.add(newCase);
                    } else if (person.wasInfectedXDaysAgoFromToday(8, d)) {
                        Person newCase = new Person(d);
                        newlyInfected.add(newCase);
                    } else if (person.wasInfectedXDaysAgoFromToday(9, d)) {
                        Person newCase = new Person(d);
                        newlyInfected.add(newCase);
                    } else if (person.wasInfectedXDaysAgoFromToday(30, d)) {
                        if (IS_IN_X_PERCENT_THAT_DIES.getNext()) {
                            person.setAlive(false);
                        }
                        noLongerInfected.add(person);
                    }
                }
            }
            System.out.printf("%d\tAdding %d newly infected\n", d, newlyInfected.size());
            persons.addAll(newlyInfected);
            System.out.printf("%d\tRemoving %d no longer infected\n\n", d, noLongerInfected.size());
            noLongerInfectedTotal += noLongerInfected.size();
            persons.removeAll(noLongerInfected);
            newlyInfected.clear();
            noLongerInfected.clear();
            if ((persons.size() + noLongerInfectedTotal) > DUTCH_POPULATION_SIZE) {
                System.out.printf("Total number infected exceeds Dutch population size %d\n\n", persons.size());
                for (Person person : persons) {
                    if (IS_IN_X_PERCENT_THAT_DIES.getNext()) {
                        person.setAlive(false);
                    }
                    //noLongerInfected.add(person);
                }
                //System.out.printf("Removing %d no longer infected (last dutch person got ill)\n", noLongerInfected.size());
//                persons.removeAll(noLongerInfected);
//                noLongerInfected.clear();
                break;
            }
        }

        System.out.println("Calculating total deceased");

        for (Person person : persons) {
            if (!person.isAlive()) totalDeaths+=1;
        }

        System.out.printf("\n\nTotal who got sick after %d days: %d\n", d, persons.size());
        System.out.printf("\n\nTotal deaths after %d days: %d\n", d, totalDeaths);

    }

//    private static boolean isInXPercentThatSurvives() {
//        float nextFloat = RANDOM_NUMBER_GENERATOR.nextFloat();
//        int percentage = Math.round(nextFloat * 100);
//        return percentage > X_PERCENT_DIES;
//    }



}

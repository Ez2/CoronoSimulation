package nl.sim;

import nl.util.SimpleList;
import nl.util.SimpleRandom;

import java.util.ArrayList;
import java.util.List;

public class CoronaEpidemicSimulation {
    private static final int DAYS = 365;
    private static final int X_PERCENT_DIES = 3;
    private static final int DUTCH_POPULATION_SIZE = 17_000_000;

    private final static SimpleRandom IS_IN_X_PERCENT_THAT_DIES = new SimpleRandom(X_PERCENT_DIES);

    public static void main(String[] args) {
        int totalDeaths = 0, noLongerInfectedTotal = 0;

        SimpleList<Person> persons = new SimpleList<>(Person.class, DUTCH_POPULATION_SIZE);
        persons.add(new Person(0));

        int t;
        List<Person> newlyInfected = new ArrayList<>();
        int noLongerInfected = 0;
        int deceased = 0;
        int totalAffected = 0;

        for (t=1; t<DAYS; t++) {
            int previousRoundInfected = persons.getLastAddedIndex();
            for (int i=0; i<=previousRoundInfected; i++) {
                if (persons.get(i).isAlive() && persons.get(i).isContagious()) {

                    if (persons.get(i).getsIllAfterXDays(7, t)) {
                        newlyInfected.add(new Person(t));
                    }

                    if (persons.get(i).getsIllAfterXDays(8, t)) {
                        newlyInfected.add(new Person(t));
                    }

                    if (persons.get(i).getsIllAfterXDays(30, t)) {
                        if (IS_IN_X_PERCENT_THAT_DIES.getNext()) {
                            persons.get(i).setAlive(false);
                            deceased++;
                        }
                        persons.get(i).setContagious(false);
                        noLongerInfected++;
                    }
                }
            }

            System.out.printf("%d\tAdding %d newly infected\n", t, newlyInfected.size());
            for (Person p : newlyInfected) {
                if (persons.getLastAddedIndex() < DUTCH_POPULATION_SIZE - 1) {
                    persons.add(p);
                } else {
                    break;
                }
            }

            System.out.printf("%d\t%d no longer infected (%d deceased) \n\n", t, noLongerInfected, deceased);
            noLongerInfectedTotal += noLongerInfected;
            noLongerInfected = 0;
            newlyInfected.clear();

            totalAffected = persons.getLastAddedIndex() + noLongerInfectedTotal;
            if (totalAffected > DUTCH_POPULATION_SIZE) {
                System.out.printf("The entire population (and more) have been infected %d\n\n", totalAffected);

                aftermath(persons);

                break;
            }
        }

        createSummary(totalDeaths, persons, t, totalAffected);

    }

    private static void createSummary(int totalDeaths, SimpleList<Person> persons, int t, int totalAffected) {
        for (Person person : persons) {
            if (!person.isAlive()) totalDeaths+=1;
        }

        System.out.printf("\nTotal who got ill after %d days: %d\n", t, totalAffected);
        System.out.printf("Deaths caused by this epidemic: %d\n", totalDeaths);
    }

    private static void aftermath(SimpleList<Person> persons) {
        for (Person person : persons) {
            if (person.isAlive() && IS_IN_X_PERCENT_THAT_DIES.getNext()) {
                person.setAlive(false);
            }
        }
    }

}

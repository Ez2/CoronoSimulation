package nl.sim;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Data
@RequiredArgsConstructor
public class Person {
    private final int infectedDay;
    private boolean alive=true;
    private boolean contagious=true;

    public boolean wasInfectedXDaysAgoFromToday(int x, int today) {
        return (today - x) == infectedDay;
    }

}

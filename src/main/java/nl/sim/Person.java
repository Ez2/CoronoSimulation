package nl.sim;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Person {
    private final int infectedDay;
    private boolean alive=true;
    private boolean contagious=true;

    public boolean getsIllAfterXDays(int x, int today) {
        return (today - x) == infectedDay;
    }

}

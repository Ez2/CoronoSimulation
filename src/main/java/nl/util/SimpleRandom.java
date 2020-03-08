package nl.util;

public class SimpleRandom {
    private static final int SIZE = 100;
    private boolean[] values = new boolean[SIZE];
    private int next = -1;

    public SimpleRandom(int truePercentage) {
        int spread = 100 / truePercentage;
        for (int i = 0; i < SIZE; i++) {
            if ((i + 1) % spread == 0) {
                values[i] = true;
            }
        }
    }

    public boolean getNext() {
        if (next == SIZE - 1) next = -1;
        return values[++next];
    }

}

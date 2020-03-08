package nl.sim;

public class PoorMansRandom {
    private static final int SIZE = 100;
    private boolean[] values = new boolean[SIZE];
    private int next = -1;

    public PoorMansRandom(int truePercentage) {
        int spread = 100 / truePercentage;
        for (int i = 0; i < SIZE; i++) {
            if ((i + 1) % spread == 0) {
                values[i] = true;
            }
        }
    }

    boolean getNext() {
        if (next == SIZE - 1) next = -1;
        return values[++next];
    }

//    public static void main(String[] args) {
//       int totalTrue=0;
//        PoorMansRandom p = new PoorMansRandom(12);
//        for (int i=0; i<100; i++) {
//            if (p.getNext()) {
//                totalTrue+=1;
//            };
//        }
//        System.out.printf("Total true : %d ", totalTrue);
//    }

}

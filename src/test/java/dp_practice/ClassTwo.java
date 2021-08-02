package dp_practice;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;

/**
 * @author wangwy3
 * @date 2021/8/2 14:16
 */
public class ClassTwo {
    long countHit = 0;

    @Test
    public void knapsack() {
        int size = 11;
        int[] weights = new Random(234).ints(size, 0, 17).toArray();
        int[] values = new Random(455).ints(size, 0, 23).toArray();
        int bagCapacity = 25;

        System.out.println("weights = " + Arrays.toString(weights));
        System.out.println("values = " + Arrays.toString(values));
        System.out.println("bag capacity = " + bagCapacity);
        final int best = process(weights, values, 0, bagCapacity);
        System.out.println("best = " + best);
        System.out.println("count hit: " + countHit);
    }

    private int process(int[] w, int[] v, int i, int c) {
        if (i >= w.length || c <= 0) {
            return 0;
        }
        // take it
        int get = (c >= w[i]) ? process(w, v, i + 1, c - w[i]) + v[i] : 0;
        // don't take it
        int donGet = process(w, v, i + 1, c);
        countHit++;
        return Math.max(get, donGet);
    }
}

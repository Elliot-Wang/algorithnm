package dp_practice;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;

/**
 * @author wangwy3
 * @date 2021/8/2 14:38
 */
public class ClassTwoOptimal {
    @Test
    public void knapsack() {
        int size = 11;
        int[] weights = new Random(234).ints(size, 0, 17).toArray();
        int[] values = new Random(455).ints(size, 0, 23).toArray();
        int bagCapacity = 25;
        int[][] dp = new int[size + 1][bagCapacity + 1];
        for (int i = size - 1; i >= 0; i--) {
            for (int j = 0; j <= bagCapacity; j++) {
                dp[i][j] = (j < weights[i]) ? dp[i+1][j]
                    : Math.max(dp[i+1][j], dp[i+1][j - weights[i]] + values[i]);
            }
        }

        System.out.println("weights = " + Arrays.toString(weights));
        System.out.println("values = " + Arrays.toString(values));
        System.out.println("bag capacity = " + bagCapacity);
        System.out.println("dp table = ");
        for (int[] ints : dp) {
            System.out.println(Arrays.toString(ints));
        }
        final int best = dp[0][bagCapacity];
        System.out.println("best = " + best);
    }

    @Test
    public void convertToLetterString() {
        int[] numbers = {1, 1, 1, 1, 1};
        System.out.println("numbers: " + Arrays.toString(numbers));
        int[] dp = new int[numbers.length + 1];
        dp[numbers.length - 1] = (numbers[numbers.length - 1] == 0) ? 0 : 1;
        for (int i = numbers.length - 2; i >= 0 ; i--) {
            dp[i] = (numbers[i] == 0) ? 0 :
                (numbers[i] * 10 + numbers[i + 1] <= 26) ?
                    dp[i + 2] + dp[i + 1] :
                    dp[i + 1];
        }
        final int score = dp[0];
        System.out.println("dp: " + Arrays.toString(dp));
        System.out.println("score: " + score);
    }


}

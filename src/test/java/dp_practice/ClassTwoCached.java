package dp_practice;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;

/**
 * @author wangwy3
 * @date 2021/8/2 14:38
 */
public class ClassTwoCached {
    @Test
    public void knapsack() {
        int size = 11;
        int[] weights = new Random(234).ints(size, 0, 17).toArray();
        int[] values = new Random(455).ints(size, 0, 23).toArray();
        int bagCapacity = 25;
        int[][] dp = new int[size + 1][bagCapacity + 1];
        for (int i = 0; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                dp[i][j] = -1;
            }
        }

        System.out.println("weights = " + Arrays.toString(weights));
        System.out.println("values = " + Arrays.toString(values));
        System.out.println("bag capacity = " + bagCapacity);
        final int best = knapsackProcess(weights, values, 0, bagCapacity, dp);
        System.out.println("best = " + best);

    }

    private int knapsackProcess(int[] w, int[] v, int i, int c, int[][] dp) {
        if (i >= w.length || c <= 0) {
            return 0;
        }
        if (dp[i][c] != -1) {
            return dp[i][c];
        }
        // take it
        int get = (c >= w[i]) ? knapsackProcess(w, v, i + 1, c - w[i], dp) + v[i] : 0;
        // don't take it
        int donGet = knapsackProcess(w, v, i + 1, c, dp);
        dp[i][c] = Math.max(get, donGet);
        return dp[i][c];
    }

    @Test
    public void convertToLetterString() {
        int[] numbers = {1, 1, 1, 1, 1};
        System.out.println("numbers: " + Arrays.toString(numbers));
        int[] dp = new int[numbers.length];
        ctlsProcess(numbers, 0, dp);
        final int score = dp[0];
        System.out.println("score: " + score);
    }

    private int ctlsProcess(int[] nums, int i, int[] dp) {
        if (i == nums.length - 1 && nums[i] != 0) {
            dp[i] = 1;
        } else if (i >= nums.length) {
            return 0;
        } else if (nums[i] == 0) {
            dp[i] = 0;
        } else {
            dp[i] = (nums[i] * 10 + nums[i + 1] <= 26) ?
                ctlsProcess(nums, i + 2, dp) + ctlsProcess(nums, i + 1, dp) :
                ctlsProcess(nums, i + 1, dp);
        }
        return dp[i];
    }
}

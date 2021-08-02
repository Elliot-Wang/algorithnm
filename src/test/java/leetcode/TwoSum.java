package leetcode;

import java.util.Arrays;
import java.util.Random;

/**
 * @author wangwy3
 * @date 2021/7/28 14:58
 */
public class TwoSum {
    public static void main(String[] args) {
        int[] arr = new Random().ints(20, 0, 100).toArray();
        System.out.println("origin" + Arrays.toString(arr));
        mergeSort(arr, 0, arr.length - 1);
        System.out.println("sort" + Arrays.toString(arr));
    }

    public static void mergeSort(int[] nums, int L, int R) {
        if (L >= R) {
            return;
        }
        int[] help = Arrays.copyOf(nums, nums.length);
        int M = L + (R - L >> 1) + 1;
        int l = L;
        int r = M;
        int i = l;
        mergeSort(nums, L, M - 1);
        mergeSort(nums, M, R);
        while (l < M && r <= R) {
            help[i++] = (nums[l] <= nums[r]) ? nums[l++] : nums[r++];
        }
        while (l < M) {
            help[i++] = nums[l++];
        }
        while (r <= R) {
            help[i++] = nums[r++];
        }
        System.arraycopy(help, 0, nums, 0, nums.length);
    }

    // mergeSort 没有那么有用，把位置信息打乱掉了。
    public int[] twoSum(int[] nums, int target) {
        int L = nums.length;
        mergeSort(nums, 0, L - 1);
        for (int i = 0; i < L - 1; i++) {
            for (int j = i + 1; j < L; j++) {
                if(nums[i] + nums[j] > target) {
                    break;
                }
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{-1, -1};
    }
}

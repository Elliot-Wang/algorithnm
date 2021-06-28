package class03_linkedList_stack_queue;

public class Code08_GetMax {

	// 求arr中的最大值
	public static int getMax(int[] arr) {
		return process(arr, 0, arr.length - 1);
	}

	/**
	 * // TODO 看不懂，这个算法的优势在哪里？
	 * @param arr
	 * @param L
	 * @param R
	 * @return
	 */
	// arr[L..R]范围上求最大值  L ... R   N
	public static int process(int[] arr, int L, int R) {
		// arr[L..R]范围上只有一个数，直接返回，base case
		if (L == R) { 
			return arr[L];
		}
		// L...R 不只一个数
		// mid = (L + R) / 2
		int mid = L + ((R - L) >> 1); // 中点   	1
		int leftMax = process(arr, L, mid);
		int rightMax = process(arr, mid + 1, R);
		return Math.max(leftMax, rightMax);
	}

}

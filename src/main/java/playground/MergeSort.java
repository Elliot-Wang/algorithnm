package playground;

import java.util.Arrays;

/**
 * 归并排序，真的很有意思啊。
 * 有意思的点在于归并的过程，一个O(N)的过程
 * @author WangWy
 * @program Algorithm
 * @create 2021-05-25 20:58
 **/
public class MergeSort {
	public static void main(String[] args) {
		int[] arr = {2, 1, 4, 9, 3, 8, 11, 0, 2, 1};
		System.out.println("Origin:" + Arrays.toString(arr));
		// sortRecurse(arr, 0, arr.length - 1);
		sortByLoop(arr);
		System.out.println("Sorted:" + Arrays.toString(arr));
	}

	public static void sortByRecurse(int[] arr, int L, int R) {
		if (L == R || arr == null || arr.length == 0) {
			return;
		}
		int mid = L + (R - L) / 2;
		if (R - L > 1) {
			sortByRecurse(arr, L, mid);
			sortByRecurse(arr, mid + 1, R);
		}
		merge(arr, L, mid, mid + 1, R);
	}

	public static void sortByLoop(int[] arr) {
		int step = 1;
		while (step < arr.length) {
			int index = 0;
			while (step < arr.length - index) {
				int rs = Math.min(index + step, arr.length - 1);
				int re = Math.min(index + 2 * step - 1, arr.length - 1);
				merge(arr, index, index + step - 1, rs, re);
				index += 2 * step;
			}
			// 防止溢出
			if (step > arr.length >> 1) {
				break;
			}
			step <<= 2;
		}
	}

	public static void merge(int[] arr, int ls, int le, int rs, int re) {
		int[] help = arr.clone();
		int i = ls;
		int start = ls;
		while (ls <= le && rs <= re) {
			help[i++] = (arr[ls] <= arr[rs]) ? arr[ls++] : arr[rs++];
		}
		while (ls <= le) {
			help[i++] = arr[ls++];
		}
		while (rs <= re) {
			help[i++] = arr[rs++];
		}
		while (--i >= start) {
			arr[i] = help[i];
		}
	}
}

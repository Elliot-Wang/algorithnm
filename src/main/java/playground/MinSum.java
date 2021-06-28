package playground;

/**
 * @author WangWy
 * @program Algorithm
 * @create 2021-05-26 19:41
 **/
public class MinSum {
	private static int[] arr_2s;

	public static void main(String[] args) {
		int[] arr_1 = new int[] {
			1, 3, 14, 11, 6, 9
		};
		arr_2s = new int[] {
			1, 3, 14, 11, 6, 9
		};

		merge(arr_1, 0, 2, 3, arr_1.length - 1);
		for (int i : arr_1) {
			System.out.println( i );
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

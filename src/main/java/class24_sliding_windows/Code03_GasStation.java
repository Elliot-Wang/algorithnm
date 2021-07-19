package class24_sliding_windows;

import java.util.LinkedList;

// 测试链接：https://leetcode.com/problems/gas-station

/**
 * 加油站的良好出发点问题
 * gas  = [1,2,3,4,5]  该加油站的可加油量
 * cost = [3,4,5,1,2]  到下一个加油站的距离（耗油量）
 * 请问从哪一个加油站出发能够跑完一圈，只能单方向跑
 */
public class Code03_GasStation {

	// 这个方法的时间复杂度O(N)，额外空间复杂度O(N)
	public static int canCompleteCircuit(int[] gas, int[] cost) {
		boolean[] good = goodArray(gas, cost);
		for (int i = 0; i < gas.length; i++) {
			if (good[i]) {
				return i;
			}
		}
		return -1;
	}

	public static boolean[] goodArray(int[] g, int[] c) {
		int N = g.length;
		int M = N << 1;
		int[] arr = new int[M];
		// 把两个数组整合成一个 gas - cost
        // 当sum(gas) - sum(cost) < 0 的时候
        // 汽车马上抛锚，否则就可以一直跑下去
		for (int i = 0; i < N; i++) {
			arr[i] = g[i] - c[i];
			arr[i + N] = g[i] - c[i];
		}
		// 累加和
		for (int i = 1; i < M; i++) {
			arr[i] += arr[i - 1];
		}
		// 滑动窗口的结构
        // 窗口大小为N，即一圈
        // 需要获取最小值
		LinkedList<Integer> w = new LinkedList<>();
		for (int i = 0; i < N; i++) {
			while (!w.isEmpty() && arr[w.peekLast()] >= arr[i]) {
				w.pollLast();
			}
			w.addLast(i);
		}
		// 开始进行运算
		boolean[] ans = new boolean[N];
		// i 左端 j 右端 offset 起点的累加和（需要校准为0）
		for (int offset = 0, i = 0, j = N; j < M; offset = arr[i++], j++) {
			if (arr[w.peekFirst()] - offset >= 0) {
			    // 一圈范围之内的最小值没有跌破起点的累加和
                // 说明从此点出发，可以跑完一圈
				ans[i] = true;
			}
			// 窗口滑动，修正最小值
			if (w.peekFirst() == i) {
				w.pollFirst();
			}
			while (!w.isEmpty() && arr[w.peekLast()] >= arr[j]) {
				w.pollLast();
			}
			w.addLast(j);
		}
		return ans;
	}

}

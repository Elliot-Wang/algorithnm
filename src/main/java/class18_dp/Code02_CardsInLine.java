package class18_dp;

/**
 * 一个整数数组
 * player A和player B依次从左右两端取值
 * 最后取值最大者获胜
 * 假设两位player都是用最优策略
 * 求获胜者的分数
 *
 * 首先要知道获胜策略，然后按照获胜策略取分计算
 */
public class Code02_CardsInLine {

	// 根据规则，返回获胜者的分数

    /**
     * 原来需要自己和对手两个回合之下的递归函数
     * 有些复杂，穷举的递归栈都是树状结构
     * 把递归调用的树画出来就清楚许多了，里面蕴含着状态转移的公式
     * @param arr 整数数组
     * @return 胜者的分数
     */
	public static int win1(int[] arr) {
		if (arr == null || arr.length == 0) {
			return 0;
		}
		int first = f1(arr, 0, arr.length - 1);
		int second = g1(arr, 0, arr.length - 1);
		return Math.max(first, second);
	}

	// arr[L..R]，先手获得的最好分数返回
	public static int f1(int[] arr, int L, int R) {
		if (L == R) {
			return arr[L];
		}
		int p1 = arr[L] + g1(arr, L + 1, R);
		int p2 = arr[R] + g1(arr, L, R - 1);
		return Math.max(p1, p2);
	}

	// // arr[L..R]，后手获得的最好分数返回
	public static int g1(int[] arr, int L, int R) {
		if (L == R) {
			return 0;
		}
		int p1 = f1(arr, L + 1, R); // 对手拿走了L位置的数
		int p2 = f1(arr, L, R - 1); // 对手拿走了R位置的数
		return Math.min(p1, p2);
	}

    /**
     * 穷举加上完整缓存表版本
     * @param arr
     * @return
     */
	public static int win2(int[] arr) {
		if (arr == null || arr.length == 0) {
			return 0;
		}
		int N = arr.length;
		int[][] fmap = new int[N][N];
		int[][] gmap = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				fmap[i][j] = -1;
				gmap[i][j] = -1;
			}
		}
		int first = f2(arr, 0, arr.length - 1, fmap, gmap);
		int second = g2(arr, 0, arr.length - 1, fmap, gmap);
		return Math.max(first, second);
	}

	// arr[L..R]，先手获得的最好分数返回
	public static int f2(int[] arr, int L, int R, int[][] fmap, int[][] gmap) {
		if (fmap[L][R] != -1) {
			return fmap[L][R];
		}
		int ans = 0;
		if (L == R) {
			ans = arr[L];
		} else {
			int p1 = arr[L] + g2(arr, L + 1, R, fmap, gmap);
			int p2 = arr[R] + g2(arr, L, R - 1, fmap, gmap);
			ans = Math.max(p1, p2);
		}
		fmap[L][R] = ans;
		return ans;
	}

	// // arr[L..R]，后手获得的最好分数返回
	public static int g2(int[] arr, int L, int R, int[][] fmap, int[][] gmap) {
		if (gmap[L][R] != -1) {
			return gmap[L][R];
		}
		int ans = 0;
		if (L != R) {
			int p1 = f2(arr, L + 1, R, fmap, gmap); // 对手拿走了L位置的数
			int p2 = f2(arr, L, R - 1, fmap, gmap); // 对手拿走了R位置的数
			ans = Math.min(p1, p2);
		}
		gmap[L][R] = ans;
		return ans;
	}

    /**
     * 推导dp缓存表的规律
     * 利用状态转移关系运算得出结果
     *
     * 这两个表很有意思
     * @param arr
     * @return
     */
	public static int win3(int[] arr) {
		if (arr == null || arr.length == 0) {
			return 0;
		}
		int N = arr.length;
		int[][] fmap = new int[N][N];
		int[][] gmap = new int[N][N];
		// base case是对角线
		for (int i = 0; i < N; i++) {
			fmap[i][i] = arr[i];
			// gmap的对角线都是0
		}
		// 不断往右上角运算，得出结果
		for (int startCol = 1; startCol < N; startCol++) {
			int L = 0;
			int R = startCol;
			while (R < N) {
				fmap[L][R] = Math.max(arr[L] + gmap[L + 1][R], arr[R] + gmap[L][R - 1]);
				gmap[L][R] = Math.min(fmap[L + 1][R], fmap[L][R - 1]);
				L++;
				R++;
			}
		}
		return Math.max(fmap[0][N - 1], gmap[0][N - 1]);
	}

    /**
     * 一开始只考虑了一个递归
     * emmm
     * @param arr
     * @return
     */
	public static int greedy(int[] arr) {
	    return gg(arr, 0, arr.length - 1, 0);
    }

    public static int gg(int[] arr, int left, int right, int score) {
	    if(left == right) {
	        return arr[left];
        } else if(left == right - 1) {
	        return 0;
        }
	    int sub_left = arr[left] - Math.max(arr[left+1], arr[right]);
	    int sub_right = arr[right] - Math.max(arr[left], arr[right - 1]);
	    return (sub_left > sub_right) ? gg(arr, left + 1, right, score + arr[left])
            : gg(arr, left, right-1, score+arr[right]);
    }

	public static void main(String[] args) {
		int[] arr = { 5, 7, 4, 5, 8, 1, 6, 0, 3, 4, 6, 1, 7 };
		System.out.println(win1(arr));
		System.out.println(win2(arr));
		System.out.println(win3(arr));
	}

}

package class21_dp;

// 走格子问题
// 走过路径的和最小
// 经典的二维dp表格
// 动态规划向来有两种方法，一个从起点慢慢出发，一个从终点慢慢来到起点
// 经典的dp题目，但是本次课的重点在于不断优化dp表空间
public class Code01_MinPathSum {

	public static int minPathSum1(int[][] m) {
		if (m == null || m.length == 0 || m[0] == null || m[0].length == 0) {
			return 0;
		}
		int row = m.length;
		int col = m[0].length;
		int[][] dp = new int[row][col];
		dp[0][0] = m[0][0];
		for (int i = 1; i < row; i++) {
			dp[i][0] = dp[i - 1][0] + m[i][0];
		}
		for (int j = 1; j < col; j++) {
			dp[0][j] = dp[0][j - 1] + m[0][j];
		}
		for (int i = 1; i < row; i++) {
			for (int j = 1; j < col; j++) {
				dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + m[i][j];
			}
		}
		return dp[row - 1][col - 1];
	}

	public static int minPathSum2(int[][] m) {
		if (m == null || m.length == 0 || m[0] == null || m[0].length == 0) {
			return 0;
		}
		int row = m.length;
		int col = m[0].length;
		int[] dp = new int[col];
		dp[0] = m[0][0];
		for (int j = 1; j < col; j++) {
			dp[j] = dp[j - 1] + m[0][j];
		}
		for (int i = 1; i < row; i++) {
			dp[0] += m[i][0];
			for (int j = 1; j < col; j++) {
				dp[j] = Math.min(dp[j - 1], dp[j]) + m[i][j];
			}
		}
		return dp[col - 1];
	}

	// for test
	public static int[][] generateRandomMatrix(int rowSize, int colSize) {
		if (rowSize < 0 || colSize < 0) {
			return null;
		}
		int[][] result = new int[rowSize][colSize];
		for (int i = 0; i != result.length; i++) {
			for (int j = 0; j != result[0].length; j++) {
				result[i][j] = (int) (Math.random() * 100);
			}
		}
		return result;
	}

	// for test
	public static void printMatrix(int[][] matrix) {
		for (int i = 0; i != matrix.length; i++) {
			for (int j = 0; j != matrix[0].length; j++) {
				System.out.print(matrix[i][j] + " ");
			}
			System.out.println();
		}
	}

	public static void main(String[] args) {
		int rowSize = 10;
		int colSize = 10;
		int[][] m = generateRandomMatrix(rowSize, colSize);
		System.out.println(minPathSum1(m));
		System.out.println(minPathSum2(m));

	}
}

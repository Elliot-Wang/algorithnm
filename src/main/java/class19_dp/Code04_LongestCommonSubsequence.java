package class19_dp;

// 这个问题leetcode上可以直接测
// 链接：https://leetcode.com/problems/longest-common-subsequence/
public class Code04_LongestCommonSubsequence {

	public static int longestCommonSubsequence1(String s1, String s2) {
		if (s1 == null || s2 == null || s1.length() == 0 || s2.length() == 0) {
			return 0;
		}
		char[] str1 = s1.toCharArray();
		char[] str2 = s2.toCharArray();
		// 尝试
		return process1(str1, str2, str1.length - 1, str2.length - 1);
	}

    /**
     * str1 and str2 compare
     * from end to head
     *
     * 缕清这个遍历递归过程，让我有一点心虚，害怕错误
     * 就是担心没有考虑所有的可能情况，漏了某些情况
     * @param str1 字符串1
     * @param str2 字符串2
     * @param i 字符串1，计算点位置
     * @param j 字符串2，计算点位置
     * @return 最长子序列的长度
     */
	public static int process1(char[] str1, char[] str2, int i, int j) {
		if (i == 0 && j == 0) {
			return str1[i] == str2[j] ? 1 : 0;
		} else if (i == 0) {
		    // str1 end
			if (str1[i] == str2[j]) {
				return 1;
			} else {
				return process1(str1, str2, i, j - 1);
			}
		} else if (j == 0) {
		    // str2 end
			if (str1[i] == str2[j]) {
				return 1;
			} else {
				return process1(str1, str2, i - 1, j);
			}
		} else {
            // i != 0 && j != 0

            // str1 go
			int p1 = process1(str1, str2, i - 1, j);
			// str2 go
			int p2 = process1(str1, str2, i, j - 1);
			// str1 and str2 both go or not
			int p3 = str1[i] == str2[j] ? (1 + process1(str1, str2, i - 1, j - 1)) : 0;
			return Math.max(p1, Math.max(p2, p3));
		}
	}

    /**
     * 使用缓存表
     * @param s1
     * @param s2
     * @return
     */
	public static int longestCommonSubsequence2(String s1, String s2) {
		if (s1 == null || s2 == null || s1.length() == 0 || s2.length() == 0) {
			return 0;
		}
		char[] str1 = s1.toCharArray();
		char[] str2 = s2.toCharArray();
		int N = str1.length;
		int M = str2.length;
		int[][] dp = new int[N][M];
		dp[0][0] = str1[0] == str2[0] ? 1 : 0;
		for (int j = 1; j < M; j++) {
			dp[0][j] = str1[0] == str2[j] ? 1 : dp[0][j - 1];
		}
		for (int i = 1; i < N; i++) {
			dp[i][0] = str1[i] == str2[0] ? 1 : dp[i - 1][0];
		}
		for (int i = 1; i < N; i++) {
			for (int j = 1; j < M; j++) {
				int p1 = dp[i - 1][j];
				int p2 = dp[i][j - 1];
				int p3 = str1[i] == str2[j] ? (1 + dp[i - 1][j - 1]) : 0;
				dp[i][j] = Math.max(p1, Math.max(p2, p3));
			}
		}
		return dp[N - 1][M - 1];
	}

}

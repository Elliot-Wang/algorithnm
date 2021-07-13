package class18_dp;

/**
 * 简单的入门动态规划题
 * 可是感觉就很难
 * 一个机器人在一维的有限点上面来回走，规定在指定步数上
 * 从起点走到终点，问有几种可达到的路径
 */
public class Code01_RobotWalk {

    /**
     * @param N 可走的点数量
     * @param start 出发点
     * @param aim 目的地
     * @param K 走的步数
     * @return 达到目标的路径数
     */
	public static int ways1(int N, int start, int aim, int K) {
		if (N < 2 || start < 1 || start > N || aim < 1 || aim > N || K < 1) {
			return -1;
		}
		// 利用穷举式的递归
		return process1(start, K, aim, N);
	}

	// 机器人当前来到的位置是cur，
	// 机器人还有rest步需要去走，
	// 最终的目标是aim，
	// 有哪些位置？1~N
	// 返回：机器人从cur出发，走过rest步之后，最终停在aim的方法数，是多少？
	public static int process1(int cur, int rest, int aim, int N) {
	    // 步数用尽，是否达到终点
		if (rest == 0) {
			return cur == aim ? 1 : 0;
		}

		// 在两个边界上，只能往回走
		if (cur == 1) {
			return process1(2, rest - 1, aim, N);
		}else if (cur == N) {
			return process1(N - 1, rest - 1, aim, N);
		}

		// 往左走，和往右走的情况加和
		return process1(cur - 1, rest - 1, aim, N) + process1(cur + 1, rest - 1, aim, N);
	}

    /**
     * 怎么利用动态规划优化上面的暴力穷举
     * 思路，保存一些走法的信息
     * 以及有了状态缓存表之后，这些缓存表能带来多大的提升？
     * 这个可能是进一步优化的点
     *
     * __自顶向下的动态规划__
     * @param N 可走的点数量
     * @param start 出发点
     * @param aim 目的地
     * @param K 走的步数
     * @return 达到目标的路径数
     */
	public static int ways2(int N, int start, int aim, int K) {
		if (N < 2 || start < 1 || start > N || aim < 1 || aim > N || K < 1) {
			return -1;
		}

		// 缓存表，存储内容是可行的路径数量
        // 索引是当前状态，状态有两个数字：当前位置(狭义状态）+剩余步数(理解为时间参数)
        // 先初始化缓存表
		int[][] dp = new int[N + 1][K + 1];
		for (int i = 0; i <= N; i++) {
			for (int j = 0; j <= K; j++) {
				dp[i][j] = -1;
			}
		}
		// dp就是缓存表
		// dp[cur][rest] == -1 -> process1(cur, rest)之前没算过！
		// dp[cur][rest] != -1 -> process1(cur, rest)之前算过！返回值，dp[cur][rest]
		// N+1 * K+1
		return process2(start, K, aim, N, dp);
	}

	// cur 范: 1 ~ N
	// rest 范：0 ~ K
	public static int process2(int cur, int rest, int aim, int N, int[][] dp) {
		if (dp[cur][rest] != -1) {
			return dp[cur][rest];
		}
		// 之前没算过！
		int ans = 0;
		if (rest == 0) {
			ans = cur == aim ? 1 : 0;
		} else if (cur == 1) {
			ans = process2(2, rest - 1, aim, N, dp);
		} else if (cur == N) {
			ans = process2(N - 1, rest - 1, aim, N, dp);
		} else {
			ans = process2(cur - 1, rest - 1, aim, N, dp) + process2(cur + 1, rest - 1, aim, N, dp);
		}
		// 计算完之后保存中间结果
		dp[cur][rest] = ans;
		return ans;
	}

    /**
     * 这个是把方法2的两个代码合在一起了？!
     * 思考base case，优化缩减dp缓存表
     * 然后根据base case，和暴力递归中的状态转移关系
     * 逐步填写dp缓存表
     * 当dp缓存表添加到我们需要的那个格子上时
     * 答案得出！
     *
     * @param N 可走的点数量
     * @param start 出发点
     * @param aim 目的地
     * @param K 走的步数
     * @return 达到目标的路径数
     */
	public static int ways3(int N, int start, int aim, int K) {
		if (N < 2 || start < 1 || start > N || aim < 1 || aim > N || K < 1) {
			return -1;
		}
		int[][] dp = new int[N + 1][K + 1];
		dp[start][0] = 1;
		for (int rest = 1; rest <= K; rest++) {
		    // 第一行的状态转移
			dp[1][rest] = dp[2][rest - 1];
			// 第二至N-1行的状态转移
			for (int cur = 2; cur < N; cur++) {
				dp[cur][rest] = dp[cur - 1][rest - 1] + dp[cur + 1][rest - 1];
			}
			// 第N行的状态转移
			dp[N][rest] = dp[N - 1][rest - 1];
		}
		return dp[aim][K];
	}

	public static void main(String[] args) {
		System.out.println(ways1(5, 2, 4, 6));
		System.out.println(ways2(5, 2, 4, 6));
		System.out.println(ways3(5, 2, 4, 6));
	}

}

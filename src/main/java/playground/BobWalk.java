package playground;

/**
 * @author WangWy
 * @program algorithnm
 * @create 2021-07-19 21:00
 **/
public class BobWalk {
    public static void main(String[] args) {
        livePossibility(10, 10, 10);
    }

    public static void livePossibility(int k, int N, int M) {
        long[][][] dp = new long[N][M][k + 1];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                dp[i][j][0] = 1;
            }
        }
        for (int rest = 1; rest <= k; rest++) {
            System.out.println("\r\n==========layer " + rest + "==========");
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < M; c++) {
                    dp[r][c][rest] = pick(dp, N, M, r - 1, c, rest - 1);
                    dp[r][c][rest] += pick(dp, N, M, r + 1, c, rest - 1);
                    dp[r][c][rest] += pick(dp, N, M, r, c - 1, rest - 1);
                    dp[r][c][rest] += pick(dp, N, M, r, c + 1, rest - 1);
                }
            }

            for (int r = 0; r < N; r++) {
                for (int c = 0; c < M; c++) {
                    System.out.print(dp[r][c][rest] + " ");
                }
                System.out.println("");
            }
        }
    }

    public static long pick(long[][][] dp, int N, int M, int r, int c, int rest) {
        if (r < 0 || r == N || c < 0 || c == M) {
            return 0;
        }
        return dp[r][c][rest];
    }

}

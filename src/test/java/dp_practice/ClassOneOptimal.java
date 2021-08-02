package dp_practice;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;

/**
 * @author wangwy3
 * @date 2021/8/2 11:54
 */
public class ClassOneOptimal {
    long countHit = 0;

    /*
     * 有了缓存表再来写这个是真的舒服
     */
    @Test
    public void cardsInLine() {
        int size = 30;
        int[] cards = new Random(123).ints(size, 0, 20).toArray();
        int[][] cachedF = new int[cards.length][cards.length];
        int[][] cachedS = new int[cards.length][cards.length];
        for (int i = 0; i < cachedF.length; i++) {
            cachedF[i][i] = cards[i];
            cachedS[i][i] = 0;
        }

        for (int i = 1; i < size; i++) {
            for (int j = 0; j < size - i; j++) {
                countHit ++;
                cachedF[j][j + i] = Math.max(cards[j] + cachedS[j + 1][j + i], cards[j + i] + cachedS[j][j + i - 1]);
                countHit ++;
                cachedS[j][j + i] = Math.min(cachedF[j + 1][j + i], cachedF[j][j + i - 1]);
            }
        }
        int scoreFirst = cachedF[0][size - 1];
        int scoreLast = cachedS[0][size - 1];
        System.out.println("cards = " + Arrays.toString(cards));
        System.out.println("sum = " + Arrays.stream(cards).sum());
        System.out.println("First Player: " + scoreFirst);
        System.out.println("Second Player: " + scoreLast);
        System.out.println("count hit: " + countHit);
    }

    private int cardsInLineFirstPlayer(int[] cards, int L, int R, int[][] cachedF, int[][] cachedS) {
        // self takes left
        int sl = cards[L] + cardsInLineSecondPlayer(cards, L + 1, R, cachedF, cachedS);
        // self takes right
        int sr = cards[R] + cardsInLineSecondPlayer(cards, L, R - 1, cachedF, cachedS);
        // self choose best situation
        cachedF[L][R] = Math.max(sl, sr);
        countHit++;
        return cachedF[L][R];
    }

    private int cardsInLineSecondPlayer(int[] cards, int L, int R, int[][] cachedF, int[][] cachedS) {
        // opponent takes left
        int sl = cardsInLineFirstPlayer(cards, L + 1, R, cachedF, cachedS);
        // opponent takes right
        int sr = cardsInLineFirstPlayer(cards, L, R - 1, cachedF, cachedS);
        // opponent choose best situation
        cachedS[L][R] = Math.min(sl, sr);
        countHit++;
        return cachedS[L][R];
    }
}

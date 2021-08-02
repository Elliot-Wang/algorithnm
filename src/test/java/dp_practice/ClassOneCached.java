package dp_practice;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;

/**
 * @author wangwy3
 * @date 2021/8/2 11:16
 */
public class ClassOneCached {
    long countHit = 0;

    @Test
    public void cardsInLine(){
        int[] cards = new Random(123).ints(30, 0, 20).toArray();
        int[][] cachedF = new int[cards.length][cards.length];
        for (int i = 0; i < cachedF.length; i++) {
            for (int j = 0; j < cachedF.length; j++) {
                cachedF[i][j] = -1;
            }
        }
        int[][] cachedS = new int[cards.length][cards.length];
        for (int i = 0; i < cachedS.length; i++) {
            for (int j = 0; j < cachedS.length; j++) {
                cachedS[i][j] = -1;
            }
        }
        int scoreFirst = cardsInLineFirstPlayer(cards, 0, cards.length - 1, cachedF, cachedS);
        int scoreLast = cardsInLineSecondPlayer(cards, 0, cards.length - 1, cachedF, cachedS);
        System.out.println("cards = " + Arrays.toString(cards));
        System.out.println("sum = " + Arrays.stream(cards).sum());
        System.out.println("First Player: " + scoreFirst);
        System.out.println("Second Player: " + scoreLast);
        System.out.println("count hit: " + countHit);
    }

    /**
     * cached table
     * table first => int[size][size]
     * table second => int[size][size]
     */

    private int cardsInLineFirstPlayer(int[] cards, int L, int R, int[][] cachedF, int[][] cachedS) {
        if(cards == null || cards.length == 0) {
            return 0;
        }
        if(L == R) {
            cachedF[L][R] = cards[L];
            countHit++;
            return cards[L];
        }
        // 命中缓存表
        if(cachedF[L][R] != -1)
            return cachedF[L][R];
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
        if(cards == null || cards.length == 0) {
            return 0;
        }
        if(L == R) {
            cachedS[L][R] = 0;
            countHit++;
            return 0;
        }
        // 命中缓存表
        if(cachedS[L][R] != -1)
            return cachedS[L][R];
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

package dp_practice;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;

/**
 * @author wangwy3
 * @date 2021/8/2 10:55
 */
public class ClassOne {
    long countHit = 0;

    /*
     * 这个两个策略的递归函数，说实话让自己想一开始还是没想起来。
     * 对于递归问题的掌握不够牢靠。回想一下解决递归函数的原则：
     * 1) 每一个可变参数的类型，一定不要比int类型更加复杂
     * 2) 原则 1）可以违反，让类型突破到一维线性结构，那必须是单一可变参数
     * 3) 如果发现原则1）被违反，但不违反原则2），只需要做到记忆化搜索即可
     * 4) 可变参数的个数，能少则少
     */
    @Test
    public void cardsInLine() {
        int[] cards = new Random(123).ints(30, 0, 20).toArray();
        int scoreFirst = cardsInLineFirstPlayer(cards, 0, cards.length - 1);
        int scoreLast = cardsInLineSecondPlayer(cards, 0, cards.length - 1);
        System.out.println("cards = " + Arrays.toString(cards));
        System.out.println("sum = " + Arrays.stream(cards).sum());
        System.out.println("First Player: " + scoreFirst);
        System.out.println("Second Player: " + scoreLast);
        System.out.println("count hit: " + countHit);
    }

    /*
     * 面对当前局面，先手策略在左右选择中选择最优情况
     * 选牌分数+之后选择的最优分数
     */
    private int cardsInLineFirstPlayer(int[] cards, int L, int R) {
        if (cards == null || cards.length == 0) {
            return 0;
        }
        if (L == R) {
            countHit++;
            return cards[L];
        }
        // self takes left
        int sl = cards[L] + cardsInLineSecondPlayer(cards, L + 1, R);
        // self takes right
        int sr = cards[R] + cardsInLineSecondPlayer(cards, L, R - 1);
        // self choose best situation
        countHit++;
        return Math.max(sl, sr);
    }

    /*
     * 面对当前局面，后手在先手最优选择之后，接受
     */
    private int cardsInLineSecondPlayer(int[] cards, int L, int R) {
        if (cards == null || cards.length == 0) {
            return 0;
        }
        if (L == R) {
            countHit++;
            return 0;
        }
        // opponent takes left
        int sl = cardsInLineFirstPlayer(cards, L + 1, R);
        // opponent takes right
        int sr = cardsInLineFirstPlayer(cards, L, R - 1);
        // opponent choose best situation
        countHit++;
        return Math.min(sl, sr);
    }
}

package leetcode;

import java.util.Random;

/**
 * @author wangwy3
 * @date 2021/7/28 15:56
 */
public class AddTwoNumber {
    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public static void main(String[] args) {
        int[] arr_1 = new Random().ints(5, 0, 9).toArray();
        int[] arr_2 = new Random().ints(5, 0, 9).toArray();
        final ListNode sumList = addTwoNumbers(generateListNode(arr_1), generateListNode(arr_2));
    }

    public static ListNode generateListNode(int[] arr) {
        if(arr == null) {
            return null;
        }
        final ListNode head = new ListNode();
        ListNode node = head;
        for (int value : arr) {
            node.val = value;
            node.next = new ListNode();
            node = node.next;
        }
        return head;
    }

    static public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        return null;
    }
}

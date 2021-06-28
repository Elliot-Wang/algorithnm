package playground;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BucketSort {

    private static List<Integer> input_arr;

    public static void main(String[] args) {
        input_arr = Stream.generate(() -> new Random().nextInt(1200)).limit(20).collect(Collectors.toList());
        int max = input_arr.stream().max(Integer::compare).get();
        System.out.println("input_arr: " + input_arr.toString());
        System.out.println("max: " + max);
        bucketSort(input_arr, max);
        bucketSortWithSum(input_arr.toArray(new Integer[0]), max);
    }

    private static int getChoose(int x, int base) {
        return x / base % 10;
    }

    /**
     * 使用累加和的思想，只用一个等大数组，完成桶排序 不使用桶容器，效率更高
     * 
     * @param arr
     */
    private static void bucketSortWithSum(Integer[] arr, int max) {
        System.out.println("———————————————分割线———————————————");
        System.out.println("累加和桶排序");

        int[] count = new int[10];
        Integer[] help = new Integer[arr.length];

        int base = 1;
        for (; base <= max; base *= 10) {
            for (int i = 0; i < count.length; i++) {
                count[i] = 0;
            }
            for (int x : arr) {
                int choose = getChoose(x, base);
                count[choose]++;
            }
            for (int i = 1; i < count.length; i++) {
                count[i] += count[i - 1];
            }

            for (int i = arr.length - 1; i >= 0; i--) {
                int choose = getChoose(arr[i], base);
                try {
                help[(count[choose]--)-1] = arr[i];
                } catch (Exception e) {
                    System.out.println( "choose = "+ choose );
                    System.out.println( "count = "+ count.toString());
                    System.out.println( "count[choose] = " + count[choose] );
                }
            }
            Integer[] temp = help;
            help = arr;
            arr = temp;
            System.out.println("base:" + base);
            System.out.println("arr:" + Arrays.asList(arr));
        }
    }

    /**
     * 利用桶容器进行桶排序，效率较低
     * 
     * @param arr
     */
    private static void bucketSort(List<Integer> arr, int max) {
        System.out.println("———————————————分割线———————————————");
        System.out.println("常规桶排序");

        int base = 1;
        List<List> buckets = new ArrayList<List>();
        for (int i = 0; i < 10; i++) {
            buckets.add(new ArrayList<>());
        }
        for (; base <= max; base = base * 10) {
            for (int x : arr) {
                int choose = getChoose(x, base);
                buckets.get(choose).add(x);
            }
            arr.clear();
            for (List<Integer> bucket : buckets) {
                arr.addAll(bucket);
                bucket.clear();
            }
            System.out.println("base:" + base);
            System.out.println("arr:" + arr.toString());
        }
    }
}

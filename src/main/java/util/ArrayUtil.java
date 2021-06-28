package util;

import java.util.Random;

public class ArrayUtil {

	public static void printArray(int[] arr) {
		if (arr == null) {
			return;
		}
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}

	public static int[] randomArray(int size) {
		return randomArray(size, 100);
	}
    
	public static int[] randomArray(int size, int end) {
		return randomArray(size, 0, end);
	}

	public static int[] randomArray(int size, int start, int end) {
		if(size == 0 || start >= end) {
			return null;
		}
		
		int[] arr = new int[size];
		Random random = new Random();
		for (int i = 0; i < arr.length; i++) {
			arr[i] = random.nextInt(end - start) + start;
		}
		return arr;
	}
}

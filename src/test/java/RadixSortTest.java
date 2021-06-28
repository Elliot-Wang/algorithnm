

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.Random;

import com.mashibing.RadixSort;

import org.junit.jupiter.api.Test;

class RadixSortTest {
	
	int[] generateRandomArray() {
		Random r = new Random();

		int[] arr = new int[10000];

		for (int i = 0; i < arr.length; i++)
			arr[i] = r.nextInt(1000);

		return arr;
	}
	

	@Test
	void testSort() {
		int[] a = generateRandomArray();
		int[] result = RadixSort.sort(a);
		Arrays.sort(a);
		boolean same = true;
		
		for (int i = 0; i < a.length; i++) {
			if(result[i] != a[i]) same = false;
		}
		
		assertEquals(true, same);
		
	}

}

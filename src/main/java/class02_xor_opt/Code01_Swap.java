package class02_xor_opt;

public class Code01_Swap {
	
	public static void main(String[] args) {
		int a = 16;
		int b = 603;
		
		System.out.println( "======swap======" );
		System.out.println("a:" + a + "\tb:" +b );
		
		// 交换
		a = a ^ b;
		b = a ^ b;
		a = a ^ b;
		
		System.out.println("a:" + a + "\tb:" +b );
		
		
		// 交换的错误使用
		int[] arr = {3,1,100};
		
		int i = 0;
		int j = 0;
		
		arr[i] = arr[i] ^ arr[j];
		arr[j] = arr[i] ^ arr[j];
		arr[i] = arr[i] ^ arr[j];
		
		System.out.println( "======swap fail======" );
		System.out.println(arr[i] + " , " + arr[j]);

		System.out.println( "=====" );
		for(int s:arr) {
			System.out.print( s + " ");
		}

		swap(arr, 0, 0);
		System.out.println( "\n=====" );
		for(int s:arr) {
			System.out.print( s + " ");
		}
	}
	
	
	public static void swap (int[] arr, int i, int j) {
		// arr[0] = arr[0] ^ arr[0];
		arr[i]  = arr[i] ^ arr[j];
		arr[j]  = arr[i] ^ arr[j];
		arr[i]  = arr[i] ^ arr[j];
	}
	
	

}

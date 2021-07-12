package class11_binary_tree;

/**
 * 有趣的折纸问题，其实并不难
 * 但是左老师直接利用递归就能给出结果，太强了！
 */
public class Code07_PaperFolding {

	public static void printAllFolds(int N) {
		process(1, N, true);
		System.out.println();
	}

	// 当前你来了一个节点，脑海中想象的！
	// 这个节点在第i层，一共有N层，N固定不变的
	// 这个节点如果是凹的话，down = T
	// 这个节点如果是凸的话，down = F
	// 函数的功能：中序打印以你想象的节点为头的整棵树！
	public static void process(int i, int N, boolean down) {
		if (i > N) {
			return;
		}
		// 左子必为凹
		process(i + 1, N, true);
		System.out.print(down ? "凹 " : "凸 ");
		// 右子必为凸
		process(i + 1, N, false);
	}

	public static void main(String[] args) {
		int N = 4;
		printAllFolds(N);
	}
}

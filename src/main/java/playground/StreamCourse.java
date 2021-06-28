package playground;

import java.io.IOException;
import java.util.HashMap;

/**
 * @author WangWy
 * @program Algorithm
 * @create 2021-05-28 14:45
 **/
public class StreamCourse {
	public static void main(String[] args) throws IOException {
		// final BufferedReader bufferedReader = new BufferedReader(
		// 		new InputStreamReader(new URL("http://www.baidu.com").openStream(), StandardCharsets.UTF_8)
		// );
		// String s  ;
		// while((s = bufferedReader.readLine())!=null) {
		// 	System.out.println(s);
		// }
		// System.out.println(Integer.TYPE.getName());
		// System.out.println(Integer[].class.getName());
		HashMap<Object, Object> objectObjectHashMap = new HashMap<>();
		objectObjectHashMap.put("abc", "def");
		objectObjectHashMap.put(234L, 341L);
		System.out.println("abcdef".hashCode());
		System.out.println((int) 'a');
	}
}

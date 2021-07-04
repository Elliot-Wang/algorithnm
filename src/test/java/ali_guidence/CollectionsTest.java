package ali_guidence;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

public class CollectionsTest {

    @Test
    /**
     * asList的返回对象是一个Arrays内部类，并没有实现集合的修改方法。
     * Arrays.asList体现的是适配器模式，只是转换接口，后台的数据仍是数组
     */
    public void asList() {
        String[] str = new String[] { "yang", "hao" };
        List<String> list = Arrays.asList(str);
        try {
            list.add("abc");
        } catch (Exception e) {
            System.out.println( "add unsupported! " + e.toString() );
        }
        try {
            list.remove("hao");
        } catch (Exception e) {
            System.out.println( "remove unsupported! " + e.toString() );
        }
        list.set(1, "abc");
        System.out.println( list );

        // 换成正常的数组容器就能操作了。
        ArrayList<String> reallyList = new ArrayList<String>();
        reallyList.addAll(list);
        reallyList.add("def");
        reallyList.remove("abc");
        System.out.println( reallyList );
    }
}
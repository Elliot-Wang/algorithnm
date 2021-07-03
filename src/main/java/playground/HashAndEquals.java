package playground;

public class HashAndEquals {
    
    public static void main(String[] args) {
        checkString();
        checkDouble();
    }

    /**
     * Double的hashcode
     */
    private static void checkDouble() {
        Double c = 12.4;
        Double d = 12.4;
        System.out.println( c.hashCode() == d.hashCode() );
        // 计算当中double都会出现精度误差，所以不能直接equals
        System.out.println( c.equals(d) );
        double diff = 1e-3;
        System.out.println(Math.abs(c - d) < diff);
    }

    /**
     * String的equals已经被覆写过了
     */
    private static void checkString() {
        String a = "abc";
        String b = "abc";
        System.out.println( a==b );
        System.out.println( a.equals(b) );
        System.out.println( a.hashCode() == b.hashCode() );
    }
}

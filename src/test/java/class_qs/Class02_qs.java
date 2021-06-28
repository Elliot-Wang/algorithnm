package class_qs;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import class02_xor_opt.Code03_KM;

public class Class02_qs {
    
    @Test
    public void kmTimesDebug() {
        int[] arr = new int[] {
            2, 3, 2, 3, 1, 1, 3, 3, 3
        };
        int result = Code03_KM.onlyKTimes(arr, 5, 2);
        System.out.println( result);
        // TODO 这个KM Times算法好像有点问题，得不到答案
        assertEquals(3, result);
    }
}

package ali_guidence;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import org.junit.jupiter.api.Test;

public class OtherTest {
    
    @Test
    public void dateFormat() throws ParseException {
        String str = "2021 07 08 11:28 pm";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy MM dd hh:mm a", Locale.US);
        System.out.println( sdf.parse(str) );
    }
}

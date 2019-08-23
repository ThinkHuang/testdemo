package date;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间字符串进行大小比较
 * @author Administrator
 *
 */
public class TimeStringCompare {
    
    public static void main(String[] args) throws ParseException {
        String timeString1 = "12:59";
        String timeString2 = "13:00";
        
        DateFormat df = new  SimpleDateFormat("HH:mm");
        Date date1 = df.parse(timeString1);
        Date date2 = df.parse(timeString2);
        
        System.out.println(date1.after(date2));
        
    }
}

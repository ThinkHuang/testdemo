package date;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class HHmmDateCompare {
    
    private static final Format format = new DecimalFormat("#");
    
    public static void main(String[] args) throws ParseException {
        DateFormat df = new SimpleDateFormat("HH:mm");
        String s1 = "09:00";
        String s2 = "18:30";
        String s3 = "12:30";
        String s4 = "14:00";
        
        int s = getWorkTime(df.parse(s1), df.parse(s2), df.parse(s3), df.parse(s4));
        System.out.println(s);
        
    }
    
    private static int getWorkTime(Date startTime, Date endTime, Date startLunchTime, Date endLunchTime) {
        if (null == startTime || null == endTime) {
            return 0;
        }
        if (null == startLunchTime || null == endLunchTime) {
            return calcBetweenSeconds(startTime, endTime);
        }
        if (endLunchTime.before(startTime) || startLunchTime.after(endTime)) {
            return calcBetweenSeconds(startTime, endTime);
        } else {
            return calcBetweenSeconds(startTime, startLunchTime) + calcBetweenSeconds(endLunchTime, endTime);
        }
    }
    
    private static Integer calcBetweenSeconds(Date startTime, Date endTime) {
        if (null == startTime || null == endTime) {
            return 0;
        }
        Long interval = endTime.getTime() - startTime.getTime();
        Integer result = Integer.valueOf(format.format(interval / (1000)));
        if (result <= 0) {
            return 0;
        } else if (result > 0 && result <= 1) {
            return 1;
        } else {
            int minutes = result.intValue();
            if (minutes == result) {
                // 如果相差的分钟数刚好是整数，则直接返回，不用进行转换
                return result;
            }
            return result.intValue() + 1;
        }
    }
}

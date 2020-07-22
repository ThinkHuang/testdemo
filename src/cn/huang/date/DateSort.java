package date;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

public class DateSort {
    
    @Test
    public void sort() throws ParseException {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        final Set<Date> purePeriod = new LinkedHashSet<>();
        purePeriod.add(df.parse("2020-05-01"));
        purePeriod.add(df.parse("2020-05-17"));
        purePeriod.add(df.parse("2020-05-13"));
        purePeriod.add(df.parse("2020-05-05"));
        purePeriod.add(df.parse("2020-05-31"));
        
        
        List<Date> sortList = new ArrayList<>(purePeriod);
        sortList.sort(new Comparator<Date>() {

            @Override
            public int compare(Date o1, Date o2) {
                return o1.before(o2) ? -1 : 1;
            }
        });
        
        System.out.println(sortList);
    }
}

package Time;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

import static java.time.temporal.ChronoUnit.DAYS;


public class TimeTest {
    public static void main(String[] args){
        //获取今天的日期，年，月，日
        LocalDate today = LocalDate.now();
        int year = today.getYear();
        int month = today.getMonthValue();
        int day = today.getDayOfMonth();
        System.out.printf("Year : %d  Month : %d  day : %d t %n", year, month, day);
        System.out.println("Today's Local date : " + today);

        //处理特定日期
        LocalDate testDate = LocalDate.of(2019, 05, 01);
        System.out.println("Date of test is : " + testDate);

        //判断两个日期是否相等.LocalDate重载了equal方法
        LocalDate date1 = LocalDate.of(2019, 05, 16);
        if(date1.equals(today)){
            System.out.printf("Today %s and date1 %s are same date %n", today, date1);
        }

        //在现有的时间上增加小时
        LocalTime time = LocalTime.now();
        LocalTime newTime = time.plusHours(2); //
        System.out.println("Time after 2 hours : " +  newTime);

        //计算一周后的日期
        LocalDate nextWeek = today.plus(1, ChronoUnit.WEEKS);
        System.out.println("Today is : " + today);
        System.out.println("Date after 1 week : " + nextWeek);

        // Returns the current time based on your system clock and set to UTC.
        Clock clock = Clock.systemUTC();
        System.out.println("Clock : " + clock);

        // Returns time based on system clock zone
        Clock defaultClock = Clock.systemDefaultZone();
        System.out.println("Clock : " + clock);

        //判断日期是早于还是晚于另一个日期
        LocalDate tomorrow = LocalDate.of(2019, 5, 17);
        if(tomorrow.isAfter(today)){
            System.out.println("Tomorrow comes after today");
        }

        LocalDate yesterday = today.minus(1, DAYS);

        if(yesterday.isBefore(today)){
            System.out.println("Yesterday is day before today");
        }

        //获取当前的时间戳
        Instant timestamp = Instant.now();
        System.out.println("What is value of this instant " + timestamp);
    }
}

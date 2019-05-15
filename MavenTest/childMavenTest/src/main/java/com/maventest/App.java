package com.maventest;

import java.util.Date;

//子项目虽然没有依赖jar包，但是通过父项目达到了依赖的效果。
import cn.hutool.core.date.DateUtil;

public class App 
{
    public static void main( String[] args )
    {
        String dateStr = "2019-5-15 12:12:12";
        Date date = DateUtil.parse(dateStr);
        System.out.println(date);
        System.out.println( "Hello World!" );
    }
}

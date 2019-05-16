package Lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
/*
引用容器中对象的方法
 */
public class TestLambda4 {
    public static void main(String[] args) {
        Random r = new Random();
        List<Man> mans = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            mans.add(new Man("man " + i, r.nextInt(1000), r.nextInt(100)));
        }
        System.out.println("初始化后的集合：");
        System.out.println(mans);

        System.out.println("Lambda表达式中调用容器中的对象的matched方法：");
        filter(mans,h-> h.matched());

        System.out.println("引用容器中对象的方法 过滤结果：");
        filter(mans, Man::matched);
    }

    public  boolean testMan(Man m) {
        return m.old > 50 && m.money > 500;
    }

    private static void filter(List<Man> mans, Checker checker) {
        for (Man m : mans) {
            if (checker.test(m))
                System.out.print(m);
        }
    }
    }

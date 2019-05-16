package Lambda;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
/*
引用对象方法
 */
public class TestLambda3 {
    public static void main(String[] args) {
        List<String> strings = Arrays.asList();
        Random r = new Random();
        List<Man> mans = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            mans.add(new Man("man " + i, r.nextInt(1000), r.nextInt(100)));
        }
        System.out.println("初始化后的集合：");
        System.out.println(mans);

        System.out.println("使用引用对象方法  的过滤结果：");
        //使用类的对象方法
        TestLambda3 testLambda3 = new TestLambda3();
        filter(mans, testLambda3::testMan);
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

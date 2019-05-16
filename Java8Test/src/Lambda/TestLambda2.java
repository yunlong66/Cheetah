package Lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/*
引用静态方法
 */
public class TestLambda2 {
    public static void main(String[] args) {
        Random r = new Random();
        List<Man> mans = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            mans.add(new Man("man " + i, r.nextInt(1000), r.nextInt(100)));
        }
        System.out.println("初始化后的集合：");
        System.out.println(mans);

//        System.out.println("在Lambda表达式中使用静态方法");
//        filter(mans, m -> TestLambda2.testMan(m) );
        System.out.println("直接引用静态方法");
        filter(mans, TestLambda2::testMan);
    }

    public static boolean testMan(Man m) {
        return m.old > 50 && m.money > 500;
    }

    private static void filter(List<Man> mans, Checker checker) {
        for (Man m : mans) {
            if (checker.test(m))
                System.out.print(m);
        }
    }

}

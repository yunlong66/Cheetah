package Lambda;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class TestLambda1 {
    public static void main(String[] args) {
        Random r =new Random();
        List<Man> mans = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            mans.add(new Man("man "+ i, r.nextInt(1000), r.nextInt(100)));
        }
        System.out.println("初始化后的集合：");
        System.out.println(mans);

//        //匿名类
//        Comparator<Man> c = new Comparator<Man>() {
//            @Override
//            public int compare(Man m1, Man m2) {
//                return m1.old>=m2.old?1:-1;
//            }
//        };

        //Lambda表达式
//        c = (Man m1, Man m2)-> {
//            return m1.old>=m2.old?1:-1;
//        };
//        //去掉 return和大括号
//        c = (Man m1, Man m2)->m1.old>=m2.old?1:-1;
        //去掉 参数类型
//        c = (m1, m2)->m1.old>=m2.old?1:-1;
//        //有两个参数，无法去掉圆括号
//        Collections.sort(mans,c);

        //直接把Lambda表达式作为参数传进去
        Collections.sort(mans,(m1, m2)->m1.old>=m2.old?1:-1);
        System.out.println(mans);
    }
}

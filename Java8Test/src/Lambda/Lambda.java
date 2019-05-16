package Lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

//Lambda表达式方式实现
public class Lambda {
    public static void main(String[] args){
        Random r = new Random();
        List<Man> mans = new ArrayList<>();
        for (int i = 0; i < 10; i++){
            mans.add(new Man("man"+ i,r.nextInt(1000),r.nextInt(100)));
        }
        System.out.println("初始化后的集合：");
        System.out.println(mans);
        System.out.println("使用Lambda方式筛选出 old>50 && money>500的man");
        //匿名类的正常写法
//        Checker checker = new Checker() {
//            @Override
//            public boolean test(Man m) {
//                return (m.old > 50 && m.money > 500);
//            }
//        };

        // 把new HeroChcekcer，方法名，方法返回类型信息去掉
        // 只保留方法参数和方法体
        // 参数和方法体之间加上符号 ->
//        Checker c2 = (Man m) -> {
//            return m.old > 50 && m.money > 500;
//        };

        // 把return和{}去掉
        //Checker c3 = (Man m) -> m.old > 50 && m.money > 500;

        // 把 参数类型和圆括号去掉
        //Checker c4 = m -> m.old > 50 && m.money > 500;

        // 把c4作为参数传递进去
        //filter(mans, c4);

        // 直接把表达式传递进去
        filter(mans, m -> m.old > 50 && m.money > 500);
    }

    private static void filter(List<Man> mans,Checker checker){
        for (Man m :mans){
            if (checker.test(m)){
                System.out.println(m);
            }
        }
    }
}

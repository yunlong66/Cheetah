package Lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

//匿名类方式实现
public class Anonymous {
    public static void main(String[] args){
        Random r = new Random();
        List<Man> mans = new ArrayList<>();
        for (int i = 0; i < 10; i++){
            mans.add(new Man("man"+ i,r.nextInt(1000),r.nextInt(100)));
        }
        System.out.println("初始化后的集合：");
        System.out.println(mans);
        System.out.println("使用匿名类方式筛选出 old>50 && money>500的man");
        Checker checker = new Checker() {
            @Override
            public boolean test(Man m) {
                return (m.old > 50 && m.money > 500);
            }
        };
        filter(mans, checker);
    }
    private static void filter(List<Man> mans,Checker checker){
        for (Man m :mans){
            if (checker.test(m)){
                System.out.println(m);
            }
        }
    }
}

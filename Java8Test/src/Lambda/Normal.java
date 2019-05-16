package Lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

//用普通的方法筛选满足条件的数据
public class Normal {
    public static void main(String[] args){
        Random r = new Random();
        List<Man> mans = new ArrayList<>();
        for (int i = 0; i < 10; i++){
            mans.add(new Man("man"+ i,r.nextInt(1000),r.nextInt(100)));
        }
        System.out.println("初始化后的集合：");
        System.out.println(mans);
        System.out.println("筛选出 old>50 && money>500的man");
        filter(mans);
    }

    private static void filter(List<Man> mans){
        for (Man m :mans){
            if (m.old > 50 && m.money > 500){
                System.out.println(m);
            }
        }
    }
}


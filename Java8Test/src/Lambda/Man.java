package Lambda;

/*
测试类
用于比较筛选的数据
 */

public class Man implements Comparable<Man> {
    public String name;
    public float money;
    public int old;
    public Man(){

    }

    public Man(String name){
        this.name = name;
    }

    public Man(String name, float money, int old){
        this.name = name;
        this.money = money;
        this.old = old;
    }

    @Override
    public int compareTo(Man o) {
        //测试，比较old大小
        if(old < o.old){
            return 1;
        }else {
            return -1;
        }
    }

    @Override
    public String toString() {
        return "Man [name=" + name + ", money=" + money + ", old=" + old + "]\r\n";
    }

    public  boolean matched() {
        return this.old > 50 && this.money > 500;
    }
}


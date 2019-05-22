package 创建型.代理;

public class TestStatic {
    public static void main(String[] args) {
        //目标对象
       UserDao u = new UserDao();

        //代理对象,把目标对象传给代理对象,建立代理关系
        UserDaoProxy proxy = new UserDaoProxy(u);

        proxy.save();//执行的是代理的方法
    }
}

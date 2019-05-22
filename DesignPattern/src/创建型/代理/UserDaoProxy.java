package 创建型.代理;
/**
 * 代理对象,静态代理
 */
public class UserDaoProxy implements IUserDao{
    private UserDao target;

    public UserDaoProxy(UserDao u){
        this.target = u;
    }

    public void save(){
        System.out.println("开始事务...");
        target.save();
        System.out.println("提交事务...");
    }
}

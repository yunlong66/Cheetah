package 创建型.代理;
/**
 * 接口实现
 * 目标对象
 */
public class UserDao implements IUserDao {
    public void save() {
        System.out.println("目标对象----已保存----");
    }
}

package tmall.dao;

import tmall.bean.DBUtil;
import tmall.bean.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    public int getTotal() {
        int total = 0;
        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {

            String sql = "select count(*) from User";

            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                total = rs.getInt(1);
            }
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return total;
    }

    public void add(User bean) {

        String sql = "insert into user values(null ,? ,?)";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {

            ps.setString(1, bean.getName());
            ps.setString(2, bean.getPassword());

            ps.execute();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                int id = rs.getInt(1);
                bean.setId(id);
            }
        } catch (SQLException e) {

            e.printStackTrace();
        }
    }

    public void update(User bean) {

        String sql = "update user set name= ? , password = ? where id = ? ";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {

            ps.setString(1, bean.getName());
            ps.setString(2, bean.getPassword());
            ps.setInt(3, bean.getId());

            ps.execute();

        } catch (SQLException e) {

            e.printStackTrace();
        }

    }

    public void delete(int id) {

        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {

            String sql = "delete from User where id = " + id;

            s.execute(sql);

        } catch (SQLException e) {

            e.printStackTrace();
        }
    }



    public User get(int id){
        User bean = null;
        try (Connection c= DBUtil.getConnection();Statement ps = c.createStatement();){
            String sql = "select * from user where id =" + id;
            ResultSet rs = ps.executeQuery(sql);
            while (rs.next()){
                bean = new User();
                String name = rs.getString("name");
                String password = rs.getString("password");

                bean.setName(name);
                bean.setPassword(password);
                bean.setId(id);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return bean;
    }

    public List<User> list() {
        return list(0, Short.MAX_VALUE);
    }

    //用户列表分页查询
    public List<User> list(int start, int count){
        List<User> beans = new ArrayList<User>();
        String sql = "select * from User order by id desc limit ? ,?";

        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);){

            ps.setInt(1,start);
            ps.setInt(2,count);

            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                User bean = new User();
                String name = rs.getString("name");
                String password = rs.getString("password");
                int id = rs.getInt(1);
                bean.setName(name);
                bean.setPassword(password);
                beans.add(bean);
                bean.setId(id);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return beans;
    }

    //根据用户名查找
    public User get(String name) {
        User bean = null;

        String sql = "select * from User where name = ?";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, name);
            ResultSet rs =ps.executeQuery();

            if (rs.next()) {
                bean = new User();
                int id = rs.getInt("id");
                bean.setName(name);
                String password = rs.getString("password");
                bean.setPassword(password);
                bean.setId(id);
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }
        return bean;
    }

    //根据用户名和密码匹配
    public User get(String name, String password) {
        User bean = null;

        String sql = "select * from User where name = ? and password=?";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, name);
            ps.setString(2, password);
            ResultSet rs =ps.executeQuery();

            if (rs.next()) {
                bean = new User();
                int id = rs.getInt("id");
                bean.setName(name);
                bean.setPassword(password);
                bean.setId(id);
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }
        return bean;
    }

    //用户名是否存在
    public boolean isExist(String name) {
        User user = get(name);
        return user!=null;

    }
}

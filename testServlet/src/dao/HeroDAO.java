package dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import bean.Hero;


public class HeroDAO {

    //在构造方法里加载jdbc驱动
    public HeroDAO(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    //拿到数据库连接
    public Connection getConnection()throws SQLException{
        return  DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/how2java?characterEncoding=UTF-8", "root",
        "admin");
    }
    //获取总数
    public int getTotal(){
        int total = 0;
        try (Connection c = getConnection(); Statement s = c.createStatement();){
            String sql = "select count(*) from hero";
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                total = rs.getInt(1);
            }
            System.out.println("total:" + total);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return total;
    }

    //DAO的方式向数据库添加对象
    public void add(Hero hero){
        String sql = "insert into hero values(null,?,?,?)";
        try (Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql);){
            ps.setString(1, hero.name);
            ps.setFloat(2, hero.hp);
            ps.setInt(3, hero.damage);
            ps.execute();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                int id = rs.getInt(1);
                hero.id = id;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    //更新Hero对象
    public void update(Hero hero) {
        String sql = "update hero set name= ?, hp = ? , damage = ? where id = ?";
        try (Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {

            ps.setString(1, hero.name);
            ps.setFloat(2, hero.hp);
            ps.setInt(3, hero.damage);
            ps.setInt(4, hero.id);

            ps.execute();

        } catch (SQLException e) {

            e.printStackTrace();
        }
    }

    //删除一个对象
    public void delete(int id) {
        try (Connection c = getConnection(); Statement s = c.createStatement();) {
            String sql = "delete from hero where id = " + id;
            s.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Hero getHero(int id){
        Hero hero = null;
        try(Connection c = getConnection(); Statement s = c.createStatement();){
            String sql = "select * from gero where id = " + id;
            ResultSet rs = s.executeQuery(sql);
            if (rs.next()){
                hero = new Hero();
                String name = rs.getString("name");
                float hp = rs.getFloat("hp");
                int demage = rs.getInt(4);
                hero.name = name;
                hero.hp = hp;
                hero.damage = demage;
                hero.id = id;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return hero;
    }

    public List<Hero> list(){
        return List(0, Short.MAX_VALUE);
    }
    public List<Hero> List(int start, int count){
        List<Hero> heros = new ArrayList<Hero>();
        String sql = "select * from hero order by id desc limit ?,? ";
        try (Connection c = getConnection();PreparedStatement ps = c.prepareStatement(sql);){
            ps.setInt(1,start);
            ps.setInt(2,count);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Hero hero = new Hero();
                int id = rs.getInt(1);
                String name = rs.getString(2);
                float hp = rs.getFloat("hp");
                int damage = rs.getInt(4);
                hero.id = id;
                hero.name = name;
                hero.hp = hp;
                hero.damage = damage;
                heros.add(hero);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return heros;
    }
}

package tmall.bean;

public class User {
    private String name;
    private int id;
    private String password;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    //返回用户的匿名名称，在评论中使用
    public String getAnonymousName(){
        if (name == null)
            return null;
        if (name.length() <= 1)
            return "*";
        if (name.length() == 2)
            return name.substring(0,1)+"*";

        //此处可改进，生成一个length长度的'*'字符串
        char[] cs = name.toCharArray();
        for(int i = 0;i<cs.length-1;i++){
            cs[i] = '*';
        }
        return new String(cs);
    }
}

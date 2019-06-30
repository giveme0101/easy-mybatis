package cn.kiven.entity;/**
 * Created by xiaju on 2018/11/30.
 */

/**
 * @Author Lyle xiajun94@FoxMail.com
 * @Description
 * @name User
 * @Date 2018/11/30 22:50
 */
public class User {

    private String id;
    private String username;

    public String getId() {
        return id;
    }

    public User setId(String id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public User setUsername(String username) {
        this.username = username;
        return this;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("User{");
        sb.append("id='").append(id).append('\'');
        sb.append(", username='").append(username).append('\'');
        sb.append('}');
        return sb.toString();
    }
}

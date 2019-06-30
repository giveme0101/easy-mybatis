package cn.tech.entity;
/**
 * Created by Administrator on 2018/11/30.
 */

import java.io.Serializable;

/**
 * @Author Lyle xiajun94@FoxMail.com
 * @Description
 * @name User
 * @Date 2018/11/30 11:13
 */
public class User implements Serializable {

    private String id;
    private String name;
    private String gender;

    public String getId() {
        return id;
    }

    public User setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public User setName(String name) {
        this.name = name;
        return this;
    }

    public String getGender() {
        return gender;
    }

    public User setGender(String gender) {
        this.gender = gender;
        return this;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("User{");
        sb.append("id='").append(id).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", gender='").append(gender).append('\'');
        sb.append('}');
        return sb.toString();
    }
}

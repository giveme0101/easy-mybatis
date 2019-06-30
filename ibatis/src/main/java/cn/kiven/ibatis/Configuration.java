package cn.kiven.ibatis;/**
 * Created by xiaju on 2018/11/30.
 */

import cn.kiven.ibatis.bean.MapperBean;

import java.util.ArrayList;
import java.util.List;

/**
 *  1. 存放sql连接信息
 *  2. 存放mapper/*.xml解析后的信息
 *
 * @Author Lyle xiajun94@FoxMail.com
 * @Description
 * @name Configuration
 * @Date 2018/11/30 22:10
 */
public class Configuration {

    private String driver;
    private String username;
    private String password;
    private String url;

    private List<MapperBean> mapperBeans = new ArrayList<MapperBean>();

    public String getDriver() {
        return driver;
    }

    public Configuration setDriver(String driver) {
        this.driver = driver;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public Configuration setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public Configuration setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public Configuration setUrl(String url) {
        this.url = url;
        return this;
    }

    public List<MapperBean> getMapperBeans() {
        return mapperBeans;
    }

    public Configuration setMapperBeans(List<MapperBean> mapperBeans) {
        this.mapperBeans = mapperBeans;
        return this;
    }
}

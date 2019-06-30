package cn.core.ibaits.core;/**
 * Created by Administrator on 2018/11/30.
 */

import cn.core.ibaits.core.entity.Function;
import cn.core.ibaits.core.entity.MapperBean;
import cn.core.mvc.ApplicationContext;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @Author Lyle xiajun94@FoxMail.com
 * @Description
 * @name Config
 * @Date 2018/11/30 16:12
 */
public class Config {

    public static <T> T getBean(Class<T> clazz) throws Exception{

        SqlSession sqlSession = ApplicationContext.getBean(SqlSession.class);

        return sqlSession.getMapper(clazz);
    }

    public static Connection getConnection(){

        String driver = ApplicationContext.getProperty("driver", "");
        String username = ApplicationContext.getProperty("username", "");
        String pwd =  ApplicationContext.getProperty("pwd", "");
        String url = ApplicationContext.getProperty("url", "");

        try {
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(url, username, pwd);
            return connection;
        } catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }

    public static MapperBean readMapper(String path){
        MapperBean mapper = new MapperBean();
        try{
            InputStream stream = new Config().getClass().getResourceAsStream(path);
            SAXReader reader = new SAXReader();
            Document document = reader.read(stream);
            Element root = document.getRootElement();
            mapper.setNameSpace(root.attributeValue("nameSpace").trim()); //把mapper节点的nameSpace值存为接口名
            List<Function> list = new ArrayList<>(); //用来存储方法的List
            for(Iterator rootIter = root.elementIterator(); rootIter.hasNext();) {//遍历根节点下所有子节点
                Function fun = new Function();    //用来存储一条方法的信息
                Element e = (Element) rootIter.next();
                String funcName = e.attributeValue("id").trim();
                String sql = e.getText().trim();
                String resultType = e.attributeValue("resultType").trim();
                fun.setFunName(funcName);
                Object newInstance=null;
                try {
                    newInstance = Class.forName(resultType).newInstance();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
                fun.setResultType(newInstance);
                fun.setSql(sql);
                fun.setSqlType(e.getName());
                list.add(fun);
            }
            mapper.setFunctionList(list);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return mapper;
    }

}

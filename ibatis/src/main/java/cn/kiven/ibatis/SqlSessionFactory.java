package cn.kiven.ibatis;
/**
 * Created by xiaju on 2018/11/30.
 */

import cn.kiven.ibatis.bean.MapperBean;
import cn.kiven.ibatis.impl.DefaultSqlSession;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 *  1. 加载配置文件
 *  2. 构造sqlSession对象
 *
 * @Author Lyle xiajun94@FoxMail.com
 * @Description
 * @name SqlSessionFactory
 * @Date 2018/11/30 22:20
 */
public class SqlSessionFactory {

    private static final String CONFIG = "local.properties";
    private static final String MAPPER_DIR = "mappers";

    private Configuration config = new Configuration();

    public SqlSessionFactory(){
        loadProp();
        scanMapper();
    }

    public SqlSession build(){

        return new DefaultSqlSession(config);
    }

    private void loadProp(){

        InputStream is = null;

        try {
            is = SqlSessionFactory.class.getClassLoader().getResourceAsStream(CONFIG);
            Properties prop = new Properties();
            prop.load(is);
            config.setDriver(prop.getProperty("driver"));
            config.setUsername(prop.getProperty("username"));
            config.setPassword(prop.getProperty("password"));
            config.setUrl(prop.getProperty("url"));
        } catch (Exception ex){
            ex.printStackTrace();
        } finally {
            try {
                if (null != is) is.close();
            } catch (Exception ex){}{}
        }
    }

    private void scanMapper(){

        try {
            URL uri = SqlSessionFactory.class.getClassLoader().getResource(MAPPER_DIR);
            File dir = new File(uri.toURI());
            if (null == dir || !dir.isDirectory()){
                return;
            }

            File[] files = dir.listFiles();
            for (File file : files) {
                MapperBean mapperBean = loadMapper(file);
                if (null != mapperBean)
                    config.getMapperBeans().add(mapperBean);
            }
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }

    /**
     * 將mapper.xml解析成bean對象
     *
     * @param file
     * @return
     */
    private MapperBean loadMapper(File file){

        try {
            MapperBean mapperBean = MapperBean.class.newInstance();

            SAXReader saxReader = new SAXReader();
            Document document = saxReader.read(file);

            Element root = document.getRootElement();
            String namespace = root.attributeValue("namespace");
            mapperBean.setNameSpace(namespace);

            List<Element> elements =  root.elements();
            List<MapperBean.SqlStatement> sqlStatements = new ArrayList<MapperBean.SqlStatement>(elements.size());
            for (Element ele : elements){

                String sqlType = ele.getName();
                String sqlId = ele.attributeValue("id");
                String returnType = ele.attributeValue("resultType");
                String sql = ele.getText();

                MapperBean.SqlStatement statement = MapperBean.SqlStatement.class.newInstance();
                statement.setSqlType(sqlType).setSql(sql).setSqlId(sqlId).setResultType(returnType);
                sqlStatements.add(statement);
            }
            mapperBean.setSqlStatements(sqlStatements);

            return mapperBean;
        } catch (Exception ex){
            ex.printStackTrace();
        }

        return null;
    }

}

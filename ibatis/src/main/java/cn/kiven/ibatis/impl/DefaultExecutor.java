package cn.kiven.ibatis.impl;

import cn.kiven.ibatis.Configuration;
import cn.kiven.ibatis.Executor;
import cn.kiven.ibatis.bean.MapperBean;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author Lyle xiajun94@FoxMail.com
 * @Description
 * @name DefaultExecutor
 * @Date 2018/11/30 23:34
 */
public class DefaultExecutor implements Executor {

    private Configuration config = null;

    public DefaultExecutor(Configuration config) {
        this.config = config;
    }

    public <T> T query(String statement, Object args) {

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            MapperBean.SqlStatement sqlStatement = getSqlStatement(statement);
            System.out.println(sqlStatement.getSql());

            Class.forName(config.getDriver());
            conn = DriverManager.getConnection(config.getUrl(), config.getUsername(), config.getPassword());

            pstmt = conn.prepareStatement(sqlStatement.getSql());
            if (null != args)
                pstmt.setString(1, (String) args);

            List<T> ts = new ArrayList<T>();
            rs = pstmt.executeQuery();
            while (rs.next()){
                mapping(ts, rs, sqlStatement);
            }
            return (T) ts;
        } catch (Exception ex){
            ex.printStackTrace();
        } finally {
            tryClose(conn, pstmt, rs);
        }

        return null;
    }

    private <T> void mapping(List<T> ts, ResultSet rs, MapperBean.SqlStatement sqlStatement){

        try {
            Class clz = Class.forName(sqlStatement.getResultType());
            Object o = clz.newInstance();

            for (int i = 1, j = rs.getMetaData().getColumnCount(); i <= j; i++){

                String name = rs.getMetaData().getColumnLabel(i);
                String value = rs.getString(i);

                Field filed = clz.getDeclaredField(name);
                filed.setAccessible(true);
                filed.set(o, value);
            }

            ts.add((T) o);
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }

    private void tryClose(Connection conn, PreparedStatement pstmt, ResultSet rs){
        try {
            if (null != rs) rs.close();
        } catch (Exception ex){}

        try {
            if (null != pstmt) pstmt.close();
        } catch (Exception ex){}

        try {
            if (null != conn) conn.close();
        } catch (Exception ex){}

    }

    private MapperBean.SqlStatement getSqlStatement(String statement) {

        List<MapperBean> mapperBeans = config.getMapperBeans();
        for (MapperBean mapperBean : mapperBeans) {

            if (statement.startsWith(mapperBean.getNameSpace())){

                List<MapperBean.SqlStatement> statements = mapperBean.getSqlStatements();
                for (MapperBean.SqlStatement sqlStatement : statements) {

                    if (statement.endsWith(sqlStatement.getSqlId())){
                        return sqlStatement;
                    }
                }
            }
        }

        return null;
    }
}

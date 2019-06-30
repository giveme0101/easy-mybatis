package cn.core.ibaits.core.impl;
/**
 * Created by Administrator on 2018/11/30.
 */

import cn.core.ibaits.core.Config;
import cn.core.ibaits.core.Executeable;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Author Lyle xiajun94@FoxMail.com
 * @Description
 * @name Executor
 * @Date 2018/11/30 11:19
 */
public class Executor implements Executeable {

    @Override
    public <T> T query(String sql, Object param, Class rtn) {

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            Object [] p = (Object[]) param;

            conn = Config.getConnection();
            pstmt = conn.prepareStatement(sql);
            if (null != p){
                for (int i = 0; i < p.length; i++){
                    pstmt.setString(i + 1, (String) p[i]);
                }
            }
            rs = pstmt.executeQuery();
            List<T> result = new ArrayList();
            while (rs.next()){
                result.add((T) build(rs, rtn));
            }

            return (T) result;
        } catch (Exception ex){
            ex.printStackTrace();
        } finally {
            close(conn, pstmt,rs);
        }

        return (T) Collections.singletonList(new Object());
    }

    private <T> T build(ResultSet rs, Class rtn) throws Exception{

        Object o = rtn.newInstance();

        int rtnCount = rs.getMetaData().getColumnCount();
        for (int i = 1; i <= rtnCount; i++){
            String label = rs.getMetaData().getColumnLabel(i).toLowerCase();
            String value = rs.getString(i);

            Field field = rtn.getDeclaredField(label);
            field.setAccessible(true);
            field.set(o, value);
        }

        return (T) o;
    }

    private void close (Connection conn, Statement stmt, ResultSet rs){

        try {
            if (null != conn && !conn.isClosed()) conn.close();
        } catch (Exception ex){}

        try {
            if (null != stmt && !stmt.isClosed()) stmt.close();
        } catch (Exception ex){}

        try {
            if (null != rs && !rs.isClosed()) rs.close();
        } catch (Exception ex){}
    }

}

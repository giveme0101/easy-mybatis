package cn.kiven.ibatis.bean;/**
 * Created by xiaju on 2018/11/30.
 */

import java.util.List;

/**
 * @Author Lyle xiajun94@FoxMail.com
 * @Description
 * @name MapperBean
 * @Date 2018/11/30 22:46
 */
public class MapperBean {

    private String nameSpace;
    private List<SqlStatement> sqlStatements;

    public static class SqlStatement{

        private String sqlId;
        private String sqlType;
        private String resultType;
        private String sql;

        public String getSqlId() {
            return sqlId;
        }

        public SqlStatement setSqlId(String sqlId) {
            this.sqlId = sqlId;
            return this;
        }

        public String getSqlType() {
            return sqlType;
        }

        public SqlStatement setSqlType(String sqlType) {
            this.sqlType = sqlType;
            return this;
        }

        public String getResultType() {
            return resultType;
        }

        public SqlStatement setResultType(String resultType) {
            this.resultType = resultType;
            return this;
        }

        public String getSql() {
            return sql;
        }

        public SqlStatement setSql(String sql) {
            this.sql = sql;
            return this;
        }

        @Override
        public String toString() {
            final StringBuffer sb = new StringBuffer("SqlStatement{");
            sb.append("sqlId='").append(sqlId).append('\'');
            sb.append(", sqlType='").append(sqlType).append('\'');
            sb.append(", resultType='").append(resultType).append('\'');
            sb.append(", sql='").append(sql).append('\'');
            sb.append('}');
            return sb.toString();
        }
    }

    public String getNameSpace() {
        return nameSpace;
    }

    public MapperBean setNameSpace(String nameSpace) {
        this.nameSpace = nameSpace;
        return this;
    }

    public List<SqlStatement> getSqlStatements() {
        return sqlStatements;
    }

    public MapperBean setSqlStatements(List<SqlStatement> sqlStatements) {
        this.sqlStatements = sqlStatements;
        return this;
    }
}

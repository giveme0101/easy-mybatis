package cn.core.ibaits.core.entity;/**
 * Created by Administrator on 2018/11/30.
 */

/**
 * @Author Lyle xiajun94@FoxMail.com
 * @Description
 * @name Function
 * @Date 2018/11/30 16:08
 */
public class Function {

    private String funName;
    private String sql;
    private Object resultType;
    private String parameterType;
    private String sqlType;

    public String getFunName() {
        return funName;
    }

    public Function setFunName(String funName) {
        this.funName = funName;
        return this;
    }

    public String getSql() {
        return sql;
    }

    public Function setSql(String sql) {
        this.sql = sql;
        return this;
    }

    public Object getResultType() {
        return resultType;
    }

    public Function setResultType(Object resultType) {
        this.resultType = resultType;
        return this;
    }

    public String getParameterType() {
        return parameterType;
    }

    public Function setParameterType(String parameterType) {
        this.parameterType = parameterType;
        return this;
    }

    public String getSqlType() {
        return sqlType;
    }

    public Function setSqlType(String sqlType) {
        this.sqlType = sqlType;
        return this;
    }
}

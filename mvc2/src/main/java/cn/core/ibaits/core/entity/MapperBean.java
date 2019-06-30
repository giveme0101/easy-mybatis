package cn.core.ibaits.core.entity;/**
 * Created by Administrator on 2018/11/30.
 */

import java.util.List;

/**
 * @Author Lyle xiajun94@FoxMail.com
 * @Description
 * @name MapperBean
 * @Date 2018/11/30 16:10
 */
public class MapperBean {

    private String nameSpace;
    private List<Function> functionList;

    public String getNameSpace() {
        return nameSpace;
    }

    public MapperBean setNameSpace(String nameSpace) {
        this.nameSpace = nameSpace;
        return this;
    }

    public List<Function> getFunctionList() {
        return functionList;
    }

    public MapperBean setFunctionList(List<Function> functionList) {
        this.functionList = functionList;
        return this;
    }
}

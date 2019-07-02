package org.kevin;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Lyle xiajun94@FoxMail.com
 * @Description
 * @name org.kevin.DefaultFilterChain
 * @Date 2019/06/03 11:09
 */
public class DefaultFilterChain implements FilterChain{

    private List<Filter> filterList = new ArrayList<Filter>();
    private int index = 0 ;

    public DefaultFilterChain(){
    }

    public DefaultFilterChain addFilter(Filter filter){
        filterList.add(filter);
        return this;
    }

    public void doFilter(Request request, Response response) {

        if (index == filterList.size()){
            return;
        }

        filterList.get(index++).doFilter(request, response, this);
    }
}

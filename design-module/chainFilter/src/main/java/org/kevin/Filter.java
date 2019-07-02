package org.kevin;

/**
 * @Author Lyle xiajun94@FoxMail.com
 * @Description
 * @name org.kevin.Filter
 * @Date 2019/06/03 11:05
 */
public interface Filter {

    void doFilter(Request request, Response response, FilterChain defaultFilterChain);

}

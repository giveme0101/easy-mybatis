package org.kevin;

/**
 * @Author Lyle xiajun94@FoxMail.com
 * @Description
 * @name org.kevin.HTMLFilter
 * @Date 2019/06/03 11:13
 */
public class HTMLFilter implements Filter{

    public void doFilter(Request request, Response response, FilterChain chain) {

        request.setRequestMessage(request.getRequestMessage()
                .replace('<', '[')
                .replace('>', ']')
                + "---org.kevin.HTMLFilter");

        // 执行下一条
        chain.doFilter(request, response);

        response.setResponseMessage(response.getResponseMessage().concat("---org.kevin.HTMLFilter"));
    }

}

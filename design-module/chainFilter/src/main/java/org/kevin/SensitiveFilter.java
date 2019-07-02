package org.kevin;

/**
 * @Author Lyle xiajun94@FoxMail.com
 * @Description
 * @name org.kevin.SensitiveFilter
 * @Date 2019/06/03 11:19
 */
public class SensitiveFilter implements Filter {

    public void doFilter(Request request, Response response, FilterChain chain) {

        //处理字符串中的敏感信息，将被就业和谐成就业
        request.setRequestMessage(request.getRequestMessage()
                .replace("被就业", "就业")
                .replace("敏感", "")
                + "---sensitiveFilter()");

        chain.doFilter(request, response);
        response.setResponseMessage(response.getResponseMessage() + "---sensitiveFilter()");
    }
}
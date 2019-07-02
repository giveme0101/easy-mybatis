package org.kevin;

/**
 * @Author Lyle xiajun94@FoxMail.com
 * @Description
 * @name org.kevin.FaceFiilter
 * @Date 2019/06/03 11:21
 */
public class FaceFiilter implements Filter {

    public void doFilter(Request request, Response response, FilterChain chain) {

            //将字符串中出现的":):"转换成"^V^";
            request.setRequestMessage(request.getRequestMessage()
                    .replace(":):", "^V^")
                    + "----FaceFilter()");

            chain.doFilter(request, response);
            response.setResponseMessage(response.getResponseMessage() + "---FaceFilter()");
    }
}

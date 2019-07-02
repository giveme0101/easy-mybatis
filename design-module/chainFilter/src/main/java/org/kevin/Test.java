package org.kevin;

import java.util.Optional;

/**
 * @Author Lyle xiajun94@FoxMail.com
 * @Description
 * @name org.kevin.Test
 * @Date 2019/06/03 11:24
 */
public class Test {

    public static void main(String[] args) {

        Request request = Request.builder().requestMessage(":):,<script>,敏感,被就业,网络授课").build();
        Response response = new Response("");

        new DefaultFilterChain()
                .addFilter(new HTMLFilter())
                .addFilter(new SensitiveFilter())
                .addFilter(new FaceFiilter())
                .doFilter(request, response);

        Optional.of(request).ifPresent(System.out::println);
        Optional.of(response).ifPresent(System.out::println);
    }
}

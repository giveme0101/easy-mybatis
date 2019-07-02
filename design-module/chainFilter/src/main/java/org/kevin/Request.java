package org.kevin;

import lombok.*;

import java.io.Serializable;

/**
 * @Author Lyle xiajun94@FoxMail.com
 * @Description
 * @name org.kevin.Request
 * @Date 2019/06/03 11:06
 */
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Request implements Serializable {

    private String requestMessage;
}

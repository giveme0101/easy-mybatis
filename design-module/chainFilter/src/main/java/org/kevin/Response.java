package org.kevin;

import lombok.*;

import java.io.Serializable;

/**
 * @Author Lyle xiajun94@FoxMail.com
 * @Description
 * @name org.kevin.Response
 * @Date 2019/06/03 11:08
 */
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Response implements Serializable {

    private String responseMessage;

}

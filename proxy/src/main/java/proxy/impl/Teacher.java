package proxy.impl; /**
 * Created by Administrator on 2018/11/29.
 */

import proxy.Person;

/**
 * @Author Lyle xiajun94@FoxMail.com
 * @Description
 * @name inter.impl.Teacher
 * @Date 2018/11/29 10:05
 */
public class Teacher implements Person {

    @Override
    public void say(String sth) {

        System.out.println("i am a teacher, now i am teaching... : " + sth);
    }

}

package cn.tech.mvc.util;/**
 * Created by Administrator on 2018/11/28.
 */

/**
 * @Author Lyle xiajun94@FoxMail.com
 * @Description
 * @name WordUtil
 * @Date 2018/11/28 16:48
 */
public class WordUtil {

    public static String firstWordToSupperCase(String word){

        char firstWord = (char) ((int) word.charAt(0) - 32);
        return new String(new char[]{firstWord}).concat(word.substring(1, word.length()));
    }

}

package cn.kiven;
/**
 * Created by xiaju on 2018/11/30.
 */

import cn.kiven.dao.UserMapper;
import cn.kiven.entity.User;
import cn.kiven.ibatis.SqlSession;
import cn.kiven.ibatis.SqlSessionFactory;
import org.junit.Test;

import java.util.List;

/**
 * @Author Lyle xiajun94@FoxMail.com
 * @Description
 * @name IBatis
 * @Date 2018/11/30 23:27
 */
public class IBatis {

    @Test
    public void test1(){

        SqlSessionFactory sqlSessionFactory = new SqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.build();
        User user = sqlSession.selectOne("cn.kiven.dao.UserMapper.getById", "1");
        System.out.println(user);
    }

    @Test
    public void test2(){

        SqlSessionFactory sqlSessionFactory = new SqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.build();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        List<User> users = mapper.findAllList();
        System.out.println(users);

    }
}

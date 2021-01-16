package test;

import domain.User;
import mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MapperTest {

    @Test
    public void test1() throws IOException {
        //获得核心配置文件
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        //获得session工厂对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        //获得session会话对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //mybatis动态实现mapper接口中的方法
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        //模拟条件user
        User condition = new User();
        //condition.setId(1);
        condition.setUsername("superuser");
        //condition.setPassword("123456");

        List<User> userList = mapper.findByCondition(condition);

        //[user{id=1, username='superuser', password='123456'}]
        System.out.println(userList);
    }

    @Test
    public void test2() throws IOException {
        //获得核心配置文件
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        //获得session工厂对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        //获得session会话对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //mybatis动态实现mapper接口中的方法
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        //模拟ids的数据
        List<Integer> ids = new ArrayList<Integer>();
        ids.add(1);
        ids.add(2);
        ids.add(4);

        List<User> userList = mapper.findByIds(ids);
        System.out.println(userList);
        //[user{id=1, username='superuser', password='123456'}, user{id=2, username='zhangsan', password='123'}, user{id=4, username='wangwu', password='123'}]
    }
}

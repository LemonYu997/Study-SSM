package test;

import domain.User;
import mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MyBatisTest {

    private UserMapper mapper;

    @Before
    public void before() throws IOException {
        //获得核心配置文件
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        //获得session工厂对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        //获得session会话对象 true表示自动提交事务
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        //mybatis动态实现mapper接口中的方法
        mapper = sqlSession.getMapper(UserMapper.class);
    }

    @Test
    public void testSave() throws IOException {
        //创建user
        User user = new User();
        user.setUsername("tom");
        user.setPassword("abc");

        //执行保存操作
        mapper.save(user);
    }

    @Test
    public void testUpdate() throws IOException {
        //创建user
        User user = new User();
        user.setId(4);
        user.setUsername("lucy");
        user.setPassword("123");

        //执行保存操作
        mapper.update(user);
    }

    @Test
    public void testDelete() throws IOException {

        mapper.delete(4);
    }

    @Test
    public void testFindById() throws IOException {

        User user = mapper.findById(2);
        System.out.println(user);
        //User{id=2, username='haohao', password='123', birthday=Thu Dec 12 08:00:00 CST 2019}
    }

    @Test
    public void testFindAll() throws IOException {

        List<User> userList = mapper.findAll();
        for (User user : userList) {
            System.out.println(user);
        }
        //User{id=1, username='lucy', password='123', birthday=Wed Dec 12 08:00:00 CST 2018}
        //User{id=2, username='haohao', password='123', birthday=Thu Dec 12 08:00:00 CST 2019}
    }

}

package test;

import domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MyBatisTest {
    //查询操作
    @Test
    public void test1() throws IOException {
        //获得核心配置文件
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        //获得session工厂对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        //获得session会话对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //执行操作  参数:namespace+id
        List<User> userList = sqlSession.selectList("userMapper.findAll");
        //打印数据
        System.out.println(userList);
        //释放资源
        sqlSession.close();
    }

    //添加操作
    @Test
    public void test2() throws IOException {
        //模拟user对象
        User user = new User();
        user.setUsername("tom");
        user.setPassword("abc");

        //获得核心配置文件
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        //获得session工厂对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        //获得session会话对象 参数true表示自动提交事务
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        //执行操作  参数:namespace+id
        sqlSession.insert("userMapper.save", user);
        //mybatis默认事务不提交，如果要执行更新操作，提交事务
//        sqlSession.commit();
        //释放资源
        sqlSession.close();
    }

    //修改操作
    @Test
    public void test3() throws IOException {
        //模拟user对象
        User user = new User();
        user.setId(8);
        user.setUsername("lucy");
        user.setPassword("123");

        //获得核心配置文件
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        //获得session工厂对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        //获得session会话对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //执行操作  参数:namespace+id
        sqlSession.update("userMapper.update", user);
        //mybatis默认事务不提交，如果要执行更新操作，提交事务
        sqlSession.commit();
        //释放资源
        sqlSession.close();
    }

    //删除操作
    @Test
    public void test4() throws IOException {
        //获得核心配置文件
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        //获得session工厂对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        //获得session会话对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //执行操作  参数:namespace+id
        sqlSession.delete("userMapper.delete", 8);
        //mybatis默认事务不提交，如果要执行更新操作，提交事务
        sqlSession.commit();
        //释放资源
        sqlSession.close();
    }

    //根据id进行查询操作，查询一个
    @Test
    public void test5() throws IOException {
        //获得核心配置文件
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        //获得session工厂对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        //获得session会话对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //执行操作  参数:namespace+id
        User user = sqlSession.selectOne("userMapper.findById", 2);
        //打印数据
        System.out.println(user);   //user{id=2, username='zhangsan', password='123'}
        //释放资源
        sqlSession.close();
    }
}

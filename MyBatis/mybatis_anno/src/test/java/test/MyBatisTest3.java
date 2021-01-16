package test;

import domain.Order;
import domain.User;
import mapper.OrderMapper;
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

public class MyBatisTest3 {

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
    public void test() throws IOException {
        List<User> userList = mapper.findUserAndOrderAll();
        for (User user : userList) {
            System.out.println(user);
        }
        //User{id=1, username='lucy', password='123', birthday=Wed Dec 12 08:00:00 CST 2018, orderList=[Order{id=1, ordertime=Wed Dec 12 08:00:00 CST 2018, total=3000.0, user=null}, Order{id=2, ordertime=Wed Dec 12 08:00:00 CST 2018, total=4000.0, user=null}]}
        //User{id=2, username='haohao', password='123', birthday=Thu Dec 12 08:00:00 CST 2019, orderList=[Order{id=3, ordertime=Wed Dec 12 08:00:00 CST 2018, total=5000.0, user=null}]}
        //User{id=5, username='tom', password='abc', birthday=null, orderList=[]}
    }
}

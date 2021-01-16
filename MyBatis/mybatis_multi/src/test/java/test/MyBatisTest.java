package test;


import domain.Order;
import domain.User;
import mapper.OrderMapper;
import mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MyBatisTest {
    @Test
    public void test1() throws IOException {
        //获得核心配置文件
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        //获得session工厂对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        //获得session会话对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //mybatis动态实现mapper接口中的方法
        OrderMapper mapper = sqlSession.getMapper(OrderMapper.class);

        List<Order> orderList = mapper.findAll();
        for (Order order : orderList) {
            System.out.println(order);
        }
        //Order{id=1, ordertime=Wed Dec 12 08:00:00 CST 2018, total=3000.0, user=User{id=1, username='superuser', password='123456', birthday=null}}
        //Order{id=2, ordertime=Wed Dec 12 08:00:00 CST 2018, total=4000.0, user=User{id=1, username='superuser', password='123456', birthday=null}}
        //Order{id=3, ordertime=Wed Dec 12 08:00:00 CST 2018, total=5000.0, user=User{id=2, username='zhangsan', password='123', birthday=null}}

        sqlSession.close();
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

        List<User> userList = mapper.findAll();
        for (User user : userList) {
            System.out.println(user);
        }
        //User{id=1, username='superuser', password='123456', birthday=null, orderList=[Order{id=1, ordertime=Wed Dec 12 08:00:00 CST 2018, total=3000.0, user=null}, Order{id=2, ordertime=Wed Dec 12 08:00:00 CST 2018, total=4000.0, user=null}]}
        //User{id=2, username='zhangsan', password='123', birthday=null, orderList=[Order{id=3, ordertime=Wed Dec 12 08:00:00 CST 2018, total=5000.0, user=null}]}

        sqlSession.close();
    }

    @Test
    public void test3() throws IOException {
        //获得核心配置文件
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        //获得session工厂对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        //获得session会话对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //mybatis动态实现mapper接口中的方法
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        List<User> userAndRoleAll = mapper.findUserAndRoleAll();
        for (User user : userAndRoleAll) {
            System.out.println(user);
        }

        //User{id=1, username='lucy', password='123', birthday=Wed Dec 12 08:00:00 CST 2018, roleList=[Role{id=1, roleName='CTO', roleDesc='CTO'}, Role{id=2, roleName='COO', roleDesc='COO'}]}
        //User{id=2, username='haohao', password='123', birthday=Thu Dec 12 08:00:00 CST 2019, roleList=[Role{id=1, roleName='CTO', roleDesc='CTO'}, Role{id=2, roleName='COO', roleDesc='COO'}]}

        sqlSession.close();
    }
}

package test;

import domain.Order;
import mapper.OrderMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MyBatisTest2 {

    private OrderMapper mapper;

    @Before
    public void before() throws IOException {
        //获得核心配置文件
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        //获得session工厂对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        //获得session会话对象 true表示自动提交事务
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        //mybatis动态实现mapper接口中的方法
        mapper = sqlSession.getMapper(OrderMapper.class);
    }

    @Test
    public void testSave() throws IOException {
        List<Order> orderList = mapper.findAll();
        for (Order order : orderList) {
            System.out.println(order);
        }
        //Order{id=1, ordertime=Wed Dec 12 08:00:00 CST 2018, total=3000.0, user=User{id=1, username='lucy', password='123', birthday=null}}
        //Order{id=2, ordertime=Wed Dec 12 08:00:00 CST 2018, total=4000.0, user=User{id=1, username='lucy', password='123', birthday=null}}
        //Order{id=3, ordertime=Wed Dec 12 08:00:00 CST 2018, total=5000.0, user=User{id=2, username='haohao', password='123', birthday=null}}
    }
}

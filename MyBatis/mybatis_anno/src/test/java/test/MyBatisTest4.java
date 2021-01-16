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

public class MyBatisTest4 {

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
        List<User> userList = mapper.findUserAndRoleAll();
        for (User user : userList) {
            System.out.println(user);
        }
        //User{id=1, username='lucy', password='123', birthday=Wed Dec 12 08:00:00 CST 2018, roleList=[Role{id=1, roleName='CTO', roleDesc='CTO'}, Role{id=2, roleName='COO', roleDesc='COO'}]}
        //User{id=2, username='haohao', password='123', birthday=Thu Dec 12 08:00:00 CST 2019, roleList=[Role{id=1, roleName='CTO', roleDesc='CTO'}, Role{id=2, roleName='COO', roleDesc='COO'}]}
        //User{id=5, username='tom', password='abc', birthday=null, roleList=[]}
    }
}

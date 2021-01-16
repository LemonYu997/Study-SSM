package service;

import domain.User;
import mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class ServiceDemo {
    public static void main(String[] args) throws IOException {
        //获得核心配置文件
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        //获得session工厂对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        //获得session会话对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //mybatis动态实现mapper接口中的方法
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        List<User> all = mapper.findAll();
        User user = mapper.findById(1);

        System.out.println(all);
        //[user{id=1, username='superuser', password='123456'}, user{id=2, username='zhangsan', password='123'}, user{id=3, username='lisi', password='123'}, user{id=4, username='wangwu', password='123'}, user{id=5, username='zhaoliu', password='123'}, user{id=6, username='tianqi', password='123'}, user{id=9, username='tom', password='abc'}]
        System.out.println(user);   //user{id=1, username='superuser', password='123456'}
    }
}

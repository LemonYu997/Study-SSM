package test;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import domain.User;
import mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

public class MybatisTest {
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

        //创建user
        User user = new User();
        user.setUsername("ceshi");
        user.setPassword("123");
        user.setBirthday(new Date());

        //执行保存操作
        mapper.save(user);

        sqlSession.commit();
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

        User user = mapper.findById(10);
        System.out.println("user中的Birthday：" + user.getBirthday());
        //user中的Birthday：Fri Jan 15 20:33:30 CST 2021

        sqlSession.commit();
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

        //设置分页的相关参数 当前页 + 每页显示的条数
        PageHelper.startPage(1, 3);
        //select * from user limit ?,?

        List<User> userList = mapper.findAll();
        for (User user : userList) {
            System.out.println(user);
        }
        //User{id=1, username='superuser', password='123456', birthday=null}
        //User{id=2, username='zhangsan', password='123', birthday=null}
        //User{id=3, username='lisi', password='123', birthday=null}

        //获得与分页相关参数
        PageInfo<User> pageInfo = new PageInfo<User>(userList);
        System.out.println("当前页：" + pageInfo.getPageNum());
        System.out.println("每页显示条数：" + pageInfo.getPageSize());
        System.out.println("总条数：" + pageInfo.getTotal());
        System.out.println("总页数：" + pageInfo.getPages());
        System.out.println("上一页：" + pageInfo.getPrePage());
        System.out.println("下一页：" + pageInfo.getNextPage());
        System.out.println("是否是第一页：" + pageInfo.isIsFirstPage());
        System.out.println("是否是最后一页：" + pageInfo.isIsLastPage());
        //当前页：1
        //每页显示条数：3
        //总条数：8
        //总页数：3
        //上一页：0
        //下一页：2
        //是否是第一页：true
        //是否是最后一页：false

        sqlSession.close();
    }
}

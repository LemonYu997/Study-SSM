package ssm.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ssm.pojo.Item;
import ssm.service.ItemService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-dao.xml", "classpath:applicationContext-service.xml"})
public class SpringMybatisTest {
    @Autowired
    private ItemService itemService;

    @Test
    public void test1() {
        Item item = itemService.findById(1);
        System.out.println(item);
        //Item{id=1, name='电视机', price=4500.0, createtime=Thu Jan 10 19:30:18 CST 2019, detail='电视机'}
    }
}

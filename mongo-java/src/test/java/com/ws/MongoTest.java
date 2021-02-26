package com.ws;

import com.ws.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author: wangshuo
 * @Date: 2021/2/26 11:18
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class MongoTest {


    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    public void testAdd(){
        User user = new User();
        user.setId("110");
        user.setName("张三");
        user.setAge(16);
        user.setAddress("天津");
        mongoTemplate.save(user);
    }


}

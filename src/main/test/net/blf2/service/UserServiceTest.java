package net.blf2.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Created by blf2 on 17-2-4.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-mybatis.xml")
public class UserServiceTest {
    @Resource
    private IUserService userService;

    public IUserService getUserService() {
        return userService;
    }

    public void setUserService(IUserService userService) {
        this.userService = userService;
    }
    @Test
    public void testUserService(){
        String userGrade = "软件201301";
        try {
            userService.generateExcel(userGrade,"x");
        }catch (Exception ex){
            ex.printStackTrace();
            Assert.assertTrue(false);
        }
    }
}

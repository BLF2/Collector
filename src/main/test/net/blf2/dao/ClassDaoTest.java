package net.blf2.dao;

import net.blf2.entity.ClassInfo;
import net.blf2.entity.UserInfo;
import net.blf2.entity.UserRoleInfo;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * Created by blf2 on 17-2-9.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-mybatis.xml")
public class ClassDaoTest {

    @Resource
    private IClassDao classDao;
    @Resource
    private IUserRoleDao userRoleDao;
    @Resource
    private IUserDao userDao;

    private UserRoleInfo userRoleInfo;
    private UserInfo userInfo;
    private ClassInfo classInfo;
    private String userRoleId;
    private String userId;
    private String classId;

    @Before
    public void createResourceForTest(){
        userRoleId = UUID.randomUUID().toString();
        userId = UUID.randomUUID().toString();
        classId = UUID.randomUUID().toString();

        userRoleInfo = new UserRoleInfo();
        userRoleInfo.setRoleId(userRoleId);
        userRoleInfo.setRoleName("monitor");
        userRoleInfo.setRoleRule("111000");
        userRoleDao.insertUserRoleInfo(userRoleInfo);

        userInfo = new UserInfo();
        userInfo.setUserId(userId);
        userInfo.setUserGrade("2013");
        userInfo.setUserNum("13110572081");
        userInfo.setUserPhone("15800499264");
        userInfo.setUserPswd("mxh19940822");
        userInfo.setUserRole(userRoleInfo);
        userDao.insertUserInfo(userInfo);

        classInfo = new ClassInfo();
        classInfo.setClassId(classId);
        classInfo.setClassGrade("2013");
        classInfo.setClassNum("03");
        classInfo.setMajorName("软件工程");
        classInfo.setMonitorInfo(userInfo);
    }

    @Test
    public void testClassDao(){
        try {

        }catch (Exception ex){
            ex.printStackTrace();
            Assert.assertTrue(false);
        }
        Assert.assertTrue(true);
    }

}

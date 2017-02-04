package net.blf2.dao;

import net.blf2.entity.UserInfo;
import net.blf2.entity.UserRoleInfo;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

/**
 * Created by blf2 on 17-2-4.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-mybatis.xml")
public class UserDaoTest {
    @Resource
    private IUserDao userDao;

    public IUserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(IUserDao userDao) {
        this.userDao = userDao;
    }

    private UserInfo insertUserInfo;
    private UserRoleInfo userRoleInfo;
    private UUID userId ;
    private UUID userRoleId;

    @Before
    public void initResourceForTest(){
        userId = UUID.randomUUID();
        userRoleId = UUID.randomUUID();
        userRoleInfo = new UserRoleInfo();
        userRoleInfo.setRoleId(userRoleId.toString());
        insertUserInfo = new UserInfo();
        insertUserInfo.setUserId(userId.toString());
        insertUserInfo.setUserRole(userRoleInfo);
        insertUserInfo.setUserPswd("mxh19940822");
        insertUserInfo.setUserPhone("15800499264");
        insertUserInfo.setUserNum("13110572081");
        insertUserInfo.setUserGrade("2013");
    }

    @Test
    public void testInsertUserInfo(){
        try {
            userDao.insertUserInfo(insertUserInfo);
        }catch (Exception ex){
            ex.printStackTrace();
            Assert.assertTrue(false);
        }
        Assert.assertTrue(true);
    }

    @Test
    public void testQueryUserInfoById(){
        UserInfo queryUserInfo = null;
        try {
           queryUserInfo =  userDao.queryUserInfoById(userId.toString());
        }catch (Exception ex){
            ex.printStackTrace();
            Assert.assertTrue(false);
        }
        Assert.assertTrue(insertUserInfo.equals(queryUserInfo.equals(insertUserInfo)));
    }
    @Test
    public void testQueryUserInfoAll(){
        List<UserInfo>userInfoList = null;
        try {
            userInfoList = userDao.queryUserInfoAll();
        }catch (Exception ex){
            ex.printStackTrace();
            Assert.assertTrue(false);
        }
        if(userInfoList != null){
            for (UserInfo userInfox : userInfoList){
                System.out.println(userInfox.getUserId());
            }
        }
        Assert.assertTrue(userInfoList != null);
    }
    @Test
    public void testDeleteUserInfoById(){
        String userIdForDelete = UUID.randomUUID().toString();
        UserInfo userInfoForDelete = new UserInfo();
        userInfoForDelete.setUserId(userIdForDelete);
        userInfoForDelete.setUserRole(userRoleInfo);
        userInfoForDelete.setUserNum("13110572082");
        userInfoForDelete.setUserGrade("2013");
        try {
            userDao.insertUserInfo(userInfoForDelete);
            UserInfo userInfoForRe = userDao.queryUserInfoById(userIdForDelete);
            Assert.assertNotNull(userInfoForRe);
            Assert.assertTrue(userInfoForDelete.getUserId().equals(userInfoForRe.getUserId()));
            userDao.deleteUserInfoById(userIdForDelete);
            userInfoForRe = userDao.queryUserInfoById(userIdForDelete);
            Assert.assertNull(userInfoForRe);
        }catch (Exception ex){
            ex.printStackTrace();
            Assert.assertTrue(false);
        }
        Assert.assertTrue(true);
    }
}

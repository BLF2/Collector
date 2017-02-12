package net.blf2.dao;

import net.blf2.entity.UserInfo;
import net.blf2.entity.UserRoleInfo;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.AssertThrows;
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
    private UserInfo insertUserInfo2;
    private String userGrade = "软件201303";

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
        insertUserInfo.setUserNum("13110572082");
        insertUserInfo.setUserGrade(userGrade);

        insertUserInfo2 = new UserInfo();
        insertUserInfo2.setUserId(UUID.randomUUID().toString());
        insertUserInfo2.setUserRole(userRoleInfo);
        insertUserInfo2.setUserPswd("mxh19940823");
        insertUserInfo2.setUserPhone("15800499266");
        insertUserInfo2.setUserNum("13110572080");
        insertUserInfo2.setUserGrade(userGrade);
    }
    @Test
    public void testUserDao(){
        try {
            userDao.insertUserInfo(insertUserInfo);
            UserInfo findUserInfo = userDao.queryUserInfoById(insertUserInfo.getUserId());
            Assert.assertTrue(findUserInfo != null);
            Assert.assertEquals(findUserInfo.getUserId(), insertUserInfo.getUserId());
            String updUserPhone = "15800499265";
            insertUserInfo.setUserPhone(updUserPhone);
            userDao.updateUserInfo(insertUserInfo);
            findUserInfo = userDao.queryUserInfoById(insertUserInfo.getUserId());
            Assert.assertTrue(findUserInfo != null);
            Assert.assertEquals(findUserInfo.getUserPhone(), updUserPhone);
            String userNumId = userDao.queryUserIdByUserNum(insertUserInfo.getUserNum());
            Assert.assertTrue(userNumId != null);
            Assert.assertEquals(userNumId, insertUserInfo.getUserId());
            String userPhoneId = userDao.queryUserIdByUserPhone(updUserPhone);
            Assert.assertTrue(userPhoneId != null);
            Assert.assertEquals(userPhoneId,insertUserInfo.getUserId());
            userDao.insertUserInfo(insertUserInfo2);
            List<UserInfo>userInfoAll = userDao.queryUserInfoAll();
            for(UserInfo iUserInfo : userInfoAll){
                System.out.println(iUserInfo.getUserId()+" "+iUserInfo.getUserNum()+" "+iUserInfo.getUserPhone());
            }
            System.out.println("---------------->test query by grade");
            List<UserInfo>userInfosByGrade = userDao.queryUserInfoByUserGrade(userGrade);
            for(UserInfo iUserInfo : userInfosByGrade){
                System.out.println(iUserInfo.getUserId()+" "+iUserInfo.getUserNum()+" "+iUserInfo.getUserPhone());
            }
            for(UserInfo iUserInfo : userInfosByGrade){
                userDao.deleteUserInfoById(iUserInfo.getUserId());
            }
        }catch (Exception ex){
            ex.printStackTrace();
            Assert.assertTrue(false);
        }
    }
}

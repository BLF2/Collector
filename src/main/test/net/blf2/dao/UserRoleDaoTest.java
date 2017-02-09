package net.blf2.dao;

import net.blf2.entity.UserRoleInfo;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by blf2 on 17-2-9.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-mybatis.xml")
public class UserRoleDaoTest {
    @Resource
    private IUserRoleDao userRoleDao;
    private String roleId;
    private String roleName;
    private String roleRule;
    private String roleNote;
    private UserRoleInfo roleInfo;
    private String updateRoleName = "updateRoleName";
    @Before
    public void createSourceForTest(){
        roleId = UUID.randomUUID().toString();
        roleName = "admin";
        roleRule = "1110001";
        roleNote = "我是测试";
        roleInfo = new UserRoleInfo();
        roleInfo.setRoleId(roleId);
        roleInfo.setRoleName(roleName);
        roleInfo.setRoleRule(roleRule);
        roleInfo.setRoleNote(roleNote);
    }

    @Test
    public void testUserRoleDao(){
        try {
            userRoleDao.insertUserRoleInfo(roleInfo);
            UserRoleInfo findRoleInfo = userRoleDao.queryUserRoleInfoByRoleId(roleId);
            Assert.assertEquals(roleInfo.getRoleId(), findRoleInfo.getRoleId());
            findRoleInfo.setRoleName(updateRoleName);
            userRoleDao.updateUserRoleInfo(findRoleInfo);
            findRoleInfo = userRoleDao.queryUserRoleInfoByRoleId(roleId);
            Assert.assertEquals(findRoleInfo.getRoleName(), updateRoleName);
            userRoleDao.deleteUserRoleInfoByRoleId(roleId);
            UserRoleInfo insertRoleInfo = new UserRoleInfo();
            insertRoleInfo.setRoleId(UUID.randomUUID().toString());
            insertRoleInfo.setRoleName("test2");
            insertRoleInfo.setRoleRule("11111");
            insertRoleInfo.setRoleNote("test2");
            userRoleDao.insertUserRoleInfo(insertRoleInfo);
            List<UserRoleInfo>userRoleInfos = userRoleDao.queryUserRoleInfoAll();
            Assert.assertFalse(userRoleInfos == null);
            for(UserRoleInfo iRoleInfo : userRoleInfos){
                System.out.println(iRoleInfo.getRoleId());
            }
            for(UserRoleInfo iRoleInfo : userRoleInfos){
                userRoleDao.deleteUserRoleInfoByRoleId(iRoleInfo.getRoleId());
            }
            userRoleInfos = userRoleDao.queryUserRoleInfoAll();
            Assert.assertFalse(userRoleInfos == null);
        }catch (Exception ex){
            ex.printStackTrace();
            Assert.assertTrue(false);
        }
        Assert.assertTrue(true);
    }
}

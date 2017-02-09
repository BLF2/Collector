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
import java.util.List;
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

        userInfo = new UserInfo();
        userInfo.setUserId(userId);
        userInfo.setUserGrade("2013");
        userInfo.setUserNum("13110572081");
        userInfo.setUserPhone("15800499264");
        userInfo.setUserPswd("mxh19940822");
        userInfo.setUserRole(userRoleInfo);

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
            String updClassNum = "04";
            userRoleDao.insertUserRoleInfo(userRoleInfo);
            userDao.insertUserInfo(userInfo);
            classDao.insertClassInfo(classInfo);
            ClassInfo findClassInfo = classDao.queryClassInfoByClassId(classId);
            Assert.assertTrue(findClassInfo != null);
            Assert.assertEquals(classInfo.getClassId(),findClassInfo.getClassId());
            findClassInfo.setClassNum(updClassNum);
            classDao.updateClassInfo(findClassInfo);
            findClassInfo = classDao.queryClassInfoByClassId(findClassInfo.getClassId());
            Assert.assertEquals(findClassInfo.getClassNum(),updClassNum);
            ClassInfo findAll = new ClassInfo();
            findAll.setClassId(UUID.randomUUID().toString());
            findAll.setClassGrade("2014");
            findAll.setClassNum("04");
            findAll.setMajorName("计算机科学与技术");
            findAll.setMonitorInfo(userInfo);
            classDao.insertClassInfo(findAll);
            List<ClassInfo> classInfos = classDao.queryClassInfoAll();
            for(ClassInfo iClassInfo : classInfos){
                System.out.println(iClassInfo.getClassId()+" " + iClassInfo.getClassGrade()
                        + iClassInfo.getClassNum()+" "+iClassInfo.getMajorName());
            }
            for(ClassInfo iClassInfo : classInfos){
               classDao.deleteClassInfoByClassId(iClassInfo.getClassId());
            }
            classInfos = classDao.queryClassInfoAll();
            Assert.assertTrue(classInfos != null);
            Assert.assertTrue(classInfos.size() == 0);
        }catch (Exception ex){
            ex.printStackTrace();
            Assert.assertTrue(false);
        }
        Assert.assertTrue(true);
    }

}

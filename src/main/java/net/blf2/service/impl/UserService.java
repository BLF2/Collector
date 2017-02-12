package net.blf2.service.impl;

import net.blf2.dao.IUserDao;
import net.blf2.entity.UserInfo;
import net.blf2.service.IUserService;
import org.apache.ibatis.javassist.bytecode.stackmap.BasicBlock;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by blf2 on 17-2-4.
 */
@Service("UserService")
public class UserService implements IUserService {

    @Resource
    private IUserDao userDao;

    public IUserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(IUserDao userDao) {
        this.userDao = userDao;
    }

    public UserInfo findUserInfoByUserId(String userId) {
        return userDao.queryUserInfoById(userId);
    }

    public List<UserInfo> findAllUserInfos() {
        return userDao.queryUserInfoAll();
    }

    public boolean registerUserInfo(UserInfo userInfo) {
        try {
            userDao.insertUserInfo(userInfo);
        }catch (Exception ex){
            return false;
        }
        return true;
    }

    public boolean updateUserInfo(UserInfo userInfo) {
        try {
            userDao.updateUserInfo(userInfo);
        }catch (Exception ex){
            return false;
        }
        return true;
    }
    //TODO:delete all infomation of user infomation
    public boolean deleteUserInfoByUserId(String userId) {
        return false;
    }

    public boolean checkLoginInfo(String userNum, String userPswd) {
        return false;
    }

    public boolean checkUserPhoneHasExist(String userPhone) {
        return userDao.queryUserIdByUserPhone(userPhone) != null;
    }

    public boolean checkUserNumHasExist(String userNum) {
        return userDao.queryUserIdByUserNum(userNum) != null;
    }
}

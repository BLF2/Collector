package net.blf2.service.impl;

import net.blf2.dao.IUserDao;
import net.blf2.entity.UserInfo;
import net.blf2.service.IUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by blf2 on 17-2-4.
 */
@Service("UserService")
public class UserService implements IUserService {
    @Resource
    IUserDao userDao;

    public UserInfo queryUserInfoByUserId(String userId) {
        try {
            return userDao.queryUserInfoById(userId);
        }catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }

    public List<UserInfo> queryUserInfoAll() {
        try {
            return userDao.queryUserInfoAll();
        }catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }

    public boolean insertUserInfo(UserInfo userInfo) {
        try {
            userDao.insertUserInfo(userInfo);
        }catch (Exception ex){
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean updateUserInfo(UserInfo userInfo) {
        try {
            userDao.updateUserInfo(userInfo);
        }catch (Exception ex){
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean deleteUserInfoByUserId(String userId) {
        try {
            userDao.deleteUserInfoById(userId);
        }catch (Exception ex){
            ex.printStackTrace();
            return false;
        }
        return true;
    }
}

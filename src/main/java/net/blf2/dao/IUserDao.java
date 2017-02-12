package net.blf2.dao;

import net.blf2.entity.UserInfo;

import java.util.List;

/**
 * Created by blf2 on 17-1-8.
 * 用户操作接口
 */
public interface IUserDao {
    UserInfo queryUserInfoById(String userId);
    void insertUserInfo(UserInfo userInfo);
    void deleteUserInfoById(String userId);
    void updateUserInfo(UserInfo userInfo);
    List<UserInfo> queryUserInfoAll();
    UserInfo queryUserInfoByUserNum(String userNum);
    String queryUserIdByUserPhone(String userPhone);
    String queryUserIdByUserNum(String userNum);
}

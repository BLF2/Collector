package net.blf2.dao;

import net.blf2.entity.UserInfo;

import java.util.List;

/**
 * Created by blf2 on 17-1-8.
 * 用户操作接口
 */
public interface IUserDao {
    public UserInfo queryUserInfoById(String userId);
    public void insertUserInfo(UserInfo userInfo);
    public void deleteUserInfoById(String userId);
    public void updateUserInfo(UserInfo userInfo);
    public List<UserInfo> queryUserInfoAll();
}

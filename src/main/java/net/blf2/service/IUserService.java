package net.blf2.service;

import net.blf2.entity.UserInfo;

import java.util.List;

/**
 * Created by blf2 on 17-2-4.
 */
public interface IUserService {
    UserInfo queryUserInfoByUserId(String userId);
    List<UserInfo> queryUserInfoAll();
    boolean insertUserInfo(UserInfo userInfo);
    boolean updateUserInfo(UserInfo userInfo);
    boolean deleteUserInfoByUserId(String userId);
}

package net.blf2.service;

import net.blf2.entity.UserInfo;

import java.util.List;

/**
 * Created by blf2 on 17-2-4.
 */
public interface IUserService {
    UserInfo findUserInfoByUserId(String userId);
    List<UserInfo> findAllUserInfos();
    boolean registerUserInfo(UserInfo userInfo);
    boolean updateUserInfo(UserInfo userInfo);
    boolean deleteUserInfoByUserId(String userId);
    boolean checkLoginInfo(String userNum,String userPswd);
    boolean checkUserPhoneHasExist(String userPhone);
    boolean checkUserNumHasExist(String userNum);
}

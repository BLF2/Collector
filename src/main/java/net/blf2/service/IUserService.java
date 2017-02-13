package net.blf2.service;

import net.blf2.entity.UserInfo;

import java.util.List;
import java.util.Map;

/**
 * Created by blf2 on 17-2-4.
 */
public interface IUserService {
    UserInfo findUserInfoByUserId(String userId);
    List<UserInfo> findAllUserInfos();
    boolean registerUserInfo(UserInfo userInfo);
    boolean updateUserInfo(UserInfo userInfo);
    boolean deleteUserInfoByUserId(String userId);
    UserInfo checkLoginInfo(String userNum,String userPswd);
    boolean checkUserPhoneHasExist(String userPhone);
    boolean checkUserNumHasExist(String userNum);
    List<UserInfo> getClassMatesByUserGrade(String userGrade);
    List<Map<String,Object>> getClassMatesScoreDetail(String userGrade);
    boolean addClassMateScoreDetail(Map<String,Object> userScoreDetailMap);
    boolean updateClassMatesScoreDetail(Map<String,Object> userScoreDetailMap);
}

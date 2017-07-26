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
    UserInfo checkLoginInfo(String userNum,String userPswd) throws Exception;
    boolean checkUserPhoneHasExist(String userPhone) throws Exception;
    boolean checkUserNumHasExist(String userNum) throws Exception;
    List<UserInfo> getClassMatesByUserGrade(String userGrade);
    List<Map<String,Object>> getClassMatesScoreDetail(String userGrade);
    boolean mongoAddClassMateScoreDetail(Map<String,Object> userScoreDetailMap);
    boolean mongoUpdateClassMatesScoreDetail(Map<String,Object> userScoreDetailMap);
    UserInfo findUserInfoByUserNum(String userNum) throws Exception;
    Map<String,Object> MongoFindUserScoreDetailByUserId(String userId);
    void generateExcel(String userGrade,String fName)throws Exception;
}

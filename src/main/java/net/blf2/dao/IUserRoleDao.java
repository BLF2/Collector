package net.blf2.dao;

import net.blf2.entity.UserRoleInfo;

import java.util.List;

/**
 * Created by blf2 on 17-1-8.
 */
public interface IUserRoleDao {
    UserRoleInfo queryUserRoleInfoByRoleId(String roleId);
    void insertUserRoleInfo(UserRoleInfo userRoleInfo);
    void updateUserRoleInfo(UserRoleInfo userRoleInfo);
    void deleteUserRoleInfoByRoleId(String roleId);
    List<UserRoleInfo> queryUserRoleInfo();
}

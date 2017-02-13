package net.blf2.service;

import net.blf2.entity.UserRoleInfo;

import java.util.List;

/**
 * Created by blf2 on 17-2-13.
 */
public interface IUserRoleService {
    UserRoleInfo queryUserRoleInfoByRoleId(String roleId);
    boolean insertUserRoleInfo(UserRoleInfo userRoleInfo);
    boolean updateUserRoleInfo(UserRoleInfo userRoleInfo);
    boolean deleteUserRoleInfoByRoleId(String roleId);
    List<UserRoleInfo> queryUserRoleInfoAll();
}

package net.blf2.service.impl;

import net.blf2.dao.IUserRoleDao;
import net.blf2.entity.UserRoleInfo;
import net.blf2.service.IUserRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by blf2 on 17-2-13.
 */
@Service("UserRoleService")
public class UserRoleService implements IUserRoleService {

    @Resource
    private IUserRoleDao userRoleDao;

    public IUserRoleDao getUserRoleDao() {
        return userRoleDao;
    }

    public void setUserRoleDao(IUserRoleDao userRoleDao) {
        this.userRoleDao = userRoleDao;
    }

    public UserRoleInfo queryUserRoleInfoByRoleId(String roleId) {
        UserRoleInfo userRoleInfo = null;
        try {
            userRoleInfo = userRoleDao.queryUserRoleInfoByRoleId(roleId);
        }catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
        return userRoleInfo;
    }

    public boolean insertUserRoleInfo(UserRoleInfo userRoleInfo) {
        try {
            userRoleDao.insertUserRoleInfo(userRoleInfo);
        }catch (Exception ex){
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean updateUserRoleInfo(UserRoleInfo userRoleInfo) {
        try {
            userRoleDao.updateUserRoleInfo(userRoleInfo);
        }catch (Exception ex){
            ex.printStackTrace();
            return false;
        }
        return true;
    }
    //TODO: delete all information about it
    public boolean deleteUserRoleInfoByRoleId(String roleId) {
        return false;
    }

    public List<UserRoleInfo> queryUserRoleInfoAll() {
        List<UserRoleInfo> userRoleInfoAll = null;
        try {
            userRoleInfoAll = userRoleDao.queryUserRoleInfoAll();
        }catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
        return userRoleInfoAll == null || userRoleInfoAll.size() == 0 ? null : userRoleInfoAll;
    }
}

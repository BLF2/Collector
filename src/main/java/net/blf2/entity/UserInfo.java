package net.blf2.entity;

/**
 * Created by blf2 on 17-1-8.
 * 用户信息
 */
public class UserInfo {
    private String userId;//用户ID
    private String userNum;//用户学号
    private String userPswd;//用户密码
    private String userPhone;//用户手机号
    private String userGrade;//用户专业班级
    private UserRoleInfo userRole;//用户角色
    private String userNote;//用户备注

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserNum() {
        return userNum;
    }

    public void setUserNum(String userNum) {
        this.userNum = userNum;
    }

    public String getUserPswd() {
        return userPswd;
    }

    public void setUserPswd(String userPswd) {
        this.userPswd = userPswd;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserGrade() {
        return userGrade;
    }

    public void setUserGrade(String userGrade) {
        this.userGrade = userGrade;
    }

    public UserRoleInfo getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRoleInfo userRole) {
        this.userRole = userRole;
    }

    public String getUserNote() {
        return userNote;
    }

    public void setUserNote(String userNote) {
        this.userNote = userNote;
    }
}

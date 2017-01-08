package net.blf2.entity;

/**
 * Created by blf2 on 17-1-8.
 * 角色信息
 */
public class UserRoleInfo {
    private String roleId;//角色ID
    private String roleName;//角色名称
    private String roleRule;//角色特殊权限
    private String roleNote;//角色备注

    public String getRoleNote() {
        return roleNote;
    }

    public void setRoleNote(String roleNote) {
        this.roleNote = roleNote;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleRule() {
        return roleRule;
    }

    public void setRoleRule(String roleRule) {
        this.roleRule = roleRule;
    }
}

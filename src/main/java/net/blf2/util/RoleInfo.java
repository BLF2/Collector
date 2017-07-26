package net.blf2.util;

import java.util.List;

/**
 * Created by blf2 on 17-2-13.
 */
public class RoleInfo {
    private String roleName;
    private List<RoleRule> roleRules;

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public List<RoleRule> getRoleRules() {
        return roleRules;
    }

    public void setRoleRules(List<RoleRule> roleRules) {
        this.roleRules = roleRules;
    }
}

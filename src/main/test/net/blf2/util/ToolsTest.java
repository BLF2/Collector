package net.blf2.util;

import com.google.gson.reflect.TypeToken;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.List;
import java.util.UUID;

/**
 * Created by blf2 on 17-2-13.
 */
public class ToolsTest {
    private String userNum;
   @Before
    public void createResourceForTest(){
       userNum = "13110572081";
   }
    @Test
    public void testTools(){
        try {
            String code = Tools.generateInvitationCode(userNum);
            System.out.println(code);
            Assert.assertTrue(Tools.checkInvitationCode(code, userNum));
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
    @Test
    public void testLoadJson() {
        try {
            List<RoleInfo> roleInfos = Tools.loadJson("rbac.json", new TypeToken<List<RoleInfo>>() {
            }.getType());
            for(RoleInfo roleInfo : roleInfos){
                System.out.println(roleInfo.getRoleName());
                List<RoleRule> roleRules = roleInfo.getRoleRules();
                for(RoleRule roleRule : roleRules){
                    System.out.println(roleRule.getRoleRuleName()+" "+roleRule.getRoleRuleValue());
                }
            }
        }catch (Exception ex){
            ex.printStackTrace();
            Assert.assertTrue(false);
        }

    }
}

package lds.com.medicalsystem.staff.admin;

import lds.com.medicalsystem.common.utils.config.ThreadLocalUtil;
import lds.com.medicalsystem.common.utils.exception.BusinessException;

import java.util.Map;

// 验证是否为管理员身份
public class VerifyUtil {
    public static void adminVerify(){
        // 先校验token的role是否是管理员，否则报错
        Map<String,Object> map = ThreadLocalUtil.get();
        String role = (String)map.get("role");
        if(!role.equals("管理员")){
            System.out.println(role);   //打印日志
            throw new BusinessException("无管理员权限");
        }
    }
    public static void userVerify(){
        // 先获取token的map，用户的应该只有“phone”:"12345678901"(11位)
        Map<String,Object> map = ThreadLocalUtil.get();
        String phone = (String)map.get("phone");
        if(phone.length()!=11){
            System.out.println(phone);  // 打印日志
            throw new BusinessException("无用户权限");
        }
    }
}

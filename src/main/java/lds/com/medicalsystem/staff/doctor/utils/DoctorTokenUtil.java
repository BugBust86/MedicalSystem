package lds.com.medicalsystem.staff.doctor.utils;

import lds.com.medicalsystem.common.utils.config.ThreadLocalUtil;

import java.util.Map;

// 通过前端浏览器传来的token解析出工号
public class DoctorTokenUtil {
    public static String getDoctorNo() {
        // 解析token，给submitDTO的doctorId赋值
        Map<String, Object> map = ThreadLocalUtil.get();
        return (String) map.get("工号");
    }

}

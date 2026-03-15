package lds.com.medicalsystem.staff.doctor.utils;

import lds.com.medicalsystem.common.utils.config.ThreadLocalUtil;

import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// 通过前端浏览器传来的token解析出工号
public class DoctorTokenUtil {
    private static final Logger logger = LoggerFactory.getLogger(DoctorTokenUtil.class);

    public static String getDoctorNo() {
        // 解析 token，给 submitDTO 的 doctorId 赋值
        Map<String, Object> map = ThreadLocalUtil.get();
        if (map == null) {
            logger.error("ThreadLocal 中没有数据，可能是拦截器未执行或 Token 解析失败");
            return null;
        }
        String doctorNo = (String) map.get("工号");
        logger.info("从 ThreadLocal 获取到的工号：{}", doctorNo);
        logger.info("ThreadLocal 完整数据：{}", map);
        return doctorNo;
    }

}

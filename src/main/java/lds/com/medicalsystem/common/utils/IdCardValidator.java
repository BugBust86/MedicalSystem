package lds.com.medicalsystem.common.utils;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 身份证号校验器（实现注解的校验逻辑）
 */
public class IdCardValidator implements ConstraintValidator<ValidIdCard, String> {
    // 日期格式化（严格模式，避免非法日期如20240230）
    private static final SimpleDateFormat BIRTH_FORMAT = new SimpleDateFormat("yyyyMMdd");

    static {
        BIRTH_FORMAT.setLenient(false); // 关闭宽松模式，严格校验日期合法性
    }

    @Override
    public boolean isValid(String idCard, ConstraintValidatorContext context) {
        // 1. 空值处理（如需强制校验非空，需配合@NotBlank注解）
        if (idCard == null || idCard.trim().isEmpty()) {
            return false; // 若允许空，改为return true，再通过@NotBlank控制非空
        }
        idCard = idCard.trim();

        // 2. 校验长度：必须18位
        if (idCard.length() != 18) {
            return false;
        }

        // 3. 提取生日部分（7-14位）并校验日期格式
        String birthStr = idCard.substring(6, 14);
        Date birthDate;
        try {
            birthDate = BIRTH_FORMAT.parse(birthStr);
        } catch (ParseException e) {
            // 日期格式非法（如20240230、19991301）
            return false;
        }

        // 4. 校验生日≥1900年（19世纪及以前非法）
        Date minDate;
        try {
            minDate = BIRTH_FORMAT.parse("19000101");
        } catch (ParseException e) {
            // 理论上不会触发，19000101是合法日期
            return false;
        }
        if (birthDate.before(minDate)) {
            return false;
        }

        // 5. 校验生日≤当前时间
        Date now = new Date();
        if (birthDate.after(now)) {
            return false;
        }

        // 所有规则通过
        return true;
    }
}

package lds.com.medicalsystem.common.utils;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

/**
 * 身份证号校验注解（18位、生日合理、生日≤当前时间、生日≥1900年）
 */
@Target({ElementType.FIELD, ElementType.PARAMETER}) // 作用于字段/参数
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = IdCardValidator.class) // 指定校验器
public @interface ValidIdCard {
    // 校验失败提示语
    String message() default "身份证号不合法（需18位、生日合理且1900年后、不超过当前时间）";

    // 分组校验（默认）
    Class<?>[] groups() default {};

    // 负载（扩展用）
    Class<? extends Payload>[] payload() default {};
}

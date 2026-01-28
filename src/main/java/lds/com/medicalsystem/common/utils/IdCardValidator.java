package lds.com.medicalsystem.common.utils;


import lds.com.medicalsystem.common.exception.ValidationException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public final class IdCardValidator {
    // 前6位地区码[1-9]\d{5}，年份(19|20)\d{2}，日期\d{8}，顺序码三位\d{3}，校验码一位(数字或X)对应[0-9X]
    private static final String IdCardRegex = "^[1-9]\\d{5}(19|20)\\d{2}\\d{8}\\d{3}[0-9X]$";

    public static boolean isValid(String idCard) {
        if(idCard.length()!=18){
            return false;
        }
        // 提取并校验生日，第七位数字对应索引6
        String birthStr = idCard.substring(6, 14);
        if(!isValidBirthDate(birthStr)){
            return false;
        }
        return idCard.matches(IdCardRegex);
    }
    private static boolean isValidBirthDate(String birthStr){
        LocalDate birthDate = null;
        try {
            // 定义格式规则器，告诉parse传入字符串的格式为yyyyMMdd，让它不抛出异常能正确匹配为日期对象
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
            // 将字符串转化为日期对象，非法日期会抛异常, 2月份的有bug
            birthDate = LocalDate.parse(birthStr,formatter);
        } catch (Exception e) {
            throw new ValidationException("Invalid birth date");
        }
        LocalDate now = LocalDate.now();
        return birthDate.isBefore(now) || birthDate.isEqual(now);
    }
}

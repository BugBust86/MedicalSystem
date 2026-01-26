package lds.com.medicalsystem.common.exception;

// 参数校验异常，用于new ValidationException("参数错误")手动抛出
public class ValidationException extends BusinessException{
    public ValidationException(String message) {
        super(message);
    }
}

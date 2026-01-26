package lds.com.medicalsystem.common.exception;

// 业务逻辑异常，用于new BusinessException("错误信息")手动抛出
public class BusinessException extends RuntimeException{
    public BusinessException(String message) {
        super(message);
    }
}

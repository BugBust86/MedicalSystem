package lds.com.medicalsystem.common.exception;

// 业务逻辑异常，用于new BusinessException("错误信息")手动抛出
public class BusinessException extends RuntimeException{
    // 新增：存储底层异常，方便日志追溯
    private Throwable cause;
    // 一个参数的构造
    public BusinessException(String message) {
        super(message);
    }
    // 两个参数的构造
    public BusinessException(String message, Throwable cause) {
        super(message);
        this.cause = cause;
    }

    // 重写 getCause，让日志能打印完整异常链
    @Override
    public Throwable getCause () {
        return cause;
    }
}

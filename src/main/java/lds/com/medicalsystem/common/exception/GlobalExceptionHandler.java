package lds.com.medicalsystem.common.exception;

import lds.com.medicalsystem.common.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
// Lombok的@Slf4j注解会在编译阶段自动为类生成日志对象的代码
@Slf4j
// 全局异常处理器，捕获测试时出现的所有异常
public class GlobalExceptionHandler {
    // 处理业务异常
    @ExceptionHandler(BusinessException.class)
    public Result<Void> handleException(Exception e) {
        log.warn("业务异常：{}",e.getMessage());
        return Result.error(e.getMessage());
    }
    // 处理@Validated参数校验失败时抛出的异常
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public Result<Void> handleValidationException(MethodArgumentNotValidException e){
        String errorMsg = e.getBindingResult().getFieldError().getDefaultMessage();
        log.warn("参数校验失败: {}", errorMsg);
        return Result.error("参数错误：" + errorMsg);
    }
}

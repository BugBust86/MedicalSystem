package lds.com.medicalsystem.common.exception;

import lds.com.medicalsystem.common.ResultVO;
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
    public ResultVO<Void> handleBusinessException(BusinessException e) {
//        log.warn("业务异常：{}",e.getMessage());
//        return Result.error(e.getMessage());
        // 如果e.gerCause()返回的原因不为空（有底层异常），用 error 级别打印完整堆栈
        if (e.getCause () != null) {
            log.error ("业务异常: {}", e.getMessage (), e.getCause ());
        } else {
            // 否则仅打印业务异常对应的消息
            log.warn ("业务异常: {}", e.getMessage ());
        }
        // 最后返回前端的都是固定地返回格式：错误代码：0+错误消息：如注册失败等
        return ResultVO.error (e.getMessage ());
    }

    // 处理@Validated参数校验失败时抛出的异常
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResultVO<Void> handleValidationException(MethodArgumentNotValidException e){
        String errorMsg = e.getBindingResult().getFieldError().getDefaultMessage();
        log.warn("参数校验失败: {}", errorMsg);
        return ResultVO.error("参数错误：" + errorMsg);
    }

    // 兜底异常处理，防止异常被Tomcat中的全局处理器捕获，报500，服务器内部错误（Tomcat抛出的异常冗余繁琐，不利于定位原因）
    @ExceptionHandler(Exception.class)
    public ResultVO<Void> handleException(Exception e) {
        log.error("服务器内部错误：{}", e.getMessage());
        return ResultVO.error("服务器内部错误："+ e.getMessage());
    }
}

package lds.com.medicalsystem.common.utils.exception;

import lds.com.medicalsystem.common.VO.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
// Lombok的@Slf4j注解会在编译阶段自动为类生成日志对象的代码
@Slf4j
// 全局异常处理器，把抛的异常全部捕获并以正确的ResultVO格式返回给前端
// 全局捕获所有@RestController/@Controller的异常，返回JSON格式ResultVO
public class GlobalExceptionHandler {
    // 处理业务异常
    @ExceptionHandler(BusinessException.class)
    public ResultVO<Void> handleBusinessException(BusinessException e) {
        if (e.getCause () != null) {
            log.error ("业务异常: {}", e.getMessage (), e.getCause ());
        } else {
            // 否则仅打印业务异常对应的消息
            log.warn ("业务异常: {}", e.getMessage ());
        }
        // 最后返回前端的都是固定地返回格式：错误代码：0+错误消息：如注册失败等
        return ResultVO.error (e.getMessage ());
    }

    // 统一处理@Validated参数校验失败时抛出的异常
    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResultVO<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        // 收集所有字段的错误信息（保留第二个方法的完整逻辑）
        List<String> errorMsgList = e.getBindingResult().getFieldErrors().stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.toList());
        String errorMsg = String.join("; ", errorMsgList);
        log.warn("参数校验失败：{}", errorMsg);
        return ResultVO.error("参数错误: " + errorMsg);
    }

    // 兜底异常处理，防止异常被Tomcat中的全局处理器捕获，报500，服务器内部错误（Tomcat抛出的异常冗余繁琐，不利于定位原因）
    @ExceptionHandler(Exception.class)
    public ResultVO<Void> handleException(Exception e) {
        log.error("服务器内部错误：{}", e.getMessage());
        return ResultVO.error("服务器内部错误："+ e.getMessage());
    }
}

package lds.com.medicalsystem.common.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 全局统一响应结果
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultVO<T> {
    // 自定义状态码（0成功，1异常）
    private int code;
    // 提示信息
    private String message;
    // 响应数据（泛型支持任意类型）
    private T data;

    private ResultVO(int code,T data){
        this.code=code;
        this.data=data;
        this.message=null;
    }

    // 成功响应（带数据）,插在修饰符和返回值之间的<T>是泛型方法的声明，语法规定的
    public static <T> ResultVO<T> success(String message, T data) {
        return new ResultVO<>(0, message, data);
    }

    // 成功响应（无数据）
    public static <T> ResultVO<T> success(String message) {
        return new ResultVO<>(0, message, null);
    }

    // 成功响应（无消息，只传一个数据参数）
    public static <T> ResultVO<T> success(T data){
        return new ResultVO<>(0,data);
    }

    // 失败响应
    public static <T> ResultVO<T> error(String message) {
        return new ResultVO<>(1, message, null);
    }


}

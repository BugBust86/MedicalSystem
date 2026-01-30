package lds.com.medicalsystem.common.utils.config;

// 提供的set方法供拦截器使用，get方法供除登录注册外的所有接口使用，可获取登录token中的有效信息
public class ThreadLocalUtil {
    // 提供ThreadLocal对象
    private static final ThreadLocal THREAD_LOCAL = new ThreadLocal();

    // 根据键获取值
    public static <T> T get(){
        // 返回ThreadLocal对象get方法返回的值，并强转为<T>泛型
        return (T)THREAD_LOCAL.get();
    }

    // 存储键值对，提供给拦截器的，每个用户登录后拦截器都将token的有效负载记录在常量THREAD_LOCAL中
    public static void set(Object value){
        THREAD_LOCAL.set(value);
    }

    // 清除THREAD_LOCAL，防止内存泄漏
    public static void remove(){
        THREAD_LOCAL.remove();
    }
}

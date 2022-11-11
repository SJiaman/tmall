package com.example.common.utils;

import com.example.common.enums.ResultCode;
import lombok.Data;

import java.io.Serializable;

/**
 * 控制层需要响应前端浏览器，返回JasonResult类型数据，
 * 所以把这部分功能封装到一个类Result中需要继承Serializale
 *
 * @author tianchangqing
 * @Data自动提供get，set方法
 * @date
 */
@Data
public class Result<E> implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer state;
    private String message;
    /**
     * 数据类型不确定，声明为泛型E，类也要声明为泛型
     */
    private E data;

    /**
     * 创建构造函数
     * 将状态码传给构造方法初始化对象
     * 将状态码和描述信息传给构造方法初始化对象
     * 将异常传给构造方法初始化对象
     */
    public Result() {
    }

    /**
     * 成功返回
     *
     * @param data
     * @param <E>
     * @return
     */
    public static <E> Result<E> success(E data) {
        Result<E> result = new Result<>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage());
        result.setData(data);
        return result;
    }

    /**
     * 成功返回
     *
     * @param <E>
     * @return
     */
    public static <E> Result<E> success() {
        Result<E> result = new Result<>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage());
        return result;
    }

    /**
     * 失败返回
     *
     * @param data
     * @param <E>
     * @return
     */
    public static <E> Result<E> fail(E data) {
        Result<E> result = new Result<>(ResultCode.FAILED.getCode(), ResultCode.FAILED.getMessage());
        result.setData(data);
        return result;
    }

    public static <E> Result<E> fail() {
        Result<E> result = new Result<>(ResultCode.FAILED.getCode(), ResultCode.FAILED.getMessage());
        return result;
    }

    public Result(Integer state) {
        this.state = state;
    }

    public Result(Integer state, String message) {
        this.state = state;
        this.message = message;
    }

    public Result(Integer state, E data) {
        this.state = state;
        this.data = data;
    }

    public Result(Throwable e) {
        this.message = e.getMessage();
    }
}

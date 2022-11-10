package com.example.common.utils;

import com.example.common.enums.ResultCode;
import lombok.Data;

import java.io.Serializable;

/**
 * 控制层需要响应前端浏览器，返回JasonResult类型数据，
 * 所以把这部分功能封装到一个类JsonResult中需要继承Serializale
 *
 * @author tianchangqing
 * @Data自动提供get，set方法
 * @date
 */
@Data
public class JsonResult<E> implements Serializable {
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
    public JsonResult() {
    }

    /**
     * 成功返回
     *
     * @param data
     * @param <E>
     * @return
     */
    public static <E> JsonResult<E> success(E data) {
        JsonResult<E> result = new JsonResult<>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage());
        result.setData(data);
        return result;
    }

    /**
     * 成功返回
     *
     * @param <E>
     * @return
     */
    public static <E> JsonResult<E> success() {
        JsonResult<E> result = new JsonResult<>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage());
        return result;
    }

    /**
     * 失败返回
     *
     * @param data
     * @param <E>
     * @return
     */
    public static <E> JsonResult<E> fail(E data) {
        JsonResult<E> result = new JsonResult<>(ResultCode.FAILED.getCode(), ResultCode.FAILED.getMessage());
        result.setData(data);
        return result;
    }

    public static <E> JsonResult<E> fail() {
        JsonResult<E> result = new JsonResult<>(ResultCode.FAILED.getCode(), ResultCode.FAILED.getMessage());
        return result;
    }

    public JsonResult(Integer state) {
        this.state = state;
    }

    public JsonResult(Integer state, String message) {
        this.state = state;
        this.message = message;
    }

    public JsonResult(Integer state, E data) {
        this.state = state;
        this.data = data;
    }

    public JsonResult(Throwable e) {
        this.message = e.getMessage();
    }
}

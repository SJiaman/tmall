package com.example.common.enums;

/**
 * @author tianchangqing
 * @date 2022/9/2 16:02
 */
public enum ResponseStatusEnum {
    SUCCESS("200", "success"),
    NOT_FOUND("404", "not found"),
    OPERATE_FAIL("403", "操作失败"),

    ERROR_BUSY("-1", "系统繁忙"),
    ERROR_UNKNOWN("101", "未知异常"),
    ERROR_PARAM("102", "请求参数异常"),
    ERROR_REMOTE_CALL("103", "远程调用异常"),
    SYSTEM_ERROR("104", "系统错误"),
    ;

    private String code;
    private String message;

    ResponseStatusEnum(String code, String message) {
        this.setCode(code);
        this.setMessage(message);
    }

    public String getCode() {
        return code;
    }

    private void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    private void setMessage(String message) {
        this.message = message;
    }
}

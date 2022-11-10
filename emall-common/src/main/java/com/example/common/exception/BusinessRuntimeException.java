package com.example.common.exception;

/**
 * @author tianchangqing
 * @date 2022/8/25 16:40
 */
public class BusinessRuntimeException extends RuntimeException {
    private static final long serialVersionUID = 3423423234234L;

    private String code;

    public BusinessRuntimeException(String code, String message) {
        super(message);
        this.setCode(code);
    }

    public String getCode() {
        return code;
    }

    private void setCode(String code) {
        this.code = code;
    }
}

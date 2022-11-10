package com.example.common.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author tianchangqing
 * @date 2022/9/2 16:00
 */
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum ErrorMsgEnum {
    /**
     * 成功
     */
    SUCCESS("00000000", "成功"),

    /**
     * 返回给页面成功CODE
     */
    PAGE_SUCCESS("0", "成功"),
    ;

    private String errorCode;

    private String errorMsg;


    @Getter
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public enum StoreError {
        NOT_FOUND_PRODUCT_ERROR("10001", "没有该商品信息"),
        INSERT_PRODUCT_ERROR("10002", "商品添加未知错误"),
        DELETE_PRODUCT_ERROR("10003", "商品删除未知错误"),
        UPDATE_PRODUCT_ERROR("10004", "商品修改出现未知异常")
        ;


        private String errorCode;

        private String errorMsg;
    }


    @Getter
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public enum ShopError {
        NOT_FOUND_PRODUCT_ERROR("20001", "没有该商品信息"),
        INSERT_PRODUCT_ERROR("10002", "商品添加未知错误"),
        DELETE_PRODUCT_ERROR("10003", "商品删除未知错误"),
        UPDATE_PRODUCT_ERROR("10004", "商品修改出现未知异常")
        ;


        private String errorCode;

        private String errorMsg;
    }
}

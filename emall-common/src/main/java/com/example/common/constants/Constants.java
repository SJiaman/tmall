package com.example.common.constants;

/**
 * @author tianchangqing
 * @date 2022/9/2 16:23
 */
public final class Constants {
    private Constants(){}

    public static final String STORE_ADMIN = "仓库管理员";

    public static final Integer PRODUCT_ON_SALE = 1;

    public static final Integer PRODUCT_SOLD_OUT = 2;

    public static final Integer PURCHASE = 1;

    public static final Integer RETURN_PRODUCT = 2;



    // redis
    public static final String LOGIN_CODE_KEY = "login:code:";

    public static final Long LOGIN_CODE_TTL = 2L;

    public static final String LOGIN_USER_KEY = "login:token:";

    public static final Long LOGIN_USER_TTL = 36000L;

    public static final Long CACHE_NULL_TTL = 2L;

    public static final Long CACHE_SHOP_TTL = 30L;

    public static final String CACHE_SHOP_KEY = "cache:shop:";

    public static final String LOCK_SHOP_KEY = "lock:shop:";

    public static final Long LOCK_SHOP_TTL = 10L;

    public static final String SECKILL_STOCK_KEY = "seckill:stock:";

    public static final String BLOG_LIKED_KEY = "blog:liked:";

    public static final String FEED_KEY = "feed:";

    public static final String SHOP_GEO_KEY = "shop:geo:";

    public static final String USER_SIGN_KEY = "sign:";

}

package com.example.store.interceptor;

import com.example.common.constants.Constants;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.*;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.Properties;

/**
 * @author tianchangqing
 * @date 2022/9/9 15:43
 * @desc
 */
@Component
@Intercepts({
        @Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class})
})
public class TimeInterceptor implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
        Object object = invocation.getArgs()[1];
        //sql类型
        SqlCommandType sqlCommandType = mappedStatement.getSqlCommandType();
        if (SqlCommandType.INSERT.equals(sqlCommandType)) {
            //插入操作时，自动插入env
            Field createdTime = object.getClass().getDeclaredField("createdTime");
            createdTime.setAccessible(true);
            createdTime.set(object, new Date());
            Field createdUser = object.getClass().getDeclaredField("createdUser");
            createdUser.setAccessible(true);
            createdUser.set(object, Constants.STORE_ADMIN);
        }else{
            if (SqlCommandType.UPDATE.equals(sqlCommandType)) {
                //update时，自动更新updated_at
                Field modifiedTime = object.getClass().getDeclaredField("modifiedTime");
                modifiedTime.setAccessible(true);
                modifiedTime.set(object, new Date());
                Field modifiedUser = object.getClass().getDeclaredField("modifiedUser");
                modifiedUser.setAccessible(true);
                modifiedUser.set(object, Constants.STORE_ADMIN);
            }
        }
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object o) {
        return Plugin.wrap(o, this);
    }

    @Override
    public void setProperties(Properties properties) {

    }
}

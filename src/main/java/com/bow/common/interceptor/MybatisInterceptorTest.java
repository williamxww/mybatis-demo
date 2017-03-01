package com.bow.common.interceptor;

import java.util.Map;
import java.util.Properties;

import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;

/**
 * @author ViVi
 * @date 2015年6月28日 下午1:46:23
 */
@Intercepts({ @Signature(type = Map.class, method = "get", args = { Object.class }) })
public class MybatisInterceptorTest implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        System.out.println("hi  vv");
        return "vv";
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {

    }

}

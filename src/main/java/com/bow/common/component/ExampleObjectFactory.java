package com.bow.common.component;

import org.apache.ibatis.reflection.factory.DefaultObjectFactory;

import java.util.Collection;
import java.util.Properties;

/**
 * @author vv
 * @since 2017/3/1.
 */
public class ExampleObjectFactory extends DefaultObjectFactory {

    public <T> T create(Class<T> type) {
        return super.create(type);
    }

    public void setProperties(Properties properties) {
        super.setProperties(properties);
    }

    public <T> boolean isCollection(Class<T> type) {
        return Collection.class.isAssignableFrom(type);
    }
}

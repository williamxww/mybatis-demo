package com.bow.dao;

import java.io.IOException;
import java.io.Reader;
import java.util.Properties;

import com.bow.entity.User;
import org.apache.ibatis.datasource.pooled.PooledDataSourceFactory;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.junit.Test;

import javax.sql.DataSource;

public class MyBatisTest {

    @Test
    public void test1() {
        String resource = "mybatis/mybatis-config-test.xml";
        Reader reader = null;
        try {
            reader = Resources.getResourceAsReader(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader);
        sessionFactory.openSession();
    }

    @Test
    public void test2() {
        Properties properties = new Properties();
        properties.setProperty("driver", "com.mysql.jdbc.Driver");
        properties.setProperty("url", "jdbc:mysql://127.0.0.1:3306/shiro");
        properties.setProperty("username", "xww");
        properties.setProperty("password", "123456");
        PooledDataSourceFactory pooledDataSourceFactory = new PooledDataSourceFactory();
        pooledDataSourceFactory.setProperties(properties);
        DataSource dataSource = pooledDataSourceFactory.getDataSource();
        TransactionFactory transactionFactory = new JdbcTransactionFactory();
        Environment environment = new Environment("development", transactionFactory, dataSource);
        Configuration configuration = new Configuration(environment);
        configuration.addMapper(UserMapper.class);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);
        // 每个线程都应该有它自己的 SqlSession 实例，用完必须关闭
        SqlSession session = sqlSessionFactory.openSession();
        try {
            UserMapper mapper = session.getMapper(UserMapper.class);
            User user = mapper.getUser(1);
            System.out.println(user.getUsername());
//            session.selectOne("com.bow.entity.User.getUser", 1);
        } finally {
            session.close();
        }

    }
}

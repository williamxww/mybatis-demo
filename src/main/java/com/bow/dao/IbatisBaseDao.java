package com.bow.dao;

import java.util.List;

import com.bow.common.component.Page;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class IbatisBaseDao {

    @Autowired
    private SqlSession sqlSession;

    public List<Object> getByPage(String sqlId, Object parameter, Page page) {
        RowBounds bounds = new RowBounds((page.getCurrentPage() - 1) * page.getPageSize() + 1, page.getPageSize());
        return sqlSession.selectList(sqlId, parameter, bounds);
    }

    public Page calculatePage(String sqlId, Object parameter, Page page) {
        return null;
    }

}

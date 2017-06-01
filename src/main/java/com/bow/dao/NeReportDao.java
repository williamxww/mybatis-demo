package com.bow.dao;

import com.bow.entity.NeReport;

import java.util.List;

/**
 * @author vv
 * @since 2017/6/1.
 */
public interface NeReportDao {

    boolean batchInsert(List<NeReport> neReports);
}

package com.bow.dao;

import com.bow.entity.OnuReport;

import java.util.List;

/**
 * @author vv
 * @since 2017/6/1.
 */
public interface OnuReportDao {

    boolean batchInsert(List<OnuReport> onuReports);

}

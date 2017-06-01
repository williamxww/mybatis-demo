package com.bow.dao;

import com.bow.entity.NeReport;
import com.bow.entity.OnuReport;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.*;

/**
 * @author vv
 * @since 2017/6/1.
 */
public class NeReportDaoTest {


    private NeReportDao dao;

    private Random random = new Random(2);

    @Before
    public void before() {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring/applicationContext-mybatis.xml");
        dao = context.getBean(NeReportDao.class);
    }


    @Test
    public void batchInsert() throws Exception {
        List<NeReport> reports = new ArrayList();
        for(int i=0; i<1000; i++){
            reports.add(buildReport(i));
        }
        dao.batchInsert(reports);
    }


    public NeReport buildReport(int neId) {
        NeReport report = new NeReport();
        report.setId("10000" + neId);
        report.setEmsId(10000);
        report.setNeId(neId);

        report.setEmsName("武汉EMS");
        report.setAreaName("武汉市江夏区");
        report.setNeName("武汉市江夏区"+neId);
        report.setNeIp("10.168.12.34");

        report.setPmValue101(generate());
        report.setPmValue102(generate());
        report.setPmValue103(generate());
        report.setPmValue104(generate());
        report.setPmValue105(generate());
        return report;
    }

    private Double generate(){
        BigDecimal decimal = new BigDecimal(10+10*random.nextDouble());
        return decimal.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

}
package com.bow.dao;

import com.bow.entity.OnuReport;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author vv
 * @since 2017/6/1.
 */
public class OnuReportDaoTest {

    private OnuReportDao dao;

    private Random random = new Random(2);

    @Before
    public void before() {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring/applicationContext-mybatis.xml");
        dao = context.getBean(OnuReportDao.class);
    }

    @Test
    public void batchInsert() throws Exception {
        long totalCost = 0;
        long totalCount = 0;
        for(int i=0; i<200; i++){
            List<OnuReport> reports = build(100+i, 1, 5, 10, 100);
            long begin = System.currentTimeMillis();
            dao.batchInsert(reports);
            totalCost += System.currentTimeMillis()-begin;
            totalCount += reports.size();
        }
        System.out.println(">>> totalCount "+totalCount+" totalCost "+totalCost);
    }

    /**
     *
     * @param startNe 从哪个网元开始
     * @param neNum 网元数量
     * @param boardPerNe 每个网元对应盘数量
     * @param portPerBoard 每个盘对应端口数量
     * @param onuPerPort 每个端口对应ONU数量
     * @return
     */
    public List<OnuReport> build(int startNe, int neNum, int boardPerNe, int portPerBoard, int onuPerPort) {
        List<OnuReport> reports = new ArrayList();
        for (int i = 0; i < neNum; i++) {
            int neId = startNe + i;
            for (int b = 0; b < boardPerNe; b++) {
                for (int p = 0; p < portPerBoard; p++) {
                    for (int o = 0; o < onuPerPort; o++) {
                        int boardId = Integer.parseInt(neId + "" + b);
                        reports.add(buildReport(neId, boardId, p, o));
                    }
                }
            }
        }
        return reports;
    }

    public OnuReport buildReport(int neId, int boardId, int portNo, int onuId) {
        OnuReport report = new OnuReport();
        report.setId("10000" + neId + "-" + boardId + "-" + portNo + "-" + onuId);
        report.setEmsId(10000);
        report.setNeId(neId);
        // 1网元对应8个盘
        report.setBoardId(boardId);
        report.setPortNo(portNo);

        report.setOnuId(onuId);
        report.setOnuPonPortNo(1);

        report.setEmsName("武汉EMS");
        report.setAreaName("武汉市江夏区");
        report.setNeName("武汉市江夏区"+neId);
        report.setNeIp("10.168.12.34");
        report.setBoardName("BC001");
        report.setOnuName("PON3");

        report.setPmValue301(generate());
        report.setPmValue302(generate());
        report.setPmValue303(generate());
        report.setPmValue304(generate());
        report.setPmValue305(generate());
        return report;
    }

    private Double generate(){
        BigDecimal decimal = new BigDecimal(10+10*random.nextDouble());
        return decimal.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    @Test
    public void test(){
        System.out.println(generate());
    }

}
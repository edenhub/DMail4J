package pri.adam.dmail.log.operate;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pri.adam.dmail.log.Version;
import pri.adam.dmail.utils.dbutil.DBManager;

import java.io.InputStream;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Set;

/**
 * Created by adam on 2014/12/5.
 */
public class TestOperateLogger {
    private InputStream inputStream = Version.class.getResourceAsStream("../../../../MySQLConn.properties");
    private DBManager dbManager = new DBManager();
    private OperateLogger operateLogger;

    @Before
    public void before(){
        assert dbManager.conn(inputStream)== true;
        operateLogger = OperateLogger.getInstance(dbManager.getConnection());
    }

    @After
    public void after(){
        dbManager.disConn();
    }

    @Test
    public void testAdd(){
        OperateBean operateBean = new OperateBean( Date.valueOf("2014-3-3"),"adam","test",1,1);
        operateLogger.addOperateLog(operateBean);
    }

    @Test
    public void testEnum(){
        OperateBean operateBean = new OperateBean(Date.valueOf("2014-12-06"),"test","test",
                OperateBean.OperateLevel.TEST,OperateBean.OperateResult.SUCCESS);
        operateLogger.addOperateLog(operateBean);
    }

    @Test
    public void testSelectAll(){
        Set<OperateBean> beans = operateLogger.selectAllOperateLog();
        for (OperateBean bean : beans){
            System.out.println(bean);
        }
    }

    @Test
    public void testSelectRange(){
        Set<OperateBean> beans = operateLogger.selectRangeOperateLog(2,3);
        for (OperateBean bean : beans)
            System.out.println(bean);
    }

    @Test
    public void testSelectLeft(){
        Set<OperateBean> beans = operateLogger.selectStartOperateLog(2);
        for (OperateBean bean : beans)
            System.out.println(bean);
    }

    @Test
    public void testSelectEnd(){
        Set<OperateBean> beans = operateLogger.selectEndOperateLog(3);
        for (OperateBean bean : beans)
            System.out.println(bean);
    }

    @Test
    public void testInsert() throws SQLException {
        InputStream inputStream = Version.class.getResourceAsStream("../../../../MySQLConn.properties");
        DBManager dbManager = new DBManager();
        assert dbManager.conn(inputStream)==true;

        String sql = "insert into operatelog (datetime,initiator,f_describe,f_level,result) values(?,?,?,?,?)";
        PreparedStatement statement = dbManager.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        statement.setDate(1,Date.valueOf("2014-3-3"));
        statement.setString(2,"adam");
        statement.setString(3,"test");
        statement.setInt(4,1);
        statement.setInt(5,2);
        statement.executeUpdate();

    }
}

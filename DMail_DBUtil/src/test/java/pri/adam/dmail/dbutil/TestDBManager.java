package pri.adam.dmail.dbutil;

import org.junit.Test;

import java.io.InputStream;

/**
 * Created by adam on 2014/12/5.
 */
public class TestDBManager {

    @Test
    public void test01(){
        InputStream in = DBManager.class.getResourceAsStream("../../../../MySQLConn.properties");
        DBManager manager = new DBManager();
        assert manager.conn(in) == true;
        assert manager.isConnected() == true;
        assert manager.disConn() == true;
    }
}

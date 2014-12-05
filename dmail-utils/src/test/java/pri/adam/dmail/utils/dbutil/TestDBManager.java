package pri.adam.dmail.utils.dbutil;

import org.junit.Test;
import pri.adam.dmail.utils.Version;

import java.io.InputStream;

/**
 * Created by adam on 2014/12/5.
 */
public class TestDBManager {

    @Test
    public void test01(){
        InputStream inputStream = Version.class.getResourceAsStream("../../../../MySQLConn.properties");
        DBManager dbManager = new DBManager();
        assert dbManager.conn(inputStream) == true;
        assert dbManager.disConn() == true;
    }
}

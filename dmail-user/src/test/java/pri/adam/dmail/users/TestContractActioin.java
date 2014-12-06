package pri.adam.dmail.users;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pri.adam.dmail.users.action.ContractAction;
import pri.adam.dmail.users.model.Contract;
import pri.adam.dmail.users.model.User4Proxy;
import pri.adam.dmail.utils.dbutil.DBManager;

import java.io.InputStream;

/**
 * Created by adam on 2014/12/6.
 */
public class TestContractActioin {
    private InputStream inputStream = pri.adam.dmail.log.Version.class.getResourceAsStream("../../../../MySQLConn.properties");
    private DBManager dbManager = new DBManager();
    private ContractAction contractAction;

    @Before
    public void before(){
        assert dbManager.conn(inputStream) == true;
        contractAction = ContractAction.getInstance(dbManager.getConnection());
    }

    @After
    public void after(){
        dbManager.disConn();
    }

    @Test
    public void testAddContract(){
        Contract contract = new Contract("cxd_dan@126.com",1);
        contractAction.addContract(contract);
    }

    @Test
    public void testFindContractById(){
        Contract contract = contractAction.getContractById(2);
        System.out.println(contract);
    }

    @Test
    public void testFindFriendByContractId(){
        User4Proxy proxy = contractAction.findFriendByContractId(2);
        System.out.println(proxy);
    }
}

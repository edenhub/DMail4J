package pri.adam.dmail.users;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pri.adam.dmail.users.action.UserAction;
import pri.adam.dmail.users.model.Contract;
import pri.adam.dmail.users.model.User4Proxy;
import pri.adam.dmail.utils.dbutil.DBManager;
import pri.adam.dmail.utils.security.DigestUtil;

import java.io.InputStream;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by adam on 2014/12/6.
 */
public class TestUserAction {
    private InputStream inputStream = pri.adam.dmail.log.Version.class.getResourceAsStream("../../../../MySQLConn.properties");
    private DBManager dbManager = new DBManager();
    private UserAction userAction;

    @Before
    public void before(){
        assert dbManager.conn(inputStream) == true;
        userAction = UserAction.getInstance(dbManager.getConnection());
    }

    @After
    public void after(){
        dbManager.disConn();
    }

    @Test
    public void testAddUser() throws NoSuchAlgorithmException {
        String pwd = DigestUtil.digestString("test","SHA");
        User4Proxy proxy = new User4Proxy("adam3",pwd);

        System.out.println(userAction.addUser(proxy));
    }

    @Test
    public void testIsUserExit(){
        String user1 = "adam",user2 = "jhon";
        assert userAction.isUserExit(user1) == true;
        assert userAction.isUserExit(user2) == false;
    }

    @Test
    public void testIsUserValidated() throws NoSuchAlgorithmException {
        User4Proxy user1 = new User4Proxy("adam3",DigestUtil.digestString("test","SHA"));
        User4Proxy user2 = new User4Proxy("jhon","2");

        assert userAction.isUserValidated(user1) == true;
        assert userAction.isUserValidated(user2) == false;
    }

    @Test
    public void testGetUser(){
        User4Proxy user1 = userAction.getProxyById(4);
        User4Proxy user2 = userAction.getProxyByUsername(user1.getUsername());

        System.out.println(user1+"\n"+user2);
        assert user1.equals(user2);
    }

    @Test
    public void testUpdatePwd() throws NoSuchAlgorithmException {
        User4Proxy newProxy = new User4Proxy("adam",DigestUtil.digestString("adamchen","SHA"));
        assert userAction.updatePwdHash(newProxy)==true;
        assert userAction.isUserValidated(newProxy)==true;
    }

    @Test
    public void testGetContracts(){
        Set<Contract> contracts = userAction.getAllContracts(4);
        Iterator<Contract> iter = contracts.iterator();
        while(iter.hasNext())
            System.out.println(iter.next());

        System.out.println("----------------------------------");
        Set<Contract> contracts1 = userAction.getRangeContracts(4,1,3);
        Iterator<Contract> iter2 = contracts1.iterator();
        while(iter2.hasNext())
            System.out.println(iter2.next());
    }

    @Test
    public void testDeleteUser(){
        String username = "adam";

        assert userAction.deleteUserBySYSTEM(username) == true;
    }
}

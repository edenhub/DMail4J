package pri.adam.dmail.users;

import org.junit.Test;
import pri.adam.dmail.users.action.UserActionSql;

import java.text.MessageFormat;

/**
 * Created by adam on 2014/12/6.
 */
public class TestMessageFormat {

    @Test
    public void test01(){
        String sql = UserActionSql.addProxySql;
        String serverSql = UserActionSql.addServerSql;
        System.out.println(MessageFormat.format(sql,new Object[]{"adam","aaa"}));
        System.out.println(MessageFormat.format(serverSql,new Object[]{"adam","sss","SHA"}));
    }
}

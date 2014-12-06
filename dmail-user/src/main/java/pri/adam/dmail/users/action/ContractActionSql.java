package pri.adam.dmail.users.action;

/**
 * Created by adam on 2014/12/6.
 */
public interface ContractActionSql {

    static final String addContractSql = "insert into contracts(mail,alias,content,tofriend) values(?,?,?,?)";
    static final String findContractByIdSql = "select * from contracts where id = ?";
    static final String findFriendSql = "select f.id,f.username,f.pwdHash from contracts as c,user4proxy as f where c.id = ? and c.tofriend = f.id ";

    static final String updateContractSql = "update contracts set mail = ?,alias = ?,content = ? where id = ?";

    static final String deleteContractByIdSql = "delete from contracts where id = ?";
}

package pri.adam.dmail.users.action;

/**
 * Created by adam on 2014/12/6.
 */
public interface UserActionSql {

  static final String addProxySql = "insert into user4proxy(username,pwdHash) values(\"{0}\",\"{1}\")";
  static final String addServerSql = "insert into users(username,pwdHash,pwdAlgorithm) values (\"{0}\",\"{1}\",\"{2}\")";

  static final String isUserExitSql = "select username from users where username = ?";
  static final String isUserValidateSql = "select username,pwdHash from users where username = ? and pwdHash = ?";

  static final String getProxyByIdSql = "select * from user4proxy where id = ?";
  static final String getProxyByUsernameSql = "select * from user4proxy where username = ?";

  static final String updateProxySql = "update user4proxy set pwdHash = \"{0}\" where username = \"{1}\"";
  static final String updateServerSql = "update users set pwdHash = \"{0}\" where username =\"{1}\"";

  static final String getAllContractsSql = "select c.id,c.mail,c.alias,c.content from user4proxy as u,contracts as c where u.id = ? and c.tofriend = u.id ";

  static final String deleteProxySql = "delete from user4proxy where username = \"{0}\"";
  static final String deleteServerSql = "delete from users where username = \"{0}\"";
}

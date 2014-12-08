package pri.adam.dmail.users.action.UserFromServerImpl;

/**
 * Created by adam on 2014/12/8.
 */
public interface UserServerSql {
    static final String addServerSql = "insert into users(username,pwdHash,pwdAlgorithm) values (\"{0}\",\"{1}\",\"{2}\")";

    static final String updateServerSql = "update users set pwdHash = \"{0}\" where username =\"{1}\"";

    static final String deleteServerSql = "delete from users where username = \"{0}\"";
}

package pri.adam.dmail.log.operate;

/**
 * Created by adam on 2014/12/5.
 */
public interface OperateLogSql {

    static final String addOperateLog =  "insert into operatelog (datetime,initiator,f_describe,f_level,result) values(?,?,?,?,?)";
    static final String seeAllOperateLog = "select * from operatelog";
}

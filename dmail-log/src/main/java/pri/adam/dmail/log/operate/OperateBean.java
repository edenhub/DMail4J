package pri.adam.dmail.log.operate;

import java.sql.Date;

/**
 * Created by adam on 2014/12/5.
 */
public class OperateBean {
    private Integer id;
    private Date datetime;
    private String initiator;
    private String f_describe;
//    操作级别：{1：系统，2：用户，3：测试}
    private int f_level;
//    操作结果：{1：成功，2：失败}
    private int result;

    public OperateBean(String initiator,int level){
        this.initiator = initiator;
        this.f_level = level;
    }

    public OperateBean(Date datetime,String initiator,int level){
        this.datetime = datetime;
        this.initiator = initiator;
        this.f_describe = "";
        this.f_level = level;
        this.result = 0;
    }

    public OperateBean(Date datetime, String initiator, String describe, int level, int result) {
        this.datetime = datetime;
        this.initiator = initiator;
        this.f_describe = "";
        this.f_level = level;
        this.result = result;
    }

    public OperateBean(Integer id, Date datetime, String initiator, String describe, int level, int result) {
        this.id = id;
        this.datetime = datetime;
        this.initiator = initiator;
        this.f_describe = "";
        this.f_level = level;
        this.result = result;
    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    public String getInitiator() {
        return initiator;
    }

    public void setInitiator(String initiator) {
        this.initiator = initiator;
    }

    public String getF_describe() {
        return f_describe;
    }

    public void setF_describe(String f_describe) {
        this.f_describe = f_describe;
    }

    public int getF_level() {
        return f_level;
    }

    public void setF_level(int f_level) {
        this.f_level = f_level;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof OperateBean))
            return false;

        OperateBean operateBean = (OperateBean) obj;
        if (!this.id.equals(operateBean.getId()) || !this.datetime.equals(operateBean.getDatetime()) )
            return false;

        return true;
    }

    @Override
    public String toString() {
        return "OperateBean{" +
                "id=" + id +
                ", datetime=" + datetime +
                ", initiator='" + initiator + '\'' +
                ", f_describe='" + f_describe + '\'' +
                ", f_level=" + f_level +
                ", result=" + result +
                '}';
    }

    public class OperateLevel{
        public static final int SYSTEM=1;
        public static final int USER=2;
        public static final int TEST=3;
    }

    public class OperateResult{
        public static final int SUCCESS=1;
        public static final int FAIL=2;
    }

//    public enum OperateLevel{
////        SYSTEM,USER,TEST
//        SYSTEM(1),USER(2),TEST(3);
//
//        private OperateLevel(int l){
//            this.level = l;
//        }
//
//        private int level;
//
//        public int getLeve(){
//            return level;
//        }
//    }
//
//    public enum OperateResult{
//        SUCCESS(1),FAIL(2);
//
//        private OperateResult(int r){
//            this.result = r;
//        }
//
//        private int result;
//    }

}

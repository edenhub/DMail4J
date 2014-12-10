package pri.adam.dmail.core.dto;

import javax.mail.Authenticator;
import java.util.Arrays;
import java.util.Date;

/**
 * Created by adam on 2014/12/10.
 */
public class MessageInfoDto {
    private String from;
    private String[] to;
    private String subject;
    private Date sendDate;

    public MessageInfoDto() {
        sendDate = new Date();
        subject = "无主题";
    }

    public MessageInfoDto(String from,String[] to){
        this();
        this.from = from;
        this.to = to;
    }

    public MessageInfoDto(String from,String to){
        this();
        this.from = from;
        this.to = new String[]{to};
    }

    public MessageInfoDto(String from,String[] to,Date date){
        this.from = from;
        this.to = to;
        this.sendDate = date;
        subject = "无主题";
    }

    public MessageInfoDto(String from,String[] to,String subject){
        this(from,to);
        this.subject = subject;
    }

    public MessageInfoDto(String from,String to,String subject){
        this(from,to);
        this.subject = subject;
    }

    public MessageInfoDto(String from,String to,Date date){
        this.from = from;
        this.to = new String[]{to};
        this.sendDate = date;
        subject = "无主题";
    }

    public MessageInfoDto(String from,String[] to,Date date,String subject){
        this.from = from;
        this.sendDate = date;
        this.to = to;
        this.subject = subject;
    }

    public MessageInfoDto(String from,String to,Date date,String subject){
        this(from,new String[]{to},date,subject);
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String[] getTo() {
        return to;
    }

    public void setTo(String[] to) {
        this.to = to;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Date getSendDate() {
        return sendDate;
    }

    public void setSendDate(Date sendDate) {
        this.sendDate = sendDate;
    }

    @Override
    public String toString() {
        return "MessageInfoDto{" +
                "from='" + from + '\'' +
                ", to=" + Arrays.toString(to) +
                ", subject='" + subject + '\'' +
                ", sendDate=" + sendDate +
                '}';
    }
}

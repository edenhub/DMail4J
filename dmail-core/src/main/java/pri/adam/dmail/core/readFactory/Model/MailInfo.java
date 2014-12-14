package pri.adam.dmail.core.readFactory.model;

import java.util.Date;

/**
 * Created by adam on 2014/12/12.
 */
public class MailInfo {

    private String from;
    private String subject;
    private Date date;
    private String messageId;

    public MailInfo(String messageId){
        this.messageId = messageId;
    }

    public MailInfo(String from,Date date,String messageId){
        this(messageId);
        this.from = from;
        this.date = date;
        this.subject = "";
    }

    public MailInfo(String from,String subject,Date date,String messageId){
        this.from = from;
        this.subject = subject;
        this.date =date;
        this.messageId = messageId;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    @Override
    public String toString() {
        return "MailInfo{" +
                "from='" + from + '\'' +
                ", subject='" + subject + '\'' +
                ", date=" + date +
                ", messageId='" + messageId + '\'' +
                '}';
    }
}

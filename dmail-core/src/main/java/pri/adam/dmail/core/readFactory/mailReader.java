package pri.adam.dmail.core.readFactory;

import pri.adam.dmail.core.readFactory.Model.MailInfo;

import java.util.List;

/**
 * Created by adam on 2014/12/12.
 */
public interface mailReader {
    public List<MailInfo> getMessagesByRange(String start,String end);

    public List<MailInfo> getReceivedMessagesByRange(String start,String end);

    public List<MailInfo> getSendedMessagesByRange(String start,String end);

    public List<MailInfo> getDeletedMessagesByRange(String start,String end);

    public List<MailInfo> getWastedMessagesByRange(String start,String end);
}

package pri.adam.dmail.core.readFactory;

import pri.adam.dmail.core.readFactory.model.MailInfo;

import javax.mail.MessagingException;
import java.util.List;

/**
 * Created by adam on 2014/12/12.
 */
public interface MailReader {
    public List<MailInfo> getMessagesByRange(int start,int end) throws MessagingException;


    public List<MailInfo> getSendedMessagesByRange(int start,int end);

    public List<MailInfo> getDeletedMessagesByRange(int start,int end);

    public List<MailInfo> getWastedMessagesByRange(int start,int end);
}

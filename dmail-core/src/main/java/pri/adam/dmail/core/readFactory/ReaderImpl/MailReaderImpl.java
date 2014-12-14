package pri.adam.dmail.core.readFactory.readerImpl;

import com.sun.mail.pop3.POP3Store;
import org.apache.log4j.Logger;
import pri.adam.dmail.core.readFactory.MailReader;
import pri.adam.dmail.core.readFactory.model.MailInfo;

import javax.mail.*;
import javax.mail.internet.MimeMessage;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

/**
 * Created by adam on 2014/12/13.
 */
public class MailReaderImpl extends ReaderImpl implements MailReader {

    public MailReaderImpl(Authenticator auth) {
        super(auth);
    }

    public MailReaderImpl(Properties sessionPro, Authenticator auth) {
        super(sessionPro, auth);
    }

    public MailReaderImpl(Session session, Authenticator auth) {
        super(session, auth);
    }

    @Override
    public List<MailInfo> getMessagesByRange(int start, int end) throws MessagingException {
        Folder folder = pop3Store.getFolder("inbox");
        folder.open(Folder.READ_ONLY);

        Message[] messages = folder.getMessages(start,end);
        List<MailInfo> mailInfos = new ArrayList<MailInfo>(messages.length);
        for(int i=0;i<messages.length;i++){
            MimeMessage message = (MimeMessage) messages[i];
            String from = message.getFrom()[0].toString();
            String messageId = message.getMessageID();
            String subject = message.getSubject();
            Date date = message.getSentDate();
            MailInfo mailInfo = new MailInfo(from,subject,date,messageId);
            mailInfos.add(mailInfo);
        }

        folder.close(false);

        return mailInfos;
    }


    @Override
    public List<MailInfo> getSendedMessagesByRange(int start, int end) {
        return null;
    }

    @Override
    public List<MailInfo> getDeletedMessagesByRange(int start, int end) {
        return null;
    }

    @Override
    public List<MailInfo> getWastedMessagesByRange(int start, int end) {
        return null;
    }
}

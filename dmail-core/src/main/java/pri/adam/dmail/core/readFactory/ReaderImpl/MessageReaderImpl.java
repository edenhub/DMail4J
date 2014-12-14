package pri.adam.dmail.core.readFactory.readerImpl;

import pri.adam.dmail.core.readFactory.MessageReader;
import pri.adam.dmail.core.readFactory.model.AttrachInfo;

import javax.mail.*;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.search.MessageIDTerm;
import javax.mail.search.SearchTerm;
import java.util.List;
import java.util.Properties;

/**
 * Created by adam on 2014/12/13.
 */
public class MessageReaderImpl extends ReaderImpl implements MessageReader {

    public MessageReaderImpl(Authenticator auth) {
        super(auth);
    }

    public MessageReaderImpl(Properties sessionPro, Authenticator auth) {
        super(sessionPro, auth);
    }

    public MessageReaderImpl(Session session, Authenticator auth) {
        super(session, auth);
    }

    @Override
    public MimeMessage getMessageById(String messageId,Folder folder) throws MessagingException {
        SearchTerm messageIdTerm = new MessageIDTerm(messageId);
        Message[] messages = folder.search(messageIdTerm);

        return (MimeMessage)messages[0];
    }

    @Override
    public MimeBodyPart getBodyPartByContentId(MimeMultipart multipart, String contentId) throws MessagingException {
        MimeBodyPart bodyPart = (MimeBodyPart) multipart.getBodyPart(contentId);

        return bodyPart;
    }

    @Override
    public List<AttrachInfo> getAttrachInfosFromMessage(MimeMessage message) {

        return null;
    }
}

package pri.adam.dmail.core.readFactory;

import pri.adam.dmail.core.readFactory.model.AttrachInfo;

import javax.mail.BodyPart;
import javax.mail.Folder;
import javax.mail.MessagingException;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.List;

/**
 * Created by adam on 2014/12/12.
 */
public interface MessageReader {

    public MimeMessage getMessageById(String messageId,Folder folder) throws MessagingException;

    public MimeBodyPart getBodyPartByContentId(MimeMultipart multipart,String contentId) throws MessagingException;

    public List<AttrachInfo> getAttrachInfosFromMessage(MimeMessage message);


}

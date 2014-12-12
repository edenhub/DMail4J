package pri.adam.dmail.core.readFactory;

import pri.adam.dmail.core.readFactory.Model.AttrachInfo;
import pri.adam.dmail.core.readFactory.Model.MailInfo;

import javax.mail.BodyPart;
import javax.mail.internet.MimeMessage;
import java.util.List;

/**
 * Created by adam on 2014/12/12.
 */
public interface messageReader {

    public MimeMessage getMessageById(String messageId);

    public BodyPart getBodyPartByContentId(MimeMessage message,String contentId);

    public List<AttrachInfo> getAttrachInfosFromMessage(MimeMessage message);


}

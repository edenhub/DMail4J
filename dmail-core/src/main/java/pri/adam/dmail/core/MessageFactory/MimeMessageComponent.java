package pri.adam.dmail.core.messageFactory;

import pri.adam.dmail.core.messageFactory.dto.MessageAttrachDto;
import pri.adam.dmail.core.messageFactory.dto.MessageHtmlContentDto;
import pri.adam.dmail.core.messageFactory.dto.MessageInfoDto;
import pri.adam.dmail.core.messageFactory.dto.MessageTextContentDto;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import java.util.List;

/**
 * Created by adam on 2014/12/10.
 */
public interface MimeMessageComponent {

    public MimeMessage createMessageObject(Session session);

    public MimeMessage createMessageObject(Session session, MessageInfoDto messageInfo) throws MessagingException;

    public MimeBodyPart createTextPart(MessageTextContentDto textContent) throws MessagingException;

    public MimeBodyPart createHtmlBodyPart(MessageHtmlContentDto htmlContent) throws MessagingException;

    public List<MimeBodyPart> createHtmlConentPart(MessageHtmlContentDto htmlContent) throws MessagingException;

    public List<MimeBodyPart> createAttrachPart(MessageAttrachDto attrachDto) throws MessagingException;


}

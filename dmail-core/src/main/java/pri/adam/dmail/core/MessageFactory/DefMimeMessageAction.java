package pri.adam.dmail.core.messageFactory;

import pri.adam.dmail.core.messageFactory.dto.MessageAttrachDto;
import pri.adam.dmail.core.messageFactory.dto.MessageHtmlContentDto;
import pri.adam.dmail.core.messageFactory.dto.MessageInfoDto;
import pri.adam.dmail.core.messageFactory.dto.MessageTextContentDto;

import javax.mail.Authenticator;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.List;
import java.util.Properties;

/**
 * Created by adam on 2014/12/10.
 */
public class DefMimeMessageAction implements MimeMessageAction{
    private Session session;
    private MimeMessageComponent component;
    private Authenticator auth;

    public DefMimeMessageAction(Authenticator auth){
        this.auth = auth;
        session = Session.getInstance(new Properties(),auth);
        setComponent(new DefMimeMessageComponent());
    }

    public DefMimeMessageAction(Properties sessionPro,Authenticator auth){
        this.auth = auth;
        session = Session.getInstance(sessionPro,auth);
        setComponent(new DefMimeMessageComponent());
    }

    public DefMimeMessageAction(Session session){
        this.session = session;
        setComponent(new DefMimeMessageComponent());
    }

    @Override
    public MimeMessage createEmptyMessage(MessageInfoDto messageInfo) throws MessagingException {
        MimeMessage message = component.createMessageObject(session,messageInfo);


        return message;
    }

    @Override
    public MimeMessage createEmptyMessageWithAttrachs(
            MessageInfoDto messageInfoDto, MessageAttrachDto messageAttrach) throws MessagingException {
        MimeMessage message = createEmptyMessage(messageInfoDto);
        List<MimeBodyPart> mimeBodyParts = component.createAttrachPart(messageAttrach);

        MimeMultipart multipart = new MimeMultipart();

        for (MimeBodyPart bodyPart : mimeBodyParts){
            multipart.addBodyPart(bodyPart);
        }

        message.setContent(multipart);
        message.saveChanges();

        return message;
    }

    @Override
    public MimeMessage createTextMessage(
            MessageInfoDto messageInfo, MessageTextContentDto messageTextContent) throws MessagingException {
        MimeMessage message = createEmptyMessage(messageInfo);

        Multipart multipart = new MimeMultipart();

        MimeBodyPart textBodyPart = component.createTextPart(messageTextContent);

        multipart.addBodyPart(textBodyPart);

        message.setContent(multipart);
        message.saveChanges();

        return message;
    }

    @Override
    public MimeMessage createTextMessageWithAttrachs(
            MessageInfoDto messageInfoDto, MessageTextContentDto messageTextContentDto,
            MessageAttrachDto messageAttrach) throws MessagingException {
        MimeMessage message = createEmptyMessage(messageInfoDto);

        Multipart multipart = new MimeMultipart();

        MimeBodyPart textBodyPart = component.createTextPart(messageTextContentDto);
        multipart.addBodyPart(textBodyPart);

        List<MimeBodyPart> attrachBodyParts = component.createAttrachPart(messageAttrach);
        for (MimeBodyPart bodyPart : attrachBodyParts)
            multipart.addBodyPart(bodyPart);

        message.setContent(multipart);
        message.saveChanges();

        return message;
    }

    @Override
    public MimeMessage createHtmlMessage(
            MessageInfoDto messageInfo, MessageHtmlContentDto messageHtmlContent)
            throws MessagingException {
        MimeMessage message = createEmptyMessage(messageInfo);

        Multipart multipart = new MimeMultipart();

        MimeBodyPart htmlBodyPart = component.createHtmlBodyPart(messageHtmlContent);
        multipart.addBodyPart(htmlBodyPart);

        List<MimeBodyPart> conteBodyPart = component.createHtmlConentPart(messageHtmlContent);

        for (MimeBodyPart bodyPart : conteBodyPart)
            multipart.addBodyPart(bodyPart);


        message.setContent(multipart);
        message.saveChanges();

        return message;
    }

    @Override
    public MimeMessage createHtmlMessageWithAttrachs(
            MessageInfoDto messageInfoDto, MessageHtmlContentDto messageHtmlContent,
            MessageAttrachDto messageAttrach)
            throws MessagingException {
        MimeMessage message = createEmptyMessage(messageInfoDto);

        Multipart multipart = new MimeMultipart();

        MimeBodyPart htmlBodyPart = component.createHtmlBodyPart(messageHtmlContent);
        multipart.addBodyPart(htmlBodyPart);

        List<MimeBodyPart> conteBodyPart = component.createHtmlConentPart(messageHtmlContent);

        for (MimeBodyPart bodyPart : conteBodyPart)
            multipart.addBodyPart(bodyPart);

        List<MimeBodyPart> attrachs = component.createAttrachPart(messageAttrach);
        for (MimeBodyPart bodyPart : attrachs)
            multipart.addBodyPart(bodyPart);

        message.setContent(multipart);
        message.saveChanges();

        return message;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public MimeMessageComponent getComponent() {
        return component;
    }

    public void setComponent(MimeMessageComponent component) {
        this.component = component;
    }
}

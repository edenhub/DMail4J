package pri.adam.dmail.core.MessageFactory;

import pri.adam.dmail.core.dto.MessageAttrachDto;
import pri.adam.dmail.core.dto.MessageHtmlContentDto;
import pri.adam.dmail.core.dto.MessageInfoDto;
import pri.adam.dmail.core.dto.MessageTextContentDto;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Created by adam on 2014/12/10.
 */
public class MimeMessageComponentUtil {

    public MimeMessage createMessage(Session session){
        return new MimeMessage(session);
    }

    public MimeMessage createMessage(Session session,MessageInfoDto messageInfo) throws MessagingException {
        MimeMessage message = createMessage(session);

        message.setFrom(new InternetAddress(messageInfo.getFrom()));
        String[] toStrings = messageInfo.getTo();
        InternetAddress[] tos = new InternetAddress[toStrings.length];
        for(int i=0;i<toStrings.length;i++)
            tos[i] = new InternetAddress(toStrings[i]);

        message.setRecipients(Message.RecipientType.TO,tos);
        message.setSubject(messageInfo.getSubject());
        message.setSentDate(messageInfo.getSendDate());

        return message;
    }

    public MimeBodyPart createTextPart(MessageTextContentDto textContent) throws MessagingException {
        MimeBodyPart textBody = new MimeBodyPart();
        textBody.setContent(textContent.getText(),"text/plain;charset=utf-8");

        return textBody;
    }

    public MimeBodyPart createHtmlBodyPart(MessageHtmlContentDto htmlContent) throws MessagingException {
        MimeBodyPart htmlBody = new MimeBodyPart();
        htmlBody.setContent(htmlContent.getHtmlBody(),"text/html;charset=utf-8");

        return htmlBody;
    }

    public List<MimeBodyPart> createHtmlConentPart(MessageHtmlContentDto htmlContent) throws MessagingException {
        String[] contentIds = htmlContent.getContentIds();
        String[] conentFiles = htmlContent.getContentFiles();

        List<MimeBodyPart> contentParts = new ArrayList<MimeBodyPart>(contentIds.length);

        for (int i=0;i<contentIds.length;i++){
            MimeBodyPart contentPart = new MimeBodyPart();
            contentPart.setDataHandler(new DataHandler(new FileDataSource(conentFiles[i])));
            contentPart.setContentID(contentIds[i]);

            contentParts.add(i,contentPart);
        }

        return contentParts;
    }

    public List<MimeBodyPart> createAttrachPart(MessageAttrachDto attrachDto) throws MessagingException {
        String[] attrachNames = attrachDto.getAttrachNames();
        String[] attrachFiles = attrachDto.getAttrachFiles();

        List<MimeBodyPart> attrachs = new ArrayList<MimeBodyPart>(attrachNames.length);

        for(int i=0;i<attrachNames.length;i++){
            MimeBodyPart attrachPart = new MimeBodyPart();
            attrachPart.setDataHandler(new DataHandler(new FileDataSource(attrachFiles[i])));
            attrachPart.setFileName(attrachNames[i]);

            attrachs.add(i,attrachPart);
        }

        return attrachs;
    }
}

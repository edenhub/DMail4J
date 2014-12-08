package pri.adam.dmail.core.PreTest;

import org.junit.Test;
import pri.adam.dmail.core.VersionT;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.*;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Properties;

/**
 * Created by adam on 2014/12/8.
 */
public class TestMessage {
    public String from = "adam@test.com";
    public String to = "jhon@test.com";

    @Test
    public void testTextMessage() throws Exception {
        String subject = "testTextMessage";
        String body = "test!!!!!";

        Session session = Session.getDefaultInstance(new Properties());
        MimeMessage message = new MimeMessage(session);

        message.setFrom(new InternetAddress(from));
        message.setRecipients(MimeMessage.RecipientType.TO,new InternetAddress[]{new InternetAddress(to)});

        message.setSubject(subject);
        message.setText(body);

        message.saveChanges();

        message.writeTo(new FileOutputStream("d:\\mailTest\\testTextMessage.eml"));
    }

    @Test
    public void testHtmlMessage() throws Exception {
        String subject = "testHtmlMessage";
        String body = "<p>pic : <img src=\"cid:test_pic_01\"></img></p>";

        Session session = Session.getDefaultInstance(new Properties());
        MimeMessage message = new MimeMessage(session);

        message.setFrom(new InternetAddress(from));
        message.setRecipients(Message.RecipientType.TO,new InternetAddress[]{new InternetAddress(to)});
        message.setSubject(subject);

        MimeMultipart multipart = new MimeMultipart();

        MimeBodyPart htmlBodyPart = new MimeBodyPart();
        htmlBodyPart.setContent(body,"text/html;charset=utf-8");

        multipart.addBodyPart(htmlBodyPart);

        MimeBodyPart picPart = new MimeBodyPart();
        picPart.setDataHandler(new DataHandler(new FileDataSource("d:\\mailTest\\wflab.png")));
        picPart.setContentID("test_pic_01");

        multipart.addBodyPart(picPart);

        message.setContent(multipart);
        message.saveChanges();

        message.writeTo(new FileOutputStream(new File("d:\\mailTest\\testHtmlMessage.eml")));
    }

    @Test
    public void testMixMessage() throws Exception {
        Session session = Session.getDefaultInstance(new Properties());
        MimeMessage message = new MimeMessage(session);

        message.setFrom(new InternetAddress(from));
        message.setRecipients(Message.RecipientType.TO,new InternetAddress[]{new InternetAddress(to)});
        message.setSubject("testMixMessage");

        MimeMultipart multipart = new MimeMultipart();

        String body = "<p>pic : <img src=\"cid:test_pic_01\"></img></p>";
        MimeBodyPart htmlBodyPart = new MimeBodyPart();
        htmlBodyPart.setContent(body,"text/html;charset=utf-8");
        MimeBodyPart picBodyPart = new MimeBodyPart();
        picBodyPart.setDataHandler(new DataHandler(new FileDataSource("d:\\mailTest\\wflab.png")));
        picBodyPart.setContentID("test_pic_01");

        multipart.addBodyPart(htmlBodyPart);
        multipart.addBodyPart(picBodyPart);

        MimeBodyPart attBodyPart = new MimeBodyPart();
        attBodyPart.setDataHandler(new DataHandler(new FileDataSource("d:\\mailTest\\att1.mp4")));
        attBodyPart.setFileName("att.mp4");

        multipart.addBodyPart(attBodyPart);

        message.setContent(multipart);
        message.saveChanges();

        message.writeTo(new FileOutputStream(new File("d:\\mailTest\\testMixMessage.eml")));


    }
}

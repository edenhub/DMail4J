package pri.adam.dmail.core.PreTest;

import org.junit.Before;
import org.junit.Test;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Properties;

/**
 * Created by adam on 2014/12/8.
 */
public class TestWork {
    public Properties proper = new Properties();
    public String smtpServer = "localhost";
    public MyAuth myAuth = new MyAuth("adam3","test");

    @Before
    public void before(){
        proper.put("mail.smtp.host",smtpServer);
        proper.put("mail.smtp.auth","true");
        proper.put("mail.smtp.port","25");
        proper.put("mail.transport.protocal","smtp");
        proper.put("mail.store.protocol","pop3");
        proper.put("mail.pop3.host",smtpServer);
    }

    @Test
    public void testSend() throws Exception{
        String from = "adam2@localhost";
        String to = "adam3@localhost";

        Session session = Session.getInstance(proper,myAuth);
        MimeMessage message = new MimeMessage(session);

        message.setFrom(new InternetAddress(from));
        message.setRecipients(Message.RecipientType.TO, new InternetAddress[]{new InternetAddress(to)});

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

        Transport.send(message);

        System.out.println("success");
    }

    @Test
    public void testReceive() throws MessagingException {
        Session session = Session.getInstance(proper,myAuth);

        Store store = session.getStore();
        store.connect();

        Folder folder = store.getFolder("inbox");
        folder.open(Folder.READ_WRITE);
        Message[] messages = folder.getMessages();
        System.out.println(messages.length);

        System.out.println(messages[0].getReceivedDate());

        folder.close(false);
    }
}

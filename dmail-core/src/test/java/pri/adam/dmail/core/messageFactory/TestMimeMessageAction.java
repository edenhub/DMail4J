package pri.adam.dmail.core.messageFactory;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pri.adam.dmail.core.auth.SimpleAuth;
import pri.adam.dmail.core.messageFactory.dto.MessageAttrachDto;
import pri.adam.dmail.core.messageFactory.dto.MessageHtmlContentDto;
import pri.adam.dmail.core.messageFactory.dto.MessageInfoDto;
import pri.adam.dmail.core.messageFactory.dto.MessageTextContentDto;

import javax.mail.Authenticator;
import javax.mail.MessagingException;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * Created by adam on 2014/12/10.
 */
public class TestMimeMessageAction {
    public Properties proper = new Properties();
    public String smtpServer = "localhost";
    public Authenticator auth = new SimpleAuth("adam2","test");
    public MimeMessageAction messageAction;

    @Before
    public void before(){
        proper.put("mail.smtp.host",smtpServer);
        proper.put("mail.smtp.auth","true");
        proper.put("mail.smtp.port","25");
        proper.put("mail.transport.protocal","smtp");
        proper.put("mail.store.protocol","pop3");
        proper.put("mail.pop3.host",smtpServer);
        messageAction = new DefMimeMessageAction(proper,auth);
    }

    @After
    public void after(){
        System.out.println("success");
    }


    @Test
    public void testTextMessage() throws MessagingException {
        MessageInfoDto messageInfo = new MessageInfoDto("adam2@localhost","adam3@localhost","TestTextMessage");
        MessageTextContentDto textContent = new MessageTextContentDto("测试实体在 TestTextMessage");
        MimeMessage message = messageAction.createTextMessage(messageInfo,textContent);

        Transport.send(message);
    }

    @Test
    public void testTextMessageWithAttrach() throws MessagingException {
        MessageInfoDto messageInfoDto = new MessageInfoDto("adam2@localhost","adam3@localhost","TextTextMessageWithAttrach");
        MessageTextContentDto textContentDto = new MessageTextContentDto("测试实体在 TestTextMessage");
        MessageAttrachDto messageAttrachDto = new MessageAttrachDto(new String[]{"attr.mp4"},new String[]{"d:\\mailTest\\att1.mp4"});

        MimeMessage message = messageAction.createTextMessageWithAttrachs(messageInfoDto,textContentDto,messageAttrachDto);

        Transport.send(message);
    }

    @Test
    public void testHtmlMessage() throws MessagingException {
        String body = "<p>pic : <img src=\"cid:test_pic_01\"></img></p>";
        MessageInfoDto messageInfoDto = new MessageInfoDto("adam@localhost","adam3@localhost","TestHtmlMessage");
        MessageHtmlContentDto messageHtmlContentDto = new MessageHtmlContentDto(body);
        messageHtmlContentDto.setContentIds(new String[]{"test_pic_01"});
        messageHtmlContentDto.setContentFiles(new String[]{"d:\\mailTest\\wflab.png"});

        MimeMessage message = messageAction.createHtmlMessage(messageInfoDto,messageHtmlContentDto);

        Transport.send(message);
    }

    @Test
    public void testHtmlMessageWithAttrach() throws MessagingException {
        String body = "<p>pic : <img src=\"cid:test_pic_01\"></img></p>";
        MessageInfoDto messageInfoDto = new MessageInfoDto("adam@localhost","adam3@localhost","TestHtmlMessageWithAttrach");
        MessageHtmlContentDto messageHtmlContentDto = new MessageHtmlContentDto(body);
        messageHtmlContentDto.setContentIds(new String[]{"test_pic_01"});
        messageHtmlContentDto.setContentFiles(new String[]{"d:\\mailTest\\wflab.png"});

        MessageAttrachDto messageAttrachDto = new MessageAttrachDto(new String[]{"attr.mp4"},new String[]{"d:\\mailTest\\att1.mp4"});

        MimeMessage message = messageAction.createHtmlMessageWithAttrachs(messageInfoDto,messageHtmlContentDto,messageAttrachDto);

        Transport.send(message);
    }
}

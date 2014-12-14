package pri.adam.dmail.core.messageFactory;

import pri.adam.dmail.core.messageFactory.dto.MessageAttrachDto;
import pri.adam.dmail.core.messageFactory.dto.MessageHtmlContentDto;
import pri.adam.dmail.core.messageFactory.dto.MessageInfoDto;
import pri.adam.dmail.core.messageFactory.dto.MessageTextContentDto;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * Created by adam on 2014/12/10.
 */
public interface MimeMessageAction {

    public MimeMessage createEmptyMessage(MessageInfoDto messageInfo) throws MessagingException;

    public MimeMessage createEmptyMessageWithAttrachs(MessageInfoDto messageInfoDto,MessageAttrachDto messageAttrach) throws MessagingException;

    public MimeMessage createTextMessage(MessageInfoDto messageInfo,MessageTextContentDto messageTextContent) throws MessagingException;

    public MimeMessage createTextMessageWithAttrachs(
            MessageInfoDto messageInfoDto, MessageTextContentDto messageTextContentDto,
            MessageAttrachDto messageAttrach) throws MessagingException;

    public MimeMessage createHtmlMessage(MessageInfoDto messageInfo,MessageHtmlContentDto messageHtmlContent) throws MessagingException;

    public MimeMessage createHtmlMessageWithAttrachs(
            MessageInfoDto messageInfoDto,MessageHtmlContentDto messageHtmlContent,
            MessageAttrachDto messageAttrach) throws MessagingException;
}

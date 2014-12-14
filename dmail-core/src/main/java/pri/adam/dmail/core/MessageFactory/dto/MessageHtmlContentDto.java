package pri.adam.dmail.core.messageFactory.dto;

import java.util.Arrays;

/**
 * Created by adam on 2014/12/10.
 */
public class MessageHtmlContentDto {
    private String[] contentIds;
    private String htmlBody;
    private String[] contentFiles;

    public MessageHtmlContentDto(String htmlBody){
        this.htmlBody = htmlBody;
    }

    public MessageHtmlContentDto(String htlmBody,String[] contentIds,String[] contentFiles){
        assert contentFiles.length == contentIds.length;
        this.htmlBody = htmlBody;
        this.contentIds = contentIds;
        this.contentFiles = contentFiles;
    }

    public String[] getContentIds() {
        return contentIds;
    }

    public void setContentIds(String[] contentIds) {
        this.contentIds = contentIds;
    }

    public String getHtmlBody() {
        return htmlBody;
    }

    public void setHtmlBody(String htmlBody) {
        this.htmlBody = htmlBody;
    }

    public String[] getContentFiles() {
        return contentFiles;
    }

    public void setContentFiles(String[] contentFiles) {
        this.contentFiles = contentFiles;
    }

    @Override
    public String toString() {
        return "MessageHtmlContentDto{" +
                "contentIds=" + Arrays.toString(contentIds) +
                ", htmlBody='" + htmlBody + '\'' +
                ", contentFiles=" + Arrays.toString(contentFiles) +
                '}';
    }
}

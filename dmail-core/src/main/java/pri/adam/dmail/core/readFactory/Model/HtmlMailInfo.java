package pri.adam.dmail.core.readFactory.Model;

import java.util.Arrays;

/**
 * Created by adam on 2014/12/12.
 */
public class HtmlMailInfo {
    private String htmlBody;
    private String[] bodyContentIds;

    public HtmlMailInfo(){}

    public HtmlMailInfo(String htmlBody){
        this.htmlBody = htmlBody;
    }

    public HtmlMailInfo(String htmlBody,String[] bodyContentIds){
        this(htmlBody);
        this.bodyContentIds = bodyContentIds;
    }

    public String getHtmlBody() {
        return htmlBody;
    }

    public void setHtmlBody(String htmlBody) {
        this.htmlBody = htmlBody;
    }

    public String[] getBodyContentIds() {
        return bodyContentIds;
    }

    public void setBodyContentIds(String[] bodyContentIds) {
        this.bodyContentIds = bodyContentIds;
    }

    @Override
    public String toString() {
        return "HtmlMailInfo{" +
                "htmlBody='" + htmlBody + '\'' +
                ", bodyContentIds=" + Arrays.toString(bodyContentIds) +
                '}';
    }
}

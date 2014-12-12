package pri.adam.dmail.core.readFactory.Model;

/**
 * Created by adam on 2014/12/12.
 */
public class AttrachInfo {
    private String attrachName;
    private String contentId;

    public AttrachInfo(){}

    public AttrachInfo(String attrachName, String contentId) {
        this.attrachName = attrachName;
        this.contentId = contentId;
    }

    public String getAttrachName() {
        return attrachName;
    }

    public void setAttrachName(String attrachName) {
        this.attrachName = attrachName;
    }

    public String getContentId() {
        return contentId;
    }

    public void setContentId(String contentId) {
        this.contentId = contentId;
    }

    @Override
    public String toString() {
        return "AttrachInfo{" +
                "attrachName='" + attrachName + '\'' +
                ", contentId='" + contentId + '\'' +
                '}';
    }
}

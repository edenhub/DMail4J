package pri.adam.dmail.core.dto;

/**
 * Created by adam on 2014/12/10.
 */
public class MessageTextContentDto {
    private String text;

    public MessageTextContentDto(){}

    public MessageTextContentDto(String text){
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "MessageTextContentDto{" +
                "text='" + text + '\'' +
                '}';
    }
}

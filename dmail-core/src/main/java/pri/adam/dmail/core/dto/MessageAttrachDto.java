package pri.adam.dmail.core.dto;

import java.util.Arrays;

/**
 * Created by adam on 2014/12/10.
 */
public class MessageAttrachDto {
    private String[] attrachNames;
    private String[] attrachFiles;

    public MessageAttrachDto(){}

    public MessageAttrachDto(String[] attrachNames,String[] attrachFiles){
        assert attrachNames.length == attrachFiles.length;
        this.attrachNames = attrachNames;
        this.attrachFiles = attrachFiles;
    }

    public String[] getAttrachNames() {
        return attrachNames;
    }

    public void setAttrachNames(String[] attrachNames) {
        this.attrachNames = attrachNames;
    }

    public String[] getAttrachFiles() {
        return attrachFiles;
    }

    public void setAttrachFiles(String[] attrachFiles) {
        this.attrachFiles = attrachFiles;
    }

    @Override
    public String toString() {
        return "MessageAttrachDto{" +
                "attrachNames=" + Arrays.toString(attrachNames) +
                ", attrachFiles=" + Arrays.toString(attrachFiles) +
                '}';
    }
}

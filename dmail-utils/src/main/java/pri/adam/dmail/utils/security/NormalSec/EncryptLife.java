package pri.adam.dmail.utils.security.normalSec;

import java.io.UnsupportedEncodingException;

/**
 * Created by lab on 2014/11/13.
 */
public interface EncryptLife {
    public void beforeEntry(String password, String encodeType);
    public String encrypt(String password, String encodeType) throws UnsupportedEncodingException;
    public void afterEntry(String password, String encryptStr, String encodeType);
    public void updateEncryStrategy(EncryptStrategy encryterStrategy);
}

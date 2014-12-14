package pri.adam.dmail.utils.security.normalSec;

import java.io.UnsupportedEncodingException;

/**
 * Created by lab on 2014/11/13.
 */
public interface EncryptStrategy {
    public String encrypt(String password, String encodeType) throws UnsupportedEncodingException;
}

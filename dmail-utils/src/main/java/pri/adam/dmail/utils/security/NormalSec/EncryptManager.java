package pri.adam.dmail.utils.security.normalSec;

import java.io.UnsupportedEncodingException;

/**
 * Created by lab on 2014/11/13.
 */
public class EncryptManager {
    private Encrypter encryter;
    private EncryptStrategy encryptStrategy;
    private final String defaultEncodeType = "UTF-8";
    public String encrypt(String password) throws UnsupportedEncodingException {
        return encrypt(password, defaultEncodeType);
    }

    public String encrypt(String password,String encodeType) throws UnsupportedEncodingException {
        encryter.updateEncryStrategy(encryptStrategy);
        return encryter.encrypt(password, encodeType);
    }

    public Encrypter getEncryter() {
        return encryter;
    }

    public void setEncryter(Encrypter encryter) {
        this.encryter = encryter;
    }

    public EncryptStrategy getEncryptStrategy() {
        return encryptStrategy;
    }

    public void setEncryptStrategy(EncryptStrategy encryptStrategy) {
        this.encryptStrategy = encryptStrategy;
    }
}

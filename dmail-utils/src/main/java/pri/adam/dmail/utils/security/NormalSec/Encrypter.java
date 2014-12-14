package pri.adam.dmail.utils.security.normalSec;

import java.io.UnsupportedEncodingException;

/**
 * Created by lab on 2014/11/13.
 */
public abstract class Encrypter implements EncryptLife {
    private EncryptStrategy strategy;

    @Override
    public abstract void beforeEntry(String password,String encodeType);
    @Override
    public String encrypt(String password,String encodeType) throws UnsupportedEncodingException {
        String res = null;
        beforeEntry(password,encodeType);
        res = strategy.encrypt(password, encodeType);
        afterEntry(password,res,encodeType);

        return res;
    }

    @Override
    public void updateEncryStrategy(EncryptStrategy encryterStrategy) {
        this.strategy = encryterStrategy;
    }

    public EncryptStrategy getEncryStrategy(){
        return this.strategy;
    }

    @Override
    public abstract void afterEntry(String password,String encryptStr,String encodeType);
}

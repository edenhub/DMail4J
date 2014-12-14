package pri.adam.dmail.utils.security.normalSec.StrategyImpl;

import org.apache.commons.codec.digest.DigestUtils;
import pri.adam.dmail.utils.security.normalSec.EncryptStrategy;

import java.io.UnsupportedEncodingException;

/**
 * Created by lab on 2014/11/13.
 */
public class S_Sha512Hex implements EncryptStrategy {
    @Override
    public String encrypt(String password, String encodeType) throws UnsupportedEncodingException {
        return new String(DigestUtils.sha512Hex(password.getBytes(encodeType)));
    }
}

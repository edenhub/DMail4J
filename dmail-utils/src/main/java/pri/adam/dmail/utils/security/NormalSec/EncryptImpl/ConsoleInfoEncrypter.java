package pri.adam.dmail.utils.security.normalSec.EncryptImpl;


import pri.adam.dmail.utils.security.normalSec.Encrypter;

/**
 * Created by lab on 2014/11/13.
 */
public class ConsoleInfoEncrypter extends Encrypter {
    @Override
    public void beforeEntry(String password, String encodeType) {
        System.out.println("= = = = = = = = = = = = = = = = = =");
        System.out.println("Before encrypt,the message is : "+password+" . In encodeType : "+encodeType);
    }

    @Override
    public void afterEntry(String password, String encryptStr, String encodeType) {
        System.out.println("Finish encrypt,the message is : "+encryptStr);
        System.out.println("= = = = = = = = = = = = = = = = = =");
    }
}

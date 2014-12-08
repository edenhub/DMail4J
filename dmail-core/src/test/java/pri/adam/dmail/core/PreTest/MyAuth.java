package pri.adam.dmail.core.PreTest;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

/**
 * Created by adam on 2014/12/8.
 */
public class MyAuth extends Authenticator {

    private String username;
    private String password;

    public MyAuth(String username,String password){
        this.username = username;
        this.password = password;
    }

    @Override
    protected PasswordAuthentication getPasswordAuthentication() {
        super.getPasswordAuthentication();

        return new PasswordAuthentication(username,password);
    }
}

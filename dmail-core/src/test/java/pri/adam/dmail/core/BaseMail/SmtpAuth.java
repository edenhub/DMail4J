package pri.adam.dmail.core.BaseMail;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

/**
 * Created by adam on 14-11-30.
 */
public class SmtpAuth extends Authenticator{
    private String user,pass;

    public void setUserInfo(String user,String pass){
        this.user = user;
        this.pass = pass;
    }

    public SmtpAuth() {
        super();
    }

    @Override
    protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(user,pass);
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}

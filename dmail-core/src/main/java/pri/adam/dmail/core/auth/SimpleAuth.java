package pri.adam.dmail.core.auth;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

/**
 * Created by adam on 2014/12/10.
 */
public class SimpleAuth extends Authenticator {
    private String username;
    private String password;

    public SimpleAuth(){
        super();
    }

    public SimpleAuth(String username,String password){
        this.username = username;
        this.password = password;
    }

    @Override
    protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(username,password);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

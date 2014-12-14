package pri.adam.dmail.core.readFactory.readerImpl;

import com.sun.mail.pop3.POP3Store;
import org.apache.log4j.Logger;

import javax.mail.Authenticator;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import java.util.Properties;

/**
 * Created by adam on 2014/12/13.
 */
public class ReaderImpl implements Reader{
    protected Session session;
    protected Authenticator auth;
    protected POP3Store pop3Store;

    protected static Logger logger = Logger.getLogger(MailReaderImpl.class);

    public ReaderImpl(Authenticator auth){
        this.auth = auth;
    }

    public ReaderImpl(Properties sessionPro,Authenticator auth){
        session = Session.getInstance(sessionPro,auth);
        this.auth = auth;
    }

    public ReaderImpl(Session session,Authenticator auth){
        this.session = session;
        this.auth = auth;
    }

    public boolean init(){
        try {
            pop3Store = (POP3Store) session.getStore();
            pop3Store.connect();
        } catch (NoSuchProviderException e) {
//            e.printStackTrace();
            logger.error("不支持pop3协议",e);
            return false;
        } catch (MessagingException e) {
//            e.printStackTrace();
            logger.error("无法连接服务器",e);
            return false;
        }

        return true;
    }

    @Override
    public boolean close() {
        try {
            pop3Store.close();
        } catch (MessagingException e) {
//            e.printStackTrace();
            logger.error("无法关闭连接",e);
            return false;
        }

        return true;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public Authenticator getAuth() {
        return auth;
    }

    public void setAuth(Authenticator auth) {
        this.auth = auth;
    }

    public POP3Store getPop3Store() {
        return pop3Store;
    }

    public void setPop3Store(POP3Store pop3Store) {
        this.pop3Store = pop3Store;
    }

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        ReaderImpl.logger = logger;
    }

    @Override
    public String toString() {
        return "ReaderImpl{" +
                "session=" + session +
                ", auth=" + auth +
                ", pop3Store=" + pop3Store +
                '}';
    }
}

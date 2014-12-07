package pri.adam.dmail.core.BaseMail;

import javax.mail.*;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by adam on 14-12-1.
 */
public class TestReceiveMail {

    public static void receiveMail() throws MessagingException, IOException {
        String host="localhost";
        String user="adam";
        String pass = "adamchen";

        Properties prop = new Properties();
        prop.put("mail.smtp.host","localhost");
        prop.put("mail.smtp.auth","true");
        prop.put("mail.smtp.port","25");
        prop.put("mail.transport.protocol","smtp");
        prop.put("mail.store.protocol","pop3");

        Session session = Session.getDefaultInstance(prop,null);

        Store store = session.getStore("pop3");
        store.connect(host,user,pass);

        Folder folder = store.getFolder("inbox");
        folder.open(Folder.READ_ONLY);

        Message message[] = folder.getMessages();
        for(int i=0;i<message.length;i++){
//            System.out.println("Message : "+(i+1));
            System.out.println(i+ " : "+message[i].getFrom()[0]+"\t"+message[i].getSubject());
            System.out.println(message[i].getContentType());
        }

        folder.close(false);
        store.close();
    }
}

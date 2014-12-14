package pri.adam.dmail.core.PreTest;

import com.sun.mail.imap.IMAPStore;
import com.sun.mail.pop3.POP3Folder;
import com.sun.mail.pop3.POP3Store;
import com.sun.org.apache.bcel.internal.generic.POP;
import org.junit.Before;
import org.junit.Test;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import javax.mail.search.MessageIDTerm;
import javax.mail.search.SearchTerm;
import java.io.*;
import java.util.Date;
import java.util.Properties;

/**
 * Created by adam on 2014/12/8.
 */
public class TestWork {
    public Properties proper = new Properties();
    public String smtpServer = "localhost";
    public MyAuth myAuth = new MyAuth("adam3", "test");
    public static int attnum = 1;

    @Before
    public void before() {
        proper.put("mail.smtp.host", smtpServer);
        proper.put("mail.smtp.auth", "true");
        proper.put("mail.smtp.port", "25");
        proper.put("mail.transport.protocal", "smtp");
        proper.put("mail.store.protocol", "pop3");
        proper.put("mail.pop3.host", smtpServer);
    }

    @Test
    public void testSend() throws Exception {
        String from = "adam2@localhost";
        String to = "adam3@localhost";

        Session session = Session.getInstance(proper, myAuth);
        MimeMessage message = new MimeMessage(session);

        message.setFrom(new InternetAddress(from));
        message.setRecipients(Message.RecipientType.TO, new InternetAddress[]{new InternetAddress(to)});

        MimeMultipart multipart = new MimeMultipart();

        String body = "<p>pic : <img src=\"cid:test_pic_01\"></img></p>";
        MimeBodyPart htmlBodyPart = new MimeBodyPart();
        htmlBodyPart.setContent(body, "text/html;charset=utf-8");
        MimeBodyPart picBodyPart = new MimeBodyPart();
        picBodyPart.setDataHandler(new DataHandler(new FileDataSource("d:\\mailTest\\wflab.png")));
        picBodyPart.setContentID("test_pic_01");

        multipart.addBodyPart(htmlBodyPart);
        multipart.addBodyPart(picBodyPart);

        MimeBodyPart attBodyPart = new MimeBodyPart();
        attBodyPart.setDataHandler(new DataHandler(new FileDataSource("d:\\mailTest\\att1.mp4")));
        attBodyPart.setFileName("att.mp4");

        multipart.addBodyPart(attBodyPart);

        message.setContent(multipart);
        message.saveChanges();

        Transport.send(message);

        System.out.println("success");
    }

    @Test
    public void testReceive() throws MessagingException {
        Session session = Session.getInstance(proper, myAuth);

        Store store = session.getStore();
        store.connect();

        Folder folder = store.getFolder("inbox");
        folder.open(Folder.READ_WRITE);
        Message[] messages = folder.getMessages();
        System.out.println(messages.length);

        System.out.println(messages[0].getReceivedDate());

        folder.close(false);
    }

    @Test
    public void testReadMessageType() throws Exception {
        Session session = Session.getInstance(proper, myAuth);

        Store store = session.getStore();
        store.connect();
        ;

        Folder folder = store.getFolder("inbox");
        folder.open(Folder.READ_WRITE);

        Message[] messages = folder.getMessages();

        System.out.println(messages.length);

        for (int i = 0; i < messages.length; i++) {
            System.out.println(messages[i].getContentType());
            MimeMultipart mimeMultipart = (MimeMultipart) messages[i].getContent();
            int bodyNum = mimeMultipart.getCount();
            System.out.println("bodyNum : " + bodyNum);
            System.out.println("=======================>>>");
            for (int j = 0; j < bodyNum; j++) {
                MimeBodyPart bodyPart = (MimeBodyPart) mimeMultipart.getBodyPart(j);
                System.out.println(bodyPart.getContentType());
                if (bodyPart.isMimeType("text/plain"))
                    System.out.println("text : " + bodyPart.getContent().toString());
                else {
                    System.out.println("other : " + bodyPart.getContentID());
                }
            }
            System.out.println("<<<=======================");

        }

        folder.close(false);

    }

    @Test
    public void testUid() throws MessagingException, IOException {
        Session session = Session.getInstance(proper, myAuth);
        Store store = session.getStore();
        store.connect();
//            System.out.println(store instanceof IMAPStore);
        if (store instanceof POP3Store) {
            POP3Store pop3Store = (POP3Store) store;
            POP3Folder folder = (POP3Folder) pop3Store.getFolder("inbox");
            folder.open(Folder.READ_WRITE);

            Message[] messages = folder.getMessages();
            long startT = System.currentTimeMillis();
            String fid = null;
            for (int i = 0; i < messages.length; i++) {
                MimeMessage mimeMessage = (MimeMessage) messages[i];
//                String uid = folder.getUID(messages[i]);
                String mid = mimeMessage.getMessageID();
                if (fid == null) fid = mid;
//                Address from = mimeMessage.getSender();
//                Object content = mimeMessage.getContent();
//                System.out.println("uid : "+uid);
//                System.out.println("mid : "+mimeMessage.getMessageID());
            }

            SearchTerm searchTerm = new MessageIDTerm(fid);
            Message[] resultMessage = folder.search(searchTerm);
            System.out.println("search length : " + resultMessage.length);
            System.out.println("time : " + (System.currentTimeMillis() - startT));

            folder.close(true);
        }


        store.close();
    }

    @Test
    public void getMessageInRange() throws Exception {
        int start = 1, end = 2;

        Session session = Session.getInstance(proper, myAuth);
        POP3Store store = (POP3Store) session.getStore();
        store.connect();

        POP3Folder folder = (POP3Folder) store.getFolder("inbox");
        folder.open(Folder.READ_WRITE);

        Message[] rangeMessages = folder.getMessages(start, end);
        for (int i = 0; i < rangeMessages.length; i++) {
            MimeMessage mimeMessage = (MimeMessage) rangeMessages[i];
            System.out.println("mid : " + mimeMessage.getMessageID());

            dumpPart(mimeMessage);
        }


    }

    public static void dumpPart(Part p) throws Exception {
        if (p instanceof Message)
            dumpEnvelope((Message) p);

        /** Dump input stream ..

         InputStream is = p.getInputStream();
         // If "is" is not already buffered, wrap a BufferedInputStream
         // around it.
         if (!(is instanceof BufferedInputStream))
         is = new BufferedInputStream(is);
         int c;
         while ((c = is.read()) != -1)
         System.out.write(c);

         **/

        String ct = p.getContentType();
        try {
            pr("CONTENT-TYPE: " + (new ContentType(ct)).toString());
        } catch (ParseException pex) {
            pr("BAD CONTENT-TYPE: " + ct);
        }
        String filename = p.getFileName();
        if (filename != null)
            pr("FILENAME: " + filename);

	/*
     * Using isMimeType to determine the content type avoids
	 * fetching the actual content data until we need it.
	 */
        if (p.isMimeType("text/plain")) {
            pr("This is plain text");
            pr("---------------------------");
//            if (!showStructure && !saveAttachments)
            System.out.println((String) p.getContent());
        } else if (p.isMimeType("multipart/*")) {
            pr("This is a Multipart");
            pr("---------------------------");
            Multipart mp = (Multipart) p.getContent();
//            level++;
            int count = mp.getCount();
            for (int i = 0; i < count; i++)
                dumpPart(mp.getBodyPart(i));
//            level--;
        } else if (p.isMimeType("message/rfc822")) {
            pr("This is a Nested Message");
            pr("---------------------------");
//            level++;
            dumpPart((Part) p.getContent());
//            level--;
        } else {
//            if (!showStructure && !saveAttachments) {
		/*
		 * If we actually want to see the data, and it's not a
		 * MIME type we know, fetch it and check its Java type.
		 */
            Object o = p.getContent();
            if (o instanceof String) {
                pr("This is a string");
                pr("---------------------------");
                System.out.println((String) o);
            } else if (o instanceof InputStream) {
                pr("This is just an input stream");
                pr("---------------------------");
                InputStream is = (InputStream) o;
                int c;
                while ((c = is.read()) != -1)
                    System.out.write(c);
            } else {
                pr("This is an unknown type");
                pr("---------------------------");
                pr(o.toString());
            }
//            } else {
//                // just a separator
//                pr("---------------------------");
//            }
        }

	/*
	 * If we're saving attachments, write out anything that
	 * looks like an attachment into an appropriately named
	 * file.  Don't overwrite existing files to prevent
	 * mistakes.
	 */
        if (
//                saveAttachments && level != 0 &&
                p instanceof MimeBodyPart &&
                        !p.isMimeType("multipart/*")) {
            String disp = p.getDisposition();
            // many mailers don't include a Content-Disposition
            if (disp == null || disp.equalsIgnoreCase(Part.ATTACHMENT)) {
                if (filename == null)
                    filename = "Attachment" + (attnum++);
                pr("Saving attachment to file " + filename);
                try {
                    File f = new File(filename);
                    if (f.exists())
                        // XXX - could try a series of names
                        throw new IOException("file exists");
                    ((MimeBodyPart) p).saveFile(f);
                } catch (IOException ex) {
                    pr("Failed to save attachment: " + ex);
                }
                pr("---------------------------");
            }
        }
    }

    public static void dumpEnvelope(Message m) throws Exception {
        pr("This is the message envelope");
        pr("---------------------------");
        Address[] a;
        // FROM
        if ((a = m.getFrom()) != null) {
            for (int j = 0; j < a.length; j++)
                pr("FROM: " + a[j].toString());
        }

        // REPLY TO
        if ((a = m.getReplyTo()) != null) {
            for (int j = 0; j < a.length; j++)
                pr("REPLY TO: " + a[j].toString());
        }

        // TO
        if ((a = m.getRecipients(Message.RecipientType.TO)) != null) {
            for (int j = 0; j < a.length; j++) {
                pr("TO: " + a[j].toString());
                InternetAddress ia = (InternetAddress) a[j];
                if (ia.isGroup()) {
                    InternetAddress[] aa = ia.getGroup(false);
                    for (int k = 0; k < aa.length; k++)
                        pr("  GROUP: " + aa[k].toString());
                }
            }
        }

        // SUBJECT
        pr("SUBJECT: " + m.getSubject());

        // DATE
        Date d = m.getSentDate();
        pr("SendDate: " +
                (d != null ? d.toString() : "UNKNOWN"));

        // FLAGS
        Flags flags = m.getFlags();
        StringBuffer sb = new StringBuffer();
        Flags.Flag[] sf = flags.getSystemFlags(); // get the system flags

        boolean first = true;
        for (int i = 0; i < sf.length; i++) {
            String s;
            Flags.Flag f = sf[i];
            if (f == Flags.Flag.ANSWERED)
                s = "\\Answered";
            else if (f == Flags.Flag.DELETED)
                s = "\\Deleted";
            else if (f == Flags.Flag.DRAFT)
                s = "\\Draft";
            else if (f == Flags.Flag.FLAGGED)
                s = "\\Flagged";
            else if (f == Flags.Flag.RECENT)
                s = "\\Recent";
            else if (f == Flags.Flag.SEEN)
                s = "\\Seen";
            else
                continue;    // skip it
            if (first)
                first = false;
            else
                sb.append(' ');
            sb.append(s);
        }

        String[] uf = flags.getUserFlags(); // get the user flag strings
        for (int i = 0; i < uf.length; i++) {
            if (first)
                first = false;
            else
                sb.append(' ');
            sb.append(uf[i]);
        }
        pr("FLAGS: " + sb.toString());

        // X-MAILER
        String[] hdrs = m.getHeader("X-Mailer");
        if (hdrs != null)
            pr("X-Mailer: " + hdrs[0]);
        else
            pr("X-Mailer NOT available");
    }

    public static void pr(String s) {

//        if (showStructure)
//            System.out.print(indentStr.substring(0, level * 2));
        System.out.println(s);
    }

    @Test
    public void testReadDetail() throws MessagingException, IOException {
        int start=1,end=3;
        Session session = Session.getInstance(proper,myAuth);
        POP3Store store = (POP3Store) session.getStore();
        store.connect();
        POP3Folder folder = (POP3Folder) store.getFolder("inbox");
        folder.open(Folder.READ_WRITE);

        Message[] messages = folder.getMessages(start,end);
        for(int i=0;i<messages.length;i++){
            MimeMessage mimeMessage = (MimeMessage) messages[i];
            dumpMessageInfo(mimeMessage);
            dumpMultipart((MimeMultipart) mimeMessage.getContent());
        }

        folder.close(true);
        store.close();

    }

    public static void dumpMessageInfo(MimeMessage message) throws MessagingException {
        Address[] from = message.getFrom();
        System.out.println("From : ");
        for(int i=0;i<from.length;i++)
            System.out.println(from[i].toString());
        Address[] to = message.getRecipients(MimeMessage.RecipientType.TO);
        System.out.println("To : ");
        for(int i=0;i<to.length;i++)
            System.out.println(to[i].toString());
        String subject = message.getSubject();
        System.out.println("Subject : "+subject);
        Date date = message.getSentDate();
        System.out.println("Date : "+date);
        System.out.println("========================================");
    }

    public static void dumpMultipart(MimeMultipart multipart) throws MessagingException, IOException {
        int count = multipart.getCount();
        System.out.println("BodyPart nums : "+count);
        for(int i=0;i<count;i++){
            MimeBodyPart bodyPart = (MimeBodyPart) multipart.getBodyPart(i);
            String contentType = bodyPart.getContentType();
            System.out.println("Type : "+ contentType);

            if (bodyPart.isMimeType("text/plain")){
                System.out.println("text/plain : "+bodyPart.getContent().toString());
            }else if (bodyPart.isMimeType("text/html")){
                System.out.println("text/hmlt : "+bodyPart.getContent().toString());
            }else if (!bodyPart.isMimeType("multipart/*")){
                System.out.println("!multipart/*");
                String contentId = bodyPart.getContentID();
                String fileName = bodyPart.getFileName();
                if (fileName == null)
                    fileName = contentId+"_"+i;

                System.out.println(contentId+" : "+fileName);

                InputStream in = bodyPart.getInputStream();
                System.out.println("stream : "+in.available());
                File file = new File(fileName);
                if (file.exists()){
                    System.out.println(fileName+" had exists.");
                }

                bodyPart.saveFile(file);
            }



        }

        System.out.println("===============================");
    }

    /**
     * pop3不支持多箱操作
     * @throws MessagingException
     */
    @Test
    public void testSended() throws MessagingException {
        Session session = Session.getInstance(proper,myAuth);
        POP3Store store = (POP3Store) session.getStore();
        store.connect();;
        POP3Folder folder = (POP3Folder) store.getFolder("inbox");
        folder.open(Folder.READ_WRITE);

        if (!folder.exists()){
            folder.create(Folder.HOLDS_MESSAGES);
        }

        folder.open(Folder.READ_WRITE);

        System.out.println(folder.exists());
    }
}

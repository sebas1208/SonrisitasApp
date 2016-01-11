package util;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Gmail {

    //Configura estos parametros de acuerdo a tu STMP
    static Properties props = new Properties();    
    static final String host = "smtp.gmail.com";
    static final String puerto = "465";
    static final String usuario = "sebas1208.avalos@gamil.com";
    static final String password = "goog1208";

    public Gmail(){        
    }

    public static boolean send(String para,String asunto,String mensaje){        
        props.put("mail.imap.ssl.enable", "true"); // required for Gmail
        props.put("mail.imap.auth.mechanisms", "XOAUTH2");        
        // props.put("mail.smtp.socketFactory.port", puerto);
        // props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
        // props.put("mail.smtp.auth", "true");
        // props.put("mail.smtp.port", puerto);
        try {
            Session session = Session.getInstance(props);
            Store store = session.getStore("imap");
            store.connect("imap.gmail.com", usuario, "oauth2_access_token");
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(usuario));
            message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(para));
            message.setSubject(asunto);
            message.setText(mensaje);
            Transport.send(message);
            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }
    }
}
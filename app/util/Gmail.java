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
    static final String usuario = "sonrisitas.servicio@gmail.com";
    static final String password = "sonri1208";

    public Gmail(){        
    }

    public static boolean send(String para,String asunto,String mensaje){        
        Properties props = new Properties();
      props.put("mail.smtp.auth", "true");
      props.put("mail.smtp.starttls.enable", "true");
      props.put("mail.smtp.host", host);
      props.put("mail.smtp.port", "587");

      // Get the Session object.
      Session session = Session.getInstance(props,
      new javax.mail.Authenticator() {
         protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(usuario, password);
         }
      });

      try {
         // Create a default MimeMessage object.
         Message message = new MimeMessage(session);

         // Set From: header field of the header.
         message.setFrom(new InternetAddress(usuario));

         // Set To: header field of the header.
         message.setRecipients(Message.RecipientType.TO,
         InternetAddress.parse(para));

         // Set Subject: header field
         message.setSubject(asunto);

         // Now set the actual message
         message.setText(mensaje);

         // Send message
         Transport transport = session.getTransport("smtp");
         transport.connect(host, usuario, password);
         transport.send(message);

         System.out.println("Sent message successfully....");
         return true;

      } catch (MessagingException e) {
            throw new RuntimeException(e);
      }
    }
}
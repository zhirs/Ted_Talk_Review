package tedtalk.emailer;

import java.util.Date;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
public class SendEmail {
	
	//need to figure out how to test this
	public SendEmail(){
		
	}
	public void sendEmail(String mailTo) {
	final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
	      // Assuming you are sending email from localhost
	      String host = "localhost";
	      
	      //The email address this originates from
	      String mailFrom = "Berjoigy@gmail.com";

	      // Get system properties
	      Properties properties = System.getProperties();

	      // Setup mail server
	      properties.setProperty("mail.smtp.host", "smtp.gmail.com");
	      properties.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
	      properties.setProperty("mail.smtp.socketFactory.fallback", "false");
	      properties.setProperty("mail.smtp.port", "465");
	      properties.setProperty("mail.smtp.socketFactory.port", "465");
	      properties.put("mail.smtp.auth", "true");
	      properties.put("mail.debug", "true");
	      properties.put("mail.store.protocol", "pop3");
	      properties.put("mail.transport.protocol", "smtp");
	      final String username = "Berjoigy@gmail.com";//
	      final String password = "Newk879*&hi";

	      // Get the default Session object.
	      Session session = Session.getDefaultInstance(properties, 
                  new Authenticator(){
                     protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                     }});

	      try {
	         // Create a default MimeMessage object.
	         MimeMessage message = new MimeMessage(session);

	         // Set From: header field of the header.
	         message.setFrom(new InternetAddress(mailFrom));

	         // Set To: header field of the header.
	         message.addRecipient(Message.RecipientType.TO, new InternetAddress(mailTo));

	         // Set Subject: header field
	         message.setSubject("This is Adrian, testing stuff");

	         // Now set the actual message
	         message.setText("This is a test email please do not respond back");
	         
	         //Sets the date for the message
	         message.setSentDate(new Date());

	         // Send message
	         Transport.send(message);
	         System.out.println("Sent message successfully....");
	      } catch (MessagingException mex) {
	         mex.printStackTrace();
	      }
	   }
	
	}
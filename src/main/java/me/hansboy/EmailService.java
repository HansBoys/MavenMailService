package me.hansboy;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;

public class EmailService {
    public static void main(String[] args) {

        //String for Input Panel
        String target = "";
        String subject = "";
        String text = "";

        target = JOptionPane.showInputDialog("Enter email address");
        subject = JOptionPane.showInputDialog("Enter subject");
        text = JOptionPane.showInputDialog("Enter text");

        //MailServer Configurations
        final String username = "email_here"; //Change "email_here" to your email
        final String password = "pass_here"; //Change "pass_here" to correct password of your email

        Properties props = new Properties();

        //You can use Gmail SMTP (GSMTP), but for now i prefer to use Outlook SMTP

        props.put("mail.smtp.host", "host_here"); //E.g, smtp-mail.outlook.com
        props.put("mail.smtp.port", "port_here"); //E.g, 587
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.ssl.trust", "host_here"); //E.g, smtp-mail.outlook.com

        Session session_sent = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
        session_sent.setDebug(true);
        try {
            MimeMessage msg = new MimeMessage(session_sent);
            msg.setFrom(new InternetAddress(username));
            msg.addRecipient(Message.RecipientType.TO, new InternetAddress(target));
            msg.setSubject(subject);
            msg.setText(text);

            //You can check Output

            System.out.println("Sending Mail...");
            Transport.send(msg);
            System.out.println("Email sended!");

        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }
}
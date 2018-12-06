package utils;

import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.Authenticator;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.Properties;

public class SendMailUtils {

    private SendMailUtils() {
        throw new AssertionError();
    }

    private static final String ALIDM_SMTP_HOST = "smtp.flashhold.com";
    private static final int ALIDM_SMTP_PORT = 465;

    public static void sendMail(String toAddress, String title, String context,String filePath) {
        final Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", ALIDM_SMTP_HOST);
        props.put("mail.smtp.port", ALIDM_SMTP_PORT);
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.socketFactory.port", ALIDM_SMTP_PORT);
        props.put("mail.user", "songjian@flashhold.com");
        props.put("mail.password", "cj@1208005851");
        Authenticator authenticator = new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                String userName = props.getProperty("mail.user");
                String password = props.getProperty("mail.password");
                return new PasswordAuthentication(userName, password);
            }
        };
        Session mailSession = Session.getInstance(props, authenticator);
        JavaMailSender mailSender = new JavaMailSenderImpl();
        ((JavaMailSenderImpl) mailSender).setSession(mailSession);
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper message = null;
        try {
            message = new MimeMessageHelper(mimeMessage, true, "UTF-8");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        InternetAddress form = null;
        try {
            form = new InternetAddress(props.getProperty("mail.user"));
            message.setFrom(form);
        } catch (AddressException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        InternetAddress to = null;
        try {
            to = new InternetAddress(toAddress);
            message.setTo(to);
            message.setSubject(title);
            message.setText(context,true);
            FileSystemResource file = new FileSystemResource(new File(filePath));
            message.addAttachment(filePath, file);
            mailSender.send(mimeMessage);
        } catch (AddressException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}

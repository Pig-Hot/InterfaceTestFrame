package utils;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class SendMailUtils {

    private SendMailUtils() {
        throw new AssertionError();
    }

    private static final String ALIDM_SMTP_HOST = "smtp.flashhold.com";
    private static final int ALIDM_SMTP_PORT = 465;

    public static void sendMail(String toAddress, String title, String context) {
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
        MimeMessage message = new MimeMessage(mailSession);
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
            message.setRecipient(MimeMessage.RecipientType.TO, to);
            message.setSubject(title);
            message.setContent(context, "text/html;charset=UTF-8");
            Transport.send(message);
        } catch (AddressException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
//    public static void main(String[] args) {
//        SendMailUtils.sendMail("zhuran@flashhold.com", "测试", "测试");
//    }
}

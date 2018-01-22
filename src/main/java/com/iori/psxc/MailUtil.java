package com.iori.psxc;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@PropertySource(value = "classpath:/mail.properties",encoding = "utf-8")
@Service
public class MailUtil {

    @Value("${mail.from}")
    private String from;

    @Value("${mail.pwd}")
    private String pwd;

    @Value("${mail.to}")
    private String to;

    @Value("${mail.host}")
    private String host;

    @Value("${mail.subject}")
    private String subject;

    public void sendMail(Custom custom){
        try {
            Properties prop = new Properties();
            prop.setProperty("mail.host", host);
            prop.setProperty("mail.transport.protocol", "smtp");
            prop.setProperty("mail.smtp.auth", "true");
            Session session = Session.getInstance(prop);
            session.setDebug(true);
            Transport ts = session.getTransport();
            ts.connect(host, from, pwd);
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject(subject);
            message.setContent(custom.toString(), "text/html;charset=UTF-8");
            message.saveChanges();
            ts.sendMessage(message, message.getAllRecipients());
            ts.close();
            System.out.println("mail send success. custom="+custom.toString());
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}

package com.kuang.utils;

import com.kuang.pojo.User;
import com.sun.mail.util.MailSSLSocketFactory;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.security.GeneralSecurityException;
import java.util.Properties;

/**
 * @auther shkstart
 * @date 2021/5/29 - 20:57
 */
public class Mail extends Thread{

    private User user;
    public Mail(User user){
        this.user=user;
    }

    public String userun(){
        String s = "";
        while(s.length()<6)
            s+=(int)(Math.random()*10);
        run(s);
        return s;

    }


    public void run(String s){

        Properties prop = new Properties();
        prop.setProperty("mail.host","smtp.qq.com");
        prop.setProperty("mail.transport.protocol","smtp");
        prop.setProperty("mail.smtp.auth","true");

        try {
            MailSSLSocketFactory sf = new MailSSLSocketFactory();
            sf.setTrustAllHosts(true);
            prop.put("mail.smtp.ssl.enable","true");
            prop.put("mail.smtp.ssl.socketFactory",sf);

            Session session = Session.getDefaultInstance(prop, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication("792917149@qq.com","aiejsqzqwzjnbfbd");
                }
            });

            session.setDebug(true);

            Transport ts = session.getTransport();

            ts.connect("smtp.qq.com","792917149@qq.com","aiejsqzqwzjnbfbd");

            MimeMessage message=new MimeMessage(session);

            message.setFrom(new InternetAddress("792917149@qq.com"));
            message.setRecipient(Message.RecipientType.TO,new InternetAddress(user.getUserCode()));
            message.setSubject("注册码");

            message.setContent(s,"text/html;charset=UTF-8");

            ts.sendMessage(message,message.getAllRecipients());

            ts.close();





        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }


    }

}

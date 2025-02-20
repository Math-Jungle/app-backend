package com.appbackend.mathjungle.Service;

import com.appbackend.mathjungle.Model.MailBody;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendSimpleMessage(MailBody mailBody) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(mailBody.getTo());
        message.setFrom("nipunarajapaksa52@gmail.com");
        message.setSubject(mailBody.getSubject());
        message.setText(mailBody.getBody());

        mailSender.send(message);


    }
}

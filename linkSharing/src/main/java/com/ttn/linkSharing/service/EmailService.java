package com.ttn.linkSharing.service;

import com.ttn.linkSharing.entities.Topic;
import com.ttn.linkSharing.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender sender;

    public void sendEmail(String email) throws Exception{
        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        helper.setTo(email);
        helper.setText("Hi, WELCOME TO LINK SHARING. This is your confirmation email");
        helper.setSubject("Link Sharing Registration");
        sender.send(message);
    }

    public void sendPasswormEmail(String email, String password) throws Exception{
        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        helper.setTo(email);
        helper.setText("Hi, please login with the following credentials and change the password.\n\n Email : " + email + "\n Password : " + password);
        helper.setSubject("Link Sharing Forgot Password Mail");
        sender.send(message);
    }

    public void sendInvitation(User user, String email, Topic topic){
        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        try {
            helper.setTo(email);
            String text = user.getFirstName() + " invites you to subscribe " + topic.getTopicName() + ".\nClick on the below link to view topic." +
                    "\n\n http://localhost:8080/topic/view/" + topic.getId() + " \n\n";
            helper.setText(text);
            helper.setSubject("Link Sharing: Invitation For Topic Subscription");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        sender.send(message);
    }
}

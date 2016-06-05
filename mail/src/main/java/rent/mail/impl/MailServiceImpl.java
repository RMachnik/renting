package rent.mail.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import rent.mail.MailService;

@Slf4j
public class MailServiceImpl implements MailService {

    private final JavaMailSender javaMailSender;

    public MailServiceImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }


    @Override
    public void sendEmail(String email, String body) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(email);
        mailMessage.setReplyTo("renting.mgr@gmail.com");
        mailMessage.setFrom("renting.mgr@gmail.com");
        mailMessage.setSubject("Account activation");
        mailMessage.setText(body);
        javaMailSender.send(mailMessage);
    }
}

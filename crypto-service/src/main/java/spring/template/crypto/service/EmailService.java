package spring.template.crypto.service;


import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import spring.template.crypto.dto.ReqSendEmailDto;
import spring.template.crypto.dto.ResMessageDto;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Service
public class EmailService {

    JavaMailSender javaMailSender;

    EmailService emailService;

    // Send bulk email
    public ResMessageDto<Void> sendBulkEmail(List<ReqSendEmailDto> emailRequests) {
        for (ReqSendEmailDto request : emailRequests) {
            emailService.sendEmail(request);
        }
        return new ResMessageDto<>("Bulk emails sent successfully", 201, null);
    }


    public ResMessageDto<Void> sendEmail(ReqSendEmailDto request) {
        emailService.sendEmailAsync(request);
        return new ResMessageDto<>("Email sent successfully", 201, null);

    }

    @Async
    public void sendEmailAsync(ReqSendEmailDto request) {
        try{
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setTo(request.getEmail());
            helper.setSubject(request.getSubject());
            helper.setText(request.getMessage(), true);
            helper.setFrom(new InternetAddress("email-service@techexplore.com"));

            // Send the email
            javaMailSender.send(mimeMessage);
        }catch (MessagingException e) {
            log.error("Failed to send email to " + request.getEmail(), e);
        }
    }
}
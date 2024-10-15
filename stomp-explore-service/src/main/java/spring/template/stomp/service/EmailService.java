package spring.template.stomp.service;


import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import spring.template.stomp.dto.ReqSendEmailDto;
import spring.template.stomp.dto.ResMessageDto;
import spring.template.stomp.exception.EmailSendingException;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RequiredArgsConstructor
@Slf4j
@Service
public class EmailService {

    JavaMailSender javaMailSender;

    EmailService self;

    // Send bulk email
    public ResMessageDto<Void> sendBulkEmail(List<ReqSendEmailDto> emailRequests) throws EmailSendingException {
        log.info("Starting bulk email sending");
        for (ReqSendEmailDto request : emailRequests) {
            self.sendEmailAsync(request); // Call via injected dependency
        }
        log.info("Finished bulk email sending");
        return new ResMessageDto<>("Bulk emails sent successfully", 201, null);
    }

    public ResMessageDto<Void> sendEmail(ReqSendEmailDto request) throws EmailSendingException {
        log.info("Starting email sending");
        self.sendEmailAsync(request); // Call via injected dependency
        log.info("Finished email sending");
        return new ResMessageDto<>("Email sent successfully", 201, null);
    }


    @Async
    public CompletableFuture<Void> sendEmailAsync(ReqSendEmailDto request) throws EmailSendingException {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setTo(request.getEmail());
            helper.setSubject(request.getSubject());
            helper.setText(request.getMessage(), true);
            helper.setFrom(new InternetAddress("email-service@techexplore.com"));

            // Send the email
            javaMailSender.send(mimeMessage);
            return CompletableFuture.completedFuture(null);
        } catch (MessagingException e) {
            log.error("Failed to send email to " + request.getEmail(), e);
            throw new EmailSendingException("Could not send email", e); // Propagate the error
        }
    }
}
package spring.template.email.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import spring.template.email.dto.ReqSendEmailDto;
import spring.template.email.dto.ResMessageDto;
import spring.template.email.exception.EmailSendingException;
import spring.template.email.service.EmailService;
import spring.template.email.validation.groups.Update;

import java.util.List;

@RestController
@RequestMapping("/v1/email")
@RequiredArgsConstructor
public class EmailController {

    private final EmailService emailService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResMessageDto<Void> createEmail(@RequestBody ReqSendEmailDto emailDto)
            throws EmailSendingException
    {
        return emailService.sendEmail(emailDto);
    }

    @PostMapping("/bulk")
    @ResponseStatus(HttpStatus.CREATED)
    public ResMessageDto<Void> createBulkEmail(@RequestBody List<ReqSendEmailDto> emailDtos)
            throws EmailSendingException
    {
        return emailService.sendBulkEmail(emailDtos);
    }


}

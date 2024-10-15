package spring.template.stomp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import spring.template.stomp.dto.ReqSendEmailDto;
import spring.template.stomp.dto.ResMessageDto;
import spring.template.stomp.exception.EmailSendingException;
import spring.template.stomp.service.EmailService;

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

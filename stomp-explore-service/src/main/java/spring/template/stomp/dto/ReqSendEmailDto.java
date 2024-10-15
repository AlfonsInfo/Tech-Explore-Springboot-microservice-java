package spring.template.stomp.dto;

import lombok.Data;

@Data
public class ReqSendEmailDto {
    private String email;
    private String subject;
    private String message;
}

package spring.template.stomp.dto;

import lombok.Data;

@Data
public class ReqConsumeWebSocketServiceDto {
    private String user;
    private String destination;
    private String payload;
}

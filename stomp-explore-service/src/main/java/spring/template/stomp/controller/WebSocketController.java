package spring.template.stomp.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.template.stomp.dto.ReqConsumeWebSocketServiceDto;

@RestController
@RequestMapping("/rest/v1/websocket")
public class WebSocketController {

    @Autowired
    SimpMessagingTemplate simpMessagingTemplate;

    @PostMapping
    public void createWebSocket(@RequestBody ReqConsumeWebSocketServiceDto reqConsumeWebSocketServiceDto) {
        System.out.println("createWebSocket()");
        simpMessagingTemplate.convertAndSendToUser(reqConsumeWebSocketServiceDto.getUser(),reqConsumeWebSocketServiceDto.getDestination(), reqConsumeWebSocketServiceDto.getPayload());
    }
}

package spring.template.mediasocial.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.template.mediasocial.dto.ResMessageDto;
import spring.template.mediasocial.service.jwt.JwtService;

import java.util.HashMap;


/**
 * Controller for generate JWT, testing purpose
 */
@RestController
@RequestMapping("/v1/jwt")
@Validated
@RequiredArgsConstructor
public class JwtController {

    private final JwtService jwtService;

    @GetMapping("/example")
    String jwtExample(){
        return jwtService.createJwtToken(
                new HashMap<>()
        );
    }
}

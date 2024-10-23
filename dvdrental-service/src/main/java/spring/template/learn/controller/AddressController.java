package spring.template.learn.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import spring.template.learn.dto.ResMessageDto;
import spring.template.learn.dto.actor.ActorResponseDto;
import spring.template.learn.dto.address.AddressResponseDto;
import spring.template.learn.service.AddressService;

import java.util.List;

@RestController
@RequestMapping("/v1/addresses")
@RequiredArgsConstructor
@Validated
public class AddressController {

    private final AddressService addressService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    ResMessageDto<List<AddressResponseDto>> getAllActor() {
        return addressService.getAllAddress();
    }

}

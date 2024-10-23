package spring.template.learn.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import spring.template.learn.dto.ResMessageDto;
import spring.template.learn.dto.address.AddressResponseDto;
import spring.template.learn.mapper.AddressMapper;
import spring.template.learn.repository.AddressRepository;

import java.util.List;


@Service
@RequiredArgsConstructor
public class AddressService {

    //mapper
    private final AddressMapper addressMapper;

    //repository
    private final AddressRepository addressRepository;

    //method
    public ResMessageDto<List<AddressResponseDto>> getAllAddress(){
        List<AddressResponseDto> addressResponseList = addressMapper.addressEntitiesToAddressResponseDtos(addressRepository.findAll());
        return ResMessageDto.<List<AddressResponseDto>>builder()
                .message("Get all address success")
                .data(addressResponseList)
                .statusCode(HttpStatus.OK.value())
                .build();
    }

}

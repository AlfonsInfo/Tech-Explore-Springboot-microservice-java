package spring.template.learn.mapper;


import org.mapstruct.Mapper;
import spring.template.learn.dto.address.AddressResponseDto;
import spring.template.learn.entity.AddressEntity;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AddressMapper {
    AddressResponseDto addressEntityToAddressResponseDto(AddressEntity addressEntity);
    List<AddressResponseDto> addressEntitiesToAddressResponseDtos(List<AddressEntity> addressEntities);
}

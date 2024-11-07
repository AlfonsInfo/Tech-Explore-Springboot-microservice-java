package spring.template.mediasocial.mapper;

import org.mapstruct.Mapper;
import spring.template.mediasocial.dto.user.CreateUserDto;
import spring.template.mediasocial.entity.UserEntity;


@Mapper(componentModel = "spring")
public interface UserMapper {
    UserEntity toUserEntityFromCreateUserDto(CreateUserDto createUserDto);
}


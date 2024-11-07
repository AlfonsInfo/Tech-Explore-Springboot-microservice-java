package spring.template.mediasocial.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import spring.template.mediasocial.dto.user.CreateUserDto;
import spring.template.mediasocial.entity.UserEntity;
import spring.template.mediasocial.mapper.UserMapper;
import spring.template.mediasocial.repository.UserRepository;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    //Repository
    private final UserRepository userRepository;

    //Validation


    //Mapping
    private final UserMapper userMapper;
    public void registerUser(CreateUserDto request){
        UserEntity userEntity = userMapper.toUserEntityFromCreateUserDto(request);
        userRepository.save(userEntity);
    }
}

package spring.template.redis.controller;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.template.redis.dto.UserDto;


@RestController
@RequestMapping("v1/redis-cache")
@Slf4j
public class RedisCacheController {

    @GetMapping("/user")
    @Cacheable(value ="cache_data", key = "#userId")
    public UserDto getUser(String userId){
        log.info("Fetching user from DB");
        return new UserDto("1", "Dummy User");
    }
}

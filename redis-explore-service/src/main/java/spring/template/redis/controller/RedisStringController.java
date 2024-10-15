package spring.template.redis.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import spring.template.redis.dto.RedisDto;
import spring.template.redis.service.StringRedisService;

@RestController
@RequestMapping("/v1/redis-string")
@RequiredArgsConstructor
public class RedisStringController {

    private final StringRedisService stringRedisService;

    @PostMapping("/save")
    public void save(@RequestBody RedisDto redisDto) {
        stringRedisService.save(redisDto.getKey(), redisDto.getValue());
    }

    @GetMapping("/get")
    public Object get(@RequestParam String key) {
        return stringRedisService.get(key);
    }

    @PostMapping("/delete")
    public void delete(@RequestParam String key) {
        stringRedisService.delete(key);
    }
}

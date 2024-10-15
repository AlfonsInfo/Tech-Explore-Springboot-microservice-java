package spring.template.redis.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import spring.template.redis.dto.RedisDto;
import spring.template.redis.service.BooleanRedisService;

@RestController
@RequestMapping("/v1/redis-boolean")
@RequiredArgsConstructor
public class RedisBooleanController {

    private final BooleanRedisService booleanRedisService;

    @PostMapping("/save")
    public void save(@RequestBody RedisDto redisDto) {
        booleanRedisService.save(redisDto.getKey(), (Boolean) redisDto.getValue());
    }

    @GetMapping("/get")
    public Object get(@RequestParam String key) {
        return booleanRedisService.get(key);
    }

    @PostMapping("/delete")
    public void delete(@RequestParam String key) {
        booleanRedisService.delete(key);
    }
}

package spring.template.redis.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import spring.template.redis.dto.RedisDto;

@RestController
@RequestMapping("/v1/redis-list")
@RequiredArgsConstructor
public class RedisListOperationController {

    private final RedisTemplate<String, Object> redisTemplate;

    @PostMapping("/save")
    public void save(@RequestBody RedisDto redisDto) {
        ListOperations<String, Object>  listOperations = redisTemplate.opsForList();
        listOperations.rightPush("names", "alfons");
        listOperations.rightPush("names", "setiawan");
        listOperations.rightPush("names", "jacub");
    }

    @GetMapping("/get")
    public Object getListOperations() {
        ListOperations<String, Object>  listOperations = redisTemplate.opsForList();
        return listOperations.leftPop("names");
    }
}

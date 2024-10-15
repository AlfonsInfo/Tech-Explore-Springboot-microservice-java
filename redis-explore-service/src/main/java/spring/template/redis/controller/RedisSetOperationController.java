package spring.template.redis.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.web.bind.annotation.*;
import spring.template.redis.dto.RedisDto;

@RestController
@RequestMapping("/v1/redis-set")
@RequiredArgsConstructor
public class RedisSetOperationController {

    private final RedisTemplate<String, Object> redisTemplate;

    @PostMapping("/save")
    public void save(@RequestBody RedisDto redisDto) {
        SetOperations<String, Object> listOperations = redisTemplate.opsForSet();
        listOperations.add("name_set", "alfons");
        listOperations.add("name_set", "setiawan");
        listOperations.add("name_set", "jacub");
    }

    @GetMapping("/get")
    public Object getListOperations() {
        ListOperations<String, Object>  listOperations = redisTemplate.opsForList();
        return listOperations.leftPop("name_set");
    }
}

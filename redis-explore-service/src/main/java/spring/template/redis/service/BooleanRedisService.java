package spring.template.redis.service;

import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BooleanRedisService {


    private final RedisTemplate<String, Boolean> booleanRedisTemplate;

    public void save(String key, Boolean value) {
        booleanRedisTemplate.opsForValue().set(key, value);
    }

    public Object get(String key) {
        return booleanRedisTemplate.opsForValue().get(key);
    }

    public void delete(String key) {
        booleanRedisTemplate.delete(key);
    }
}

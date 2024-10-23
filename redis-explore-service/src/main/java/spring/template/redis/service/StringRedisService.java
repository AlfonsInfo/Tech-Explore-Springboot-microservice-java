package spring.template.redis.service;

import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class StringRedisService {


    private final StringRedisTemplate stringRedisTemplate;

    public void save(String key, Object value) {
        stringRedisTemplate.opsForValue().set(key, value.toString());
    }

    public Object get(String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }

    public void delete(String key) {
        stringRedisTemplate.delete(key);
    }
}

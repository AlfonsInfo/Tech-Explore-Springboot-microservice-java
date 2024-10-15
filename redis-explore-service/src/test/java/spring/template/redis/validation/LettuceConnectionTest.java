package spring.template.redis.validation;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Slf4j
public class LettuceConnectionTest {

    @Test
    void testLettuceConnection() {
//        log.info("Test Lettuce Connection");
//        LettuceConnectionFactory connectionFactory=  new LettuceConnectionFactory();
//        connectionFactory.afterPropertiesSet();
//        RedisTemplate<String, String> template = new RedisTemplate<>();
//        template.setConnectionFactory(connectionFactory);
//        template.setDefaultSerializer(StringRedisSerializer.UTF_8);
//        template.opsForValue().set("foo", "bar");
//        log.info("Value at foo:" + template.opsForValue().get("foo"));
//        connectionFactory.destroy();

    }

}

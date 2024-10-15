package spring.template.redis.dto;


import lombok.Data;

@Data
public class RedisDto {
    private String key;
    private Object value;
}

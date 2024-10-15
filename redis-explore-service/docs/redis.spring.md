# Spring Data Redis
Spring Data Redis, part of the larger Spring Data family, provides easy configuration and access to Redis from Spring applications. It offers both low-level and high-level abstractions for interacting with Redis, allowing you to choose the level of abstraction that best suits your needs.

## Configuration
prefix : `spring.data.redis` [Properties](https://docs.spring.io/spring-boot/docs/current/reference/html/appendix-application-properties.html#data-properties-redis)

```properties
spring.data.redis.host=localhost
spring.data.redis.port=6379
spring.data.redis.password=
spring.data.redis.database=0
spring.data.redis.ssl=false
spring.data.redis.timeout=2000
```

## RedisTemplate
`RedisTemplate` is the central class for Redis interaction in Spring Data Redis. It provides a high-level abstraction for interacting with Redis, allowing you to perform common operations such as reading and writing data to Redis.

```java
@Autowired
private RedisTemplate<String, String> redisTemplate;
```

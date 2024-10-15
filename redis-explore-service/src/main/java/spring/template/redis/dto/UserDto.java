package spring.template.redis.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serial;

@Data
@AllArgsConstructor
public class UserDto implements java.io.Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private String id;
    private String name;
}

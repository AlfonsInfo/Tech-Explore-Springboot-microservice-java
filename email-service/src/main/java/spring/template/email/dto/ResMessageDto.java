package spring.template.email.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResMessageDto<T> {
    private String message;
    private Integer statusCode;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;
}

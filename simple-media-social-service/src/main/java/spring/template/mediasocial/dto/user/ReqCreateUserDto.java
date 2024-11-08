package spring.template.mediasocial.dto.user;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ReqCreateUserDto {
    @NotBlank
    private String mobileNumberOrEmail;
    @NotBlank
    private String password;
    @NotBlank
    private String fullName;
    @NotBlank
    private String userName;
}

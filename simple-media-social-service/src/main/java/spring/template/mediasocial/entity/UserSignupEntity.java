package spring.template.mediasocial.entity;

import jakarta.persistence.*;
import lombok.*;
import spring.template.mediasocial.enums.SignupMethod;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Table(name = "user_signup")
public class UserSignupEntity extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private SignupMethod signupMethod = SignupMethod.USING_PHONE;
    private SignupState signupState = SignupState.INPUT_SIGNUP_IDENTIFIER;
    private String phoneNumber;
    private String email;
    private String name;
    private String password;
    private Boolean isConnectToFacebook;


    public enum SignupState{
        INPUT_SIGNUP_IDENTIFIER,
        SEND_VERIFICATION_CODE,
        SEND_VERIFICATION_CODE_SUCCESS,
        SEND_VERIFICATION_CODE_FAILED,
        VERIFICATION_CODE_CONFIRMED,
        INPUT_FULL_NAME,
        INPUT_PASSWORD,
        INPUT_USERNAME
    }


    public enum SignupMethod {
        USING_EMAIL,
        USING_PHONE
    }

}
